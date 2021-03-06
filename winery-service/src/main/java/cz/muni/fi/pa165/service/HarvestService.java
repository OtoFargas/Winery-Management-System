package cz.muni.fi.pa165.service;


import cz.muni.fi.pa165.entities.Harvest;

import java.util.List;

/**
 * An interface that defines a service access to the Harvest entity.
 *
 * @author Lukáš Fudor
 */

public interface HarvestService {

    /**
     * @param harvest to be created
     */
    void createHarvest(Harvest harvest);

    /**
     * @return list of all harvests
     */
    List<Harvest> findAllHarvests();

    /**
     * @param id of desired harvest
     * @return found harvest
     */
    Harvest findHarvestById(Long id);

    /**
     * @param year of desired harvest
     * @return list of found harvests
     */
    List<Harvest> findHarvestByYear(Integer year);

    /**
     * @param harvest to be updated
     */
    void updateHarvest(Harvest harvest);

    /**
     * @param harvest to be deleted
     */
    void removeHarvest(Harvest harvest);
}
