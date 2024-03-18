package sk.tuke.gamestudio.game.CubeRoll.core;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Cube {
    private Map<String, Color> cubeSides = new HashMap<>();
    boolean wasExecuted;

    public Cube()
    {
        this.cubeSides.put("top", Color.white);
        this.cubeSides.put("bottom", Color.white);
        this.cubeSides.put("right", Color.white);
        this.cubeSides.put("left", Color.white);
        this.cubeSides.put("up", Color.white);
        this.cubeSides.put("down", Color.white);
        this.wasExecuted = false;
    }

    public Map<String, Color> getCubeSides() {
        return cubeSides;
    }

    public Color getTopSide() {
        return cubeSides.get("top");
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
