package sk.tuke.gamestudio.game.CubeRoll.core;

public class ButtonTile extends Tile{

    private int disappearingTileXPos;
    private int disappearingTileYPos;
    public ButtonTile(int disappearingTileYPos, int diappearingTileXPos)
    {
        this.disappearingTileXPos = diappearingTileXPos;
        this.disappearingTileYPos = disappearingTileYPos;
    }

    public Tile[][] toggleTile(Tile[][] tiles) {
        if(tiles[disappearingTileYPos][disappearingTileXPos] == null)
        {
            tiles[disappearingTileYPos][disappearingTileXPos] = new Tile();
        }
        else
        {
            tiles[disappearingTileYPos][disappearingTileXPos] = null;
        }
        return tiles;
    }
}
