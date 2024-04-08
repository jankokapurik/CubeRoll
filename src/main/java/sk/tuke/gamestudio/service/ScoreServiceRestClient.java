package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ScoreServiceRestClient implements ScoreService{

    private final String url = "http://localhost:8080/api/score";

    @Autowired
    private RestTemplate restTemplate;
    //private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url, score, Score.class);
    }

    @Override
    public List<Score> getTopScores(String gameName) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForEntity(url + "/" + gameName, Score[].class).getBody()));
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }

    @Override
    public Score getScore(String gameName, String playerName) {
        ResponseEntity<Score> score = restTemplate.getForEntity(url + "/" + gameName + "/" + playerName, Score.class);
        if(score.getBody() == null) {
            return null;
        } else {
            return score.getBody();
        }
    }
}
