package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.facade.FeedbackFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.FeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * This class implements FeedbackFacade interface
 *
 * @author Oto Fargas
 */
@Service
@Transactional
public class FeedbackFacadeImpl implements FeedbackFacade {

    private final FeedbackService feedbackService;

    private final BeanMappingService beanMappingService;

//    @Inject
    public FeedbackFacadeImpl(FeedbackService feedbackService, BeanMappingService beanMappingService) {
        this.feedbackService = feedbackService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public Long createFeedback(FeedbackCreateDTO feedbackCreateDTO) {
        Feedback feedback = beanMappingService.mapTo(feedbackCreateDTO, Feedback.class);
        feedbackService.createFeedback(feedback);
        return feedback.getId();
    }

    @Override
    public FeedbackDTO findFeedbackById(Long feedbackId) {
        Feedback feedback = feedbackService.findFeedbackById(feedbackId);
        return (feedback == null) ? null : beanMappingService.mapTo(feedback, FeedbackDTO.class);
    }

    @Override
    public List<FeedbackDTO> findAllFeedbacks() {
        return beanMappingService.mapTo(feedbackService.findAllFeedbacks(), FeedbackDTO.class);
    }

    @Override
    public List<FeedbackDTO> findFeedbacksByAuthor(String author) {
        return beanMappingService.mapTo(feedbackService.findFeedbackByAuthor(author), FeedbackDTO.class);
    }

    @Override
    public void updateFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = beanMappingService.mapTo(feedbackDTO, Feedback.class);
        feedbackService.updateFeedback(feedback);
    }

    @Override
    public void removeFeedback(Long feedbackId) {
        Feedback feedback = feedbackService.findFeedbackById(feedbackId);
        feedbackService.removeFeedback(feedback);
    }
}
