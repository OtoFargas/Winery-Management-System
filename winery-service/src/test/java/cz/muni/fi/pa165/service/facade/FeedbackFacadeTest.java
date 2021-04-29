package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.facade.FeedbackFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.FeedbackService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import javafx.util.Pair;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Test class for FeedbackFacade
 *
 * @author Lukáš Fudor
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class FeedbackFacadeTest extends AbstractTestNGSpringContextTests {

    private FeedbackService feedbackService = mock(FeedbackService.class);

    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private FeedbackFacade feedbackFacade;

    private Feedback feedback;
    private FeedbackCreateDTO feedbackCreateDTO;
    private FeedbackDTO feedbackDTO;

    @BeforeMethod
    public void setFacade() {
        feedbackFacade = new FeedbackFacadeImpl(feedbackService, beanMappingService);
    }

    @BeforeMethod
    public void createEntities(){
        Wine wine = new Wine();
        wine.setIngredients(new ArrayList<>());
        wine.setSold(11);
        wine.setStocked(85);
        //wine.setType(new Pair<>(WineColor.SPARKLING, Taste.SEMI_SWEET));
        wine.setName("Nitrianske knieža");

        feedback = new Feedback();
        feedback.setAuthor("Foto Argaš");
        feedback.setRating(5);
        feedback.setContent("šmaky šmak");
        feedback.setDate(new Date());
        feedback.setWine(wine);
        feedback.setId(17L);

        feedbackCreateDTO = new FeedbackCreateDTO();
        feedbackCreateDTO.setAuthor("Šakul Roduf");
        feedbackCreateDTO.setRating(10);
        feedbackCreateDTO.setDate(new Date());
        feedbackCreateDTO.setContent("5/7 perfect score");
        feedbackCreateDTO.setWine(beanMappingService.mapTo(wine, WineDTO.class));

        feedbackDTO = new FeedbackDTO();
        feedbackDTO.setAuthor("Volodomir Cherry");
        feedbackDTO.setRating(9);
        feedbackDTO.setDate(new Date());
        feedbackDTO.setContent("noice");
        feedbackDTO.setWine(beanMappingService.mapTo(wine, WineDTO.class));
        feedbackDTO.setId(11L);
    }

    @Test
    public void testCreateFeedback() {
        feedback = beanMappingService.mapTo(feedbackCreateDTO, Feedback.class);
        Long id = feedbackFacade.createFeedback(feedbackCreateDTO);
        verify(feedbackService).createFeedback(feedback);
        assertThat(id).isEqualTo(feedback.getId());
    }

    @Test
    public void testFindFeedbackById() {
        when(feedbackService.findFeedbackById(feedback.getId())).thenReturn(feedback);
        FeedbackDTO testFeedbackDTO = feedbackFacade.findFeedbackById(feedback.getId());
        verify(feedbackService).findFeedbackById(feedback.getId());
        assertThat(testFeedbackDTO).isEqualTo(beanMappingService.mapTo(feedback, FeedbackDTO.class));
    }

    @Test
    public void testFindAllFeedbacks() {
        when(feedbackService.findAllFeedbacks()).thenReturn(new ArrayList<>(List.of(feedback)));
        List<FeedbackDTO> feedbacks = feedbackFacade.findAllFeedbacks();
        verify(feedbackService).findAllFeedbacks();
        assertThat(feedbacks).containsExactlyInAnyOrder(beanMappingService.mapTo(feedback, FeedbackDTO.class));
    }

    @Test
    public void testFindFeedbacksByAuthor() {
        when(feedbackService.findFeedbackByAuthor("Foto Argaš")).thenReturn(new ArrayList<>(List.of(feedback)));
        List<FeedbackDTO> feedbacks = feedbackFacade.findFeedbacksByAuthor("Foto Argaš");
        verify(feedbackService).findFeedbackByAuthor("Foto Argaš");
        assertThat(feedbacks).containsExactlyInAnyOrder(beanMappingService.mapTo(feedback, FeedbackDTO.class));
    }

    @Test
    public void testUpdateFeedback() {
        feedbackFacade.updateFeedback(feedbackDTO);
        verify(feedbackService).updateFeedback(beanMappingService.mapTo(feedbackDTO, Feedback.class));
    }

    @Test
    public void testRemoveFeedback() {
        when(feedbackService.findFeedbackById(feedbackDTO.getId())).thenReturn(beanMappingService.mapTo(feedbackDTO, Feedback.class));
        feedbackFacade.removeFeedback(feedbackDTO.getId());
        verify(feedbackService).removeFeedback(beanMappingService.mapTo(feedbackDTO, Feedback.class));
    }
}