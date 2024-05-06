package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.NonUniqueResultException;
import sk.tuke.gamestudio.entity.Rating;
@Transactional
public class RatingServiceJPA implements RatingService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {
        try {
            if(rating.getRating() < 1 || rating.getRating() > 5) throw new RatingException("Rating out of range");
            Rating existingRating = (Rating) this.entityManager.createNamedQuery("Rating.getRating").setParameter("game", rating.getGame()).setParameter("player", rating.getPlayer()).getSingleResult();
            existingRating.setRating(rating.getRating());
            this.entityManager.merge(existingRating);
        } catch (NoResultException e) {
            this.entityManager.persist(rating);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        try {
            Query query = entityManager.createNamedQuery("Rating.getAverageRating")
                    .setParameter("game", game);
            Double averageRating = (Double) query.getSingleResult();
            return averageRating != null ? averageRating : 0.0; // Return 0.0 if no rating found
        } catch (NoResultException e) {
            throw new RatingException("No rating found for the game: " + game, e);
        } catch (Exception e) {
            throw new RatingException("Error retrieving average rating for the game: " + game, e);
        }
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        try {
            Rating rating = (Rating) entityManager.createNamedQuery("Rating.getRating")
                    .setParameter("game", game)
                    .setParameter("player", player)
                    .getSingleResult();
            return rating.getRating();
        } catch (NoResultException e) {
            return -1;
        }
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRatings").executeUpdate();
    }
}
