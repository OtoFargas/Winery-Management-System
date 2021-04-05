package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Harvest;

import java.util.List;

/**
 * Representation of DAO for harvest
 *
 * @author Oto Fargas
 */
public interface HarvestDao {

    /**
     * Creates persistent representation of harvest in database
     *
     * @param harvest to be created
     */
    void create(Harvest harvest);

    /**
     * Finds all harvests in database
     *
     * @return list of all harvests
     */
    List<Harvest> findAll();

    /**
     * Finds harvest by id
     *
     * @param id of desired harvest
     * @return found harvest
     */
    Harvest findById(Long id);

    /**
     * Finds harvests by year
     *
     * @param year of desired harvest
     * @return list of found harvests
     */
    List<Harvest> findByYear(Integer year);

    /**
     * Removes harvest from database
     *
     * @param harvest to be deleted
     */
    void remove(Harvest harvest);
}
