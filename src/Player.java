import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;

    Player(String name) {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
    }

    Player(String name, ArrayList<Card> c) {
        this.name = name;
        points = 0;
        hand = c;

    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int p) {
        points += p;
    }

    public void addCard(Card c) {
        hand.add(c);
        points += c.getValue();
    }

    public boolean isBusted() {
        if(points > 21) {
            return true;
        }
        return false;
    }

    public String toString() {
        return name + " has " + points + " points \n" + name + "'s cards: " + hand;
    }


}
