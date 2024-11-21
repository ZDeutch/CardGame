import java.util.Scanner;

public class Game {

    private Deck deck;
    private Player player;
    private Player dealer;

    public Game() {
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};
        int[] values = {1,2,3,4,5,6,7,8,9,10,10,10,10};
        deck = new Deck(ranks, suits, values);
        deck.shuffle();

        player = new Player("Player");
        dealer = new Player("Dealer");
        Scanner s1 = new Scanner(System.in);
    }

    public void printInstructions() {
        System.out.println("In BlackJack, you play against the dealer in a game to 21");
        System.out.println("You and the dealer will each be given a hand of two cards");
        System.out.println("If you want another card to get closer to 21, then hit, otherwise stand");
        System.out.println("If the dealer is closer to 21 by the end, then the dealer wins, otherwise you win");
        System.out.println("Note: If you go over 21, then you bust and automatically lose");
        System.out.println("All cards are their given value, face cards are 10, and Ace is 1");
    }


}
