package sk.tuke.gamestudio.game.CubeRoll.core;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameField {
    private int mapRows;
    private int mapCols;
    private Tile[][] tiles;
    private final Cube cube;
    private int cubeXPos;
    private int cubeYPos;
    private int min_moves;
    private int moves;
    private GameState state = GameState.PLAYING;

    public GameField(int level, Cube cube) throws IOException {
        loadLevel(level);
        this.cube = cube;
        this.moves = 0;
    }

    public int getMin_moves() {
        return min_moves;
    }

    public void setCubeXPos(int cubeXPos) {
        this.cubeXPos = cubeXPos;
    }

    public void setCubeYPos(int cubeYPos) {
        this.cubeYPos = cubeYPos;
    }

    public int getMapRows() {
        return this.mapRows;
    }

    public int getMapCols(){ return this.mapCols;}
    public Tile[][] getTiles() {
        return this.tiles;
    }

    public int getCubeXPos() {
        return this.cubeXPos;
    }

    public int getCubeYPos() {
        return this.cubeYPos;
    }

    public GameState getState() {
        return this.state;
    }

    public Cube getCube() {
        return cube;
    }

    public int getMoves() {
        return this.moves;
    }

    public void moveHorizontally(int move) {
        int newX = this.cubeXPos + move;
        if (((move == 1 && this.cubeXPos < mapCols - 1 ) ||
                (move == -1 && this.cubeXPos > 0))
                && this.tiles[cubeYPos][newX] != null)
        {
            if(move == 1) {
                if(cantMoveToTile("right", newX, cubeYPos)) return;
                this.cube.rollRight();
            } else {
                if(cantMoveToTile("left", newX, cubeYPos)) return;
                this.cube.rollLeft();
            }
            this.cube.wasExecuted = false;
            this.cubeXPos = newX;
            this.moves++;
        }
        checkForFunctions();
    }

    public void moveVertically(int move) {
        int newY = cubeYPos + move;
        if (((move == 1 && this.cubeYPos < mapRows - 1) ||
                (move == -1 && this.cubeYPos > 0)) &&
                this.tiles[newY][cubeXPos] != null) {
            if(move == 1) {
                if(cantMoveToTile("down", cubeXPos, newY)) return;
                this.cube.rollDown();
            } else {
                if(cantMoveToTile("up", cubeXPos, newY)) return;
                this.cube.rollUp();
            }
            this.cube.wasExecuted = false;
            this.cubeYPos = newY;
            this.moves++;
        }
        checkForFunctions();
    }

    public boolean cantMoveToTile(String sideToCompare, int xPos, int yPos) {
        if (this.tiles[yPos][xPos] instanceof ColorTile colorTile) {
            return !colorTile.getColor().equals(this.cube.getCubeSides().get(sideToCompare));
        }
        return false;
    }

    private void checkForFunctions() {
        Tile tile = this.tiles[this.cubeYPos][this.cubeXPos];
        isFinish();
        if (tile instanceof PaintTile) {
            ((PaintTile) tile).paintCubeSide(this.cube);
        }
        if (tile instanceof TeleportTile) {
            ((TeleportTile) tile).teleportCube(this);
        }
        if (tile instanceof ButtonTile) {
            this.tiles = ((ButtonTile) tile).toggleTile(this.tiles, this.cube);
            this.cube.wasExecuted = true;
        }

    }

    private void isFinish() {
        if(this.tiles[cubeYPos][cubeXPos] instanceof FinishTile)
        {
            this.state = GameState.SOLVED;
        }
    }
    private void loadLevel(int level) throws IOException {

        String filename = "src/main/resources/level" + level + ".txt";

        try (Scanner scanner = new Scanner(new File(filename))) {
            this.mapRows = Integer.parseInt(scanner.next());
            this.mapCols = Integer.parseInt(scanner.next());
            this.cubeYPos = Integer.parseInt(scanner.next());
            this.cubeXPos = Integer.parseInt(scanner.next());
            this.min_moves = Integer.parseInt(scanner.next());

            this.tiles = new Tile[this.mapRows][this.mapCols];

            for (int row = 0; row < this.mapRows; row++) {
                for (int col = 0; col < this.mapCols; col++) {
                    tiles[row][col] = readTile(scanner);
                }
            }
        }
    }

    private Tile readTile(Scanner scanner)
    {
        String buffer = scanner.next();

        return switch (buffer) {
            case "r" -> new Tile();
            case "b" -> new ButtonTile(Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()));
            case "t" -> new TeleportTile(Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()));
            case "p" -> new PaintTile(parseColor(scanner.next()));
            case "c" -> new ColorTile(parseColor(scanner.next()));
            case "f" -> new FinishTile();
            default -> null;
        };
    }
    private Color parseColor(String colorString) {
        return switch (colorString.toLowerCase()) {
            case "red" -> Color.RED;
            case "blue" -> Color.BLUE;
            case "green" -> Color.GREEN;
            case "white" -> Color.WHITE;
            default -> Color.BLACK;
        };
    }
}
