package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Wine;
import java.util.List;


/**
 * Representation of DAO for Wine
 *
 * @author Vladimir Visnovsky
 */
public interface WineDao {
    /**
     * Creates persistent representation of wine in database
     *
     * @param wine to be created
     */
    void create(Wine wine);

    /**
     * Finds all wines in database
     *
     * @return list of all wines
     */
    List<Wine> findAll();

    /**
     * Finds wine by id
     *
     * @param id of desired wine
     * @return found wine
     */
    Wine findById(Long id);

    /**
     * Finds wine by name
     *
     * @param name of desired wine
     * @return found wine
     */
    Wine findByName(String name);

    /**
     * Removes wine from database
     *
     * @param wine to be deleted
     */
    void remove(Wine wine);

    /**
     * Updates Wine entity
     *
     * @param wine to be updated
     */
    void update(Wine wine);
}
