package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.io.IOException;

/**
 * Takes ServiceConfiguration and adds the SampleDataLoadingFacade bean.
 *
 * @author Oto Fargas
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class AppWithSampleDataConfiguration {


    private final SampleDataLoadingFacade sampleDataLoadingFacade;

    @Inject
    public AppWithSampleDataConfiguration(SampleDataLoadingFacade sampleDataLoadingFacade) {
        this.sampleDataLoadingFacade = sampleDataLoadingFacade;
    }

    @PostConstruct
    public void dataLoading() throws IOException {
        sampleDataLoadingFacade.loadData();
    }
}