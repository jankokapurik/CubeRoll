package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Score;

import java.util.List;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreServiceTest {
    private ScoreServiceJDBC scoreService;

    @BeforeEach
    public void setUp() {
        scoreService = new ScoreServiceJDBC();
    }

    @AfterEach
    public void tearDown() {
        scoreService.reset();
    }

    @Test
    public void testAddScore() {
        Score score = new Score("cuberoll", "Janko", 100, new Date());
        scoreService.addScore(score);
        List<Score> topScores = scoreService.getTopScores("cuberoll");
        assertFalse(topScores.isEmpty());
        assertEquals(1, topScores.size());
//        assertEquals(score, topScores.get(0));
    }


    @Test
    public void testGetTopScores() {
        Score score1 = new Score("cuberoll", "Janko", 100, new Date());
        Score score2 = new Score("cuberoll", "Jozko", 200, new Date());
        scoreService.addScore(score1);
        scoreService.addScore(score2);
        List<Score> topScores = scoreService.getTopScores("cuberoll");
        assertFalse(topScores.isEmpty());
//        assertEquals(1, topScores.size());
        assertEquals(score2, topScores.get(1));
        assertEquals(score2, topScores.get(0));
    }


    @Test
    public void testReset() {
        Score score = new Score("cuberoll", "Veronika", 100, new Date());
        scoreService.addScore(score);
        scoreService.reset();
        List<Score> topScores = scoreService.getTopScores("cuberoll");
        assertTrue(topScores.isEmpty());
    }

    @Test
    public void testUpdatePlayer() {
        Score score = new Score("cuberoll", "janko", 100, new Date());
        scoreService.addScore(score);
        assertEquals(200, scoreService.getScore("cuberoll", "janko"));
    }

    @Test
    public void testGetScore() {
        Score score = new Score("cuberoll", "Pata", 100, new Date());
        scoreService.addScore(score);
        assertEquals(100, scoreService.getScore("cuberoll", "Pata"));
    }
}
