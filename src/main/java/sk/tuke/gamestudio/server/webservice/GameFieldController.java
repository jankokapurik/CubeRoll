package sk.tuke.gamestudio.server.webservice;

import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.game.CubeRoll.core.Cube;
import sk.tuke.gamestudio.game.CubeRoll.core.GameField;

import java.io.IOException;

@RestController
@RequestMapping("/api/cuberoll/field")
public class GameFieldController {

    private GameField field;

    @GetMapping()
    public GameField getField()
    {
        return this.field;
    }
    @GetMapping("/newGame/{level}")
    public GameField newGame(@PathVariable String level) throws IOException {
        this.field = new GameField(Integer.parseInt(level), new Cube());
        System.out.println(this.field);
        return this.field;
    }

}
