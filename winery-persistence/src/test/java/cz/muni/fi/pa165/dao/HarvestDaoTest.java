package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.enums.Quality;
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
    public void setup() {
        h1 = new Harvest();
        h2 = new Harvest();
        h3 = new Harvest();
        h4 = new Harvest();
        h5 = new Harvest();

        h1.setHarvestYear(2013);
        h2.setHarvestYear(2012);
        h3.setHarvestYear(2017);
        h4.setHarvestYear(2013);
        h5.setHarvestYear(2019);

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

        em.persist(h1);
        em.persist(h2);
        em.persist(h3);
        em.persist(h4);
        em.persist(h5);
    }

    @Test
    public void createTest() {
        Harvest h = new Harvest();
        h.setHarvestYear(2013);
        h.setQuantity(350);
        h.setQuality(Quality.LOW);
        Grape g = new Grape();
        g.setName("CreateTestGrape");
        g.setQuantity(150);
        g.setDiseases(new ArrayList<>());
        g.setColor(GrapeColor.RED);
        h.setGrape(g);
        harvestDao.create(h);

        List<Harvest> harvestList = em.createQuery("select h from Harvest h", Harvest.class).getResultList();
        Assert.assertEquals(harvestList.size(), 6);
    }

    @Test
    public void findAll() {
        List<Harvest> harvestList = harvestDao.findAll();
        Assert.assertEquals(harvestList.size(), 5);
    }

    @Test
    public void findByYear() {
        Assert.assertEquals(harvestDao.findByYear(2013).size(), 2);
        Assert.assertEquals(harvestDao.findByYear(2017).size(), 1);
        Assert.assertEquals(harvestDao.findByYear(2017).get(0).getGrape().getName(), "Nebbiolo");
    }

    @Test
    public void remove() {
        Assert.assertNotNull(harvestDao.findById(h1.getId()));
        harvestDao.remove(h1);
        Assert.assertNull(harvestDao.findById(h1.getId()));
    }

    @Test
    public void findById() {
        Assert.assertEquals(h2, harvestDao.findById(h2.getId()));
    }

    @Test
    public void harvestAttributesTest() {
        Assert.assertEquals(h3.getQuality(), harvestDao.findById(h3.getId()).getQuality());
        Assert.assertEquals(h4.getQuantity(), harvestDao.findById(h4.getId()).getQuantity());
        Assert.assertEquals(h5.getGrape(), harvestDao.findById(h5.getId()).getGrape());
    }

    @Test
    public void update() {
        h1.setHarvestYear(2014);
        harvestDao.update(h1);
        Assert.assertEquals(h1, harvestDao.findById(h1.getId()));
        Assert.assertEquals(h1.getHarvestYear(), harvestDao.findById(h1.getId()).getHarvestYear());

        Harvest newHarvest = new Harvest();
        newHarvest.setQuality(Quality.HIGH);
        newHarvest.setQuantity(142);
        newHarvest.setHarvestYear(2020);

        Grape newGrape = new Grape();
        newGrape.setColor(GrapeColor.RED);
        newGrape.setDiseases(new ArrayList<>());
        newGrape.setQuantity(63);
        newGrape.setName("Red Globe");

        newHarvest.setGrape(newGrape);
        newGrape.addHarvest(newHarvest);
        harvestDao.create(newHarvest);

        newHarvest.setQuantity(85);
        harvestDao.update(newHarvest);
        Assert.assertEquals(newHarvest, harvestDao.findById(newHarvest.getId()));
        Assert.assertEquals(newHarvest.getQuantity(), harvestDao.findById(newHarvest.getId()).getQuantity());
    }
}

