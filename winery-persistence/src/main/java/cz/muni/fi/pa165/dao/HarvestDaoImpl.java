package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Harvest;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of DAO interface Harvest
 *
 * @author Oto Fargas
 */
@Repository
public class HarvestDaoImpl implements HarvestDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Harvest harvest) {
        em.persist(harvest);
    }

    @Override
    public List<Harvest> findAll() {
        return em.createQuery("select h from Harvest h", Harvest.class).getResultList();
    }

    @Override
    public Harvest findById(Long id) {
        return em.find(Harvest.class, id);
    }

    @Override
    public List<Harvest> findByYear(Integer harvestYear) {
        return em.createQuery("select h from Harvest h where harvestYear=:harvestYear", Harvest.class)
                .setParameter("harvestYear", harvestYear)
                .getResultList();
    }

    @Override
    public void update(Harvest harvest) {
        em.merge(harvest);
    }

    @Override
    public void remove(Harvest harvest) {
        em.remove(harvest);
    }
}
