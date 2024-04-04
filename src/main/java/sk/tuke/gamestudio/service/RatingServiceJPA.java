package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Rating;
@Transactional
public class RatingServiceJPA implements RatingService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {
        entityManager.persist(rating);
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        return entityManager.createNamedQuery("Rating.getAverageRating").setParameter("game", game).getFirstResult();

    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return entityManager.createNamedQuery("Rating.getRating").setParameter("game", game).setParameter("player", player).getFirstResult();
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRatings").executeUpdate();
    }
}
