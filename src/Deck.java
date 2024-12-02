import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private int cardsLeft;

//    public Deck(String[] ranks, String[] suits, int[] values) {
//        cards = new ArrayList<Card>();
//        for(int i = 0; i < ranks.length; i++){
//            for(int j = 0; j < suits.length; j++) {
//                Card c1 = new Card(ranks[i], suits[j], values[i]);
//                cards.add(c1);
//            }
//        }
//        cardsLeft = cards.size();
//
//    }
    public Deck(String[] ranks, String[] suits, int[] values, int[] uniCodeVal) {
        deck = new ArrayList<Card>();
        for(int i = 0; i < ranks.length; i++){
            for(int j = 0; j < suits.length; j++) {
                Card c1 = new Card(ranks[i], suits[j], values[i], uniCodeVal[i]);
                deck.add(c1);
            }
        }
        cardsLeft = deck.size();

    }

    public boolean isEmpty(int cardsLeft) {
        if(cardsLeft == 0) {
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if(isEmpty(cardsLeft)) {
            return null;
        }

        cardsLeft--;
        return deck.get(cardsLeft);
    }

    public void shuffle() {
        for(int i = cardsLeft - 1; i > 0; i--) {
            int j = (int) (Math.random() * i);
            Card k = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j,k);
        }
    }
}
