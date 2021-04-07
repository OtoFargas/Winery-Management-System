package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entities.Grape;
import cz.fi.muni.pa165.enums.GrapeColor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Implementation of the GrapeDao interface
 *
 * @author Lukáš Fudor
 */
public class GrapeDaoImpl implements GrapeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Grape grape) {
        em.persist(grape);
    }

    @Override
    public List<Grape> findAll() {
        return em.createQuery("select g from Grape g", Grape.class).getResultList();
    }

    @Override
    public Grape findById(Long id) {
        return em.find(Grape.class, id);
    }

    @Override
    public Grape findByName(String name) {
        return em.createQuery("select g from Grape g where name=:name", Grape.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Grape> findByColor(GrapeColor color) {
        return em.createQuery("select g from Grape g where color=:color", Grape.class)
                .setParameter("color", color)
                .getResultList();
    }

    @Override
    public void remove(Grape grape) {
        em.remove(grape);
    }

    @Override
    public void update(Grape grape) {
        em.merge(grape);
    }

}
