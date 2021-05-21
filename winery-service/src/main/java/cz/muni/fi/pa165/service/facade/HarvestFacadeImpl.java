package cz.muni.fi.pa165.service.facade;

import java.util.List;

import javax.transaction.Transactional;

import javax.inject.Inject;

import cz.muni.fi.pa165.dto.HarvestCreateDTO;
import cz.muni.fi.pa165.service.GrapeService;
import cz.muni.fi.pa165.service.WineService;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.facade.HarvestFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.HarvestService;


/**
 * @author Vladimir Visnovsky
 */
@Transactional
@Service
public class HarvestFacadeImpl implements HarvestFacade {

    private final HarvestService harvestService;
    private final WineService wineService;
    private final GrapeService grapeService;

    private final BeanMappingService beanMappingService;

    @Inject
    public HarvestFacadeImpl(HarvestService harvestService, WineService wineService,
                             GrapeService grapeService, BeanMappingService beanMappingService) {
        this.harvestService = harvestService;
        this.wineService = wineService;
        this.grapeService = grapeService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public Long createHarvest(HarvestCreateDTO harvestCreateDTO) {
        Harvest harvest = beanMappingService.mapTo(harvestCreateDTO, Harvest.class);
        harvest.setGrape(grapeService.findGrapeById(harvestCreateDTO.getGrapeId()));
        harvestService.createHarvest(harvest);
        return harvest.getId();
    }

    @Override
    public List<HarvestDTO> findAllHarvests() {
        return beanMappingService.mapTo(harvestService.findAllHarvests(), HarvestDTO.class);
    }

    @Override
    public HarvestDTO findHarvestById(Long id) {
        Harvest harvest = harvestService.findHarvestById(id);
        return (harvest == null) ? null : beanMappingService.mapTo(harvest, HarvestDTO.class);
    }

    @Override
    public List<HarvestDTO> findHarvestsByYear(Integer year) {
        return beanMappingService.mapTo(harvestService.findHarvestByYear(year), HarvestDTO.class);
    }

    @Override
    public void updateHarvest(HarvestDTO harvestDTO) {
        Harvest harvest = beanMappingService.mapTo(harvestDTO, Harvest.class);

        harvestService.updateHarvest(harvest);
    }

    @Override
    public void removeHarvest(Long id) {
        harvestService.removeHarvest(harvestService.findHarvestById(id));
    }

    
}
