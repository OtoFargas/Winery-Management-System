package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;

import java.util.List;

/**
 * Facade interface for Grape entity
 *
 * @author Lukáš Fudor
 */

public interface GrapeFacade {

    /**
     * @return a list of all grapes
     */
    List<GrapeDTO> getAllGrapes();

    /**
     * @param id from database
     * @return Grape with id from the parameter
     */
    GrapeDTO getGrapeById(Long id);

    /**
     * @param grapeColor enum, color of the grape
     * @return a list of grapes with the grapeColor color
     */
    List<GrapeDTO> getGrapesByColor(GrapeColor grapeColor);

    /**
     * @param id of the grape to be deleted
     */
    void deleteGrape(Long id);

    /**
     * @param grapeCreateDTO grape to be created
     * @return id of newly created GrapeDTO
     */
    Long createGrape(GrapeCreateDTO grapeCreateDTO);

    /**
     * @param harvestID of harvest to be added
     * @param grapeID of grape to receive harvest
     */
    void addHarvest(Long harvestID, Long grapeID);

    /**
     * @param grapeID to be cured
     * @param disease to be cured
     */
    void cureDisease(Long grapeID, Disease disease);

    /**
     * @param grapeID to be cured
     */
    void cureAllDiseases(Long grapeID);
}
