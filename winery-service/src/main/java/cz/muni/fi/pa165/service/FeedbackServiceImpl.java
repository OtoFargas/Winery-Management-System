package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.FeedbackDao;
import cz.muni.fi.pa165.entities.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the FeedbackService.
 *
 * @author Oto Fargas
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public void create(Feedback feedback) {
        feedbackDao.create(feedback);
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackDao.findAll();
    }

    @Override
    public Feedback findById(Long id) {
        return feedbackDao.findById(id);
    }

    @Override
    public List<Feedback> findByAuthor(String author) {
        return feedbackDao.findByAuthor(author);
    }

    @Override
    public void update(Feedback feedback) {
        feedbackDao.update(feedback);
    }

    @Override
    public void remove(Feedback feedback) {
        feedbackDao.remove(feedback);
    }
}