package main.java.sk.tuke.gamestudio.game.CubeRoll.core;

public class TeleportTile extends Tile{
    private final int DESTINATIONX;
    private final int DESTINATIONY;

    public TeleportTile(int destinationX, int destinationY) {
        this.DESTINATIONX = destinationX;
        this.DESTINATIONY = destinationY;
    }

    public void teleportCube(GameField field)
    {
        if (field.getTiles()[DESTINATIONY][DESTINATIONX] != null)
        {
            field.setCubeXPos(this.DESTINATIONX);
            field.setCubeYPos(this.DESTINATIONY);
        }
        else {
            System.out.println("Wrong destination positions.");
        }
    }
}
