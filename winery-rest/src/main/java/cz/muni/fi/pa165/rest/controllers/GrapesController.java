package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.facade.GrapeFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * REST Controller for Orders
 *
 * @author Lukáš Fudor
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_GRAPES)
public class GrapesController {

    final static Logger logger = LoggerFactory.getLogger(GrapesController.class);

    @Inject
    private GrapeFacade grapeFacade;

    /**
     * @param id of the grape we are looking for
     * @return grapeDTO with given id
     * @throws ResourceNotFoundException when grapeDTO with given id doesnt exist
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final GrapeDTO getGrape(@PathVariable("id") long id) throws ResourceNotFoundException {

        logger.debug("rest getGrape({})", id);

        GrapeDTO grapeDTO = grapeFacade.findGrapeById(id);
        if (grapeDTO == null) {
            throw new ResourceNotFoundException();
        }

        return grapeDTO;
    }


    @RequestMapping(value = "by_color/{color}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<GrapeDTO> getGrapesByColor(@PathVariable("color") GrapeColor color) throws ResourceNotFoundException {

        logger.debug("rest getGrapesByColor({})", color);

        List<GrapeDTO> grapeDTOs = grapeFacade.findGrapesByColor(color);
        if (grapeDTOs == null){
            throw new ResourceNotFoundException();
        }
        return grapeDTOs;

    }
}
