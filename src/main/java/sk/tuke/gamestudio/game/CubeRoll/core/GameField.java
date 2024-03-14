package main.java.sk.tuke.gamestudio.game.CubeRoll.core;

import java.awt.*;

public class GameField {
    private final int MAP_SIZE = 10;
    private Tile[][] tiles = new Tile[this.MAP_SIZE][this.MAP_SIZE];
    private final Cube cube;
    private int cubeXPos;
    private int cubeYPos;
    private GameState state = GameState.PLAYING;

    public GameField(int rowCount, int level, Cube cube)
    {
        loadMap(level);
        this.cubeXPos = 0;
        this.cubeYPos = 5;
        this.cube = cube;
    }

    public void setCubeXPos(int cubeXPos) {
        this.cubeXPos = cubeXPos;
    }

    public void setCubeYPos(int cubeYPos) {
        this.cubeYPos = cubeYPos;
    }

    public int getMapSize() {
        return MAP_SIZE;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getCubeXPos() {
        return cubeXPos;
    }

    public int getCubeYPos() {
        return cubeYPos;
    }

    public GameState getState() {
        return this.state;
    }

    public void moveHorizontally(int move) {
        int newX = this.cubeXPos + move;
        if (((move == 1 && this.cubeXPos < MAP_SIZE - 1 ) ||
                (move == -1 && this.cubeXPos > 0))
                && this.tiles[cubeYPos][newX] != null)
        {
            if(move == 1) {
                if(canMoveToTile("right", newX, cubeYPos)) return;
                this.cube.rollRight();
            } else {
                if(canMoveToTile("left", newX, cubeYPos)) return;
                this.cube.rollLeft();
            }
            this.cubeXPos = newX;
        }
        checkForFunctions();
    }


    public void moveVertically(int move) {
        int newY = cubeYPos + move;
        if (((move == 1 && this.cubeYPos < MAP_SIZE - 1) ||
                (move == -1 && this.cubeYPos > 0)) &&
                this.tiles[newY][cubeXPos] != null) {
            if(move == 1) {
                if(canMoveToTile("down", cubeXPos, newY)) return;
                this.cube.rollDown();
            } else {
                if(canMoveToTile("up", cubeXPos, newY)) return;
                this.cube.rollUp();
            }
            this.cubeYPos = newY;

        }
        checkForFunctions();
    }
    public boolean canMoveToTile(String sideToCompare, int xPos, int yPos) {
        if (this.tiles[yPos][xPos] instanceof ColorTile) {
            ColorTile colorTile = (ColorTile) this.tiles[yPos][xPos];
            return !colorTile.getColor().equals(this.cube.getCubeSides().get(sideToCompare));
        }
        return false;
    }

    private void checkForFunctions() {
        Tile tile = this.tiles[this.cubeYPos][this.cubeXPos];
        isFinish();
        if (tile instanceof PaintTile) {
            this.cube.paintSide(((PaintTile) this.tiles[this.cubeYPos][cubeXPos]).color);
        }
        if (tile instanceof TeleportTile) {
            ((TeleportTile) tile).teleportCube(this);
        }
        if (tile instanceof ButtonTile) {
            this.tiles = ((ButtonTile) tile).toggleTile(this.tiles);
        }

    }

    private void isFinish() {
        if(this.tiles[cubeYPos][cubeXPos] instanceof FinishTile)
        {
            this.state = GameState.SOLVED;
            System.out.println("YOU WON!");
        }
    }
    private void loadMap(int level)
    {
        for(int i = 0; i < MAP_SIZE; i++)
        {
            for(int j = 0; j < MAP_SIZE; j++)
            {
                if (i == 5 && j == 9)
                {
                    tiles[i][j] = new FinishTile();
                } else if (i == 5 && j == 8) {
                    tiles[i][j] = new ColorTile(Color.red);
                } else if (i == 3 && j == 6) {
                    tiles[i][j] = new PaintTile(Color.red);
                } else if (i == 4 && j == 6) {
                    tiles[i][j] = new Tile();
                }else if (i == 6 && j == 6) {
                        tiles[i][j] = new TeleportTile(1, 4);
                }else if (i == 4 && j == 3) {
                    tiles[i][j] = new ButtonTile(1, 4);
                }else if (i == 3 && j == 1) {
                    tiles[i][j] = new TeleportTile(6, 6);
                } else if (i == 5)
                {
                    tiles[i][j] = new Tile();
                }
                else {
                    tiles[i][j] = null;
                }
            }
        }
    }
}
