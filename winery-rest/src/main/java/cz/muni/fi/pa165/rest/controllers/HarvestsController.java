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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

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

    
    /**
     * Creates harvest
     * 
     * curl -X POST -i -H "Content-Type: application/json" --data 
     * '{"harvestYear":"2020","quality":"LOW","quantity":"20","grapeId":"1"}' 
     * http://localhost:8080/pa165/rest/harvests/create
     * 
     * @param harvestCreateDTO
     * @return created harvest
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HarvestDTO createHarvest(@RequestBody HarvestCreateDTO harvestCreateDTO) {
        logger.info("create({})", harvestCreateDTO);
        Long id = harvestFacade.createHarvest(harvestCreateDTO);
        return harvestFacade.findHarvestById(id);
    }

    /**
     * Gets harvest with specified id
     * 
     * curl -i -X GET "http://localhost:8080/pa165/rest/harvests/1"
     * 
     * @param id
     * @return harvest with specified id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HarvestDTO findHarvest(@PathVariable("id") Long id) {
        logger.info("findById({})", id);
        return harvestFacade.findHarvestById(id);
    }

    /**
     * Gets list of all harvests
     * 
     * curl -i -X GET "http://localhost:8080/pa165/rest/harvests/list"
     * 
     * @return list of all harvests
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HarvestDTO> findAllHarvests() {
        logger.info("findAllHarvests()");
        return harvestFacade.findAllHarvests();
    }

    /**
     * Update one specific harvest
     * 
     * curl -X PUT -i -H "Content-Type: application/json" --data 
     * '{"id":"1","harvestYear":"1000","quality":"LOW","quantity":"90"}' 
     * http://localhost:8080/pa165/rest/harvests/update
     * 
     * @param harvestDTO
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateHarvest(@RequestBody HarvestDTO harvestDTO) {
        logger.info("update({})", harvestDTO);
        harvestFacade.updateHarvest(harvestDTO);
    }

    /**
     * Remove one specific harvest
     * 
     * curl -i -X DELETE "http://localhost:8080/pa165/rest/harvests/remove/1"
     * 
     * @param id
     */
    @DeleteMapping("/remove/{id}")
    public final void removeHarvest(@PathVariable("id") long id) {
        logger.debug("rest removeFeedback({})", id);
        harvestFacade.removeHarvest(id);
    }

}
