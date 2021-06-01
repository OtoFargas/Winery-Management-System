package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.dto.WineBuyDTO;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import cz.muni.fi.pa165.facade.FeedbackFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.facade.WineFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Set;

/**
 * MVC Controller for root home page.
 *
 * @author Vladimir Visnovsky
 */
@Controller
@RequestMapping("/")
public class RootController {

    final static Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private WineFacade wineFacade;

    @Autowired
    private FeedbackFacade feedbackFacade;

    /**
     * Redirects to the home page containing all the wines.
     *
     * @param model page data
     * @return      page name
     */
    @GetMapping("/")
    public String admin(Model model) {
        model.addAttribute("wines", wineFacade.findAllWines());
        return "home";
    }

    /**
     * Redirects to ../wine/buy page with interface for buying wine.
     *
     * @param id    of the id to be bought
     * @param model page data
     * @return      page name
     */
    @GetMapping("/wine/buy/{id}")
    public String wineBuy(@PathVariable long id, Model model) {
        model.addAttribute("wine", wineFacade.findWineById(id));
        model.addAttribute("wineBuy", new WineBuyDTO());
        return "wine/buy";
    }

    /**
     * Redirects to ../new page containing the form for the creation of the new feedback.
     *
     * @param id    of the wine to be reviewed
     * @param model page data
     * @return      page name
     */
    @GetMapping("/feedback/new/{id}")
    public String newFeedback(@PathVariable long id, Model model) {
        log.debug("new()");
        model.addAttribute("wine", wineFacade.findWineById(id));
        model.addAttribute("feedbackCreate", new FeedbackCreateDTO());
        return "feedback/new";
    }

    /**
     * Redirects to ../listByWine displaying all feedbacks of the wine
     * with given ID.
     *
     * @param id    of the wine
     * @param model page data
     * @return      page name
     */
    @GetMapping("/feedback/listByWine/{id}")
    public String viewFeedbacksForWine(@PathVariable long id, Model model) {
        log.debug("viewFeedbacksForWine({})", id);
        WineDTO wineDTO = wineFacade.findWineById(id);
        model.addAttribute("wine", wineDTO);
        Set<FeedbackDTO> feedbacks = wineDTO.getFeedbacks();
        model.addAttribute("feedbacks", feedbacks.toArray(new FeedbackDTO[0]));
        return "feedback/listByWine";
    }

    /**
     * Creates new feedback based on the information from the formBean.
     *
     * @param formBean           data from the form
     * @param bindingResult      -
     * @param model              page data
     * @param redirectAttributes attributes for redirect scenario
     * @param uriBuilder         sets URI components
     * @return                   page name
     */
    @PostMapping("/feedback/create/{id}")
    public String addFeedback(@Valid @ModelAttribute("feedbackCreate") FeedbackCreateDTO formBean, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder,
                              @PathVariable long id) {

        log.debug("create(formBean={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "feedback/new";
        }

        formBean.setWineId(id);
        String wineName = wineFacade.findWineById(id).getName();
        feedbackFacade.createFeedback(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Feedback for wine \""
                                            + wineName + "\" was created.");
        return "redirect:" + uriBuilder.path("/feedback/listByWine/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * Redirects to ../about page.
     *
     * @return page name
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    /**
     * Buys formBean.amount of wine with given ID.
     *
     * @param formBean           data from the form
     * @param bindingResult      -
     * @param model              page data
     * @param redirectAttributes attributes for redirect scenario
     * @param uriBuilder         sets URI components
     * @param id                 of the wine to be bought
     * @return                   page name
     */
    @PostMapping("/wine/buyAmount/{id}")
    public String buyWine(@Valid @ModelAttribute("wineBuy") WineBuyDTO formBean,
                                 BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                                 UriComponentsBuilder uriBuilder, @PathVariable long id) {

        log.debug("cureGrape(formBean={})", formBean);
        String wineName = wineFacade.findWineById(id).getName();

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            redirectAttributes.addFlashAttribute("alert_danger", wineName + " could not have been sold.");
            return "redirect:" + uriBuilder.path("/wine/buy/{id}").buildAndExpand(id).encode().toUriString();
        }
        formBean.setId(id);
        try {
            wineFacade.sellWine(formBean);
        } catch (WineryServiceException e) {
            redirectAttributes.addFlashAttribute("alert_danger", "No sufficient amount of stocked wine.");
            return "redirect:" + uriBuilder.path("/wine/buy/{id}").buildAndExpand(id).encode().toUriString();
        }

        StringBuilder message = new StringBuilder();
        message.append("Congratulations, you bought ");
        message.append(formBean.getAmount()).append(" bottle");
        if (formBean.getAmount() > 1)
            message.append("s");
        message.append(" of ").append(wineName).append(".");

        redirectAttributes.addFlashAttribute("alert_success", message.toString());
        return "redirect:" + uriBuilder.path("/wine/buy/{id}").buildAndExpand(id).encode().toUriString();
    }

    @ModelAttribute("rating")
    public Integer[] rating() {
        log.debug("rating()");
        return new Integer[]{1,2,3,4,5,6,7,8,9,10};
    }

    @ModelAttribute("wines")
    public WineDTO[] wines() {
        log.debug("wines()");
        return wineFacade.findAllWines().toArray(new WineDTO[0]);
    }
}
