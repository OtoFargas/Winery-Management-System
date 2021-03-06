package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Feedback;

import java.util.List;

/**
 * Representation of DAO for feedback
 *
 * @author Oto Fargas
 */
public interface FeedbackDao {

    /**
     * Creates persistent representation of feedback in database
     *
     * @param feedback to be created
     */
    void create(Feedback feedback);

    /**
     * Finds all feedbacks in database
     *
     * @return list of all feedbacks
     */
    List<Feedback> findAll();

    /**
     * Finds feedback by id
     *
     * @param id of desired feedback
     * @return found feedback
     */
    Feedback findById(Long id);

    /**
     * Finds feedbacks by author
     *
     * @param author of desired feedback
     * @return list of found feedbacks
     */
    List<Feedback> findByAuthor(String author);

    /**
     * Updates Feedback entity
     *
     * @param feedback to be updated
     */
    void update(Feedback feedback);

    /**
     * Removes feedback from database
     *
     * @param feedback to be deleted
     */
    void remove(Feedback feedback);
}
