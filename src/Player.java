//BlackJack by Zander Deutch
import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;
    private BlackJackViewer window;

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
        // rather than simply returning points, every time make sure the ace is accounted for
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
        // Makes sure that the user hasn't gone over 21 and lost
        if (points > 21) {
            return true;
        }
        return false;
    }

    public String firstCard() {
       return name + " has " + computeAceSingle() + " points";
    }

    public String toString() {
        return name + " has " + computeAce() + " points";
    }

    // This method decides whether an ace should be 11 or 1
    public int computeAce() {
        int counter = 0;
        int tempPoints = 0;
        // See how many aces are in the hand
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getRank() == "Ace") {
                counter++;
                continue;
            }
            // Set tempPoints as a copy of points
            tempPoints += hand.get(i).getValue();
        }
        // If their are no aces, points remain the same
        if (counter == 0) {
            return tempPoints;
        } else {
            // otherwise, if there is enough room, 1 ace is 11 and the rest are 1
            if (21 - tempPoints >= (counter + 10)) {
                return tempPoints + 10 + counter;
            }
            // if not, all aces are 1
            return tempPoints + counter;
        }
    }

    public int computeAceSingle() {
        int counter = 0;
        int tempPoints = 0;
        // See how many aces are in the hand
            if (hand.get(0).getRank() == "Ace") {
                counter++;
            }
            // Set tempPoints as a copy of points
            tempPoints = hand.get(0).getValue();
        // If their are no aces, points remain the same
        if (counter == 0) {
            return tempPoints;
        } else {
            // otherwise, if there is enough room, 1 ace is 11 and the rest are 1
            if (21 - tempPoints >= (counter + 10)) {
                return tempPoints + 10 + counter;
            }
            // if not, all aces are 1
            return tempPoints + counter;
        }
    }

}
