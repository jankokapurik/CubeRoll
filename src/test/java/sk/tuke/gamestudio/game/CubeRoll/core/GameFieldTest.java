package sk.tuke.gamestudio.game.CubeRoll.core;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GameFieldTest {

    @Test
    public void testInitialGameState() throws IOException {
        Cube cube = new Cube();
        GameField gameField = new GameField(1, cube);
        assertEquals(0, gameField.getMoves());
        assertEquals(GameState.PLAYING, gameField.getState());
    }

    @Test
    public void testMoveHorizontally() throws IOException {
        Cube cube = new Cube();
        GameField gameField = new GameField(1, cube);

        int xpos = gameField.getCubeXPos();
        int ypos = gameField.getCubeYPos();

        gameField.moveHorizontally(-1);

        assertEquals(xpos - 1, gameField.getCubeXPos());
        assertEquals(ypos, gameField.getCubeYPos());
        assertEquals(1, gameField.getMoves());
        assertEquals(GameState.PLAYING, gameField.getState());
    }

    @Test
    public void testMoveToColorTile() throws IOException {
        Cube cube = new Cube();
        GameField gameField = new GameField(1, cube);

        int xpos = gameField.getCubeXPos();
        gameField.moveHorizontally(-1);

        assertEquals(xpos - 1, gameField.getCubeXPos());
        assertEquals(1, gameField.getMoves());
        assertEquals(GameState.PLAYING, gameField.getState());



        gameField.moveHorizontally(-1);

    }

    @Test
    public void testRollCube() throws IOException {
        Cube cube = new Cube();
        GameField gameField = new GameField(1, cube);

        cube.paintSide(Color.BLUE);
        gameField.moveHorizontally(-1);

        assertEquals(Color.WHITE, gameField.getCube().getTopSide());
        assertEquals(Color.BLUE, gameField.getCube().getCubeSides().get("right"));
        assertEquals(Color.WHITE, gameField.getCube().getCubeSides().get("left"));
        assertEquals(Color.WHITE, gameField.getCube().getCubeSides().get("up"));
        assertEquals(Color.WHITE, gameField.getCube().getCubeSides().get("down"));
        assertEquals(Color.WHITE, gameField.getCube().getCubeSides().get("bottom"));
    }

    @Test
    public void testMoveToFinishTile() throws IOException {
        Cube cube = new Cube();
        GameField gameField = new GameField(1, cube);

        gameField.setCubeXPos(2);
        gameField.setCubeYPos(1);


        assertEquals(GameState.PLAYING, gameField.getState());
        gameField.moveVertically(-1);
        assertEquals(GameState.SOLVED, gameField.getState());
    }
}
