package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.User;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *  Test class for UserService
 *
 * @author Oto Fargas
 */
@ContextConfiguration(classes= ServiceConfiguration.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    private User user1;
    private User user2;
    private User user3;

    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserService userService;

    private AutoCloseable mocks;

    @BeforeClass
    public void setup() throws ServiceException {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterClass
    public void close() throws Exception {
        mocks.close();
    }

    @BeforeMethod
    public void prepareTestUser() {
        user1 = new User();
        user1.setFirstName("Oto");
        user1.setSurname("Fargas");
        user1.setEmail("oto.fargas@winery.com");
        user1.setAdmin(true);

        user2 = new User();
        user2.setFirstName("Vladimir");
        user2.setSurname("Visnovsky");
        user2.setEmail("vladimir.visnovsky@winery.com");
        user2.setAdmin(false);

        user3 = new User();
        user3.setFirstName("Lukas");
        user3.setSurname("Fudor");
        user3.setEmail("lukas.fudor@winery.com");
        user3.setAdmin(false);
    }

    @Test
    public void testRegisterUser() {
        userService.registerUser(user1, "testHeslo");
        userService.registerUser(user2, "testHeslo2");

        verify(userDao, times(1)).create(user1);
        verify(userDao, times(1)).create(user2);
    }

}
