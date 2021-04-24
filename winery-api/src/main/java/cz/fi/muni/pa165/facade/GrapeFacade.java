package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GrapeCreateDTO;
import cz.fi.muni.pa165.dto.GrapeDTO;
import cz.fi.muni.pa165.enums.GrapeColor;

import java.util.List;

/**
 * Facade interface for Grape entity
 *
 * @author Lukáš Fudor
 */

// TODO add more functions
public interface GrapeFacade {
    List<GrapeDTO> getAllGrapes();
    GrapeDTO getGrapeById(Long id);
    List<GrapeDTO> getGrapesByColor(GrapeColor grapeColor);

    Long createGrape(GrapeCreateDTO grapeCreateDTO);
}
