package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.entities.Wine;

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
    void create(Wine wine);

    /**
     * @return list of all wines
     */
    List<Wine> findAll();

    /**
     *
     * @param id of desired wine
     * @return fond wine
     */
    Wine findById(Long id);

    /**
     * @param name of desired wine
     * @return found wine
     */
    Wine findByName(String name);

    /**
     * @param wine to be removed
     */
    void remove(Wine wine);
}
