import javax.swing.*;
import java.awt.*;

//BlackJack by Zander Deutch
public class Card {
    // Instance variables
    private final String rank;
    private final String suit;
    private final int value;
    private final Image image;
    private final Image cardDown;

    // Constructor
    Card(String r, String s, int v, Image i) {
        rank = r;
        suit = s;
        value = v;
        image = i;

        // initialize cardDown to upside down card
        cardDown = new ImageIcon("Resources/back.png").getImage();
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    // Draw each card using the given parameters
    public void draw(Graphics g, int x, int y, BlackJackViewer window, boolean facedown) {

        // If face down, then draw the card using instance variable
        if (facedown) {
            g.drawImage(cardDown, x, y, BlackJackViewer.CARD_WIDTH, BlackJackViewer.CARD_HEIGHT, window);
        } else {
            g.drawImage(image, x, y, BlackJackViewer.CARD_WIDTH, BlackJackViewer.CARD_HEIGHT, window);
        }
    }
}
