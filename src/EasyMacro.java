import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class EasyMacro implements ActionListener {

    JFrame window = new JFrame("EasyMacro");
    JButton pauseButton = new JButton("Reset");
    JButton playButton = new JButton("Reset");
    JButton stopButton = new JButton("Reset");
    JButton[][] gameButtons = new JButton[20][20];

    Container UI = new Container();
    Container buttons = new Container();

    int[][] board = new int[20][20];
    boolean won = false;
    final int BLANK = 0;
    final int BOMB = 1;
    final int BOMBS = 40;
    Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        new EasyMacro();
    }

    public EasyMacro() {

        userInput.useDelimiter("\\n");

        window.setSize(900,1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        // Buttons layout
        buttons.setLayout(new GridLayout(20,20));
        for (int row = 0; row < gameButtons.length; row++) {
            for (int column = 0; column < gameButtons[0].length; column++) {
                gameButtons[column][row] = new JButton();
                buttons.add(gameButtons[column][row]);
                gameButtons[column][row].addActionListener(this);
            }
        }
        window.add(buttons, BorderLayout.CENTER);

        // Scores layout
        UI.setLayout(new GridLayout(1,1));
        UI.add(resetGame);
        resetGame.addActionListener(this);
        window.add(UI, BorderLayout.NORTH);

        reset();

        window.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}