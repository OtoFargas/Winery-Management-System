package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 *  Test class for HarvestService
 *
 * @author Lukáš Fudor
 */

@ContextConfiguration(classes= ServiceConfiguration.class)
public class HarvestServiceTest extends AbstractTestNGSpringContextTests  {
}
