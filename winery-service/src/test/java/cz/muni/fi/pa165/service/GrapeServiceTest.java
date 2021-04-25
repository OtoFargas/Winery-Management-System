package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.GrapeDao;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
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

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void prepareTestGrape() {
        grape1 = new Grape(1L);
        grape1.setName("Test");
        grape1.setColor(GrapeColor.RED);
        grape1.setQuantity(56);

        List<Disease> diseases = new ArrayList<>();
        diseases.add(Disease.CROWN_GALL);
        grape1.setDiseases(diseases);

        grape2 = new Grape(1L);
        grape2.setName("Test");
        grape2.setColor(GrapeColor.RED);
        grape2.setQuantity(56);

        diseases = new ArrayList<>();
        diseases.add(Disease.DOWNY_MILDEW);
        diseases.add(Disease.POWDERY_MILDEW);
        grape2.setDiseases(diseases);
    }

    @Test
    public void testCreateGrape() {
        grapeService.createGrape(grape1);
        verify(grapeDao, times(1)).create(grape1);
    }

//    @Test
//    public void testFindGrapeById() {
//        when(grapeDao.findById(1L)).thenReturn(grape);
//
//        Grape foundGrape = grapeService.findGrapeById(1L);
//        assertEq (lecture).isEqualToComparingFieldByField(chineseLecture);
//    }

    @Test
    public void deleteLecture() {
        when(grapeDao.findById(1L)).thenReturn(grape1);
        grapeService.removeGrape(grape1);
        verify(grapeDao, times(1)).remove(grape1);
    }

}
