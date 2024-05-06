package sk.tuke.gamestudio.game.CubeRoll.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeleportTile extends Tile{
    private final int DESTINATIONX;
    private final int DESTINATIONY;

    public TeleportTile(int destinationY, int destinationX) {
        this.DESTINATIONX = destinationX;
        this.DESTINATIONY = destinationY;
    }

    public void teleportCube(GameField field)
    {
        Tile destinationTile = field.getTiles()[DESTINATIONY][DESTINATIONX];

        if (destinationTile instanceof TeleportTile &&
                !field.getCube().wasExecuted && (
                (DESTINATIONX > 0 && field.getTiles()[DESTINATIONY][DESTINATIONX - 1] != null) ||
                (DESTINATIONX < field.getMapCols() - 1 && field.getTiles()[DESTINATIONY][DESTINATIONX + 1] != null) ||
                (DESTINATIONY > 0 && field.getTiles()[DESTINATIONY - 1][DESTINATIONX] != null) ||
                (DESTINATIONY < field.getMapRows() - 1 && field.getTiles()[DESTINATIONY + 1][DESTINATIONX] != null)
        ))
        {
            field.setCubeXPos(this.DESTINATIONX);
            field.setCubeYPos(this.DESTINATIONY);
            field.getCube().wasExecuted = true;
        }
    }
}
