//BlackJack by Zander Deutch
import java.util.Scanner;

public class BlackJack {
    private Deck deck;
    private Player player;
    private Player dealer;
    private BlackJackViewer window;
    //Initialize each suit as a constant with their corresponding unicode value
    final static private String SPADES = "\u2660\uFE0F";
    final static private String HEARTS = "\u2665\uFE0F";
    final static private String DIAMONDS = "\u2666\uFE0F";
    final static private String CLUBS = "\u2663\uFE0F";

    //Every playing card in Unicode has a hex value + the true value of a card
    //Setting these constant variables as the hex for each suit
    final static private int CARD_ART_SPADES = 0x1F0A1;
    final static private int CARD_ART_CLUBS = 0x1F0D1;
    final static private int CARD_ART_HEARTS = 0x1F0B1;
    final static private int CARD_ART_DIAMONDS = 0x1F0C1;
    Scanner s1 = new Scanner(System.in);

    public BlackJack() {
        this.window = new BlackJackViewer(this);

        // Create the characteristics of a deck of cards
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {SPADES, HEARTS, DIAMONDS, CLUBS};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        // uniCodeVal is made for each cards "true" order in a deck, so that I can generate the card art easily
        int[] uniCodeVal = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        // Pass in arrays to create a shuffled deck of cards;
        deck = new Deck(ranks, suits, values, uniCodeVal);
        System.out.println("What is your name?");
        player = new Player(s1.nextLine());
        dealer = new Player("Dealer");
        deck.shuffle();
    }

    public String printInstructions() {
        return "In BlackJack, you play against the dealer in a game to 21 \n You and the dealer will each be " +
                "given a hand of two cards \n If you want another card to get closer to 21, then hit, otherwise stand \n " +
                "If the dealer is closer to 21 by the end, then the dealer wins, otherwise you win \n Note: If " +
                "you go over 21, then you bust and automatically lose \n All cards are their given value, face " +
                "cards are 10, and Ace is either 1 or 11";
    }

    public void getFirstCards() {
        // Initialize the hands of the player and dealer, giving them both 2 cards
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
    }

    public String cardArtHand(Player p) {
        // Create an array the size of the player's hand
        String[] artH = new String[p.getHand().size()];
        for (int i = 0; i < p.getHand().size(); i++) {
            //For each card, pass it into the cardArt method to get the unicode card art
            artH[i] = cardArt(p.getHand().get(i));
        }
        String h = "";
        // Concatenate each card to the string h
        for (int j = 0; j < artH.length; j++) {
            h += artH[j];
        }
        // Return the hand as actual playing cards
        return h;

    }

    public static String cardArt(Card c) {
        //For each card check what suit it is depicting
        //Character.toChars converts unicode values into an array of chars
        //The UniCodeVal of that specific card is added to this char value and the card art is formed
        // This value is then converted back into a string which turns it into card art
        if (c.getSuit() == SPADES) {
            return new String(Character.toChars(CARD_ART_SPADES + c.getUniCodeVal()));
        } else if (c.getSuit() == HEARTS) {
            return new String(Character.toChars(CARD_ART_HEARTS + c.getUniCodeVal()));
        } else if (c.getSuit() == DIAMONDS) {
            return new String(Character.toChars(CARD_ART_DIAMONDS + c.getUniCodeVal()));
        } else if (c.getSuit() == CLUBS) {
            return new String(Character.toChars(CARD_ART_CLUBS + c.getUniCodeVal()));
        } else {
            //If none of the suits are shown, throw an error
            System.out.println("Unknown Suit Encountered.");
            return "";
        }
    }

    public void playerTurn() {
        // Show the user's current hand
        System.out.print("\nYour current hand: " + "\n" + player.toString() + "  " + cardArtHand(player));
        boolean notOver = true;

        while (notOver) {
            System.out.println("\nDo you want to hit or stand?");
            String choice = s1.nextLine();
            if (choice.equals("hit")) {
                // If the player hits give them the next card
                player.addCard(deck.deal());
                System.out.println("\n\nYour current hand: \n" + player.toString() + cardArtHand(player));
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
        System.out.println("Dealer's Turn: \n" + dealer.toString() + cardArtHand(dealer));
        // To simulate the dealer advantage, they will always hit unless their score is greater
        while (dealer.getPoints() < player.getPoints()) {
            System.out.println("Dealer hits");
            dealer.addCard(deck.deal());
            System.out.println("\n\nDealer's current hand: \n" + dealer.toString() + cardArtHand(dealer));
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
        if (dScore > pScore) {
            System.out.println("Dealer Wins!");
        } else if (pScore > dScore) {
            System.out.println("Player Wins!");
        } else if (pScore == dScore) {
            System.out.println("Nobody Wins!");
        }
    }

    public void playGame() {

        getFirstCards();

        playerTurn();

        if (!player.isBusted()) {
            dealerTurn();
        }
        // Only determine winner if both people haven't lost
        if (!player.isBusted() && !dealer.isBusted()) {
            determineWinner();
        }
    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.playGame();
    }
}



