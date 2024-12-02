import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;
    Scanner s1 = new Scanner(System.in);

    public Game() {
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
//        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] suits = {"\u2660\uFE0E", "\u2665\uFE0F", "\u2663\uFE0E", "\u2666\uFE0F"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        int[] uniCodeVal = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        deck = new Deck(ranks, suits, values, uniCodeVal);
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

    public static String cardArt(Card c) {
        if(c.getSuit() == "\u2665\uFE0F") {
            return new String(Character.toChars(0x1F0B1 + c.getUniCodeVal()));
        } else if(c.getSuit() == "\u2663\uFE0E") {
            return new String(Character.toChars(0x1F0C1 + c.getUniCodeVal()));
        } else if(c.getSuit() == "\u2660\uFE0E") {
            return new String(Character.toChars(0x1F0A1 + c.getUniCodeVal()));
        } else if(c.getSuit() == "\u2666\uFE0F") {
            return new String(Character.toChars(0x1F0D1 + c.getUniCodeVal()));
        } else {
//            throw new Exception("Unknown Suit");
            System.out.println("Unknown Suit Encountered.");
            return "";
        }
    }

    public void getFirstCards() {
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
    }
    public String cardArtHand(Player p) {
        String[] artH = new String[p.getHand().size()];
        for(int i = 0; i < p.getHand().size(); i++) {
            artH[i] = cardArt(p.getHand().get(i));
        }
        String h = "";
        for(int j = 0; j < artH.length; j++) {
            h += artH[j];
        }
        return h;

    }
    public void playerTurn() {
        System.out.print("\n\nYour current hand: " + "\n" + player.toString() + "  " + cardArtHand(player));
        boolean notOver = true;

        while(notOver) {
            System.out.println("\nDo you want to hit or stand?");
            String choice = s1.nextLine();
            if(choice.equals("hit")) {
                player.addCard(deck.deal());
                System.out.println("\n\nYour current hand: \n" + player.toString() + cardArtHand(player));
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
        System.out.println("Dealer's Turn: \n" + dealer.toString() + cardArtHand(dealer));

        while(dealer.getPoints() < 17) {
            System.out.println("Dealer hits");
            dealer.addCard(deck.deal());
            System.out.println("\n\nDealer's current hand: \n" + dealer.toString() + cardArtHand(dealer));

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
        if(player.isBusted()) {
            System.out.println("Dealer Wins!");
        } else if(dealer.isBusted()) {
            System.out.println("Player Wins!");
        } else if(dScore > pScore) {
            System.out.println("Dealer Wins!");
        } else if(pScore > dScore) {
            System.out.println("Player Wins!");
        } else if(pScore == dScore) {
            System.out.println("Nobody Wins!");
        }
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



