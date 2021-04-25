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
    void create(Feedback feedback);

    /**
     * @return list of all feedbacks
     */
    List<Feedback> findAll();

    /**
     * @param id of desired feedback
     * @return found feedback
     */
    Feedback findById(Long id);

    /**
     * @param author of feedbacks
     * @return list of found feedbacks
     */
    List<Feedback> findByAuthor(String author);

    /**
     * @param feedback to be updated
     */
    void update(Feedback feedback);

    /**
     * @param feedback to be removed
     */
    void remove(Feedback feedback);
}
