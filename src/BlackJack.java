// BlackJack by Zander Deutch
// February-27-2025
// A front-end version of blackjack, with a player and automatic dealer

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

    // Instance variables
    private final Deck deck;
    private final Player player;
    private final Player dealer;
    private final BlackJackViewer window;
    private int state;
    private int playerWin;

    // Create scanner object
    Scanner s1 = new Scanner(System.in);

    // Constructor
    public BlackJack() {

        // Initialize instance variables
        this.window = new BlackJackViewer(this);

        // Set state to 0 which prints instructions
        state = 0;

        // Create the characteristics of a deck of cards
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        // Pass in arrays to create a shuffled deck of cards;
        deck = new Deck(ranks, suits, values);
        System.out.println("What is your name?");

        // Initialize player names
        player = new Player(s1.nextLine());
        dealer = new Player("Dealer");

    }

    // This method makes the player draw their
    public void drawPlayer(Graphics g) {
        // Make local arrays for the player's hands
        ArrayList<Card> tempPlayerHand = getPlayer().getHand();
        ArrayList<Card> tempDealerHand = getDealer().getHand();

        // Print every card as face up in the middle of the screen
        for (int i = 0; i < tempPlayerHand.size(); i++) {
            tempPlayerHand.get(i).draw(g, BlackJackViewer.STARTING_WIDTH - (i * BlackJackViewer.CARD_WIDTH), BlackJackViewer.STARTING_HEIGHT, window, false);
        }
        for (int i = 0; i < tempDealerHand.size(); i++) {
            // draw the dealer's first card as face down initially
            tempDealerHand.get(i).draw(g, BlackJackViewer.STARTING_WIDTH - (i * BlackJackViewer.CARD_WIDTH), BlackJackViewer.WINDOW_WIDTH / 8, window, i % 2 == 1);
        }
    }

    // This method redraws the player's hands, but this time every card face up
    public void drawDealer(Graphics g) {
        ArrayList<Card> tempPlayerHand = getPlayer().getHand();
        ArrayList<Card> tempDealerHand = getDealer().getHand();
        for (int i = 0; i < tempPlayerHand.size(); i++) {
            tempPlayerHand.get(i).draw(g, BlackJackViewer.STARTING_WIDTH - (i * BlackJackViewer.CARD_WIDTH), BlackJackViewer.STARTING_HEIGHT, window, false);
        }
        for (int i = 0; i < tempDealerHand.size(); i++) {
            tempDealerHand.get(i).draw(g, BlackJackViewer.STARTING_WIDTH - (i * BlackJackViewer.CARD_WIDTH), BlackJackViewer.WINDOW_WIDTH / 8, window, false);
        }
    }

    // Getters
    public int getState() {
        return state;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }

    public int getPlayerWin() {
        return playerWin;
    }

    public void getFirstCards() {
        // Initialize the hands of the player and dealer, giving them both 2 cards
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
    }

    public void playerTurn() {
        // Show the user's current hand
        System.out.print("\nYour current hand: " + "\n" + player.toString());
        boolean notOver = true;

        while (notOver) {
            System.out.println("\nDo you want to hit or stand?");
            String choice = s1.nextLine();
            if (choice.equals("hit")) {
                // If the player hits give them the next card
                player.addCard(deck.deal());
                window.repaint();
                System.out.println("\n\nYour current hand: \n" + player);
                // If this card causes them to lose, then exit the loop
                if (player.isBusted()) {
                    System.out.println("You lose!");
                    return;
                }
            } else if (choice.equals("stand")) {
                // If the user stands then their turn is over
                notOver = false;
            } else {
                System.out.println("Wrong choice. Please enter either 'hit' or 'stand'");
            }
        }
    }

    public void dealerTurn() {
        System.out.println("Dealer's Turn: \n" + dealer.toString());
        // To simulate the dealer advantage, they will always hit unless their score is greater than 17
        while (dealer.getPoints() < 17) {
            System.out.println("Dealer hits");
            dealer.addCard(deck.deal());
            System.out.println("\n\nDealer's current hand: \n" + dealer);
            if (dealer.isBusted()) {
                System.out.println("Dealer Loses!");
                return;
            }
        }
        // If the dealer loses or has more points, they stand
        System.out.println("Dealer Stands");
    }

    public void determineWinner() {
        // Created variables for better code fluency
        int pScore = player.getPoints();
        int dScore = dealer.getPoints();

        System.out.println("Final Scores: ");
        System.out.println("Player: " + pScore);
        System.out.println("Dealer: " + dScore);

        // Accounts for all scenarios in what the score could be
        if (pScore > 21) {
            System.out.println("You Lose!");
            // for every scenario, update playerWin to display accurate end screen
            playerWin = 1;
        } else if (dScore > 21) {
            System.out.println("You Win!");
            playerWin = 2;
            // If both players haven't busted, compare scores
        } else if (pScore > dScore) {
            System.out.println("You Win!");
            playerWin = 2;
        } else if (dScore > pScore) {
            System.out.println("You Lose!");
            playerWin = 1;
        } else {
            System.out.println("Nobody Wins!");
            playerWin = 3;
        }
    }

    public void playGame() {
        // Once game starts, change states to first game screen
        state = 1;
        window.repaint();
        getFirstCards();
        playerTurn();

        if (!player.isBusted()) {
            // If the dealer gets a turn, then state changes
            state = 2;
            window.repaint();
            dealerTurn();
        }

        // Otherwise, determine the winner and switch to the end screen
        determineWinner();
        state = 3;
        window.repaint();
    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.playGame();
    }
}
