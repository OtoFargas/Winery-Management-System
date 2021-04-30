package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.facade.FeedbackFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.FeedbackService;
import cz.muni.fi.pa165.service.WineService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

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
    private WineService wineService = mock(WineService.class);

    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private FeedbackFacade feedbackFacade;

    private Feedback feedback1;
    private Feedback feedback2;
    private Wine wine;
    private FeedbackCreateDTO feedbackCreateDTO;
    private FeedbackDTO feedbackDTO;

    @BeforeMethod
    public void setFacade() {
        feedbackFacade = new FeedbackFacadeImpl(feedbackService, wineService, beanMappingService);
    }

    @BeforeMethod
    public void createEntities() {
        wine = new Wine();
        wine.setId(1L);
        wine.setIngredients(new ArrayList<>());
        wine.setSold(11);
        wine.setStocked(85);
        wine.setColor(WineColor.WHITE);
        wine.setTaste(Taste.DRY);
        wine.setName("Nitrianske knieža");

        feedback1 = new Feedback();
        feedback1.setAuthor("Foto Argaš");
        feedback1.setRating(5);
        feedback1.setContent("šmaky šmak");
        feedback1.setDate(new Date());
        feedback1.setWine(wine);
        feedback1.setId(17L);

        feedback2 = new Feedback();
        feedback2.setAuthor("Šakul Roduf");
        feedback2.setRating(10);
        feedback2.setContent("5/7 perfect score");
        feedback2.setDate(new Date());
        feedback2.setWine(wine);
        feedback2.setId(22L);

        feedbackCreateDTO = new FeedbackCreateDTO();
        feedbackCreateDTO.setAuthor("Šakul Roduf");
        feedbackCreateDTO.setRating(10);
        feedbackCreateDTO.setDate(new Date());
        feedbackCreateDTO.setContent("5/7 perfect score");
        feedbackCreateDTO.setWineId(wine.getId());

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
        when(wineService.findWineById(feedbackCreateDTO.getWineId())).thenReturn(wine);
        Feedback feedback = beanMappingService.mapTo(feedbackCreateDTO, Feedback.class);
        Long id = feedbackFacade.createFeedback(feedbackCreateDTO);
        verify(feedbackService).createFeedback(feedback2);
        assertThat(id).isEqualTo(feedback.getId());
    }

    @Test
    public void testFindFeedbackById() {
        when(feedbackService.findFeedbackById(feedback1.getId())).thenReturn(feedback1);
        FeedbackDTO testFeedbackDTO = feedbackFacade.findFeedbackById(feedback1.getId());
        verify(feedbackService).findFeedbackById(feedback1.getId());
        assertThat(testFeedbackDTO).isEqualTo(beanMappingService.mapTo(feedback1, FeedbackDTO.class));
    }

    @Test
    public void testFindAllFeedbacks() {
        when(feedbackService.findAllFeedbacks()).thenReturn(new ArrayList<>(List.of(feedback1)));
        List<FeedbackDTO> feedbacks = feedbackFacade.findAllFeedbacks();
        verify(feedbackService).findAllFeedbacks();
        assertThat(feedbacks).containsExactlyInAnyOrder(beanMappingService.mapTo(feedback1, FeedbackDTO.class));
    }

    @Test
    public void testFindFeedbacksByAuthor() {
        when(feedbackService.findFeedbackByAuthor("Foto Argaš")).thenReturn(new ArrayList<>(List.of(feedback1)));
        List<FeedbackDTO> feedbacks = feedbackFacade.findFeedbacksByAuthor("Foto Argaš");
        verify(feedbackService).findFeedbackByAuthor("Foto Argaš");
        assertThat(feedbacks).containsExactlyInAnyOrder(beanMappingService.mapTo(feedback1, FeedbackDTO.class));
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