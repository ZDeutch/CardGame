import javax.swing.*;
import java.awt.*;

//BlackJack by Zander Deutch
public class Card {
    private String rank;
    private String suit;
    private int value;
    private Image image;
    // This is used to show the unique value of each card, regardless of if it's a face card
    // private int uniCodeVal;
    private BlackJackViewer table;
    private Image cardDown;
    final static int CARD_WIDTH = 80;
    final static int CARD_HEIGHT = 100;


    Card(BlackJackViewer table, String r, String s, int v, Image i) {
        this.table = table;
        rank = r;
        suit = s;
        value = v;
        image = i;
        cardDown = new ImageIcon("Resources/back.png").getImage();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

//    public void getUniCodeVal() {
//        return uniCodeVal;
//    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    public void draw(Graphics g, int x, int y, BlackJackViewer window, boolean facedown) {
        if(facedown) {
            g.drawImage(cardDown, x, y, CARD_WIDTH, CARD_HEIGHT, window);
        } else {
            g.drawImage(image, x, y, CARD_WIDTH, CARD_HEIGHT, window);
        }
    }
}
