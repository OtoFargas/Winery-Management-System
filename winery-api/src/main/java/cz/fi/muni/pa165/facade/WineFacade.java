package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.WineCreateDTO;
import cz.fi.muni.pa165.dto.WineDTO;
import cz.fi.muni.pa165.enums.Ingredient;
import cz.fi.muni.pa165.enums.Taste;
import cz.fi.muni.pa165.enums.WineColor;

import java.util.List;

/**
 * Facade interface for Wine entity
 *
 * @author Lukáš Fudor
 */

public interface WineFacade {
    /**
     * @param wine to be created
     * @return id of newly created wine
     */
    Long createWine(WineCreateDTO wine);

    /**
     * @param id of the desired wine
     * @return WineDTO with the id
     */
    WineDTO getWineById(Long id);

    /**
     * @param feedbackID of the feedback to be added
     * @param wineID of the wine for the addition
     */
    void addFeedback(Long feedbackID, Long wineID);

    /**
     * @param feedbackID of the feedback to removed
     * @param wineID of the wine for the removal
     */
    void removeFeedback(Long feedbackID, Long wineID);

    /**
     * @param wineID of the wine to be deleted
     */
    void deleteWine(Long wineID);

    /**
     * @return list of all wines
     */
    List<WineDTO> getAllWines();

    /**
     * @param ingredient to be looked for
     * @return list of wines that contain the ingredient
     */
    List<WineDTO> getWinesByIngredient(Ingredient ingredient);

    /**
     * @param taste to be looked for
     * @return list of wines with the tast
     */
    List<WineDTO> getWinesByTaste(Taste taste);

    /**
     * * @param color to be looked for
     * @return list of wines with the color
     */
    List<WineDTO> getWinesByColor(WineColor color);
}
