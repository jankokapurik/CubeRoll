package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Score;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreServiceTest {
    private ScoreService scoreService = new ScoreServiceJDBC();

    @Test
    public void reset() {
        scoreService.reset();
        assertEquals(0, scoreService.getTopScores("mines").size());
    }

    @Test
    public void addScore() {
        var date = new Date();
        var score = new Score("Jaro", "mines", 120, date);
        scoreService.reset();
        scoreService.addScore(score);
        var scores = scoreService.getTopScores("mines");
        assertEquals(1, scores.size());
//        assertEquals("Jaro", scores.get(0).getPlayer());
//        assertEquals("mines", scores.get(0).getGame());
//        assertEquals(120, scores.get(0).getValue());
//        assertEquals(date, scores.get(0).getPlayedAt());
        assertEquals(score, scores.get(0));
    }

    @Test
    public void getTopScores() {
        var date = new Date();
        scoreService.reset();
        scoreService.addScore(new Score("Jaro", "mines", 200, date));
        scoreService.addScore(new Score("Jozo", "mines", 250, date));
        scoreService.addScore(new Score("Anca", "mines", 150, date));

        var scores = scoreService.getTopScores("mines");
        assertEquals(3, scores.size());
        assertEquals("Jozo", scores.get(0).getPlayer());
        assertEquals(250, scores.get(0).getPoints());
        assertEquals("Jaro", scores.get(1).getPlayer());
        assertEquals(200, scores.get(1).getPoints());
        assertEquals("Anca", scores.get(2).getPlayer());
        assertEquals(150, scores.get(2).getPoints());
    }
}
