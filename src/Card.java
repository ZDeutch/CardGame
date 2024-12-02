public class Card {
    private String rank;
    private String suit;
    private int value;
    private int uniCodeVal;

//    Card(String r, String s, int v) {
//        rank = r;
//        suit = s;
//        value = v;
//    }

    Card(String r, String s, int v, int uni) {
        rank = r;
        suit = s;
        value = v;
        uniCodeVal = uni;
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
