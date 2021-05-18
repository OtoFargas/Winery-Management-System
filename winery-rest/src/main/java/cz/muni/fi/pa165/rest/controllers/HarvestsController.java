package cz.muni.fi.pa165.rest.controllers;


import cz.muni.fi.pa165.dto.HarvestCreateDTO;
import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.facade.HarvestFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * REST Controller for Harvests
 *
 * @author Oto Fargas
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_HARVESTS)
public class HarvestsController {

    final static Logger logger = LoggerFactory.getLogger(HarvestsController.class);

    @Autowired
    private HarvestFacade harvestFacade;

    @PostMapping("harvest/create")
    public HarvestDTO createHarvest(@RequestBody HarvestCreateDTO harvestCreateDTO) {
        logger.info("create({})", harvestCreateDTO);
        Long id = harvestFacade.createHarvest(harvestCreateDTO);
        return harvestFacade.findHarvestById(id);
    }

    @GetMapping("harvest/{id}")
    public HarvestDTO findHarvest(@PathVariable("id") Long id) {
        logger.info("findById({})", id);
        return harvestFacade.findHarvestById(id);
    }

    @GetMapping("harvest/list")
    public final List<HarvestDTO> findAllHarvests() {
        logger.info("findAllHarvests()");
        return harvestFacade.findAllHarvests();
    }

    @PostMapping("harvest/update")
    public void updateHarvest(@RequestBody HarvestDTO harvestDTO) {
        logger.info("update({})", harvestDTO);
        harvestFacade.updateHarvest(harvestDTO);
    }

    @DeleteMapping("harvest/remove/{id}")
    public final void removeHarvest(@PathVariable("id") long id) {
        logger.debug("rest removeFeedback({})", id);
        harvestFacade.removeHarvest(id);
    }

}
