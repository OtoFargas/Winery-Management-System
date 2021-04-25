package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.service.GrapeServiceImpl;
import cz.muni.fi.pa165.service.facade.FeedbackFacadeImpl;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;


@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses={GrapeServiceImpl.class, FeedbackFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    /**
     * Custom config for Dozer if needed
     * @author Lukáš Fudor
     *
     */
    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Grape.class, GrapeDTO.class);
        }
    }
}
