package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Wine;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Implementation of DAO interface Wine
 *
 * @author Vladimir Visnovsky
 */
public class WineDaoImpl implements WineDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Wine wine) {
        em.persist(wine);
    }

    @Override
    public List<Wine> findAll() {
        return em.createQuery("select w from Wine w", Wine.class).getResultList();
    }

    @Override
    public Wine findById(Long id) {
        return em.find(Wine.class, id);
    }

    @Override
    public Wine findByName(String name) {
        return em.createQuery("select w from Wine w where name=:name", Wine.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void remove(Wine wine) {
        em.remove(wine);
    }

    @Override
    public void update(Wine wine) {
        em.merge(wine);
    }

    @Override
    public void sell(Wine wine) {
        Integer stocked = wine.getStocked();
        Integer sold = wine.getSold();

        wine.setStocked(++stocked);
        wine.setSold(++sold);
        this.update(wine);
    }
}
