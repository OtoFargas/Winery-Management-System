package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeChangeDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.enums.GrapeColor;

import java.util.List;

/**
 * Facade interface for Grape entity
 *
 * @author Lukáš Fudor
 */

public interface GrapeFacade {

    /**
     * @param grapeCreateDTO grape to be created
     * @return id of newly created GrapeDTO
     */
    Long createGrape(GrapeCreateDTO grapeCreateDTO);

    /**
     * @return a list of all grapes
     */
    List<GrapeDTO> findAllGrapes();

    /**
     * @param id from database
     * @return Grape with id from the parameter
     */
    GrapeDTO findGrapeById(Long id);

    /**
     * @param grapeColor enum, color of the grape
     * @return a list of grapes with the grapeColor color
     */
    List<GrapeDTO> findGrapesByColor(GrapeColor grapeColor);

    /**
     * @param name of the grape
     * @return grape with the given name
     */
    GrapeDTO findGrapeByName(String name);

    /**
     * @param id of the grape to be deleted
     */
    void removeGrape(Long id);

    /**
     * @param grapeDTO to be updated
     */
    void updateGrape(GrapeDTO grapeDTO);

    /**
     * @param harvestID of harvest to be added
     * @param grapeID of grape to receive harvest
     */
    void addHarvest(Long harvestID, Long grapeID);

    /**
     * @param grapeChangeDTO to be added grapeChangeDTO.disease
     */
    void addDisease(GrapeChangeDTO grapeChangeDTO);

    /**
     * @param grapeChangeDTO to be cured of grapeChangeDTO.disease
     */
    void cureDisease(GrapeChangeDTO grapeChangeDTO);

    /**
     * @param grapeID to be cured of all diseases
     */
    void cureAllDiseases(Long grapeID);

    /**
     * @param grapeChangeDTO .grape.quantity to be changed to grapeChangeDTO.quantity
     */
    void changeQuantity(GrapeChangeDTO grapeChangeDTO);
}
