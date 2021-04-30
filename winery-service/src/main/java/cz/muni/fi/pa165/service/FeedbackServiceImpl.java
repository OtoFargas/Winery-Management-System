package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.FeedbackDao;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public void createFeedback(Feedback feedback) {
        try {
            feedbackDao.create(feedback);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public List<Feedback> findAllFeedbacks() {
        try {
            return feedbackDao.findAll();
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public Feedback findFeedbackById(Long id) {
        try {
            return feedbackDao.findById(id);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public List<Feedback> findFeedbackByAuthor(String author) {
        try {
            return feedbackDao.findByAuthor(author);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        try {
            feedbackDao.update(feedback);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void removeFeedback(Feedback feedback) {
        try {
            feedbackDao.remove(feedback);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }
}
