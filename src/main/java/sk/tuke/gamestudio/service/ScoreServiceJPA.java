package sk.tuke.gamestudio.service;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Score;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


@Transactional
public class ScoreServiceJPA implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) throws ScoreException {
        Score existingScore = getScore(score.getGame(), score.getPlayer());
        System.out.println(existingScore);
        if (existingScore != null) {
            existingScore.setPoints(existingScore.getPoints() + score.getPoints());
            entityManager.merge(existingScore);
        } else {
            entityManager.persist(score);
        }
    }

    @Override
    public List getTopScores(String game) throws ScoreException {
        return entityManager.createNamedQuery("Score.getTopScores")
                .setParameter("game", game).setMaxResults(10).getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNamedQuery("Score.resetScores").executeUpdate();

    }

    @Override
    public Score getScore(String game, String player) {
        try {
            return (Score) entityManager.createNamedQuery("Score.getScore")
                    .setParameter("game", game)
                    .setParameter("player", player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}