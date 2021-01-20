import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Scanner;

public class EasyMacro implements ActionListener {

    JFrame window = new JFrame("EasyMacro");

    Container functions = new Container();
    Container timeline = new Container();
    Container addAction = new Container();

    JButton pauseButton = new JButton("Pause");
    JButton playButton = new JButton("Play");
    JButton stopButton = new JButton("Stop");
    JButton moveUp = new JButton("Up");
    JButton moveDown = new JButton("Down");
    JButton remove = new JButton("Remove");
    JTextField xMouseCoord = new JTextField("X");
    JLabel coordsXLabel = new JLabel("X");
    JTextField yMouseCoord = new JTextField("Y");
    JComboBox clickType = new JComboBox();
    JButton addMouseClick = new JButton("Add Mouse Click");
    JTextField keyPressKey = new JTextField("Key");
    JButton addKeyPress = new JButton("Add Key Press");
    JTextField keyReleaseKey = new JTextField("Key");
    JButton addKeyRelease = new JButton("Add Key Release");
    JTextField delayTime = new JTextField("0.0");
    JButton addDelay = new JButton("Add Delay");

    Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        new EasyMacro();
    }

    public EasyMacro() {

        userInput.useDelimiter("\\n");

        window.setSize(1000,1100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        // Actions buttons
        functions.setLayout(new GridLayout(2,3));
        // Playback buttons layout
        functions.add(pauseButton);
        pauseButton.addActionListener(this);
        functions.add(playButton);
        playButton.addActionListener(this);
        functions.add(stopButton);
        stopButton.addActionListener(this);

        // Order change buttons layout
        functions.add(moveUp);
        moveUp.addActionListener(this);
        functions.add(moveDown);
        moveDown.addActionListener(this);
        functions.add(remove);
        remove.addActionListener(this);

        window.add(functions, BorderLayout.NORTH);

        // Add functions layout
        addAction.setLayout(new GridLayout(4,5));
        // Add mouse click layout
        addAction.add(xMouseCoord);
        xMouseCoord.addActionListener(this);
        addAction.add(coordsXLabel);
        coordsXLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addAction.add(yMouseCoord);
        yMouseCoord.addActionListener(this);
        addAction.add(clickType);
        addAction.add(addMouseClick);
        addMouseClick.addActionListener(this);

        // Add key press layout

        window.add(addAction, BorderLayout.SOUTH);

        window.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}