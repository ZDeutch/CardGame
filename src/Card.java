import java.awt.*;

//BlackJack by Zander Deutch
public class Card {
    private String rank;
    private String suit;
    private int value;
    // This is used to show the unique value of each card, regardless of if it's a face card
    private int uniCodeVal;
    private BlackJackViewer table;


    Card(BlackJackViewer table, String r, String s, int v, int uni) {
        this.table = table;
        rank = r;
        suit = s;
        value = v;
        uniCodeVal = uni;
    }

    public void draw(Graphics g) {

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

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getUniCodeVal() {
        return uniCodeVal;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}
