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
        return computeAce();
    }

    public void addPoints(int p) {
        points += p;
    }

    public void addCard(Card c) {
        hand.add(c);
        addPoints(c.getValue());
    }

    public boolean isBusted() {
        if(points > 21) {
            return true;
        }
        return false;
    }

    public String toString() {
        return name + " has " + computeAce() + " points \n" + name + "'s cards: " + hand;
    }

    public int computeAce() {
        int counter = 0;
        int tempPoints = 0;
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getRank() == "Ace") {
                counter++;
                continue;
            }
            tempPoints += hand.get(i).getValue();
        }
        if(counter == 0) {
            return tempPoints;
        } else {
            if(21 - tempPoints >= (counter + 10)) {
                return tempPoints + 10 + counter;
            }
            return tempPoints + counter;
        }
    }

}
