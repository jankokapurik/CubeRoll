package sk.tuke.gamestudio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;

import java.util.Objects;

public class RatingServiceRestClient implements RatingService {

    private final String url = "http://localhost:8080/api/rating";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void setRating(Rating rating)  {
        restTemplate.postForEntity(url, rating, Rating.class);
    }

    @Override
    public int getAverageRating(String game) {
        try {
            return Objects.requireNonNull(restTemplate.getForObject(url + "/" + game, Integer.class));
        } catch (Exception e) {
            throw new RatingException("Failed to retrieve average rating for the game: " + game, e);
        }
    }

    @Override
    public int getRating(String game, String player) {
        try {
            int rating = Objects.requireNonNull(restTemplate.getForObject(url + "/" + game + "/" + player, Integer.class));
            return rating;
        } catch (Exception e) {

            return -1;
        }
    }
    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");

    }
}
