package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.dto.GrapeChangeDTO;
import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.dto.WineBuyDTO;
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
import org.springframework.web.bind.annotation.RequestMethod;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.facade.WineFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Set;

/**
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
     * TODO
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/")
    public String admin(Model model) {
        model.addAttribute("wines", wineFacade.findAllWines());
        return "home";
    }

    @GetMapping(value = "/wine/buy/{id}")
    public String wineBuy(@PathVariable long id, Model model) {
        model.addAttribute("wine", wineFacade.findWineById(id));
        model.addAttribute("wineBuy", new WineBuyDTO());
        return "wine/buy";
    }

    /**
     * TODO
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/feedback/new/{id}")
    public String newFeedback(@PathVariable long id, Model model) {
        log.debug("new()");
        model.addAttribute("wine", wineFacade.findWineById(id));
        model.addAttribute("feedbackCreate", new FeedbackCreateDTO());
        return "feedback/new";
    }

    /**
     * TODO
     *
     * @param id
     * @param model
     * @return
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
     * TODO
     *
     * @param formBean
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @param uriBuilder
     * @return
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
     * TODO
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/about")
    public String about(Model model) {
        return "about";
    }

    /**
     *
     * @param formBean
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @param uriBuilder
     * @param id
     * @return
     */
    @PostMapping(value = "/wine/buyAmount/{id}")
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
            redirectAttributes.addFlashAttribute("alert_danger", wineName + "could not have been sold.");
            return "redirect:" + uriBuilder.path("/admin/grape/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
        formBean.setId(id);
        wineFacade.sellWine(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Congratulations, you bought "
                                            + formBean.getAmount() + " of " + wineName + ".");
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
