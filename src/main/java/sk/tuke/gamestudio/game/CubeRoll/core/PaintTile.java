package main.java.sk.tuke.gamestudio.game.CubeRoll.core;

import java.awt.*;

public class PaintTile extends Tile{

    protected Color color;

    public PaintTile(Color color) {
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }
}
