package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.GrapeDao;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Oto Fargas
 */
@ContextConfiguration(classes= ServiceConfiguration.class)
public class GrapeServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private GrapeDao grapeDao;

    @Autowired
    @InjectMocks
    private GrapeService grapeService;

    private Grape grape1;
    private Grape grape2;
    private Grape grape3;

    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void prepareTestGrape() {
        grape1 = new Grape(1L);
        grape1.setName("Test1");
        grape1.setColor(GrapeColor.RED);
        grape1.setQuantity(56);

        List<Disease> diseases = new ArrayList<>();
        diseases.add(Disease.CROWN_GALL);
        diseases.add(Disease.GREY_MOLD);
        grape1.setDiseases(diseases);

        grape2 = new Grape(2L);
        grape2.setName("Test2");
        grape2.setColor(GrapeColor.WHITE);
        grape2.setQuantity(99);

        diseases = new ArrayList<>();
        diseases.add(Disease.DOWNY_MILDEW);
        diseases.add(Disease.POWDERY_MILDEW);
        grape2.setDiseases(diseases);

        grape3 = new Grape(3L);
        grape3.setName("Test3");
        grape3.setColor(GrapeColor.WHITE);
        grape3.setQuantity(12);

        diseases = new ArrayList<>();
        diseases.add(Disease.ANTHRACNOSE);
        grape3.setDiseases(diseases);
    }

    @Test
    public void testCreateGrape() {
        grapeService.createGrape(grape1);
        verify(grapeDao, times(1)).create(grape1);
    }

    @Test
    public void testFindGrapeById() {
        when(grapeDao.findById(1L)).thenReturn(grape1);
        Grape foundGrape = grapeService.findGrapeById(1L);
        assertThat(grape1).isEqualToComparingFieldByField(foundGrape);
    }

    @Test
    public void testFindNonExistingGrapeById() {
        Grape foundGrape = grapeService.findGrapeById(5L);
        assertThat(foundGrape).isNull();
    }

    @Test
    public void testFindGrapeByName() {
        when(grapeDao.findByName("Test1")).thenReturn(grape1);
        Grape foundGrape = grapeService.findGrapeByName("Test1");
        assertThat(grape1).isEqualToComparingFieldByField(foundGrape);
    }

    @Test
    public void testFindGrapesByColor() {
        when(grapeDao.findByColor(GrapeColor.WHITE)).thenReturn(Arrays.asList(grape2, grape3));
        List<Grape> grapes = grapeService.findGrapeByColor(GrapeColor.WHITE);
        assertThat(grapes).containsExactlyInAnyOrder(grape2, grape3);
    }

    @Test
    public void testFindAllGrapes() {
        when(grapeDao.findAll()).thenReturn(Arrays.asList(grape1, grape2, grape3));
        List<Grape> grapes = grapeService.findAllGrapes();
        assertThat(grapes).containsExactlyInAnyOrder(grape1, grape2, grape3);
    }

    @Test
    public void testUpdateGrape() {
        when(grapeDao.findById(1L)).thenReturn(grape1);
        grape1.setQuantity(111);
        grapeService.updateGrape(grape1);
        verify(grapeDao, times(1)).update(grape1);
    }

    @Test
    public void testRemoveGrape() {
        when(grapeDao.findById(1L)).thenReturn(grape1);
        grapeService.removeGrape(grape1);
        verify(grapeDao, times(1)).remove(grape1);
    }

    @Test
    public void testCureDisease() {
        when(grapeDao.findById(1L)).thenReturn(grape1);
        // cure first disease
        grapeService.cureDisease(grape1, Disease.CROWN_GALL);
        List<Disease> diseases = grapeDao.findById(1L).getDiseases();
        assertThat(diseases).contains(Disease.GREY_MOLD);
        // cure second disease
        grapeService.cureDisease(grape1, Disease.GREY_MOLD);
        diseases = grapeDao.findById(1L).getDiseases();
        assertThat(diseases).isEmpty();
        verify(grapeDao, times(2)).update(grape1);
    }

    @Test
    public void testCureNullDisease() {
        when(grapeDao.findById(1L)).thenReturn(grape1);
        assertThatThrownBy(() -> grapeService.cureDisease(grape1, null))
                .isInstanceOf(WineryServiceException.class);
    }

    @Test
    public void testCureNonExistingDisease() {
        assertThatThrownBy(() -> grapeService.cureDisease(grape1, Disease.ANTHRACNOSE))
                .isInstanceOf(WineryServiceException.class);
    }

    @Test
    public void testCureAllDiseases() {
        when(grapeDao.findById(2L)).thenReturn(grape2);
        grapeService.cureAllDiseases(grape2);
        List<Disease> diseases = grapeDao.findById(2L).getDiseases();
        assertThat(diseases).isEmpty();
        verify(grapeDao, times(1)).update(grape2);
    }
}
