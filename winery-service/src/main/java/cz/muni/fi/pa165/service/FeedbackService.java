package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Feedback;

import java.util.List;

/**
 * An interface that defines a service access to the Feedback entity.
 *
 * @author Oto Fargas
 */
public interface FeedbackService {

    /**
     * @param feedback to be created
     */
    void createFeedback(Feedback feedback);

    /**
     * @return list of all feedbacks
     */
    List<Feedback> findAllFeedbacks();

    /**
     * @param id of desired feedback
     * @return found feedback
     */
    Feedback findFeedbackById(Long id);

    /**
     * @param author of feedbacks
     * @return list of found feedbacks
     */
    List<Feedback> findFeedbackByAuthor(String author);

    /**
     * @param feedback to be updated
     */
    void updateFeedback(Feedback feedback);

    /**
     * @param feedback to be removed
     */
    void removeFeedback(Feedback feedback);
}
