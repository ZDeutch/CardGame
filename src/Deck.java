import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<Card>();
        for(int i = 0; i < ranks.length; i++){
            for(int j = 0; j < suits.length; j++) {
                Card c1 = new Card(ranks[i], suits[j], values[i]);
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
}
