package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.entities.Wine;

import java.util.List;

/**
 * An interface that defines a service access to the Wine entity.
 *
 *
 */
public interface WineService {

    /**
     *
     */
    void create(Wine wine);

    /**
     *
     */
    List<Wine> findAll();

    /**
     *
     */
    Wine findById(Long id);

    /**
     *
     */
    Wine findByName(String name);

    /**
     *
     */
    void remove(Wine wine);
}
