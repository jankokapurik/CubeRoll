package sk.tuke.gamestudio.game.CubeRoll.ConsoleUI;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.game.CubeRoll.core.*;
import sk.tuke.gamestudio.service.*;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    public static final String ANSI_RESET = "\u001B[0m";
    private GameField field;
    private Cube cube;
    private final String player_name;
    private final ScoreService scoreService = new ScoreServiceJDBC();
    private final RatingService ratingService = new RatingServiceJDBC();
    private final CommentService commentService = new CommentServiceJDBC();
    private final Scanner scanner;

    private boolean isLevelFinished;

    public ConsoleUI() {

        this.scanner = new Scanner(System.in);
        welcomeText();
        this.player_name = getPlayerName();
    }

    public void play() throws IOException {

        while (true) {
            showMenu();
            String choice = scanner.next();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case "1":
                    startGame();
                    break;
                case "2":
                    rateGame();
                    break;
                case "3":
                    getRating();
                    break;
                case "4":
                    printScores();
                    break;
                case "5":
                    leaveComment();
                    break;
                case "6":
                    getComments();
                    break;
                case "7":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    public void startGame() throws IOException {

        this.isLevelFinished = false;
        this.cube = new Cube();
        int level = chooseLevel();
        this.field = new GameField(level, cube);
        JFrame myJFrame = new JFrame();
        getKeyListener(myJFrame);
        show();
        while(!isLevelFinished)
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void showMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1. Play");
        System.out.println("2. Rate game");
        System.out.println("3. Get your rating");
        System.out.println("4. Hall of fame");
        System.out.println("5. Leave Comment");
        System.out.println("6. View Comments");
        System.out.println("7. Quit");
        System.out.print("Enter your choice: ");
    }

    public void show()
    {
        if(this.cube == null || this.field == null) return;
        for (int row = 0; row < this.field.getMapRows(); row++) {
            for (int col = 0; col < this.field.getMapCols(); col++) {
                if(this.field.getCubeXPos() == col && this.field.getCubeYPos() == row)
                {
                    System.out.print(" " + printColorfulString("■", this.cube.getTopSide()));
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

    private void getKeyListener(JFrame myJFrame)
    {
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
                show();
                if (field.getState() == GameState.SOLVED)
                {
                    System.out.println("==============================================\n" + printColorfulString("YOU WON", Color.green));
                    myJFrame.dispatchEvent(new WindowEvent(myJFrame, WindowEvent.WINDOW_CLOSING));
                    myJFrame.removeKeyListener(this);
                    qToExit();
                    isLevelFinished = true;
                }
            }
        };
        myJFrame.addKeyListener(arrowAdapter);
        myJFrame.setVisible(true);
    }

    private void printAdequateTile(int row, int col)
    {
        if (this.field.getTiles()[row][col] instanceof FinishTile)
        {
            System.out.print(" ▩");
        }
        else if (this.field.getTiles()[row][col] instanceof PaintTile)
        {
            System.out.print(" " + printColorfulString("+", ((PaintTile) this.field.getTiles()[row][col]).getColor()));
        }else if (this.field.getTiles()[row][col] instanceof ColorTile)
        {
            System.out.print(" " + printColorfulString("█", ((ColorTile) this.field.getTiles()[row][col]).getColor()));
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
        System.out.println("    " + printColorfulString("■", this.cube.getCubeSides().get("up")));
        System.out.println("    ^");
        System.out.println(printColorfulString("■", this.cube.getCubeSides().get("left")) + " < "
                + printColorfulString("■", this.cube.getCubeSides().get("top")) + " > "
                + printColorfulString("■", this.cube.getCubeSides().get("right")) +
                "     ↻  " + printColorfulString("■", this.cube.getCubeSides().get("bottom")));
        System.out.println("    ↓");
        System.out.println("    " + printColorfulString("■", this.cube.getCubeSides().get("down")));
        System.out.println("Your moves:" + this.field.getMoves());
    }
    public static String colorToAnsi(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        // Convert RGB to ANSI escape code
        return String.format("\u001B[38;2;%d;%d;%dm", r, g, b);
    }

    public String printColorfulString(String character, Color side)
    {
       return colorToAnsi(side) + character + ANSI_RESET;
    }

    public void welcomeText()
    {
        System.out.println("                        |‾‾|                               ");
        System.out.println("            ____ __   __|  |___   _____      __ _   _____ |‾‾|‾‾|");
        System.out.println("           /  __|  | |  |   _  \\ /_____\\    |  ‾_‾|/  _  \\|  |  |");
        System.out.println("           |  |_|  | |  |  |_)  | \\_____    |  | ‾|  (_)  |  |  |");
        System.out.println("           \\____|\\______|______/ \\_____/    |__|   \\_____/|__|__|");
        System.out.println("Welcome to the game Cube roll!" + ratingService.getAverageRating("cuberoll") + "/5");
    }

    public int chooseLevel()
    {
        System.out.println("\nWhich level would you like to play?" );
        return scanner.nextInt();
    }

    private void printScores() {
        var scores = scoreService.getTopScores("cuberoll");
        System.out.println("===============================================================");
        for (int i = 0; i < scores.size(); i++) {
            var score = scores.get(i);
            System.out.printf("%d. %s %d\n", i + 1, score.getPlayer(), score.getPoints());
        }
        System.out.println("================================================================");
        qToExit();
    }

    private String getPlayerName()
    {
        System.out.println("What is your name?");
        return scanner.next();
    }

    private void rateGame()
    {
        System.out.println("Please enter your rating(1-5): \n");
        int rating_input;
        do {
         rating_input = this.scanner.nextInt();
        } while(rating_input > 5 || rating_input < 1);

        Rating rating = new Rating("cuberoll", this.player_name, rating_input, Date.valueOf(LocalDate.now()));
        this.ratingService.setRating(rating);
        System.out.println("Thank you for rating our game :)");
    }

    private void leaveComment()
    {
        System.out.println("Please enter your comment: \n" );
        String comment_input = this.scanner.nextLine();
        Comment comment = new Comment("cuberoll", this.player_name, comment_input, Date.valueOf(LocalDate.now()));
        commentService.addComment(comment);
        System.out.println("Thank you for commenting :)");
    }

    private void getComments()
    {
        List<Comment> comments = commentService.getComments("cuberoll");
        for (Comment comment : comments)
        {
            System.out.println(comment.getPlayer() + "(" + comment.getCommentedOn() + "): " + comment.getComment() + "\n");
        }
       qToExit();
    }

    private void getRating()
    {
        int rating = ratingService.getRating("cuberoll", this.player_name);
        if(rating == -1)
        {
            System.out.println("You haven't rated the game yet. \n");
        }
        else
        {
            System.out.println("Your rating is " + rating + "\n");
        }
        qToExit();
    }

    private void qToExit()
    {
        System.out.println("Press q to quit: ");
        String input;
        do {
            input = this.scanner.next();
        } while(!input.equalsIgnoreCase("q"));
    }
}
