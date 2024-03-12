package main.java.sk.tuke.gamestudio.game.CubeRoll.core;

public class GameField {
    private final int MAP_SIZE = 10;
    private final Tile[][] tiles = new Tile[this.MAP_SIZE][this.MAP_SIZE];
    private Cube cube;
    private GameState state = GameState.PLAYING;

    public GameField(int rowCount, int level)
    {
        loadMap(level);
    }

    public int getMapSize() {
        return MAP_SIZE;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public GameState getState() {
        return this.state;
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
                }
                else if (i == 5)
                {
                    tiles[i][j] = new RegularTile();
                }
                else {
                    tiles[i][j] = null;
                }
            }
        }
    }
}
