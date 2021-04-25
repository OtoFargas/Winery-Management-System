package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


/**
 * @author Vladimir Visnovsky
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class WineServiceTest extends AbstractTestNGSpringContextTests {

    private Wine testWine1;
    private Wine testWine2;
    private Wine testWine3;

    @Mock
    private WineDao wineDao;

    @InjectMocks
    @Autowired
    private WineService wineService;

    @BeforeMethod
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

        testWine3 = new Wine(3L);
        testWine3.setName("testWine3");
        testWine3.setSold(15);
        testWine3.setStocked(14);
        testWine3.setType(new Pair<>(WineColor.WHITE, Taste.SEMI_DRY));
        testWine3.setIngredients(new ArrayList<>(List.of(Ingredient.GRAPE_JUICE, Ingredient.TANNINS)));
    }

    @Test
    public void createWineTest() {
        wineService.createWine(testWine1);

        verify(wineDao, times(1)).create(testWine1);
    }

    @Test
    public void removeWineTest() {
        when(wineDao.findById(1L)).thenReturn(testWine1);

        wineService.removeWine(testWine1);

        verify(wineDao, times(1)).remove(testWine1);
    }

    @Test
    public void updateWineTest() {
        testWine1.setSold(490);

        wineService.updateWine(testWine1);

        verify(wineDao, times(1)).update(testWine1);
    }

    @Test
    public void findWineByIdTest() {
        when(wineDao.findById(2L)).thenReturn(testWine2);

        Wine wine = wineService.findWineById(2L);
        assertThat(wine).isEqualToComparingFieldByField(testWine2);
    }

    @Test
    public void findNonExistingWineByIdTest() {
        Wine wine = wineService.findWineById(10L);
        assertThat(wine).isNull();
    }

    @Test
    public void getWinesByAuthorTest() {
        when(wineDao.findByName("testWine1")).thenReturn(testWine1);

        Wine wine = wineService.findWineByName("testWine1");
        assertThat(wine).isEqualTo(testWine1);
    }

    @Test
    public void findAllWinesTest() {
        when(wineDao.findAll()).thenReturn(Arrays.asList(testWine1, testWine2, testWine3));

        List<Wine> wines = wineService.findAllWines();
        assertThat(wines).containsExactlyInAnyOrder(testWine1, testWine2, testWine3);
    }

    @Test
    public void sellWineShouldThrowWineryServiceExceptionTest() {
        assertThatThrownBy(() -> wineService.sellWine(testWine1, 40)).isInstanceOf(WineryServiceException.class);
    }

    @Test
    public void sellWineUpdateShouldBeCalledOnce() {
        wineService.sellWine(testWine1, 20);
        
        verify(wineDao, times(1)).update(testWine1);
    }

    @Test
    public void successfulSellTest() {
        when(wineDao.findById(1L)).thenReturn(testWine1);

        wineService.sellWine(testWine1, 20);
        Wine wine = wineDao.findById(1L);

        Assert.assertTrue(wine.getStocked() == 10);
    }
}
