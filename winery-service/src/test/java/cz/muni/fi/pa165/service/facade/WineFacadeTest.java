package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.dto.WineBuyDTO;
import cz.muni.fi.pa165.dto.WineCreateDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Quality;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.facade.WineFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.FeedbackService;
import cz.muni.fi.pa165.service.GrapeService;
import cz.muni.fi.pa165.service.HarvestService;
import cz.muni.fi.pa165.service.WineService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author Oto Fargas
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class WineFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private FeedbackService feedbackService;

    @Mock
    private HarvestService harvestService;

    @Mock
    private WineService wineService;

    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private WineFacade wineFacade = new WineFacadeImpl(wineService, feedbackService, harvestService, beanMappingService);

    private Wine testWine1;
    private Wine testWine2;
    private Wine testWine3;

    private WineDTO testWineDTO1;
    private WineDTO testWineDTO2;
    private WineDTO testWineDTO3;

    private WineBuyDTO testWineBuyDTO1;

    private Feedback testFeedback1;
    private FeedbackDTO testFeedbackDTO1;

    private Harvest testHarvest1;
    private HarvestDTO testHarvestDTO1;

    @BeforeMethod
    public void setupFacade() {
        wineFacade = new WineFacadeImpl(wineService, feedbackService, harvestService, beanMappingService);
    }

    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeClass
    public void createEntities() {
        testWine1 = new Wine();
        testWine1.setId(1L);
        testWine1.setName("testWine1");
        testWine1.setTaste(Taste.DRY);
        testWine1.setColor(WineColor.RED);
        testWine1.setSold(50);
        testWine1.setStocked(1500);
        testWine1.setIngredients(new ArrayList<>(List.of(Ingredient.GRAPE_JUICE, Ingredient.OAK)));

        testWine2 = new Wine();
        testWine2.setId(2L);
        testWine2.setName("testWine2");
        testWine2.setTaste(Taste.SWEET);
        testWine2.setColor(WineColor.RED);
        testWine2.setSold(165);
        testWine2.setStocked(1225);
        testWine2.setIngredients(new ArrayList<>(List.of(Ingredient.SULFUR, Ingredient.WATER)));

        testWine3 = new Wine();
        testWine3.setId(3L);
        testWine3.setName("testWine3");
        testWine3.setTaste(Taste.SEMI_DRY);
        testWine3.setColor(WineColor.WHITE);
        testWine3.setSold(999);
        testWine3.setStocked(15);
        testWine3.setIngredients(new ArrayList<>(List.of(Ingredient.GRAPE_JUICE, Ingredient.OAK)));

        testWineDTO1 = beanMappingService.mapTo(testWine1, WineDTO.class);
        testWineDTO2 = beanMappingService.mapTo(testWine2, WineDTO.class);
        testWineDTO3 = beanMappingService.mapTo(testWine3, WineDTO.class);

        testWineBuyDTO1 = beanMappingService.mapTo(testWine1,WineBuyDTO.class);
        testWineBuyDTO1.setAmount(99);

        testFeedback1 = new Feedback();
        testFeedback1.setId(42L);
        testFeedback1.setWine(new Wine());
        testFeedback1.setAuthor("Fukáš Ludor");
        testFeedback1.setContent("kajšmentke");
        testFeedback1.setRating(5);
        testFeedback1.setDate(new Date());

        testFeedbackDTO1 = beanMappingService.mapTo(testFeedback1, FeedbackDTO.class);

        testHarvest1 = new Harvest();
        testHarvest1.setQuality(Quality.MEDIUM);
        testHarvest1.setHarvestYear(2020);
        testHarvest1.setGrape(new Grape());
        testHarvest1.setQuantity(63);
        testHarvest1.setWine(new Wine());
        testHarvest1.setId(21L);

        testHarvestDTO1 = beanMappingService.mapTo(testHarvest1, HarvestDTO.class);
    }

    @Test
    public void createWineTest(){
        WineCreateDTO wineCreateDTO1 = beanMappingService.mapTo(testWine1, WineCreateDTO.class);

        wineFacade.createWine(wineCreateDTO1);
        verify(wineService).createWine(testWine1);
    }

    @Test
    public void findWineByIdTest() {
        when(wineService.findWineById(testWine1.getId())).thenReturn(testWine1);

        WineDTO wineDTO = wineFacade.findWineById(testWine1.getId());
        verify(wineService).findWineById(testWine1.getId());
        assertThat(testWineDTO1).isEqualTo(wineDTO);
    }

    @Test
    public void findWineByNameTest() {
        when(wineService.findWineByName(testWine1.getName())).thenReturn(testWine1);

        WineDTO wineDTO = wineFacade.findWineByName(testWine1.getName());

        verify(wineService).findWineByName(testWine1.getName());
        assertThat(testWineDTO1).isEqualTo(wineDTO);
    }

    @Test
    public void updateWineTest() {
        wineFacade.updateWine(testWineDTO1);
        verify(wineService).updateWine(testWine1);
    }

    @Test
    public void removeWineTest() {
        when(wineService.findWineById(testWineDTO2.getId())).thenReturn(testWine2);
        wineFacade.removeWine(testWineDTO2.getId());
        verify(wineService).removeWine(testWine2);
    }

    @Test
    public void findAllWinesTest() {
        when(wineService.findAllWines()).thenReturn(new ArrayList<>(List.of(testWine1, testWine2, testWine3)));
        List<WineDTO> wines = wineFacade.findAllWines();
        assertThat(wines).containsExactlyInAnyOrder(testWineDTO1, testWineDTO2, testWineDTO3);
        verify(wineService).findAllWines();
    }

    @Test
    public void sellWineTest() {
        when(wineService.findWineById(testWineBuyDTO1.getId())).thenReturn(testWine1);
        wineFacade.sellWine(testWineBuyDTO1);
        verify(wineService).sellWine(testWine1, testWineBuyDTO1.getAmount());
    }

    @Test
    public void addFeedbackTest() {
        when(wineService.findWineById(testWineDTO1.getId())).thenReturn(testWine1);
        when(feedbackService.findFeedbackById(testFeedbackDTO1.getId())).thenReturn(testFeedback1);
        wineFacade.addFeedback(testFeedbackDTO1.getId(), testWineDTO1.getId());
        verify(wineService).addFeedbackToWine(testWine1, testFeedback1);
    }

    @Test
    public void addHarvestTest() {
        when(wineService.findWineById(testWineDTO1.getId())).thenReturn(testWine1);
        when(harvestService.findHarvestById(testHarvestDTO1.getId())).thenReturn(testHarvest1);
        wineFacade.addHarvest(testHarvestDTO1.getId(), testWineDTO1.getId());
        verify(wineService).addHarvestToWine(testWine1, testHarvest1);
    }
}
