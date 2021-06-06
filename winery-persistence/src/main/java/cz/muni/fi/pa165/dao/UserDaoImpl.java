package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public User findUserByUserName(String userName) {
        if (userName == null || userName.isEmpty())
            throw new IllegalArgumentException("Cannot search for null user name.");
        System.out.println(userName);
        try {
        return em.createQuery("select u from User u where userName=:userName", User.class)
                .setParameter("userName", userName)
                .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
}
