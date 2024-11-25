import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
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
    public Deck(String[] ranks, String[] suits, int[] values, int[] realVal) {
        cards = new ArrayList<Card>();
        for(int i = 0; i < ranks.length; i++){
            for(int j = 0; j < suits.length; j++) {
                Card c1 = new Card(ranks[i], suits[j], values[i], realVal[i]);
                cards.add(c1);
            }
        }
        cardsLeft = cards.size();

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
        return cards.get(cardsLeft);
    }

    public void shuffle() {
        for(int i = cards.size() - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1));
            swap(cards, i, randomIndex);
        }

        cardsLeft = cards.size();
    }

    public void swap(ArrayList<Card> cards, int i, int j) {
        Card temp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, temp);
    }
}
