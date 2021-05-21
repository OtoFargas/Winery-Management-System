package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeChangeDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.facade.GrapeFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for Grapes
 *
 * @author Lukáš Fudor
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_GRAPES)
public class GrapesController {

    final static Logger logger = LoggerFactory.getLogger(GrapesController.class);

    @Autowired
    private GrapeFacade grapeFacade;

    /**
     * @param grape to be created
     * @return newly created GrapeDTO
     * @throws ResourceAlreadyExistingException if given grape already exists
     */
    @PostMapping("grape/create")
    public final GrapeDTO createGrape(@RequestBody GrapeCreateDTO grape) throws ResourceAlreadyExistingException {

        logger.debug("rest createGrape()");

        try {
            Long id = grapeFacade.createGrape(grape);
            return grapeFacade.findGrapeById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * return all grapes
     * @return list of GrapeDTOs
     */
    @GetMapping("/grape/list")
    public final List<GrapeDTO> findAllGrapes() {

        logger.debug("rest findAllGrapes()");

        return grapeFacade.findAllGrapes();
    }

    /**
     * @param id of the grape we are looking for
     * @return grapeDTO with given id
     * @throws ResourceNotFoundException when grapeDTO with given id doesnt exist
     */
    @GetMapping("grape/{id}")
    public final GrapeDTO findGrape(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest findGrape({})", id);

        GrapeDTO grapeDTO = grapeFacade.findGrapeById(id);
        if (grapeDTO == null) {
            throw new ResourceNotFoundException("No grape with ID" + id + "found.");
        }

        return grapeDTO;
    }

    /**
     * @param color of the grapes to be looked for
     * @return list of GrapeDTOs with given color
     * @throws ResourceNotFoundException when no GrapeDTOs with given color are found
     */
    @GetMapping("grape/by_color/{color}")
    public final List<GrapeDTO> findGrapesByColor(@PathVariable("color") GrapeColor color) throws ResourceNotFoundException {

        logger.debug("rest findGrapesByColor({})", color);

        List<GrapeDTO> grapeDTOs = grapeFacade.findGrapesByColor(color);
        if (grapeDTOs == null){
            throw new ResourceNotFoundException("No grapes with color" + color + "found.");
        }

        return grapeDTOs;
    }

    /**
     * @param name of the GrapeDTO to be looked for
     * @return GrapeDTo with given name
     * @throws ResourceNotFoundException when the GrapeDTO with given name cant be found
     */
    @GetMapping("grape/by_name/{name}")
    public final GrapeDTO findGrapeByName(@PathVariable("name") String name) throws ResourceNotFoundException {

        logger.debug("rest findGrapeByName({})", name);

        GrapeDTO grapeDTO = grapeFacade.findGrapeByName(name);
        if (grapeDTO == null){
            throw new ResourceNotFoundException("No grape with name" + name + "found.");
        }

        return grapeDTO;
    }

    /**
     * @param id of the grape to be deleted
     * @throws ResourceNotFoundException when the grape cant be found
     */
    @DeleteMapping("grape/{id}")
    public final void removeGrape(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest removeGrape({})", id);

        try {
            grapeFacade.removeGrape(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No grape with ID" + id + "found.");
        }
    }

    /**
     * @param id of the grape to be added to
     * @param harvest of the harvest to be added
     * @return GrapeDTO of changed grape
     * @throws InvalidParameterException when given harvest is invalid
     */
    @PostMapping("grape/{id}/harvests")
    public final GrapeDTO addHarvest(@PathVariable("id") long id, @RequestBody HarvestDTO harvest) throws InvalidParameterException {

        logger.debug("rest addHarvest({})", id);

        try {
            grapeFacade.addHarvest(harvest.getId(), id);
            return grapeFacade.findGrapeById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    /**
     * @param grapeChangeDTO to be cured of grapeChangeDTO.disease
     * @return of the cured Grape
     * @throws InvalidParameterException when the grapeChangeDTO is invalid
     */
    @PutMapping("grape/cureDisease")
    public final GrapeDTO cureDisease(@RequestBody GrapeChangeDTO grapeChangeDTO) throws InvalidParameterException {

        logger.debug("rest cureDisease({})", grapeChangeDTO.getId());

        try {
            grapeFacade.cureDisease(grapeChangeDTO);
            return grapeFacade.findGrapeById(grapeChangeDTO.getId());
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    /**
     * @param id of the grape to be cured of all diseases
     * @return the cured grape
     * @throws ResourceNotFoundException when the grape cant be found
     */
    @PutMapping(value = "grape/cureAllDiseases/{id}")
    public final GrapeDTO cureAllDiseases(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest cureAllDiseases({})", id);

        try {
            grapeFacade.cureAllDiseases(id);
            return grapeFacade.findGrapeById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No grape with ID" + id + "found.");
        }
    }
}
