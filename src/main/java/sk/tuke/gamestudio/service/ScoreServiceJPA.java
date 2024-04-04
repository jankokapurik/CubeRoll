package sk.tuke.gamestudio.service;

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
        entityManager.persist(score);
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
    public int getScore(String game, String player) {
        return entityManager.createNamedQuery("Score.getScore").setParameter("game", game).setParameter("player", player).getFirstResult();
    }

    @Override
    public void updatePlayer(Score score) throws ScoreException{
        entityManager.persist(score);
    }
}