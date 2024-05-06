package sk.tuke.gamestudio.server.webservice;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.game.CubeRoll.core.*;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;

@CrossOrigin
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
    public String newGame(@PathVariable String level) throws IOException {
        this.field = new GameField(Integer.parseInt(level), new Cube());
        return this.convertFieldToJson();
    }
    @GetMapping("/right")
    public String move_right() {
        this.field.moveHorizontally(1);
        return this.convertFieldToJson();
    }

    @GetMapping("/left")
    public String move_left() {
        this.field.moveHorizontally(-1);
        return this.convertFieldToJson();
    }

    @GetMapping("/up")
    public String move_up() {
        this.field.moveVertically(-1);
        return this.convertFieldToJson();
    }

    @GetMapping("/down")
    public String move_down() {
        this.field.moveVertically(1);
        return this.convertFieldToJson();
    }

    private String convertFieldToJson() {
        JSONObject gameFieldJson = new JSONObject();
        gameFieldJson.put("mapRows", this.field.getMapRows());
        gameFieldJson.put("mapCols", this.field.getMapCols());
        gameFieldJson.put("cubeXPos", this.field.getCubeXPos());
        gameFieldJson.put("cubeYPos", this.field.getCubeYPos());
        gameFieldJson.put("min_moves", this.field.getMin_moves());
        gameFieldJson.put("moves", this.field.getMoves());
        gameFieldJson.put("state", this.field.getState());

        JSONArray tilesArray = new JSONArray();

        for (int row = 0; row < this.field.getMapRows(); row++) {
            JSONArray rowArray = new JSONArray();
            Tile[][] tiles = this.field.getTiles();
            for (int col = 0; col < this.field.getMapCols(); col++) {
                JSONObject tileJson = getJsonObject(tiles, row, col);
                rowArray.put(tileJson);
            }
            tilesArray.put(rowArray);
        }
        gameFieldJson.put("tiles", tilesArray);

        JSONObject cubeJson = new JSONObject();

        cubeJson.put("top", formatColor(this.field.getCube().getCubeSides().get("top")));
        cubeJson.put("bottom", formatColor(this.field.getCube().getCubeSides().get("bottom")));
        cubeJson.put("right", formatColor(this.field.getCube().getCubeSides().get("right")));
        cubeJson.put("left", formatColor(this.field.getCube().getCubeSides().get("left")));
        cubeJson.put("up", formatColor(this.field.getCube().getCubeSides().get("up")));
        cubeJson.put("down", formatColor(this.field.getCube().getCubeSides().get("down")));

        gameFieldJson.put("cube", cubeJson);

        return gameFieldJson.toString();
    }

    private JSONObject getJsonObject(Tile[][] tiles, int row, int col) {
        JSONObject tileJson = new JSONObject();
        if (tiles[row][col] instanceof FinishTile)
        {
            tileJson.put("type", "finish");
        }
        else if (this.field.getTiles()[row][col] instanceof PaintTile)
        {
            tileJson.put("type", "paint");
            Color color =  ((PaintTile) tiles[row][col]).getColor();
            String hexColor = formatColor(color);

            tileJson.put("color", hexColor);
        }else if (this.field.getTiles()[row][col] instanceof ColorTile)
        {
            tileJson.put("type", "color");
            Color color =  ((ColorTile) tiles[row][col]).getColor();
            String hexColor = formatColor(color);
            tileJson.put("color", hexColor);
        } else if (this.field.getTiles()[row][col] instanceof TeleportTile) {
            tileJson.put("type", "teleport");
        } else if (this.field.getTiles()[row][col] instanceof ButtonTile) {
            tileJson.put("type", "button");
        }else if (this.field.getTiles()[row][col] != null) {
            tileJson.put("type", "Regular");
        } else
        {
            tileJson.put("type", "null");
        }
        return tileJson;
    }
    private String formatColor(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
