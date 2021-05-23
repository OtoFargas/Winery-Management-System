package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.facade.FeedbackFacade;
import cz.muni.fi.pa165.facade.WineFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * SpringMVC controller for feedback entities
 *
 * @author Lukáš Fudor
 */

@Controller
@RequestMapping("/admin/feedback")
public class FeedbackController {
    final static Logger log = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackFacade feedbackFacade;

    @Autowired
    private WineFacade wineFacade;

    /**
     * Redirects to ../view, view page of the feedback.
     *
     * @param id    of the feedback to be viewed
     * @param model page data
     * @return      page name
     */
    @GetMapping("/view/{id}")
    public String viewById(@PathVariable long id, Model model) {
        log.debug("viewById({})", id);
        model.addAttribute("feedback", feedbackFacade.findFeedbackById(id));
        return "feedback/view";
    }

    /**
     * Redirects to ../list, page with all feedbacks.
     *
     * @param model page data
     * @return      page name
     */
    @GetMapping("/list")
    public String listAllFeedbacks(Model model) {
        model.addAttribute("feedbacks", feedbackFacade.findAllFeedbacks());
        return "feedback/list";
    }

    /**
     * Redirects to ../list, page with feedbacks from give author.
     *
     * @param model  page data
     * @param author author of searched for feedbacks
     * @return       page name
     */
    @GetMapping("/list/{author}")
    public String listFeedbacksFromAuthor(Model model, @PathVariable String author) {
        model.addAttribute("feedbacks", feedbackFacade.findFeedbacksByAuthor(author));
        return "feedback/list";
    }

    /**
     * Removes a feedback with given ID and redirects to ../list.
     *
     * @param id                 of the feedback to be removed
     * @param uriBuilder         sets URI components
     * @param redirectAttributes attributes for redirect scenario
     * @return                   page name
     */
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        FeedbackDTO feedback = feedbackFacade.findFeedbackById(id);
        log.debug("remove({})", id);

        try {
            feedbackFacade.removeFeedback(id);
            redirectAttributes.addFlashAttribute("alert_success", "Feedback " + feedback.getId() + " was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Feedback " + feedback.getId() + " cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/admin/feedback/list").toUriString();
    }
}
