package sk.tuke.gamestudio.game.CubeRoll.core;

import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaintTileTest {

    @Test
    public void testPaintCubeSide() {

        PaintTile paintTile = new PaintTile(Color.RED);

        Cube cube = new Cube();
        paintTile.paintCubeSide(cube);

        assertEquals(Color.RED, cube.getCubeSides().get("bottom"));
    }
}