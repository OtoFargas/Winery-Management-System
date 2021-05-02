package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.HarvestDao;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.enums.Quality;
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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 *  Test class for HarvestService
 *
 * @author Lukáš Fudor
 */

@ContextConfiguration(classes=ServiceConfiguration.class)
public class HarvestServiceTest extends AbstractTestNGSpringContextTests  {

    private Harvest harvest1;
    private Harvest harvest2;
    private Harvest harvest3;

    @Mock
    private HarvestDao harvestDao;

    @Autowired
    @InjectMocks
    private HarvestService harvestService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void prepareTestGrape() {
        harvest1 = new Harvest();
        harvest2 = new Harvest();
        harvest3 = new Harvest();

        harvest1.setHarvestYear(2015);
        harvest2.setHarvestYear(2014);
        harvest3.setHarvestYear(2014);

        harvest1.setQuality(Quality.HIGH);
        harvest2.setQuality(Quality.MEDIUM);
        harvest3.setQuality(Quality.LOW);

        harvest1.setQuantity(200);
        harvest2.setQuantity(168);
        harvest3.setQuantity(123);

        Grape grape1 = new Grape();
        Grape grape2 = new Grape();

        grape1.setQuantity(15);
        grape2.setQuantity(64);

        grape1.setColor(GrapeColor.RED);
        grape2.setColor(GrapeColor.WHITE);

        grape1.setDiseases(new ArrayList<>());
        grape2.setDiseases(new ArrayList<>());

        grape1.setName("TestGrape1");
        grape2.setName("TestGrape2");

        harvest1.setGrape(grape1);
        harvest2.setGrape(grape2);
        harvest3.setGrape(grape1);

        Wine wine1 = new Wine();
        Wine wine2 = new Wine();

        wine1.setSold(17);
        wine2.setSold(95);

        wine1.setStocked(45);
        wine2.setStocked(141);

        wine1.setIngredients(new ArrayList<>());
        wine2.setIngredients(new ArrayList<>());

        wine1.setName("TestWine1");
        wine2.setName("TestWine2");

        wine1.setColor(WineColor.SPARKLING);
        wine2.setColor(WineColor.RED);

        wine1.setTaste(Taste.SEMI_SWEET);
        wine2.setTaste(Taste.SEMI_DRY);

        harvest1.setWine(wine1);
        harvest2.setWine(wine2);
        harvest3.setWine(wine1);
    }

    @Test
    public void testCreateHarvest() {
        harvestService.createHarvest(harvest1);
        harvestService.createHarvest(harvest2);
        verify(harvestDao, times(1)).create(harvest1);
        verify(harvestDao, times(1)).create(harvest2);
    }

    @Test
    public void testFindAllHarvests() {
        when(harvestDao.findAll()).thenReturn(Arrays.asList(harvest1, harvest2, harvest3));
        List<Harvest> list = harvestService.findAllHarvests();
        assertThat(list).contains(harvest1,harvest2,harvest3);
        verify(harvestDao, times(1)).findAll();
    }

    @Test
    public void testFindHarvestById() {
        when(harvestDao.findById(1L)).thenReturn(harvest1);
        when(harvestDao.findById(3L)).thenReturn(harvest3);
        assertThat(harvest1).isEqualTo(harvestService.findHarvestById(1L));
        assertThat(harvest3).isEqualTo(harvestService.findHarvestById(3L));
        verify(harvestDao, times(1)).findById(1L);
        verify(harvestDao, times(1)).findById(3L);
    }

    @Test
    public void testFindHarvestByYear() {
        when(harvestDao.findByYear(2014)).thenReturn(Arrays.asList(harvest2, harvest3));
        assertThat(harvestService.findHarvestByYear(2014)).contains(harvest2, harvest3);
        verify(harvestDao, times(1)).findByYear(2014);
    }

    @Test
    public void testUpdateHarvest() {
        when(harvestDao.findById(1L)).thenReturn(harvest1);
        harvest1.setQuality(Quality.MEDIUM);
        harvestService.updateHarvest(harvest1);
        verify(harvestDao, times(1)).update(harvest1);
    }

    @Test
    public void testRemoveHarvest() {
        when(harvestDao.findById(2L)).thenReturn(harvest2);
        harvestService.removeHarvest(harvest2);
        verify(harvestDao, times(1)).remove(harvest2);
    }
}
