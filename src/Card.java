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


    Card(BlackJackViewer table, String r, String s, int v, Image i) {
        this.table = table;
        rank = r;
        suit = s;
        value = v;
        image = i;
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

    public void draw(Graphics g) {
        g.drawImage(image, 50, 50, null);
    }
}
