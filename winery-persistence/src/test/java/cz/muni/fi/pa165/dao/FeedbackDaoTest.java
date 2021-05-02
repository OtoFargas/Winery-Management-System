package cz.muni.fi.pa165.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 * @author Vladimir Visnovsky
 */
@ContextConfiguration(classes= PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class FeedbackDaoTest extends AbstractTestNGSpringContextTests{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private FeedbackDao feedbackDao;

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
        testWine1 = createWine(3, 250, ingredients1, "Muskat", WineColor.RED, Taste.SWEET);
        testWine2 = createWine(25, 100, ingredients2, "Orech", WineColor.ROSE, Taste.SEMI_SWEET);

        // Create test feedbacks
        testFeedback1 = createFeedback("Oto Fargas", "Very good wine", new Date(), 7, testWine1);
        testFeedback2 = createFeedback("Lukas Fudor", "Tastes like sh*t, wanted to throw up", new Date(), 1, testWine2);
        testFeedback3 = createFeedback("Oto Fargas", "Pretty nice taste, the touch of vanilla makes all the difference", new Date(), 10, testWine2);

        em.persist(testWine1);
        em.persist(testWine2);

        em.persist(testFeedback1);
        em.persist(testFeedback2);
        em.persist(testFeedback3);
    }

    private Wine createWine(Integer sold, Integer stocked, List<Ingredient> ingredients, String name, WineColor color, Taste taste) {
        Wine wine = new Wine();
        wine.setSold(sold);
        wine.setStocked(stocked);
        wine.setIngredients(ingredients);
        wine.setName(name);
        wine.setColor(color);
        wine.setTaste(taste);;
        return wine;
    }

    private Feedback createFeedback(String author, String content, Date date, Integer rating, Wine wine) {
        Feedback feedback = new Feedback();
        feedback.setAuthor(author);
        feedback.setContent(content);
        feedback.setDate(date);
        feedback.setRating(rating);
        feedback.setWine(wine);
        return feedback;
    }

    @Test
    public void createTest() {
        Feedback newFeedback = createFeedback("Fudas Ludor", "Test content", new Date(), 7, testWine1);
        feedbackDao.create(newFeedback);

        Assert.assertEquals(em.createQuery("select f from Feedback f", Feedback.class).getResultList().size(), 4);
    }

    @Test
    public void findAllTest(){
        List<Feedback> feedbacks = feedbackDao.findAll();

        Assert.assertEquals(feedbacks.size(), 3);
        Assert.assertTrue(feedbacks.containsAll(List.of(testFeedback1, testFeedback2, testFeedback3)));
    }

    @Test(expectedExceptions= ConstraintViolationException.class)
    public void nullAuthorNotAllowedTest() {
        Feedback nullAuthorFeedback = createFeedback(null, "Very good wine", new Date(), 7, testWine1);
        feedbackDao.create(nullAuthorFeedback);
    }

    @Test(expectedExceptions= ConstraintViolationException.class)
    public void emptyStringAuthorNotAllowedTest() {
        Feedback emptyStringAuthorFeedback = createFeedback("", "Very good wine", new Date(), 7, testWine1);
        feedbackDao.create(emptyStringAuthorFeedback);
    }

    @Test(expectedExceptions= ConstraintViolationException.class)
    public void negativeRatingNotAllowedTest() {
        Feedback negativeRatingFeedback = createFeedback("Oto Fargas", "Very good wine", new Date(), -2, testWine1);
        feedbackDao.create(negativeRatingFeedback);
    }

    @Test(expectedExceptions= ConstraintViolationException.class)
    public void futureDateNotAllowedTest() {
        Date date = new GregorianCalendar(LocalDate.now().getYear() + 2, Calendar.AUGUST, 25).getTime();
        Feedback futureDateFeedback = createFeedback("Oto Fargas", "Very good wine", date, 7, testWine1);
        feedbackDao.create(futureDateFeedback);
    }

    @Test
    public void authorCanBeTheSameTest() {
        testFeedback1.setAuthor(testFeedback2.getAuthor());
        feedbackDao.create(testFeedback1);
    }

    @Test
    public void findByIdTest() {
        Feedback foundFeedback = feedbackDao.findById(testFeedback1.getId());
        Assert.assertEquals(foundFeedback, testFeedback1);
    }

    @Test
    public void findByAuthorTest() {
        List<Feedback> foundFeedbacks = feedbackDao.findByAuthor(testFeedback1.getAuthor());
        Assert.assertEquals(foundFeedbacks.size(), 2);
        Assert.assertTrue(foundFeedbacks.contains(testFeedback1));
        Assert.assertTrue(foundFeedbacks.contains(testFeedback3));
    }

    @Test
    public void updateAuthorTest() {
        testFeedback1.setAuthor("Vladimir Visnovsky");
        feedbackDao.update(testFeedback1);

        List<Feedback> feedbacks = em.createQuery("select f from Feedback f where author=:author", Feedback.class)
                .setParameter("author", "Vladimir Visnovsky")
                .getResultList();
        Assert.assertEquals(feedbacks.size(), 1);
        Assert.assertTrue(feedbacks.contains(testFeedback1));
    }

    @Test
    public void removeFeedbackTest() {
        Long id = testFeedback1.getId();
        feedbackDao.remove(testFeedback1);

        Assert.assertNull(em.find(Feedback.class, id));
    }
}