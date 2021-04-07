package cz.fi.muni.pa165.dao;

import java.time.LocalDate;
import java.util.List;

import cz.fi.muni.pa165.PersistenceApplicationContext;
import cz.fi.muni.pa165.entities.Feedback;
import cz.fi.muni.pa165.entities.Wine;
import cz.fi.muni.pa165.enums.Ingredient;
import cz.fi.muni.pa165.enums.Taste;
import cz.fi.muni.pa165.enums.WineColor;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



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
        testWine1 = new Wine();
        testWine1.setSold(3);
        testWine1.setStocked(250);
        testWine1.setIngredients(List.of(Ingredient.GRAPE_JUICE, Ingredient.CALCIUM, Ingredient.OAK));
        testWine1.setName("Muskat");
        testWine1.setType(new Pair<>(WineColor.RED, Taste.SWEET));


        testWine2 = new Wine();


        // Create test feedbacks
        testFeedback1 = createFeedback("Oto Fargas", "Very good wine", LocalDate.now(), 7, testWine1);
        testFeedback2 = createFeedback("Lukas Fudor", "Tastes like sh*t, wanted to throw up", LocalDate.now().plusDays(1), 1, testWine2);
        testFeedback3 = createFeedback("Oto Fargas", "Pretty nice taste, the touch of vanilla makes all the difference", LocalDate.now().minusDays(1), 10, testWine2);
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
        wine.setSold(25);
        wine.setStocked(100);
        wine.setIngredients(List.of(Ingredient.OAK, Ingredient.SULFUR, Ingredient.TANNINS));
        wine.setName("Orech");
        wine.setType(new Pair<>(WineColor.ROSE, Taste.SEMI_SWEET));
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
        feedbackLukasAssert.setDate(LocalDate.now().plusDays(1));
        feedbackLukasAssert.setRating(1);
        feedbackLukasAssert.setWine(testWine2);

        Assert.assertTrue(feedbacks.contains(feedbackOtoAssert));
        Assert.assertTrue(feedbacks.contains(feedbackLukasAssert));
    }

//    @Test(expectedExceptions=ConstraintViolationException.class)
//    public void nullCategoryNameNotAllowed(){
//        Category cat = new Category();
//        cat.setName(null);
//        categoryDao.create(cat);
//    }
//
//    @Test(expectedExceptions=DataAccessException.class)
//    public void nameIsUnique(){
//        Category cat = new Category();
//        cat.setName("Electronics");
//        categoryDao.create(cat);
//        cat = new Category();
//        cat.setName("Electronics");
//        categoryDao.create(cat);
//    }
//
//    @Test()
//    public void savesName(){
//        Category cat = new Category();
//        cat.setName("Electronics");
//        categoryDao.create(cat);
//        Assert.assertEquals(categoryDao.findById(cat.getId()).getName(),"Electronics");
//    }
//
//    /**
//     * Checks that null DAO object will return null for non existent ID and also that delete operation works.
//     */
//    @Test()
//    public void delete(){
//        Category cat = new Category();
//        cat.setName("Electronics");
//        categoryDao.create(cat);
//        Assert.assertNotNull(categoryDao.findById(cat.getId()));
//        categoryDao.delete(cat);
//        Assert.assertNull(categoryDao.findById(cat.getId()));
//    }
//
//    /**
//     * Testing that collections on Category is being loaded as expected
//     */
//    @Test
//    public void productsInCategory(){
//        Category categoryElectro = new Category();
//        categoryElectro.setName("Electronics");
//        categoryDao.create(categoryElectro);
//        Product p = new Product();
//        p.setName("TV");
//        productDao.create(p);
//        p.addCategory(categoryElectro);
//        Category found = categoryDao.findById(categoryElectro.getId());
//        Assert.assertEquals(found.getProducts().size(), 1);
//        Assert.assertEquals(found.getProducts().iterator().next().getName(), "TV");
//    }
}