public class ConsoleUI {

    public void play()
    {
        GameField gameField = new GameField(5, 5);


        gameField.printField();
        while (true)
        {
            gameField.moveCube();
            gameField.printField();
        }
    }
}
