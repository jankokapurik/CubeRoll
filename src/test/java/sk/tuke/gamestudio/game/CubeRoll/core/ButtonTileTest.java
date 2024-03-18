package sk.tuke.gamestudio.game.CubeRoll.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ButtonTileTest {

    @Test
    public void testToggleTile() {

        Cube cube = new Cube();
        Tile[][] tiles = new Tile[2][2];
        ButtonTile buttonTile = new ButtonTile(0, 0);

        tiles = buttonTile.toggleTile(tiles, cube);
        cube.wasExecuted = true;
        assertNotNull(tiles[0][0]);

        tiles = buttonTile.toggleTile(tiles, cube);
        assertNotNull(tiles[0][0]);
    }
}