package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.HarvestCreateDTO;
import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.enums.Quality;
import cz.muni.fi.pa165.facade.HarvestFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.HarvestService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author Oto Fargas
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HarvestFacadeTest extends AbstractTestNGSpringContextTests {

    private HarvestService harvestService = mock(HarvestService.class);

    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private HarvestFacade harvestFacade;

    private Harvest testHarvest1;
    private Harvest testHarvest2;
    private Harvest testHarvest3;

    private HarvestDTO testHarvestDTO1;
    private HarvestDTO testHarvestDTO2;
    private HarvestDTO testHarvestDTO3;

    @BeforeClass
    public void setupFacade() {
        harvestFacade = new HarvestFacadeImpl(harvestService, beanMappingService);
    }

    @BeforeMethod
    public void createEntities() {

        Grape grape1 = new Grape();
        grape1.setId(1L);
        grape1.setName("grape1");
        grape1.setColor(GrapeColor.RED);
        grape1.setDiseases(new ArrayList<>(List.of(Disease.ANTHRACNOSE, Disease.CROWN_GALL)));
        grape1.setQuantity(20);

        testHarvest1 = new Harvest();
        testHarvest1.setId(1L);
        testHarvest1.setHarvestYear(2010);
        testHarvest1.setQuantity(99);
        testHarvest1.setQuality(Quality.HIGH);
        testHarvest1.setGrape(grape1);

        Grape grape2 = new Grape();
        grape2.setId(2L);
        grape2.setName("grape2");
        grape2.setColor(GrapeColor.WHITE);
        grape2.setDiseases(new ArrayList<>(List.of(Disease.CROWN_GALL)));
        grape2.setQuantity(15);

        testHarvest2 = new Harvest();
        testHarvest2.setId(2L);
        testHarvest2.setHarvestYear(2010);
        testHarvest2.setQuantity(250);
        testHarvest2.setQuality(Quality.MEDIUM);
        testHarvest2.setGrape(grape2);

        Grape grape3 = new Grape();
        grape3.setId(3L);
        grape3.setName("grape3");
        grape3.setColor(GrapeColor.RED);
        grape3.setDiseases(new ArrayList<>());
        grape3.setQuantity(56);

        testHarvest3 = new Harvest();
        testHarvest3.setId(3L);
        testHarvest3.setHarvestYear(2006);
        testHarvest3.setQuantity(1000);
        testHarvest3.setQuality(Quality.LOW);
        testHarvest3.setGrape(grape3);

        testHarvestDTO1 = beanMappingService.mapTo(testHarvest1, HarvestDTO.class);
        testHarvestDTO2 = beanMappingService.mapTo(testHarvest2, HarvestDTO.class);
        testHarvestDTO3 = beanMappingService.mapTo(testHarvest3, HarvestDTO.class);
    }

    @Test
    public void createHarvestTest(){
        HarvestCreateDTO harvestCreateDTO1 = beanMappingService.mapTo(testHarvest1, HarvestCreateDTO.class);

        harvestFacade.createHarvest(harvestCreateDTO1);
        verify(harvestService).createHarvest(testHarvest1);
    }

    @Test
    public void findHarvestByIdTest() {
        when(harvestService.findHarvestById(testHarvest1.getId())).thenReturn(testHarvest1);

        HarvestDTO harvestDTO = harvestFacade.findHarvestById(testHarvest1.getId());
        verify(harvestService).findHarvestById(testHarvest1.getId());
        assertThat(testHarvestDTO1).isEqualTo(harvestDTO);
    }

    @Test
    public void findHarvestsByYearTest() {
        when(harvestService.findHarvestByYear(2000)).thenReturn(new ArrayList<>(List.of(testHarvest1, testHarvest2)));
        when(harvestService.findHarvestByYear(2006)).thenReturn(new ArrayList<>(List.of(testHarvest3)));

        List<HarvestDTO> harvestDTOsYear2000 = harvestFacade.findHarvestsByYear(2000);
        List<HarvestDTO> harvestDTOsYear2006 = harvestFacade.findHarvestsByYear(2006);

        verify(harvestService).findHarvestByYear(2000);
        verify(harvestService).findHarvestByYear(2006);
        assertThat(harvestDTOsYear2000).containsExactlyInAnyOrder(testHarvestDTO1, testHarvestDTO2);
        assertThat(harvestDTOsYear2006).containsExactlyInAnyOrder(testHarvestDTO3);
    }

    @Test
    public void findAllHarvestsTest() {
        when(harvestService.findAllHarvests()).thenReturn(new ArrayList<>(List.of(testHarvest1, testHarvest2, testHarvest3)));

        List<HarvestDTO> harvestDTOs = harvestFacade.findAllHarvests();

        verify(harvestService).findAllHarvests();
        assertThat(harvestDTOs).containsExactlyInAnyOrder(testHarvestDTO1, testHarvestDTO2, testHarvestDTO3);
    }

    @Test
    public void updateHarvestTest() {

        testHarvestDTO3.setHarvestYear(1995);
        harvestFacade.updateHarvest(testHarvestDTO3);
        testHarvestDTO3.setHarvestYear(2006);
        harvestFacade.updateHarvest(testHarvestDTO3);

        verify(harvestService).updateHarvest(testHarvest3);
    }

    @Test
    public void removeHarvestTest() {
        when(harvestService.findHarvestById(1L)).thenReturn(testHarvest1);

        harvestFacade.removeHarvest(1L);
        verify(harvestService, times(1)).removeHarvest(testHarvest1);
    }
}
