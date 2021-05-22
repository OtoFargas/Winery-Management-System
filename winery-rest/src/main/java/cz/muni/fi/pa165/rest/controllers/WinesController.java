package cz.muni.fi.pa165.rest.controllers;

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

import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.dto.WineBuyDTO;
import cz.muni.fi.pa165.dto.WineCreateDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.facade.WineFacade;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * REST controller for Wines
 * 
 * @author Vladimir Visnovsky
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_WINES)
public class WinesController {

    private final Logger logger = LoggerFactory.getLogger(WinesController.class);

    @Autowired
    private WineFacade wineFacade;


    @PostMapping("wine/create")
    public WineDTO createWine(@RequestBody WineCreateDTO wineCreateDTO) {
        logger.info("create({})", wineCreateDTO);
        Long id = wineFacade.createWine(wineCreateDTO);
        return wineFacade.findWineById(id);
    }

    @PostMapping("wine/update")
    public void updateWine(@RequestBody WineDTO wineDTO) {
        logger.info("update({})", wineDTO);
        wineFacade.updateWine(wineDTO);
    }

    @PostMapping("wine/{id}/addharvest")
    public void addHarvest(@PathVariable("id") long id, @RequestBody HarvestDTO harvestDTO) {
        logger.info("addHarvest({})", id);
        wineFacade.addHarvest(harvestDTO.getId(), id);
    }

    @PostMapping("wine/{id}/addfeedback")
    public void addFeedback(@PathVariable("id") long id, @RequestBody FeedbackDTO feedbackDTO) {
        logger.info("addFeedback({})", id);
        wineFacade.addFeedback(feedbackDTO.getId(), id);
    }

    @PostMapping("wine/{id}/sell")
    public void sellWine(@RequestBody WineBuyDTO wineBuyDTO) {
        logger.info("sellWine({})", wineBuyDTO);

        if (wineBuyDTO == null) {
            throw new ResourceNotFoundException();
        }
        wineFacade.sellWine(wineBuyDTO);
    }

    @GetMapping("wine/{id}")
    public WineDTO findWineById(@PathVariable("id") Long id) {
        logger.info("findById({})", id);
        WineDTO wineDTO;

        try {
            wineDTO = wineFacade.findWineById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Wine with id " + id + " not found");
        }
        return wineDTO;
    }

    @GetMapping("wine/{name}")
    public WineDTO findWineByName(@PathVariable("name") String name) {
        logger.info("findByName({})", name);
        return wineFacade.findWineByName(name);
    }

    @GetMapping("wine/list")
    public final List<WineDTO> findAllWines() {
        logger.info("findAllWines()");
        return wineFacade.findAllWines();
    }

    @DeleteMapping("wine/{id}/remove")
    public void removeWine(@PathVariable("id") long id) {
        logger.debug("removeWine({})", id);
        wineFacade.removeWine(id);
    }
}
