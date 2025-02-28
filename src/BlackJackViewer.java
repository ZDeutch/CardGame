// BlackJack by Zander Deutch

import javax.swing.*;
import java.awt.*;

public class BlackJackViewer extends JFrame {

    // Gives frontend access to backend
    private final BlackJack game;

    // Images for hit and stand button
    private final Image hit;
    private final Image stand;

    // Image to display back of card
    private final Image back;

    // Sets the window size and title size
    public final static int WINDOW_WIDTH = 800;
    public final static int WINDOW_HEIGHT = 800;
    public final static int HEADER_HEIGHT = 40;

    // Determines the background color and size for fonts used
    public final static Color BACKGROUND = new Color(51, 99, 56);
    public final static int INSTRUCTIONS = WINDOW_WIDTH / 10;
    public final static int SCOREBOARD_SIZE = WINDOW_WIDTH / 40;

    // Establishes the size of Cards
    public final static int CARD_WIDTH = WINDOW_WIDTH / 10;
    public final static int CARD_HEIGHT = WINDOW_HEIGHT / 8;

    // Sets constants for creating hit and stand button
    public final static int BUTTON_WIDTH = WINDOW_WIDTH / 8;
    public final static int BUTTON_HEIGHT = WINDOW_WIDTH / 8;
    public final static int BUTTON_X = (WINDOW_WIDTH / 2) - (BUTTON_WIDTH / 2);
    public final static int BUTTON_Y = (int) (WINDOW_HEIGHT * .5);

    // Constants for leaderboard in corner
    public final static int STARTING_HEIGHT = (int) (WINDOW_WIDTH * .75);
    public final static int STARTING_WIDTH = WINDOW_HEIGHT / 2;


    public BlackJackViewer(BlackJack game) {

        // Initialize instance variables
        this.game = game;
        hit = new ImageIcon("Resources/hit_button.png").getImage();
        stand = new ImageIcon("Resources/stand_button.png").getImage();
        back = new ImageIcon("Resources/back.png").getImage();


        // Set up the window using constants
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("BlackJack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public String[] instructions() {
        // Print instructions via an array of strings so line breaks are clean
        String[] instructions = new String[7];
        instructions[0] = "In BlackJack, you play against the dealer in a game to 21";
        instructions[1] = "You and the dealer will each be given a hand of two cards";
        instructions[2] = "If you want another card to get closer to 21, then hit, otherwise stand";
        instructions[3] = "If the dealer is closer to 21 by the end, then the dealer wins, otherwise you win";
        instructions[4] = "If you go over 21, then you bust and automatically lose";
        instructions[5] = "Note: All cards are their given value, face cards are 10, and Ace is either 1 or 11";
        instructions[6] = "Enter Your Name to Continue";
        return instructions;
    }

    public void printInstructions(Graphics g) {
        String[] steps = instructions();

        // Set fonts for header and instructions
        Font paragraphFont = new Font("TIMES NEW ROMAN", Font.ITALIC, SCOREBOARD_SIZE);
        Font headerFont = new Font("TIMES NEW ROMAN", Font.BOLD, WINDOW_HEIGHT / 16);
        g.setColor(Color.WHITE);
        g.setFont(headerFont);

        // Draw the header for instructions
        String headerString = "Instructions";
        g.drawString(headerString, WINDOW_WIDTH / 3, WINDOW_HEIGHT / 4);
        g.setFont(paragraphFont);

        // Print each string in array
        for (int i = 0; i < steps.length - 1; i++) {
            g.drawString(steps[i], INSTRUCTIONS, STARTING_WIDTH + (i * (WINDOW_WIDTH / 20)));
        }

        // Print last string lower on the screen
        g.drawString(steps[6], WINDOW_WIDTH / 3, (int) (WINDOW_HEIGHT * .90));
    }

    public void gameScreenGeneral(Graphics g) {
        // Set the scoreboard font
        Font scores = new Font("TIMES NEW ROMAN", Font.BOLD, SCOREBOARD_SIZE);
        g.setColor(Color.WHITE);
        g.setFont(scores);

        // Draw the buttons and deck card in the middle of screen
        g.drawImage(hit, BUTTON_X - BUTTON_WIDTH, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, this);
        g.drawImage(stand, BUTTON_X + BUTTON_WIDTH, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, this);
        g.drawImage(back, BUTTON_X, BUTTON_Y, CARD_WIDTH, CARD_HEIGHT, this);

        // Draw scoreboard in top right corner
        g.drawRect((int) (WINDOW_HEIGHT * .65), 0, (int) (WINDOW_WIDTH * .35), HEADER_HEIGHT * 2);
        g.drawString(game.getPlayer().toString(), (int) (WINDOW_WIDTH * .66), HEADER_HEIGHT + 1);
        g.drawString(game.getDealer().firstCard(), (int) (WINDOW_WIDTH * .66), HEADER_HEIGHT + 1 + SCOREBOARD_SIZE);
    }

    // Game screen for player turn
    public void gameScreen1(Graphics g) {
        gameScreenGeneral(g);
        // Draw the player's opening cards
        game.drawPlayer(g);
    }

    // Game screen for dealer turn
    public void gameScreen2(Graphics g) {
        gameScreenGeneral(g);
        // Draw the dealer's cards with one face down
        game.drawDealer(g);
    }

    // End screen for final results
    public void endScreen(Graphics g) {
        gameScreen2(g);

        // Use a 2d Graphic to paint a translucent screen over the board
        // Doing so makes the final screen more prominent while allowing player to see the dealers cards
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Depending on who won, print different messages
        if (game.getPlayerWin() == 1) {
            drawCenteredString(g, "You Lose!");
        } else if (game.getPlayerWin() == 2) {
            drawCenteredString(g, "You Win!");
        } else if (game.getPlayerWin() == 3) {
            drawCenteredString(g, "Nobody Wins!");
        }
    }

    // This method prints any string in the center of the screen
    public void drawCenteredString(Graphics g, String s) {

        // Make the font red for prominence
        Font headerFont = new Font("TIMES NEW ROMAN", Font.BOLD, WINDOW_WIDTH / 16);
        g.setColor(Color.RED);
        g.setFont(headerFont);
        Font f = g.getFont();

        //Get half of the string length using Font methods
        int halfStringLength = g.getFontMetrics(f).stringWidth(s) / 2;

        // Draw string on center of screen minus half the string length
        g.drawString(s, (WINDOW_WIDTH / 2) - halfStringLength, (WINDOW_WIDTH / 2));
    }


    public void paint(Graphics g) {
        // Draw the background for game
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Go to different game screens depending on state
        if (game.getState() == 0) {
            printInstructions(g);
        } else if (game.getState() == 1) {
            gameScreen1(g);
        } else if (game.getState() == 2) {
            gameScreen2(g);
        } else if (game.getState() == 3) {
            endScreen(g);
        }
    }
}
