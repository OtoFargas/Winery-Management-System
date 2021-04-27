package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.facade.GrapeFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.GrapeService;
import cz.muni.fi.pa165.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.inject.Inject;

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

    private final GrapeService grapeService;
    private final HarvestService harvestService;

    private final BeanMappingService beanMappingService;

    @Inject
    public GrapeFacadeImpl(GrapeService grapeService, HarvestService harvestService, BeanMappingService beanMappingService) {
        this.grapeService = grapeService;
        this.harvestService = harvestService;
        this.beanMappingService = beanMappingService;
    }

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
    public GrapeDTO getGrapeByName(String name) {
        Grape grape = grapeService.findGrapeByName(name);
        return beanMappingService.mapTo(grape, GrapeDTO.class);
    }

    @Override
    public void deleteGrape(Long id) {
        grapeService.removeGrape(grapeService.findGrapeById(id));
    }

    @Override
    public void updateGrape(GrapeDTO grapeDTO) {
        Grape grape = beanMappingService.mapTo(grapeDTO, Grape.class);
        grapeService.updateGrape(grape);
    }

    @Override
    public Long createGrape(GrapeCreateDTO grapeCreateDTO) {
        Grape grape = new Grape();
        grape.setName(grapeCreateDTO.getName());
        grape.setColor(grapeCreateDTO.getColor());
        grape.setQuantity(grapeCreateDTO.getQuantity());
        grape.setDiseases(grapeCreateDTO.getDiseases());

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
