package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.HarvestDao;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

/**
 *  Test class for HarvestService
 *
 * @author Lukáš Fudor
 */

@ContextConfiguration(classes=ServiceConfiguration.class)
public class HarvestServiceTest extends AbstractTestNGSpringContextTests  {

    @Mock
    private HarvestDao harvestDao;

    @Autowired
    @InjectMocks
    private HarvestService harvestService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.openMocks(this);
    }
}
