package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.entities.Wine;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of DAO interface User
 *
 * @author Oto Fargas
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findUserByEmail(String email) {
        return em.createQuery("select u from User u where email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
}