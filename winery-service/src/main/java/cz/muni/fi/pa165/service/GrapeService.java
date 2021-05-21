package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;

import java.util.List;

/**
 * An interface that defines a service access to the Grape entity.
 *
 * @author Lukáš Fudor
 */

public interface GrapeService {

    /**
     * @param grape to be created
     */
    void createGrape(Grape grape);

    /**
     * @return list of all grapes
     */
    List<Grape> findAllGrapes();

    /**
     * @param id of desired grape
     * @return found grape
     */
    Grape findGrapeById(Long id);

    /**
     * @param name of desired grape
     * @return found grape
     */
    Grape findGrapeByName(String name);

    /**
     * @param color of desired grape
     * @return list of found grapes
     */
    List<Grape> findGrapeByColor(GrapeColor color);

    /**
     * @param grape to be deleted
     */
    void removeGrape(Grape grape);

    /**
     * @param grape to be updated
     */
    void updateGrape(Grape grape);

    /**
     * @param grape to be added to
     * @param harvest to be added
     */
    void addHarvestToGrape(Grape grape, Harvest harvest);

    /**
     * Adds a disease to a grape
     *
     * @param grape to be added to
     * @param disease to be added
     */
    public void addDisease(Grape grape, Disease disease);

    /**
     * Cures disease of grape
     *
     * @param grape to be cured
     * @param disease to be cured
     */
    void cureDisease(Grape grape, Disease disease);

    /**
     * @param grape to be cured
     */
    void cureAllDiseases(Grape grape);

    /**
     * @param grape to be changed
     * @param quantity to be set
     */
    void changeQuantity(Grape grape, Integer quantity);
}
