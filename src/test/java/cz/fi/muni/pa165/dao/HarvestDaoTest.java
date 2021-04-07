package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceApplicationContext;
import cz.fi.muni.pa165.entities.Grape;
import cz.fi.muni.pa165.entities.Harvest;
import cz.fi.muni.pa165.entities.Wine;
import cz.fi.muni.pa165.enums.GrapeColor;
import cz.fi.muni.pa165.enums.Ingredient;
import cz.fi.muni.pa165.enums.Quality;
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
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for HarvestDao class.
 *
 * @author Lukáš Fudor
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HarvestDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public HarvestDao harvestDao;

    private Harvest h1;
    private Harvest h2;
    private Harvest h3;
    private Harvest h4;
    private Harvest h5;

    private Grape g1;
    private Grape g2;
    private Grape g3;

    @BeforeMethod
    public void createHarvests() {
        h1 = new Harvest();
        h2 = new Harvest();
        h3 = new Harvest();
        h4 = new Harvest();
        h5 = new Harvest();

        h1.setYear(2013);
        h2.setYear(2012);
        h3.setYear(2017);
        h4.setYear(2013);
        h5.setYear(2019);

        h1.setQuantity(350);
        h2.setQuantity(180);
        h3.setQuantity(240);
        h4.setQuantity(200);
        h5.setQuantity(110);

        h1.setQuality(Quality.LOW);
        h2.setQuality(Quality.HIGH);
        h3.setQuality(Quality.MEDIUM);
        h4.setQuality(Quality.HIGH);
        h5.setQuality(Quality.HIGH);

        g1 = new Grape();
        g2 = new Grape();
        g3 = new Grape();

        g1.setName("Grenache");
        g2.setName("Nebbiolo");
        g3.setName("Malbec");

        g1.setQuantity(150);
        g2.setQuantity(80);
        g3.setQuantity(120);

        g1.setDiseases(new ArrayList<>());
        g2.setDiseases(new ArrayList<>());
        g3.setDiseases(new ArrayList<>());

        g1.setColor(GrapeColor.RED);
        g2.setColor(GrapeColor.WHITE);
        g3.setColor(GrapeColor.RED);

        h1.setGrape(g1);
        h2.setGrape(g1);
        h3.setGrape(g2);
        h4.setGrape(g2);
        h5.setGrape(g3);

        harvestDao.create(h1);
        harvestDao.create(h2);
        harvestDao.create(h3);
        harvestDao.create(h4);
        harvestDao.create(h5);
    }

    @Test
    public void findAll() {
        List<Harvest> harvestList = harvestDao.findAll();
        Assert.assertEquals(harvestList.size(), 5);
    }

    @Test
    public void findByYear() {
        Assert.assertEquals( harvestDao.findByYear(2013).size(), 2);
        Assert.assertEquals( harvestDao.findByYear(2017).size(), 1);
        Assert.assertEquals( harvestDao.findByYear(2017).get(0).getGrape().getName(), "Nebbiolo");
    }

    @Test
    public void remove() {
        Assert.assertNotNull(harvestDao.findById(h1.getId()));
        harvestDao.remove(h1);
        Assert.assertNull(harvestDao.findById(h1.getId()));
    }

    @Test
    public void findById() {
        Long id = h2.getId();
        Assert.assertEquals(h2, harvestDao.findById(id));
    }
}

