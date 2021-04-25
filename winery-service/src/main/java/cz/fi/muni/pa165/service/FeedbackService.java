package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Feedback;
import java.util.List;

/**
 * An interface that defines a service access to the Feedback entity.
 *
 * @author Oto Fargas
 */
public interface FeedbackService {

    /**
     *
     */
    void create(Feedback feedback);

    /**
     *
     */
    List<Feedback> findAll();

    /**
     *
     */
    Feedback findById(Long id);

    /**
     *
     */
    List<Feedback> findByAuthor(String author);

    /**
     *
     */
    void remove(Feedback feedback);
}
