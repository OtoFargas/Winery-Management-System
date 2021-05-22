package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.facade.FeedbackFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * TODO
     *
     * @param model
     * @return
     */
    @GetMapping("/feedback/new")
    public String newFeedback(Model model) {
        log.debug("new()");
        model.addAttribute("feedbackCreate", new FeedbackCreateDTO());
        return "feedback/new";
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
    @PostMapping("/feedback/create")
    public String addFeedback(@Valid @ModelAttribute("feedbackCreate") FeedbackCreateDTO formBean, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

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

        String wineName = wineFacade.findWineById(formBean.getWineId()).getName();
        Long id = feedbackFacade.createFeedback(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Feedback for wine \""
                                            + wineName + "\" was created.");
        return "redirect:" + uriBuilder.path("/").toUriString();
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
