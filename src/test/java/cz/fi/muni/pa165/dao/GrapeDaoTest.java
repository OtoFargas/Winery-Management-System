package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceApplicationContext;
import cz.fi.muni.pa165.entities.Feedback;
import cz.fi.muni.pa165.entities.Grape;
import cz.fi.muni.pa165.entities.Harvest;
import cz.fi.muni.pa165.enums.Disease;
import cz.fi.muni.pa165.enums.GrapeColor;

import cz.fi.muni.pa165.enums.Quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oto Fargas
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GrapeDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private GrapeDao grapeDao;

    @Autowired
    private HarvestDao harvestDao;

    private Grape grape1;
    private Grape grape2;

    private Harvest harvest1;
    private Harvest harvest2;

    @BeforeClass
    public void init() {
        harvest1 = new Harvest();
        harvest1.setQuality(Quality.HIGH);
        harvest1.setQuantity(333);
        harvest1.setYear(2017);

        harvest2 = new Harvest();
        harvest2.setQuality(Quality.MEDIUM);
        harvest2.setQuantity(200);
        harvest2.setYear(2015);

        grape1 = new Grape();
        grape1.setName("Merlot");
        grape1.setDiseases(List.of(Disease.ANTHRACNOSE, Disease.CROWN_GALL));
        grape1.setQuantity(99);
        grape1.setColor(GrapeColor.RED);
        harvest1.setGrape(grape1);
        grape1.addHarvest(harvest1);
        grapeDao.create(grape1);


        grape2 = new Grape();
        grape2.setName("Cabernet Sauvignon");
        grape2.setColor(GrapeColor.RED);
        grape2.setQuantity(118);
        grape2.addHarvest(harvest2);
        harvest2.setGrape(grape2);
        grapeDao.create(grape2);
    }

    @Test
    public void testCreate() {
        Grape grape = new Grape();
        grape.setQuantity(105);
        grape.setName("Riesling");
        grape.setColor(GrapeColor.WHITE);
        grapeDao.create(grape);

        Assert.assertEquals(grapeDao.findById(grape.getId()), grape);
    }

    @Test
    public void testFindById() {
        Grape foundGrape = grapeDao.findById(grape1.getId());
        Assert.assertEquals(grape1.getId(), foundGrape.getId());
    }

    @Test
    public void testFindAll() {
        List<Grape> foundGrapes = grapeDao.findAll();
        Assert.assertTrue(foundGrapes.size() >= 2);
        Assert.assertTrue(foundGrapes.contains(grape1));
        Assert.assertTrue(foundGrapes.contains(grape2));
    }

    @Test
    public void testUpdate() {
        grape1.setName("Pinot Grigio");
        grapeDao.update(grape1);
        Assert.assertEquals(grapeDao.findById(grape1.getId()), grape1);
    }

    @Test
    public void testRemove() {

        Grape foundGrape = grapeDao.findById(grape2.getId());
        grapeDao.remove(foundGrape);

        Assert.assertNull(grapeDao.findById(grape2.getId()));
    }
}
