package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.dto.HarvestCreateDTO;
import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.dto.WineCreateDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.facade.HarvestFacade;
import cz.muni.fi.pa165.facade.WineFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.FeedbackService;
import cz.muni.fi.pa165.service.GrapeService;
import cz.muni.fi.pa165.service.HarvestService;
import cz.muni.fi.pa165.service.WineService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
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

    private WineService wineService = mock(WineService.class);

    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private WineFacade wineFacade;

    private Wine testWine1;
    private Wine testWine2;
    private Wine testWine3;

    private WineDTO testWineDTO1;
    private WineDTO testWineDTO2;
    private WineDTO testWineDTO3;

    @BeforeClass
    public void setupFacade() {
        wineFacade = new WineFacadeImpl(wineService, feedbackService, harvestService, beanMappingService);
    }

    @BeforeMethod
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
}
