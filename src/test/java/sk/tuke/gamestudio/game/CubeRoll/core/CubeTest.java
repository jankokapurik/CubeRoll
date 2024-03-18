package sk.tuke.gamestudio.game.CubeRoll.core;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class CubeTest {

    @Test
    public void testCubeFunctionality() {
        Cube cube = new Cube();


        cube.paintSide(Color.RED);
        assertEquals(Color.RED, cube.getCubeSides().get("bottom"));
//        assertEquals(Color.RED, cube.getTopSide());

        cube.rollLeft();
        assertEquals(Color.RED, cube.getCubeSides().get("right"));

        cube.rollLeft();
        cube.rollLeft();
        assertEquals(Color.RED, cube.getCubeSides().get("left"));

        cube.rollRight();
        cube.rollUp();
        assertEquals(Color.RED, cube.getCubeSides().get("up"));

        cube.rollDown();
        assertEquals(Color.RED, cube.getTopSide());

    }
}
