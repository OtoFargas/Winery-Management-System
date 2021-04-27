package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.dto.FeedbackDTO;


import java.util.List;

/**
 * Facade interface for Feedback entity
 *
 * @author Oto Fargas
 */
public interface FeedbackFacade {
    /**
     * @param feedbackCreateDTO to be created
     * @return id of newly created feedback
     */
    Long createFeedback(FeedbackCreateDTO feedbackCreateDTO);

    /**
     * @param id of the desired feedback
     * @return FeedbackDTO with the id
     */
    FeedbackDTO findFeedbackById(Long id);

    /**
     * @return list of all feedbacks
     */
    List<FeedbackDTO> findAllFeedbacks();

    /**
     * @param author of feedbacks
     * @return a list of found feedbacks
     */
    List<FeedbackDTO> findFeedbacksByAuthor(String author);

    /**
     * @param feedbackDTO to be updated
     */
    void updateFeedback(FeedbackDTO feedbackDTO);

    /**
     * @param feedbackId of Feedback to be deleted
     */
    void removeFeedback(Long feedbackId);
}
