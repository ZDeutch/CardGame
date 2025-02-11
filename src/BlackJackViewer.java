import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class BlackJackViewer extends JFrame {
    private BlackJack game;

    public final static Color BACKGROUND = new Color(51, 99, 56);
    public final static int WINDOW_WIDTH = 800;
    public final static int WINDOW_HEIGHT = 800;
    public final static int INSTRUCTIONS = 200;

    public BlackJackViewer(BlackJack game) {
        this.game = game;


        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("BlackJack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public String[] instructions() {
        String[] instructions = new String[7];
        instructions[0] = "In BlackJack, you play against the dealer in a game to 21";
        instructions[1] = "You and the dealer will each be given a hand of two cards";
        instructions[2] = "If you want another card to get closer to 21, then hit, otherwise stand";
        instructions[3] = "If the dealer is closer to 21 by the end, then the dealer wins, otherwise you win";
        instructions[4] = "If you go over 21, then you bust and automatically lose";
        instructions[5] = "Note: All cards are their given value, face cards are 10, and Ace is either 1 or 11";
        instructions[6] = "Click 1 to Continue";
        return instructions;
    }
    public void paint(Graphics g) {
        String[] steps = instructions();
        Font paragraphFont = new Font("TIMES NEW ROMAN", Font.ITALIC, 17);
        Font headerFont = new Font("TIMES NEW ROMAN", Font.BOLD, 30);
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(headerFont);
        String headerString = "Instructions";
        g.drawString(headerString, 325, 250);
        g.setFont(paragraphFont);
        for(int i = 0; i < steps.length - 1; i++) {
            g.drawString(steps[i], INSTRUCTIONS, (300 + (i * 30)));
        }
        g.drawString(steps[6], 330, 600);

    }
}
