package cz.muni.fi.pa165.service.facade;

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

import java.util.ArrayList;
import java.util.List;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeCureDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Quality;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.facade.GrapeFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.GrapeService;
import cz.muni.fi.pa165.service.HarvestService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Vladimir Visnovsky
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GrapeFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private GrapeService grapeService;

    @Mock
    private HarvestService harvestService;

    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private GrapeFacade grapeFacade = new GrapeFacadeImpl(grapeService, harvestService, beanMappingService);

    @BeforeMethod
    public void setupFacade() {
        grapeFacade = new GrapeFacadeImpl(grapeService, harvestService, beanMappingService);
    }

    private Grape testGrape1;
    private Grape testGrape2;
    private Grape testGrape3;

    private Harvest testHarvest1;

    private Wine testWine1;

    private GrapeDTO grapeDto1;
    private GrapeDTO grapeDto2;
    private GrapeDTO grapeDto3;
    private GrapeCreateDTO grapeCreateDto1;
    private GrapeCureDTO grapeCureDto1;

    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeClass
    public void createEntities() {
        testGrape1 = new Grape();
        testGrape1.setName("testGrape1");
        testGrape1.setColor(GrapeColor.RED);
        testGrape1.setDiseases(new ArrayList<>(List.of(Disease.ANTHRACNOSE, Disease.CROWN_GALL)));
        testGrape1.setQuantity(20);
        testGrape1.setId(1L);

        testGrape2 = new Grape();
        testGrape2.setName("testGrape2");
        testGrape2.setColor(GrapeColor.WHITE);
        testGrape2.setDiseases(new ArrayList<>(List.of(Disease.CROWN_GALL)));
        testGrape2.setQuantity(15);
        testGrape2.setId(2L);

        testGrape3 = new Grape();
        testGrape3.setName("testGrape3");
        testGrape3.setColor(GrapeColor.RED);
        testGrape3.setDiseases(new ArrayList<>());
        testGrape3.setQuantity(0);
        testGrape3.setId(3L);

        testWine1 = new Wine();
        testWine1.setId(1L);
        testWine1.setIngredients(new ArrayList<Ingredient>(List.of(Ingredient.CALCIUM)));
        testWine1.setName("testWine1");
        testWine1.setSold(20);
        testWine1.setStocked(40);
        testWine1.setColor(WineColor.RED);
        testWine1.setTaste(Taste.SWEET);

        testHarvest1 = new Harvest();
        testHarvest1.setHarvestYear(2009);
        testHarvest1.setId(1L);
        testHarvest1.setQuality(Quality.HIGH);
        testHarvest1.setGrape(testGrape1);
        testHarvest1.setQuantity(20);
        testHarvest1.setWine(testWine1);

        grapeDto1 = beanMappingService.mapTo(testGrape1, GrapeDTO.class);
        grapeDto2 = beanMappingService.mapTo(testGrape2, GrapeDTO.class);
        grapeDto3 = beanMappingService.mapTo(testGrape3, GrapeDTO.class);
        grapeCreateDto1 = beanMappingService.mapTo(testGrape1, GrapeCreateDTO.class);
        grapeCureDto1 = beanMappingService.mapTo(testGrape1, GrapeCureDTO.class);
    }

    @Test
    public void findGrapeByIdTest() {
        GrapeDTO grapeDto1 = beanMappingService.mapTo(testGrape1, GrapeDTO.class);

        when(grapeService.findGrapeById(testGrape1.getId())).thenReturn(testGrape1);

        GrapeDTO grapeDto2 = grapeFacade.findGrapeById(testGrape1.getId());

        verify(grapeService).findGrapeById(testGrape1.getId());
        assertThat(grapeDto1).isEqualTo(grapeDto2);
    }

    @Test
    public void findGrapeByColorTest() {
        when(grapeService.findGrapeByColor(GrapeColor.RED)).thenReturn(new ArrayList<>(List.of(testGrape1, testGrape3)));
        when(grapeService.findGrapeByColor(GrapeColor.WHITE)).thenReturn(new ArrayList<>(List.of(testGrape2)));

        List<GrapeDTO> redGrapeDtos = grapeFacade.findGrapesByColor(GrapeColor.RED);
        List<GrapeDTO> whiteGrapeDtos = grapeFacade.findGrapesByColor(GrapeColor.WHITE);

        verify(grapeService).findGrapeByColor(GrapeColor.RED);
        verify(grapeService).findGrapeByColor(GrapeColor.WHITE);
        assertThat(redGrapeDtos).contains(grapeDto1, grapeDto3);
        assertThat(whiteGrapeDtos).contains(grapeDto2);
    }

    @Test
    public void findGrapeByNameTest() {
        when(grapeService.findGrapeByName(testGrape1.getName())).thenReturn(testGrape1);

        GrapeDTO grapeDto2 = grapeFacade.findGrapeByName(testGrape1.getName());

        verify(grapeService).findGrapeByName(testGrape1.getName());
        assertThat(grapeDto1).isEqualTo(grapeDto2);
    }

    @Test
    public void findAllGrapesTest() {
        when(grapeService.findAllGrapes()).thenReturn(new ArrayList<>(List.of(testGrape1, testGrape2, testGrape3)));

        List<GrapeDTO> allGrapeDtos = grapeFacade.findAllGrapes();

        verify(grapeService).findAllGrapes();
        assertThat(allGrapeDtos).containsExactlyInAnyOrder(grapeDto1, grapeDto2, grapeDto3);
    }

    @Test
    public void updateGrapeTest() {
        grapeDto1.setName("newName");
        grapeFacade.updateGrape(grapeDto1);
        grapeDto1.setName("testGrape1");
        grapeFacade.updateGrape(grapeDto1);
 
        verify(grapeService).updateGrape(testGrape1);
    }

    @Test
    public void deleteGrapeTest() {
        when(grapeService.findGrapeById(1L)).thenReturn(testGrape1);

        grapeFacade.removeGrape(1L);

        verify(grapeService).removeGrape(testGrape1);
    }

    @Test
    public void createGrapeTest(){
        grapeFacade.createGrape(grapeCreateDto1);
        verify(grapeService, times(1)).createGrape(testGrape1);
    }

    @Test
    public void addHarvestTest(){
        grapeFacade.addHarvest(testHarvest1.getId(), testGrape1.getId());
        verify(grapeService).addHarvestToGrape(any(), any());
    }

    @Test
    public void cureDiseaseTest(){
        grapeFacade.cureDisease(grapeCureDto1);
        verify(grapeService).cureDisease(any(), any());
    }

    @Test
    public void cureAllDiseasesTest(){
        grapeFacade.cureAllDiseases(1L);
        verify(grapeService).cureAllDiseases(any());
    }
}

