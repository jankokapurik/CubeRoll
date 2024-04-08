package sk.tuke.gamestudio.game.CubeRoll.ConsoleUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.SpringClient;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
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
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class ConsoleUI {

    public static final String ANSI_RESET = "\u001B[0m";
    private GameField field;
    private Cube cube;
    private final String player_name;

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RestTemplate restTemplate;
    private final Scanner scanner;
    private int level;
    private boolean isLevelFinished;
    private SpringClient springClient;
    public ConsoleUI(SpringClient springClient) {
        this.springClient = springClient;

        this.scanner = new Scanner(System.in);
//        welcomeText();
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
                    if(isServerConnected())
                        rateGame();
                    break;
                case "3":
                    if(isServerConnected())
                        getRating();
                    break;
                case "4":
                    if (isServerConnected())
                        printScores();
                    break;
                case "5":
                    if(isServerConnected())
                        leaveComment();
                    break;
                case "6":
                    if(isServerConnected())
                        getComments();
                    break;
                case "7":
                    help();
                    break;
                case "8":
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
        chooseLevel();


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
        saveScore();
    }

    private void showMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Play");
        System.out.println("2. Rate game");
        System.out.println("3. Get your rating");
        System.out.println("4. Hall of fame");
        System.out.println("5. Leave Comment");
        System.out.println("6. View Comments");
        System.out.println("7. Help");
        System.out.println("8. Quit");
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

    public String printColorfulString(String text, Color side)
    {
       return colorToAnsi(side) + text + ANSI_RESET;
    }

    public void welcomeText()
    {
        System.out.println("/‾‾‾‾‾|       |‾‾|");
        System.out.println("|  |‾‾ __   __|  |___   _____      __ _   _____ |‾‾|‾‾|");
        System.out.println("|  |  |  | |  |   _  \\ /  __ \\    |  ‾_‾|/  _  \\|  |  |");
        System.out.println("|  |__|  | |  |  |_)  |   ___/    |  | ‾|  (_)  |  |  |");
        System.out.println("\\_____|\\______|______/ \\_____|    |__|   \\_____/|__|__|");
        System.out.println("Welcome to the game Cube roll! " + ratingService.getAverageRating("cuberoll") + "/5");
    }

    public void chooseLevel()
    {
        System.out.println("\nWhich level would you like to play? (1-10)" );
        do
        {
            this.level = scanner.nextInt();
        } while (this.level < 1 || this.level > 10);
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

    private void saveScore() {
        if (isServerConnected())
        {
            int newScoreValue = 100 * this.field.getMin_moves() / this.field.getMoves();
            scoreService.addScore(new Score("cuberoll", this.player_name, newScoreValue, Date.valueOf(LocalDate.now())));
        }
        else
        {
            System.out.println("Failed to save Score");
        }
    }

    private void help() {
        System.out.println("================================================");
        System.out.println("You can control the game with arrow keys, the cube will move if possible.\n");
        System.out.println("■ => Cube position on the map, it can have any given color.");
        System.out.println("█ => Regular tile, if it is white. If it is any other color, you have to step \n\ton it only with the side of the cube that matches the color.");
        System.out.println("▢ => Button tile, one tile appears or disappears if you step on it.");
        System.out.println("▣ => Teleport tile, it will move you to the other teleport if you step on it.");
        System.out.println("+ => Paint, it will paint the bottom side of the cube with given color.");
        System.out.println("================================================\n");
        qToExit();
    }

    private boolean isServerConnected()
    {
        boolean isConnected = this.springClient.isServerConnected();

        if(!isConnected)
        {
            System.out.println(printColorfulString("Server is not connected", Color.red));
        }
        return isConnected;
    }
}
