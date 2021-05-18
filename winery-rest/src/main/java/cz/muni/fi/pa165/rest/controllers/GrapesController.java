package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeCureDTO;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
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
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
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
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<GrapeDTO> findAllGrapes() {

        logger.debug("rest findAllGrapes()");

        return grapeFacade.findAllGrapes();
    }

    /**
     * @param id of the grape we are looking for
     * @return grapeDTO with given id
     * @throws ResourceNotFoundException when grapeDTO with given id doesnt exist
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final GrapeDTO findGrape(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest findGrape({})", id);

        GrapeDTO grapeDTO = grapeFacade.findGrapeById(id);
        if (grapeDTO == null) {
            throw new ResourceNotFoundException();
        }

        return grapeDTO;
    }

    /**
     * @param color of the grapes to be looked for
     * @return list of GrapeDTOs with given color
     * @throws ResourceNotFoundException when no GrapeDTOs with given color are found
     */
    @RequestMapping(value = "by_color/{color}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<GrapeDTO> findGrapesByColor(@PathVariable("color") GrapeColor color) throws ResourceNotFoundException {

        logger.debug("rest findGrapesByColor({})", color);

        List<GrapeDTO> grapeDTOs = grapeFacade.findGrapesByColor(color);
        if (grapeDTOs == null){
            throw new ResourceNotFoundException();
        }

        return grapeDTOs;
    }

    /**
     * @param name of the GrapeDTO to be looked for
     * @return GrapeDTo with given name
     * @throws ResourceNotFoundException when the GrapeDTO with given name cant be found
     */
    @RequestMapping(value = "by_name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final GrapeDTO findGrapeByName(@PathVariable("name") String name) throws ResourceNotFoundException {

        logger.debug("rest findGrapeByName({})", name);

        GrapeDTO grapeDTO = grapeFacade.findGrapeByName(name);
        if (grapeDTO == null){
            throw new ResourceNotFoundException();
        }

        return grapeDTO;
    }

    /**
     * @param id of the grape to be deleted
     * @throws ResourceNotFoundException when the grape cant be found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeGrape(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest removeGrape({})", id);

        try {
            grapeFacade.removeGrape(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * @param id of the grape to be added to
     * @param harvest of the harvest to be added
     * @return GrapeDTO of changed grape
     * @throws InvalidParameterException when given harvest is invalid
     */
    @RequestMapping(value = "/{id}/harvests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
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
     * @param grapeCureDTO to be cured of grapeCureDTO.disease
     * @return of the cured Grape
     * @throws InvalidParameterException when the grapeCureDTO is invalid
     */
    @RequestMapping(value = "/cureDisease", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final GrapeDTO cureDisease(@RequestBody GrapeCureDTO grapeCureDTO) throws InvalidParameterException {

        logger.debug("rest cureDisease({})", grapeCureDTO.getId());

        try {
            grapeFacade.cureDisease(grapeCureDTO);
            return grapeFacade.findGrapeById(grapeCureDTO.getId());
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    /**
     * @param id of the grape to be cured of all diseases
     * @return the cured grape
     * @throws ResourceNotFoundException when the grape cant be found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public final GrapeDTO cureAllDiseases(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest cureAllDiseases({})", id);

        try {
            grapeFacade.cureAllDiseases(id);
            return grapeFacade.findGrapeById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
