import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class BlackJackViewer extends JFrame {
    private BlackJack game;

    public final static Color BACKGROUND = new Color(51, 99, 56);
    public final static int WINDOW_WIDTH = 800;
    public final static int WINDOW_HEIGHT = 800;
    public final static int INSTRUCTIONS = 10;


    public BlackJackViewer(BlackJack game) {
        this.game = game;


        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("BlackJack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public String[] instructions() {
        String[] instructions = new String[7];
        instructions[0] = "In BlackJack, you play against the dealer in a game to 21";
        instructions[1] = "You and the dealer will each be given a hand of two cards";
        instructions[2] = "If you want another card to get closer to 21, then hit, otherwise stand";
        instructions[3] = "If the dealer is closer to 21 by the end, then the dealer wins, otherwise you win";
        instructions[4] = "If you go over 21, then you bust and automatically lose";
        instructions[5] = "Note: All cards are their given value, face cards are 10, and Ace is either 1 or 11";
        instructions[6] = "Enter Your Name to Continue";
        return instructions;
    }

    public void printInstructions(Graphics g) {
        String[] steps = instructions();
        Font paragraphFont = new Font("TIMES NEW ROMAN", Font.ITALIC, 24);
        Font headerFont = new Font("TIMES NEW ROMAN", Font.BOLD, 50);
        g.setColor(Color.WHITE);
        g.setFont(headerFont);
        String headerString = "Instructions";
        g.drawString(headerString, 280, 200);
        g.setFont(paragraphFont);
        for(int i = 0; i < steps.length - 1; i++) {
            g.drawString(steps[i], INSTRUCTIONS, (300 + (i * 40)));
        }
        g.drawString(steps[6], 280, 600);
    }
    public void gameScreen1(Graphics g) {
        Font scores = new Font("TIMES NEW ROMAN", Font.BOLD, 24);
        g.setColor(Color.WHITE);
        g.setFont(scores);
        g.drawRect(525,0,275,100);
        g.drawString(game.getPlayer().toString(), 550, 50);
        g.drawString(game.getDealer().firstCard(), 550, 75);
        for(int i = 0; i < 2; i++) {
            game.drawPlayer(g);
        }
    }

    public void gameScreen2(Graphics g) {
        Font scores = new Font("TIMES NEW ROMAN", Font.BOLD, 24);
        g.setColor(Color.WHITE);
        g.setFont(scores);
        g.drawRect(525,0,275,100);
        g.drawString(game.getPlayer().toString(), 550, 50);
        g.drawString(game.getDealer().toString(), 550, 75);
        game.drawDealer(g);
    }

    public void endScreen(Graphics g) {
        Font headerFont = new Font("TIMES NEW ROMAN", Font.BOLD, 50);
        g.setColor(Color.WHITE);
        g.setFont(headerFont);
        if(game.getplayerWin() == 1) {
            Font f = g.getFont();
            String loser = "You Lose!";
            int stringLength = g.getFontMetrics(f).stringWidth(loser) / 2;
            g.drawString(loser, (WINDOW_WIDTH / 2) - stringLength, (WINDOW_WIDTH / 2));
        } else if(game.getplayerWin() == 2) {
            Font f = g.getFont();
            String winner = "You Win!";
            int stringLength = g.getFontMetrics(f).stringWidth(winner) / 2;
            g.drawString(winner, (WINDOW_WIDTH / 2) - stringLength, (WINDOW_WIDTH / 2));
        } else if(game.getplayerWin() == 3) {
            Font f = g.getFont();
            String nobody = "Nobody Wins!";
            int stringLength = g.getFontMetrics(f).stringWidth(nobody) / 2;
            g.drawString(nobody, (WINDOW_WIDTH / 2) - stringLength, (WINDOW_WIDTH / 2));
        }
    }


    public void paint(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        if(game.getState() == 0) {
            printInstructions(g);
        } else if(game.getState() == 1) {
            gameScreen1(g);
        } else if(game.getState() == 2) {
            gameScreen2(g);
        } else if(game.getState() == 3) {
            endScreen(g);
        }

    }


}
