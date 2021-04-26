package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.facade.GrapeFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.GrapeService;
import cz.muni.fi.pa165.service.HarvestService;
import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements GrapeFacade interface
 *
 * @author Lukáš Fudor
 */
@Service
@Transactional
public class GrapeFacadeImpl implements GrapeFacade {

    @Inject
    private GrapeService grapeService;

    @Inject
    private HarvestService harvestService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<GrapeDTO> getAllGrapes() {
        return beanMappingService.mapTo(grapeService.findAllGrapes(), GrapeDTO.class);
    }

    @Override
    public GrapeDTO getGrapeById(Long id) {
        Grape grape = grapeService.findGrapeById(id);
        return (grape == null) ? null : beanMappingService.mapTo(grape, GrapeDTO.class);
    }

    @Override
    public List<GrapeDTO> getGrapesByColor(GrapeColor grapeColor) {
        List<Grape> grapes = grapeService.findGrapeByColor(grapeColor);
        return beanMappingService.mapTo(grapes, GrapeDTO.class);
    }

    @Override
    public GrapeDTO getGrapesByName(String name) {
        Grape grape = grapeService.findGrapeByName(name);
        return beanMappingService.mapTo(grape, GrapeDTO.class);
    }

    @Override
    public void deleteGrape(Long id) {
        grapeService.removeGrape(grapeService.findGrapeById(id));
    }

    @Override
    public Long createGrape(GrapeCreateDTO grapeCreateDTO) {
        Grape grape = new Grape();
        grape.setName(grapeCreateDTO.getName());
        grapeService.createGrape(grape);
        return grape.getId();
    }

    @Override
    public void addHarvest(Long harvestID, Long grapeID) {
        grapeService.addHarvestToGrape(grapeService.findGrapeById(grapeID),
                harvestService.findHarvestById(harvestID));
    }

    @Override
    public void cureDisease(Long grapeID, Disease disease) {
        Grape grape = grapeService.findGrapeById(grapeID);
        grapeService.cureDisease(grape, disease);
    }

    @Override
    public void cureAllDiseases(Long grapeID) {
        grapeService.cureAllDiseases(grapeService.findGrapeById(grapeID));
    }
}
