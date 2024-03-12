package main.java.sk.tuke.gamestudio.game.CubeRoll;

import main.java.sk.tuke.gamestudio.game.CubeRoll.ConsoleUI.ConsoleUI;
import main.java.sk.tuke.gamestudio.game.CubeRoll.core.Cube;
import main.java.sk.tuke.gamestudio.game.CubeRoll.core.GameField;

public class CubeRoll {

    public static void main(String[] args) {
        GameField field = new GameField(10, 1);
        Cube cube = new Cube(0,0, field);

        ConsoleUI consoleUI = new ConsoleUI(field, cube);
        consoleUI.play();
    }
}
