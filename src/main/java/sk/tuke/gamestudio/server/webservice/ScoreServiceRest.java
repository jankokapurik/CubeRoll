package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreServiceRest {

    @Autowired
    private ScoreService scoreService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{game}")
    public List<Score> getTopScores(@PathVariable String game) {
        return scoreService.getTopScores(game);
    }

    @GetMapping("/{game}/{player}")
    public Score getScore(@PathVariable String game, @PathVariable String player) {
        return scoreService.getScore(game, player);
    }

    @PostMapping
    public void addScore(@RequestBody CommentRequestDto requestDto) {
        scoreService.addScore(new Score("cuberoll", requestDto.getUser(), requestDto.getRating(),  Date.valueOf(LocalDate.now())));
    }
}
