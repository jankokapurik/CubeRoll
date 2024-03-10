import java.awt.event.KeyEvent;
import java.util.Scanner;

public class GameField {

    private int rowCount;
    private int colCount;
    private Cube cube;
    private int x;
    private int y;
    public GameField(int rowCount, int colCount)
    {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.cube = new Cube();
    }

    public void printField()
    {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if(x == j && y == i)
                {
                    System.out.print("|" + cube.getTopSide());
                }
                else
                {
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }
        System.out.println();
        cube.printCube();
    }

    public void moveCube(KeyEvent evt)
    {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                y += 1;
                break;
            case KeyEvent.VK_UP:
                y -= 1;
                break;
            case KeyEvent.VK_LEFT:
                x -= 1;
                break;
            case KeyEvent.VK_RIGHT:
                x += 1;
                break;
        }
    }
    }
}
