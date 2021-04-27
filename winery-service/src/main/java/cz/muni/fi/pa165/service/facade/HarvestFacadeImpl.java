package cz.muni.fi.pa165.service.facade;

import java.util.List;

import javax.transaction.Transactional;

import javax.inject.Inject;
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

    private final BeanMappingService beanMappingService;

    @Inject
    public HarvestFacadeImpl(HarvestService harvestService, BeanMappingService beanMappingService) {
        this.harvestService = harvestService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public void createHarvest(HarvestDTO harvestDTO) {
        Harvest harvest = beanMappingService.mapTo(harvestDTO, Harvest.class);
        harvestService.createHarvest(harvest);
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
    public List<HarvestDTO> findHarvestByYear(Integer year) {
        return beanMappingService.mapTo(harvestService.findHarvestByYear(year), HarvestDTO.class);
    }

    @Override
    public void updateHarvest(HarvestDTO harvestDTO) {
        Harvest harvest = beanMappingService.mapTo(harvestDTO, Harvest.class);

        harvestService.updateHarvest(harvest);
    }

    @Override
    public void removeHarvest(HarvestDTO harvestDTO) {
        harvestService.removeHarvest(beanMappingService.mapTo(harvestDTO, Harvest.class));
    }
}
