package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Grape;
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
    void create(Grape grape);

    /**
     * @return list of all grapes
     */
    List<Grape> findAll();

    /**
     * @param id of desired grape
     * @return found grape
     */
    Grape findById(Long id);

    /**
     * @param name of desired grape
     * @return found grape
     */
    Grape findByName(String name);

    /**
     * @param color of desired grape
     * @return list of found grapes
     */
    List<Grape> findByColor(GrapeColor color);

    /**
     * @param grape to be deleted
     */
    void remove(Grape grape);

    /**
     * @param grape to be updated
     */
    void update(Grape grape);
}
