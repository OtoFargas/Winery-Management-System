package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Harvest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 *
 * @author Oto Fargas
 */
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
    public List<Harvest> findByYear(Integer year) {
        return em.createQuery("select h from Harvest h where year=:year", Harvest.class)
                .setParameter("year", year)
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
