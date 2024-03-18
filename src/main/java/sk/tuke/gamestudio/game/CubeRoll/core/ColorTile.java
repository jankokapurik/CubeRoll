package sk.tuke.gamestudio.game.CubeRoll.core;

import java.awt.Color;

public class ColorTile extends Tile{

    protected Color color;
    public ColorTile(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
