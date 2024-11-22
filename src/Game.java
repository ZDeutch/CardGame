import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;
    Scanner s1 = new Scanner(System.in);

    public Game() {
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values);
        deck.shuffle();

        player = new Player("Player");
        dealer = new Player("Dealer");
    }

    public void printInstructions() {
        System.out.println("In BlackJack, you play against the dealer in a game to 21");
        System.out.println("You and the dealer will each be given a hand of two cards");
        System.out.println("If you want another card to get closer to 21, then hit, otherwise stand");
        System.out.println("If the dealer is closer to 21 by the end, then the dealer wins, otherwise you win");
        System.out.println("Note: If you go over 21, then you bust and automatically lose");
        System.out.println("All cards are their given value, face cards are 10, and Ace is 1");
    }

    public void getFirstCards() {
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
    }

    public void playerTurn() {
        System.out.print("\n\nYour current hand: " + player.getHand());
        boolean notOver = true;

        while(notOver) {
            System.out.println("\nDo you want to hit or stand?");
            String choice = s1.nextLine();
            if(choice.equals("hit")) {
                player.addCard(deck.deal());

                if(player.isBusted()) {
                    System.out.println("You lose!");
                    return;
                }
            } else if(choice.equals("stand")) {
                notOver = false;
            } else {
                System.out.println("Wrong choice. Please enter either 'hit' or 'stand'");
            }
        }
    }

    public void dealerTurn() {
        System.out.println("Dealer's Turn: " + dealer.getHand());

        while(dealer.getPoints() < 17) {
            System.out.println("Dealer hits");
            dealer.addCard(deck.deal());
            dealer.getHand();

            if(dealer.isBusted()) {
                System.out.println("Dealer Loses!");
                return;
            }
        }
        System.out.println("Dealer Stands");
    }

    public void determineWinner() {
        int pScore = player.getPoints();
        int dScore = dealer.getPoints();

        System.out.println("Final Scores: ");
        System.out.println("Player: " + pScore);
        System.out.println("Dealer: " + dScore);

    }

    public void playGame() {
        printInstructions();
        getFirstCards();

        playerTurn();

        if(!player.isBusted()) {
            dealerTurn();
        }

        if(!player.isBusted() && !dealer.isBusted()) {
            determineWinner();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}



