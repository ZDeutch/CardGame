import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class BlackJackViewer extends JFrame {
    private BlackJack game;

    public final static Color BACKGROUND = new Color(51,99, 56);
    public final static int WINDOW_WIDTH = 800;
    public final static int WINDOW_HEIGHT = 800;
    public BlackJackViewer(BlackJack game) {
        this.game = game;


        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("BlackJack");
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString(game.printInstructions(), WINDOW_HEIGHT / 2, WINDOW_WIDTH + 1);
    }
}
