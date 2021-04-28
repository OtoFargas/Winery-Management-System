package cz.muni.fi.pa165.service.facade;

import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
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

    private GrapeService grapeService = mock(GrapeService.class);
    private HarvestService harvestService = mock(HarvestService.class);

    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private GrapeFacade grapeFacade;

    @BeforeMethod
    public void setupFacade() {
        grapeFacade = new GrapeFacadeImpl(grapeService, harvestService, beanMappingService);
    }

    private Grape testGrape1;
    private Grape testGrape2;
    private Grape testGrape3;

    private GrapeDTO testGrapeDTO1;
    private GrapeDTO testGrapeDTO2;
    private GrapeDTO testGrapeDTO3;

    @BeforeMethod
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
    }

    @BeforeMethod
    public void createDTO() {
        testGrapeDTO1 = new GrapeDTO();
        testGrapeDTO1.setName("testGrape1");
        testGrapeDTO1.setColor(GrapeColor.RED);
        testGrapeDTO1.setDiseases(new ArrayList<>(List.of(Disease.ANTHRACNOSE, Disease.CROWN_GALL)));
        testGrapeDTO1.setQuantity(20);
        testGrapeDTO1.setId(1L);

        testGrapeDTO2 = new GrapeDTO();
        testGrapeDTO2.setName("testGrape2");
        testGrapeDTO2.setColor(GrapeColor.WHITE);
        testGrapeDTO2.setDiseases(new ArrayList<>(List.of(Disease.CROWN_GALL)));
        testGrapeDTO2.setQuantity(15);
        testGrapeDTO2.setId(2L);

        testGrapeDTO3 = new GrapeDTO();
        testGrapeDTO3.setName("testGrape3");
        testGrapeDTO3.setColor(GrapeColor.RED);
        testGrapeDTO3.setDiseases(new ArrayList<>());
        testGrapeDTO3.setQuantity(0);
        testGrapeDTO3.setId(3L);
    }

    @Test
    public void deleteGrapeTest() {
        when(grapeService.findGrapeById(1L)).thenReturn(testGrape1);

        grapeFacade.deleteGrape(1L);

        verify(grapeService, times(1)).removeGrape(testGrape1);
    }

    @Test
    public void getGrapeByIdTest() {
        GrapeDTO grapeDto1 = beanMappingService.mapTo(testGrape1, GrapeDTO.class);

        when(grapeService.findGrapeById(testGrape1.getId())).thenReturn(testGrape1);

        GrapeDTO grapeDto2 = grapeFacade.getGrapeById(testGrape1.getId());

        verify(grapeService).findGrapeById(testGrape1.getId());
        assertThat(grapeDto1).isEqualTo(grapeDto2);
    }

    @Test
    public void getGrapeByColorTest() {
        GrapeDTO grapeDto1 = beanMappingService.mapTo(testGrape1, GrapeDTO.class);
        GrapeDTO grapeDto2 = beanMappingService.mapTo(testGrape2, GrapeDTO.class);
        GrapeDTO grapeDto3 = beanMappingService.mapTo(testGrape3, GrapeDTO.class);

        when(grapeService.findGrapeByColor(GrapeColor.RED)).thenReturn(new ArrayList<>(List.of(testGrape1, testGrape3)));
        when(grapeService.findGrapeByColor(GrapeColor.WHITE)).thenReturn(new ArrayList<>(List.of(testGrape2)));

        List<GrapeDTO> redGrapeDtos = grapeFacade.getGrapesByColor(GrapeColor.RED);
        List<GrapeDTO> whiteGrapeDtos = grapeFacade.getGrapesByColor(GrapeColor.WHITE);

        verify(grapeService).findGrapeByColor(GrapeColor.RED);
        verify(grapeService).findGrapeByColor(GrapeColor.WHITE);
        assertThat(redGrapeDtos).contains(grapeDto1, grapeDto3);
        assertThat(whiteGrapeDtos).contains(grapeDto2);
    }

    @Test
    public void getGrapeByNameTest() {
        GrapeDTO grapeDto1 = beanMappingService.mapTo(testGrape1, GrapeDTO.class);

        when(grapeService.findGrapeByName(testGrape1.getName())).thenReturn(testGrape1);

        GrapeDTO grapeDto2 = grapeFacade.getGrapeByName(testGrape1.getName());

        verify(grapeService).findGrapeByName(testGrape1.getName());
        assertThat(grapeDto1).isEqualTo(grapeDto2);
    }

    // @Test
    // public void testGetLecturesByCourse() {
    //     when(lectureService.getLecturesByCourse(englishCourse)).thenReturn(Arrays.asList(englishLecture1, englishLecture2));

    //     List<LectureDTO> lectures = lectureFacade.getLecturesByCourse(englishCourseDTO);

    //     assertThat(lectures).containsExactlyInAnyOrder(englishLecture1DTO, englishLecture2DTO);
    // }

    // @Test
    // public void testGetLecturesByTeacher() {
    //     when(lectureService.getLectureByTeacher(englishTeacher)).thenReturn(Arrays.asList(englishLecture2));

    //     List<LectureDTO> lectures = lectureFacade.getLectureByTeacher(englishTeacherDTO);

    //     assertThat(lectures).containsExactlyInAnyOrder(englishLecture2DTO);
    // }

    // @Test
    // public void testGetLecturesByLanguage() {
    //     when(lectureService.getLecturesByLanguage(Language.ENGLISH)).thenReturn(Arrays.asList(englishLecture1, englishLecture2));

    //     List<LectureDTO> lectures = lectureFacade.getLecturesByLanguage(Language.ENGLISH);

    //     assertThat(lectures).containsExactlyInAnyOrder(englishLecture1DTO, englishLecture2DTO);
    // }
}
