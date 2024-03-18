package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Rating;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RatingServiceTest {

    private RatingServiceJDBC ratingService;

    @BeforeEach
    public void setUp() {
        ratingService = new RatingServiceJDBC();
    }

    @AfterEach
    public void tearDown() {
        ratingService.reset();
    }

    @Test
    public void testSetRatingAndGetRating() {
        Rating rating = new Rating("cuberoll", "janko", 5, new Date());
        ratingService.setRating(rating);
        assertEquals(5, ratingService.getRating("cuberoll", "janko"));
    }

    @Test
    public void testGetAverageRating() {
        Rating rating1 = new Rating("cuberoll", "Lucka", 3, new Date());
        Rating rating2 = new Rating("cuberoll", "Linda", 4, new Date());
        Rating rating3 = new Rating("cuberoll", "Simona", 5, new Date());
        ratingService.setRating(rating1);
        ratingService.setRating(rating2);
        ratingService.setRating(rating3);
        assertEquals(4, ratingService.getAverageRating("cuberoll"));
    }

    @Test
    public void testReset() {
        Rating rating = new Rating("cuberoll", "Janko", 5, new Date());
        ratingService.setRating(rating);
        ratingService.reset();
        assertThrows(RatingException.class, () -> ratingService.getRating("cuberoll", "Janko"));
    }
}