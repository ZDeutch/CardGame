// BlackJack by Zander Deutch

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Deck {
    // Instance Variables
    private final ArrayList<Card> deck;
    private int cardsLeft;

    // Constructor
    public Deck(String[] ranks, String[] suits, int[] values) {
        deck = new ArrayList<>();
        int counter = 1;
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                String fileName = "resources/" + counter + ".png";
                Image cardImage = new ImageIcon(fileName).getImage();
                Card c1 = new Card(ranks[i], suit, values[i], cardImage);
                counter++;
                // Add each card to your deck once made
                deck.add(c1);
            }
        }
        cardsLeft = deck.size();

        // Have the deck shuffle itself
        shuffle();

    }

    public boolean isEmpty(int cardsLeft) {
        return cardsLeft == 0;
    }

    public Card deal() {
        if (isEmpty(cardsLeft)) {
            return null;
        }
        // Deal from the value of cardsLeft
        cardsLeft--;
        return deck.get(cardsLeft);
    }

    public void shuffle() {
        // iterate over each card in the deck
        for (int i = cardsLeft - 1; i > 0; i--) {
            // set a value that is randomized based on how many other cards are in the deck
            int j = (int) (Math.random() * i);
            Card k = deck.get(i);
            // swap the random location and i
            deck.set(i, deck.get(j));
            deck.set(j, k);
        }
    }
}
