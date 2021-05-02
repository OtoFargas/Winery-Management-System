package cz.muni.fi.pa165.service;


import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.Wine;

import java.util.List;

/**
 * An interface that defines a service access to the Wine entity.
 *
 * @author Oto Fargas
 */
public interface WineService {

    /**
     * @param wine to be created
     */
    void createWine(Wine wine);

    /**
     * @return list of all wines
     */
    List<Wine> findAllWines();

    /**
     * @param id of desired wine
     * @return fond wine
     */
    Wine findWineById(Long id);

    /**
     * @param name of desired wine
     * @return found wine
     */
    Wine findWineByName(String name);

    /**
     * @param wine to be updated
     */
    void updateWine(Wine wine);

    /**
     * @param wine to be removed
     */
    void removeWine(Wine wine);

    /**
     * @param wine to be sold
     * @param amount to be sold
     */
    void sellWine(Wine wine, Integer amount);

    /**
     * @param wine to be added to
     * @param feedback to be added
     */
    void addFeedbackToWine(Wine wine, Feedback feedback);

    /**
     * @param wine to be added to
     * @param harvest to be added
     */
    void addHarvestToWine(Wine wine, Harvest harvest);
}
