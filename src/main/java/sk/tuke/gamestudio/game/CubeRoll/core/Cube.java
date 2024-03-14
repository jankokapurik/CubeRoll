package main.java.sk.tuke.gamestudio.game.CubeRoll.core;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Cube {
    Map<String, Color> cubeSides = new HashMap<>();

    public Map<String, Color> getCubeSides() {
        return cubeSides;
    }

    public Cube()
    {
        cubeSides.put("top", Color.white);
        cubeSides.put("bottom", Color.white);
        cubeSides.put("right", Color.white);
        cubeSides.put("left", Color.white);
        cubeSides.put("up", Color.white);
        cubeSides.put("down", Color.white);
    }

    public Color getTopSide() {
        return cubeSides.get("top");
    }

    public Color getBottomSide() {
        return cubeSides.get("bottom");
    }

    public void paintSide (Color color)
    {
        this.cubeSides.put("bottom", color);
    }

    public void rollLeft()
    {
        Color buffer = this.cubeSides.get("top");
        this.cubeSides.put("top", cubeSides.get("right"));
        this.cubeSides.put("right", this.cubeSides.get("bottom"));
        this.cubeSides.put("bottom", this.cubeSides.get("left"));
        this.cubeSides.put("left", buffer);
    }

    public void rollRight()
    {
        Color buffer = this.cubeSides.get("top");
        this.cubeSides.put("top", this.cubeSides.get("left"));
        this.cubeSides.put("left", this.cubeSides.get("bottom"));
        this.cubeSides.put("bottom", this.cubeSides.get("right"));
        this.cubeSides.put("right", buffer);
    }

    public void rollUp()
    {
        Color buffer = this.cubeSides.get("top");
        this.cubeSides.put("top", this.cubeSides.get("down"));
        this.cubeSides.put("down", this.cubeSides.get("bottom"));
        this.cubeSides.put("bottom", this.cubeSides.get("up"));
        this.cubeSides.put("up", buffer);
    }

    public void rollDown()
    {
        Color buffer = this.cubeSides.get("top");
        this.cubeSides.put("top", this.cubeSides.get("up"));
        this.cubeSides.put("up", this.cubeSides.get("bottom"));
        this.cubeSides.put("bottom", this.cubeSides.get("down"));
        this.cubeSides.put("down", buffer);
    }

}
