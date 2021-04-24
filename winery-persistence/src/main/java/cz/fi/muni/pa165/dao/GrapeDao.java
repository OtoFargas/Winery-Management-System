package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entities.Grape;
import cz.fi.muni.pa165.enums.GrapeColor;

import java.util.List;

/**
 * Representation of DAO for Grape
 *
 * @author Lukáš Fudor
 */

public interface GrapeDao {
    /**
     * Creates persistent representation of grape entity in database
     *
     * @param grape to be created
     */
    void create(Grape grape);

    /**
     * Finds all grapes in database
     *
     * @return list of all grapes
     */
    List<Grape> findAll();

    /**
     * Finds grape by id
     *
     * @param id of desired grape
     * @return found grape
     */
    Grape findById(Long id);

    /**
     * Finds grape by name
     *
     * @param name of desired grape
     * @return found grape
     */
    Grape findByName(String name);

    /**
     * Finds grape by name
     *
     * @param color of desired grape
     * @return list of found grapes
     */
    List<Grape> findByColor(GrapeColor color);

    /**
     * Removes grape from database
     *
     * @param grape to be deleted
     */
    void remove(Grape grape);

    /**
     * Updates Grape entity
     *
     * @param grape to be updated
     */
    void update(Grape grape);
}
