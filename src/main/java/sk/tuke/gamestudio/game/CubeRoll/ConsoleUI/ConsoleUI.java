package main.java.sk.tuke.gamestudio.game.CubeRoll.ConsoleUI;

import main.java.sk.tuke.gamestudio.game.CubeRoll.core.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class ConsoleUI {

    private GameField field;
    private Cube cube;

    public ConsoleUI(GameField gameField, Cube cube) {

        this.field = gameField;
        this.cube = cube;
    }
    public void play()
    {
        JFrame myJFrame = new JFrame();
        myJFrame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    if(cube.getyPos() > 0)
                    {
                        cube.rollUp();
                    }
                }
                else if (keyCode == KeyEvent.VK_DOWN) {
                    cube.rollDown();
                }
                else if (keyCode == KeyEvent.VK_LEFT) {
                    cube.rollLeft();
                }
                else if (keyCode == KeyEvent.VK_RIGHT) {
                    cube.rollRight();
                }
                show(cube);
            }
        });
        myJFrame.setVisible(true);

        while (true)
        {
            do {

            } while (field.getState() == GameState.PLAYING);
        }


    }

    public void show(Cube cube)
    {
        for (int i = 0; i < this.field.getMapSize(); i++) {
            for (int j = 0; j < this.field.getMapSize(); j++) {
                if(this.cube.getxPos() == j && this.cube.getyPos() == i)
                {
                    System.out.print("|" + cube.getTopSide());
                }
                else
                {
                    if (this.field.getTiles()[i][j] instanceof RegularTile)
                    {
                        System.out.print("|█");
                    }
                    else if (this.field.getTiles()[i][j] instanceof FinishTile)
                    {
                        System.out.print("|f");
                    }
                    else
                    {
                        System.out.print("| ");
                    }
                }
            }
            System.out.println("|");
        }
        System.out.println();
        cube.printCube();
    }

//
}
