package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/api/rating")
public class RatingServiceRest {


    @Autowired
    private RatingService ratingService;

    @GetMapping("/{game}")
    public int getAverageRating(@PathVariable String game) {
        return ratingService.getAverageRating(game);
    }

    @GetMapping("/{game}/{user}")
    public int getRating(@PathVariable String game, @PathVariable String user) {
        return ratingService.getRating(game, user);
    }

    @PostMapping
    public void SetRating(@RequestBody CommentRequestDto requestDto) {
        ratingService.setRating(new Rating("cuberoll", requestDto.getUser(), requestDto.getRating(),  Date.valueOf(LocalDate.now())));
    }
}
