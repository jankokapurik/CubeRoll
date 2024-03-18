package sk.tuke.gamestudio.game.CubeRoll.core;

public class ButtonTile extends Tile{

    private int disappearingTileXPos;
    private int disappearingTileYPos;
    public ButtonTile(int disappearingTileYPos, int diappearingTileXPos)
    {
        this.disappearingTileXPos = diappearingTileXPos;
        this.disappearingTileYPos = disappearingTileYPos;
    }

    public Tile[][] toggleTile(Tile[][] tiles, Cube cube) {
        if(cube.wasExecuted) return tiles;
        if(tiles[disappearingTileYPos][disappearingTileXPos] == null)
        {
            tiles[disappearingTileYPos][disappearingTileXPos] = new Tile();
        }
        else
        {
            tiles[disappearingTileYPos][disappearingTileXPos] = null;
        }
        cube.wasExecuted = true;
        return tiles;
    }
}
