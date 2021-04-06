package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Feedback;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 *
 * @author Oto Fargas
 */
@Repository
public class FeedbackDaoImpl implements FeedbackDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Feedback feedback) {
        em.persist(feedback);
    }

    @Override
    public List<Feedback> findAll() {
        return em.createQuery("select f from Feedback f", Feedback.class).getResultList();
    }

    @Override
    public Feedback findById(Long id) {
        return em.find(Feedback.class, id);
    }

    @Override
    public List<Feedback> findByAuthor(String author) {
        return em.createQuery("select f from Feedback f where author = :author", Feedback.class)
                .setParameter("author", author)
                .getResultList();
    }

    @Override
    public void update(Feedback feedback) {
        em.merge(feedback);
    }

    @Override
    public void remove(Feedback feedback) {
        em.remove(feedback);
    }
}
