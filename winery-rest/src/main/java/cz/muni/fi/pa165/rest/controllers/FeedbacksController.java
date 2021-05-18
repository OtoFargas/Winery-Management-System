package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.facade.FeedbackFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * REST Controller for Feedbacks
 *
 * @author Lukáš Fudor
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_FEEDBACKS)
public class FeedbacksController {

    final static Logger logger = LoggerFactory.getLogger(GrapesController.class);

    @Inject
    private FeedbackFacade feedbackFacade;

    /**
     * @param feedback to be created
     * @return FeedbackDTO of the newly created feedback
     * @throws ResourceAlreadyExistingException if the given feedback already exists
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final FeedbackDTO createFeedback(@RequestBody FeedbackCreateDTO feedback) throws ResourceAlreadyExistingException {

        logger.debug("rest createFeedback()");

        try {
            Long id = feedbackFacade.createFeedback(feedback);
            return feedbackFacade.findFeedbackById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * @return list of all feedbacks
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<FeedbackDTO> findAllFeedbacks() {

        logger.debug("rest findAllFeedbacks()");

        return feedbackFacade.findAllFeedbacks();
    }

    /**
     * @param id of the feedback to be found
     * @return found feedback
     * @throws ResourceNotFoundException if the feedback cant be found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final FeedbackDTO findFeedback(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest findFeedbackById({})", id);

        FeedbackDTO feedbackDTO = feedbackFacade.findFeedbackById(id);
        if (feedbackDTO == null) {
            throw new ResourceNotFoundException();
        }

        return feedbackDTO;
    }

    /**
     * @param author of the feedbacks
     * @return feedbacks written by the given author
     * @throws ResourceNotFoundException when no feedbacks are found
     */
    @RequestMapping(value = "by_author/{author}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<FeedbackDTO> findFeedbacksByAuthor(@PathVariable("author") String author) throws ResourceNotFoundException {

        logger.debug("rest findFeedbacksByAuthor({})", author);

        List<FeedbackDTO> feedbackDTOs = feedbackFacade.findFeedbacksByAuthor(author);
        if (feedbackDTOs == null){
            throw new ResourceNotFoundException();
        }

        return feedbackDTOs;
    }

    /**
     * @param id of the feedback to be deleted
     * @throws ResourceNotFoundException when the feedback cant be found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeFeedback(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest removeFeedback({})", id);

        try {
            feedbackFacade.removeFeedback(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
