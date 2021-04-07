package cz.fi.muni.pa165.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cz.fi.muni.pa165.PersistenceApplicationContext;
import cz.fi.muni.pa165.entities.Feedback;
import cz.fi.muni.pa165.entities.Wine;
import cz.fi.muni.pa165.enums.Ingredient;
import cz.fi.muni.pa165.enums.Taste;
import cz.fi.muni.pa165.enums.WineColor;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;


@ContextConfiguration(classes= PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class FeedbackDaoTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private FeedbackDao feedbackDao;

    @Autowired
    private WineDao wineDao;

    private Wine testWine1;

    private Wine testWine2;

    private Feedback testFeedback1;

    private Feedback testFeedback2;

    private Feedback testFeedback3;

    @BeforeMethod
    public void setup() {
        // Create test wines
        List<Ingredient> ingredients1 = new ArrayList<>(List.of(Ingredient.GRAPE_JUICE, Ingredient.CALCIUM, Ingredient.OAK));
        List<Ingredient> ingredients2 = new ArrayList<>(List.of(Ingredient.OAK, Ingredient.SULFUR, Ingredient.TANNINS));
        testWine1 = createWine(3, 250, ingredients1, "Muskat", new Pair<>(WineColor.RED, Taste.SWEET));
        testWine2 = createWine(25, 100, ingredients2, "Orech", new Pair<>(WineColor.ROSE, Taste.SEMI_SWEET));

        // Create test feedbacks
        testFeedback1 = createFeedback("Oto Fargas", "Very good wine", LocalDate.now(), 7, testWine1);
        testFeedback2 = createFeedback("Lukas Fudor", "Tastes like sh*t, wanted to throw up", LocalDate.now(), 1, testWine2);
        testFeedback3 = createFeedback("Oto Fargas", "Pretty nice taste, the touch of vanilla makes all the difference", LocalDate.now(), 10, testWine2);
    }

    private void createAllWines() {
        wineDao.create(testWine1);
        wineDao.create(testWine2);
    }

    private void createAllFeedbacks() {
        feedbackDao.create(testFeedback1);
        feedbackDao.create(testFeedback2);
        feedbackDao.create(testFeedback3);
    }

    private Wine createWine(Integer sold, Integer stocked, List<Ingredient> ingredients, String name, Pair<WineColor, Taste> type) {
        Wine wine = new Wine();
        wine.setSold(sold);
        wine.setStocked(stocked);
        wine.setIngredients(ingredients);
        wine.setName(name);
        wine.setType(type);
        return wine;
    }

    private Feedback createFeedback(String author, String content, LocalDate date, Integer rating, Wine wine) {
        Feedback feedback = new Feedback();
        feedback.setAuthor(author);
        feedback.setContent(content);
        feedback.setDate(date);
        feedback.setRating(rating);
        feedback.setWine(wine);
        return feedback;
    }

    @Test
    public void findAll(){
        createAllWines();
        createAllFeedbacks();

        List<Feedback> feedbacks = feedbackDao.findAll();

        Assert.assertEquals(feedbacks.size(), 3);

        Feedback feedbackOtoAssert = new Feedback();
        Feedback feedbackLukasAssert = new Feedback();

        feedbackOtoAssert.setAuthor("Oto Fargas");
        feedbackOtoAssert.setContent("Very good wine");
        feedbackOtoAssert.setDate(LocalDate.now());
        feedbackOtoAssert.setRating(7);
        feedbackOtoAssert.setWine(testWine1);

        feedbackLukasAssert.setAuthor("Lukas Fudor");
        feedbackLukasAssert.setContent("Tastes like sh*t, wanted to throw up");
        feedbackLukasAssert.setDate(LocalDate.now());
        feedbackLukasAssert.setRating(1);
        feedbackLukasAssert.setWine(testWine2);

        Assert.assertTrue(feedbacks.contains(feedbackOtoAssert));
        Assert.assertTrue(feedbacks.contains(feedbackLukasAssert));
    }

    @Test(expectedExceptions= ConstraintViolationException.class)
    public void nullAuthorNotAllowed() {
        Feedback nullAuthorFeedback = createFeedback(null, "Very good wine", LocalDate.now(), 7, testWine1);
        feedbackDao.create(nullAuthorFeedback);
    }

    @Test(expectedExceptions= ConstraintViolationException.class)
    public void emptyStringAuthorNotAllowed() {
        Feedback emptyStringAuthorFeedback = createFeedback("", "Very good wine", LocalDate.now(), 7, testWine1);
        feedbackDao.create(emptyStringAuthorFeedback);
    }

    @Test(expectedExceptions= ConstraintViolationException.class)
    public void negativeRatingNotAllowed() {
        Feedback negativeRatingFeedback = createFeedback("Oto Fargas", "Very good wine", LocalDate.now(), -2, testWine1);
        feedbackDao.create(negativeRatingFeedback);
    }

    @Test(expectedExceptions= ConstraintViolationException.class)
    public void futureDateNotAllowed() {
        Feedback futureDateFeedback = createFeedback("Oto Fargas", "Very good wine", LocalDate.now().plusMonths(1), 7, testWine1);
        feedbackDao.create(futureDateFeedback);
    }

    @Test
    public void authorCanBeTheSame() {
        testFeedback2.setAuthor(testFeedback1.getAuthor());

        feedbackDao.create(testFeedback1);
        feedbackDao.create(testFeedback2);
    }

    @Test
    public void findById() {
        feedbackDao.create(testFeedback1);
        feedbackDao.create(testFeedback2);

        Feedback foundFeedback = feedbackDao.findById(testFeedback1.getId());
        Assert.assertEquals(foundFeedback, testFeedback1);
    }

    @Test
    public void findByAuthor() {
        feedbackDao.create(testFeedback1);
        feedbackDao.create(testFeedback2);
        feedbackDao.create(testFeedback3);

        List<Feedback> foundFeedbacks = feedbackDao.findByAuthor(testFeedback1.getAuthor());
        Assert.assertEquals(foundFeedbacks.size(), 2);
        Assert.assertTrue(foundFeedbacks.contains(testFeedback1));
        Assert.assertTrue(foundFeedbacks.contains(testFeedback3));
    }

    @Test
    public void updateAuthor() {
        feedbackDao.create(testFeedback1);

        testFeedback1.setAuthor("Vladimir Visnovsky");
        feedbackDao.update(testFeedback1);

        List<Feedback> feedbacks = feedbackDao.findByAuthor("Vladimir Visnovsky");
        Assert.assertEquals(feedbacks.size(), 1);
        Assert.assertTrue(feedbacks.contains(testFeedback1));
    }

    @Test
    public void removeFeedback() {
        feedbackDao.create(testFeedback1);

        Feedback foundFeedback = feedbackDao.findById(testFeedback1.getId());
        feedbackDao.remove(foundFeedback);

        foundFeedback = feedbackDao.findById(testFeedback1.getId());
        Assert.assertNull(foundFeedback);
    }
}