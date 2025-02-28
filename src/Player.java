import java.util.ArrayList;

public class Player {
    // Instance Variables
    private final String name;
    private final ArrayList<Card> hand;
    private int points;

    // Constructor
    Player(String name) {
        this.name = name;
        points = 0;
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        // rather than simply returning points, every time make sure the ace is accounted for
        return computeAce(hand.size());
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
        return points > 21;
    }

    public String firstCard() {
        return name + " has " + computeAce(1) + " points";
    }

    public String toString() {
        return name + " has " + computeAce(hand.size()) + " points";
    }

    // This method decides whether an ace should be 11 or 1
    public int computeAce(int handSize) {
        int counter = 0;
        int tempPoints = 0;
        // See how many aces are in the hand
        for (int i = 0; i < handSize; i++) {
            if (hand.get(i).getRank().equals("Ace")) {
                counter++;
                continue;
            }
            // Set tempPoints as a copy of points
            tempPoints += hand.get(i).getValue();
        }
        // If there are no aces, points remain the same
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
