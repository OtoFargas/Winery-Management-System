package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.facade.FeedbackFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * SpringMVC controller for feedback entities
 *
 * @author Lukáš Fudor
 */

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    final static Logger log = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackFacade feedbackFacade;

    /**
     * TODO
     *
     * @param model
     * @return
     */
    @GetMapping("/new")
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
    @PostMapping("/create")
    public String createFeedback(@Valid @ModelAttribute("feedbackCreate") FeedbackCreateDTO formBean, BindingResult bindingResult,
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

        Long id = feedbackFacade.createFeedback(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Feedback " + id + " was created");
        return "redirect:" + uriBuilder.path("/feedback/list").toUriString();
    }

    /**
     * TODO
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/view/{id}")
    public String viewById(@PathVariable long id, Model model) {
        log.debug("viewById({})", id);
        model.addAttribute("feedback", feedbackFacade.findFeedbackById(id));
        return "feedback/view";
    }

    /**
     * TODO
     *
     * @param model to be displayed
     * @return
     */
    @GetMapping("/list")
    public String listAllFeedbacks(Model model) {
        model.addAttribute("feedbacks", feedbackFacade.findAllFeedbacks());
        return "feedback/list";
    }

    /**
     * TODO
     *
     * @param model
     * @param author
     * @return
     */
    @GetMapping("/list/{author}")
    public String listFeedbacksFromAuthor(Model model, @PathVariable String author) {
        model.addAttribute("feedbacks", feedbackFacade.findFeedbacksByAuthor(author));
        return "feedback/list";
    }

    /**
     * TODO
     *
     * @param id
     * @param uriBuilder
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        FeedbackDTO feedback = feedbackFacade.findFeedbackById(id);
        log.debug("remove({})", id);

        try {
            feedbackFacade.removeFeedback(id);
            redirectAttributes.addFlashAttribute("alert_success", "Feedback \"" + feedback.getId() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Feedback \"" + feedback.getId() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/feedback/list").toUriString();
    }
}
