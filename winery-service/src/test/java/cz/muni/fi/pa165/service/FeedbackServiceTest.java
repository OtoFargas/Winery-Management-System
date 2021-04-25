package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.FeedbackDao;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Vladimir Visnovsky
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class FeedbackServiceTest extends AbstractTestNGSpringContextTests {

    private Feedback testFeedback1;
    private Feedback testFeedback2;
    private Feedback testFeedback3;

    private Wine testWine1;
    private Wine testWine2;

    @Mock
    private FeedbackDao feedbackDao;

    @InjectMocks
    @Autowired
    private FeedbackService feedbackService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void createEntities() {
        testWine1 = new Wine(1L);
        testWine1.setName("testWine1");
        testWine1.setSold(20);
        testWine1.setStocked(30);
        testWine1.setType(new Pair<>(WineColor.RED, Taste.SWEET));
        testWine1.setIngredients(new ArrayList<>(List.of(Ingredient.OAK, Ingredient.SULFUR, Ingredient.TANNINS)));

        testWine2 = new Wine(2L);
        testWine2.setName("testWine2");
        testWine2.setSold(10);
        testWine2.setStocked(40);
        testWine2.setType(new Pair<>(WineColor.DESSERT, Taste.DRY));
        testWine2.setIngredients(new ArrayList<>(List.of(Ingredient.GRAPE_JUICE, Ingredient.SULFUR)));

        testFeedback1 = new Feedback(1L);
        testFeedback1.setAuthor("testAuthor1");
        testFeedback1.setContent("testContent1");
        testFeedback1.setDate(LocalDate.now());
        testFeedback1.setRating(5);
        testFeedback1.setWine(testWine1);

        testFeedback2 = new Feedback(2L);
        testFeedback2.setAuthor("testAuthor2");
        testFeedback2.setContent("testContent2");
        testFeedback2.setDate(LocalDate.now());
        testFeedback2.setRating(3);
        testFeedback2.setWine(testWine2);

        testFeedback3 = new Feedback(3L);
        testFeedback3.setAuthor("testAuthor3");
        testFeedback3.setContent("testContent3");
        testFeedback3.setDate(LocalDate.now());
        testFeedback3.setRating(8);
        testFeedback3.setWine(testWine1);
    }

    @Test
    public void createFeedbackTest() {
        feedbackService.createFeedback(testFeedback1);

        verify(feedbackDao, times(1)).create(testFeedback1);
    }

    @Test
    public void removeFeedbackTest() {
        when(feedbackDao.findById(1L)).thenReturn(testFeedback1);

        feedbackService.removeFeedback(testFeedback1);

        verify(feedbackDao, times(1)).remove(testFeedback1);
    }

    @Test
    public void updateFeedbackTest() {
        testFeedback1.setRating(1);

        feedbackService.updateFeedback(testFeedback1);

        verify(feedbackDao, times(1)).update(testFeedback1);
    }

    @Test
    public void findFeedbackByIdTest() {
        when(feedbackDao.findById(2L)).thenReturn(testFeedback2);

        Feedback feedback = feedbackService.findFeedbackById(2L);
        assertThat(feedback).isEqualToComparingFieldByField(testFeedback2);
    }

    @Test
    public void findNonExistingFeedbackByIdTest() {
        Feedback feedback = feedbackService.findFeedbackById(10L);
        assertThat(feedback).isNull();
    }

    @Test
    public void getFeedbacksByAuthorTest() {
        when(feedbackDao.findByAuthor("testAuthor1")).thenReturn(Arrays.asList(testFeedback1));

        List<Feedback> feedbacks = feedbackService.findFeedbackByAuthor("testAuthor1");
        assertThat(feedbacks).containsExactlyInAnyOrder(testFeedback1);
    }

    @Test
    public void findAllFeedbacksTest() {
        when(feedbackDao.findAll()).thenReturn(Arrays.asList(testFeedback1, testFeedback2, testFeedback3));

        List<Feedback> feedbacks = feedbackService.findAllFeedbacks();
        assertThat(feedbacks).containsExactlyInAnyOrder(testFeedback1, testFeedback2, testFeedback3);
    }
}
