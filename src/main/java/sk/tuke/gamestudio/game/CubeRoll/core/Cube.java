package main.java.sk.tuke.gamestudio.game.CubeRoll.core;

import java.awt.Color;

public class Cube {
    private Color bottom;
    private Color top = Color.red;
    private Color left;
    private Color right;
    private Color up;
    private Color down;
    private int xPos;
    private int yPos;
    private GameField field;
    public Cube(int startX, int startY, GameField field)
    {
        this.xPos = startX;
        this.yPos = startY;
        this.field = field;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void printCube()
    {
        System.out.println("  " + printSide(up));
        System.out.println("  ^");
        System.out.println(printSide(left) + "<" + printSide(top) + ">" + printSide(right));
        System.out.println("  ↓");
        System.out.println("  " + printSide(down));
    }

    public String printSide(Color side)
    {
        if(side == null)
        {
            return "none";
        }
        else
        {
            return side.toString();
        }
    }


    public Color getTopSide() {
        return top;
    }
    public void moveHorizontally(int move)
    {
        if(move == 1 && xPos < this.field.getMapSize() - 1)
        {
            xPos++;
        }
        if(move == -1 && xPos > 1)
        {
            xPos--;
        }
    }

    public void moveVertically(int move)
    {
        if(move == 1 && yPos < this.field.getMapSize() - 1)
        {
            yPos++;
        }
        if(move == -1 && yPos > 1)
        {
            yPos--;
        }
    }

    public void rollLeft()
    {
        Color buffer = this.top;
        this.top = this.right;
        this.right = this.bottom;
        this.bottom = this.left;
        this.left = buffer;
        moveHorizontally(-1);
    }

    public void rollRight()
    {
        Color buffer = this.top;
        this.top = this.left;
        this.left = this.bottom;
        this.bottom = this.right;
        this.right = buffer;
        moveHorizontally(1);
    }

    public void rollUp()
    {
        Color buffer = this.top;
        this.top = this.down;
        this.down = this.bottom;
        this.bottom = this.up;
        this.up= buffer;
        moveVertically(-1);
    }


    public void rollDown()
    {
        Color buffer = this.top;
        this.top = this.up;
        this.up = this.bottom;
        this.bottom = this.down;
        this.down = buffer;
        moveVertically(1);
    }

}
