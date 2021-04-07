package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceApplicationContext;
import cz.fi.muni.pa165.entity.Wine;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for WineDao class.
 *
 * @author Lukáš Fudor
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class WineDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public WineDao wineDao;

    private Wine w1;
    private Wine w2;
    private Wine w3;
    private Wine w4;
    private Wine w5;

    @BeforeMethod
    public void createWines() {
        w1 = new Wine();
        w2 = new Wine();
        w3 = new Wine();
        w4 = new Wine();
        w5 = new Wine();

        w1.setName("Cabernet Sauvignon");
        w2.setName("Chardonnay");
        w3.setName("Merlot");
        w4.setName("Muscat");
        w5.setName("Pinot Blanc");

        w1.setStocked(150);
        w1.setSold(17);
        w2.setStocked(98);
        w2.setSold(25);
        w3.setStocked(77);
        w3.setSold(65);
        w4.setStocked(187);
        w4.setSold(38);
        w5.setStocked(43);
        w5.setSold(15);

        w1.setType(new Pair<>(WineColor.DESSERT, Taste.SEMI_SOUR));
        w2.setType(new Pair<>(WineColor.RED, Taste.SOUR));
        w3.setType(new Pair<>(WineColor.ROSE, Taste.SEMI_SWEET));
        w4.setType(new Pair<>(WineColor.WHITE, Taste.SWEET));
        w5.setType(new Pair<>(WineColor.SPARKLING,Taste.SEMI_SWEET));

        List<Ingredient> w1IngredientList = new ArrayList<>();
        w1IngredientList.add(Ingredient.WATER);
        w1IngredientList.add(Ingredient.SUGAR);
        List<Ingredient> w2IngredientList = new ArrayList<>();
        w2IngredientList.add(Ingredient.VANILLA);
        List<Ingredient> w3IngredientList = new ArrayList<>();
        w3IngredientList.add(Ingredient.SULFUR);
        w3IngredientList.add(Ingredient.SUGAR);
        List<Ingredient> w4IngredientList = new ArrayList<>();
        w4IngredientList.add(Ingredient.WATER);
        w4IngredientList.add(Ingredient.OAK);
        w4IngredientList.add(Ingredient.CALCIUM);
        w4IngredientList.add(Ingredient.POTASSIUM);
        List<Ingredient> w5IngredientList = new ArrayList<>();
        w5IngredientList.add(Ingredient.GRAPE_JUICE);
        w5IngredientList.add(Ingredient.SUGAR);
        w5IngredientList.add(Ingredient.YEAST);

        w1.setIngredients(w1IngredientList);
        w2.setIngredients(w2IngredientList);
        w3.setIngredients(w3IngredientList);
        w4.setIngredients(w4IngredientList);
        w5.setIngredients(w5IngredientList);

        wineDao.create(w1);
        wineDao.create(w2);
        wineDao.create(w3);
        wineDao.create(w4);
        wineDao.create(w5);
    }

    @Test
    public void findAll() {
        List<Wine> wineList = wineDao.findAll();
        Assert.assertEquals(wineList.size(), 5);
    }

    @Test
    public void findByName() {
        Assert.assertEquals(w3, wineDao.findByName("Merlot"));
        Assert.assertEquals(w4, wineDao.findByName("Muscat"));
        Assert.assertEquals(w5, wineDao.findByName("Pinot Blanc"));
    }

    @Test
    public void remove() {
        Assert.assertNotNull(wineDao.findById(w1.getId()));
        wineDao.remove(w1);
        Assert.assertNull(wineDao.findById(w1.getId()));
    }

    @Test
    public void findById() {
        Long id = w2.getId();
        Assert.assertEquals(w2, wineDao.findById(id));
    }
}
