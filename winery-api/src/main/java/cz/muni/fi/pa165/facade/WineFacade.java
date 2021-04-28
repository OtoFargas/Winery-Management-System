package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.WineBuyDTO;
import cz.muni.fi.pa165.dto.WineCreateDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;

import java.util.List;

/**
 * Facade interface for Wine entity
 *
 * @author Lukáš Fudor
 */

public interface WineFacade {
    /**
     * @param wineCreateDTO to be created
     * @return id of newly created wine
     */
    Long createWine(WineCreateDTO wineCreateDTO);

    /**
     * @param id of the desired wine
     * @return WineDTO with the id
     */
    WineDTO findWineById(Long id);

    /**
     * @param name of the desired wine
     * @return WineDTO with the name
     */
    WineDTO findWineByName(String name);

    /**
     * @param wineDTO to be updated
     */
    void updateWine(WineDTO wineDTO);

    /**
     * @param wineID of the wine to be deleted
     */
    void removeWine(Long wineID);

    /**
     * @return list of all wines
     */
    List<WineDTO> getAllWines();

    /**
     * @param wineBuyDTO to be bought
     */
    void sellWine(WineBuyDTO wineBuyDTO);

    /**
     * @param feedbackID of the feedback to be added
     * @param wineID of the wine for the addition
     */
    void addFeedback(Long feedbackID, Long wineID);

    /**
     * @param harvestID of the harvest to be added
     * @param wineID of the wine for the addition
     */
    void addHarvest(Long harvestID, Long wineID);
}
