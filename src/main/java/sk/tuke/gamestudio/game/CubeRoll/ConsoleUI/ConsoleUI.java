package main.java.sk.tuke.gamestudio.game.CubeRoll.ConsoleUI;

import main.java.sk.tuke.gamestudio.game.CubeRoll.core.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class ConsoleUI {

    public static final String ANSI_RESET = "\u001B[0m";
    private final GameField field;
    private final Cube cube;

    public ConsoleUI(GameField gameField, Cube cube) {

        this.field = gameField;
        this.cube = cube;
    }
    public void play()
    {
        JFrame myJFrame = new JFrame();
        KeyAdapter arrowAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    field.moveVertically(-1);
                }
                else if (keyCode == KeyEvent.VK_DOWN) {
                    field.moveVertically(1);
                }
                else if (keyCode == KeyEvent.VK_LEFT) {
                    field.moveHorizontally(-1);
                }
                else if (keyCode == KeyEvent.VK_RIGHT) {
                    field.moveHorizontally(1);
                }
                if (field.getState() == GameState.SOLVED)
                {
                    myJFrame.dispatchEvent(new WindowEvent(myJFrame, WindowEvent.WINDOW_CLOSING));
                    myJFrame.removeKeyListener(this);
                    return;

                }
                show();
            }
        };
        myJFrame.addKeyListener(arrowAdapter);
        myJFrame.setVisible(true);
    }

    public void show()
    {
        for (int row = 0; row < this.field.getMapSize(); row++) {
            for (int col = 0; col < this.field.getMapSize(); col++) {
                if(this.field.getCubeXPos() == col && this.field.getCubeYPos() == row)
                {
                    System.out.print(" " + printColorfulChar('■', this.cube.getTopSide()));
                }
                else
                {
                   printAdequateTile(row, col);
                }
            }
            System.out.println();
        }
        System.out.println();
        printCube();
    }

    private void printAdequateTile(int row, int col)
    {
        if (this.field.getTiles()[row][col] instanceof FinishTile)
        {
            System.out.print(" ▩");
        }
        else if (this.field.getTiles()[row][col] instanceof PaintTile)
        {
            System.out.print(" " + printColorfulChar('+', ((PaintTile) this.field.getTiles()[row][col]).getColor()));
        }else if (this.field.getTiles()[row][col] instanceof ColorTile)
        {
            System.out.print(" " + printColorfulChar('█', ((ColorTile) this.field.getTiles()[row][col]).getColor()));
        } else if (this.field.getTiles()[row][col] instanceof TeleportTile) {
            System.out.print(" ▣");
        } else if (this.field.getTiles()[row][col] instanceof ButtonTile) {
            System.out.print(" ▢");
        } else if (this.field.getTiles()[row][col] != null)
        {
            System.out.print(" █");
        }
        else
        {
            System.out.print("  ");
        }
    }

    private void printCube()
    {
        System.out.println("    " + printColorfulChar( '■', this.cube.getCubeSides().get("up")));
        System.out.println("    ^");
        System.out.println(printColorfulChar( '■', this.cube.getCubeSides().get("left")) + " < "
                + printColorfulChar( '■', this.cube.getCubeSides().get("top")) + " > "
                + printColorfulChar(  '■', this.cube.getCubeSides().get("right")) +
                "     ↻  " + printColorfulChar('■', this.cube.getCubeSides().get("bottom")));
        System.out.println("    ↓");
        System.out.println("    " + printColorfulChar( '■', this.cube.getCubeSides().get("down")));
        System.out.println(this.field.getState());
    }
    public static String colorToAnsi(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        // Convert RGB to ANSI escape code
        return String.format("\u001B[38;2;%d;%d;%dm", r, g, b);
    }
    public String printColorfulChar(char character, Color side)
    {
       return colorToAnsi(side) + character + ANSI_RESET;
    }
}
