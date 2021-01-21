import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Scanner;

public class EasyMacro implements ActionListener, FocusListener {

    String[] mouseClickTypes = {"Left Click","Right Click","Middle Click"};
    String[] timelineActions = {"Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Left Click","Right Click","Middle Click","Right Click","Middle Click","Right Click","Middle Click","Right Click",};

    JFrame window = new JFrame("EasyMacro");

    Container playback = new Container();
    Container timelineList = new Container();
    Container changeOrder = new Container();
    Container mouseClick = new Container();
    Container keyPress = new Container();
    Container keyRelease = new Container();
    Container delay = new Container();
    Container timeline = new Container();
    Container buttons = new Container();

    JButton pauseButton = new JButton("Pause");
    JButton playButton = new JButton("Play");
    JButton stopButton = new JButton("Stop");
    JList timelineActionList = new JList(timelineActions);
    JScrollPane scrollTimeline = new JScrollPane(timelineActionList);
    JButton moveUp = new JButton("Up");
    JButton moveDown = new JButton("Down");
    JButton remove = new JButton("Remove");
    JTextField xMouseCoord = new JTextField("X");
    JLabel coordsXLabel = new JLabel("X");
    JTextField yMouseCoord = new JTextField("Y");
    JComboBox clickType = new JComboBox(mouseClickTypes);
    JButton addMouseClick = new JButton("Add Mouse Click");
    JTextField keyPressKey = new JTextField("Key");
    JButton addKeyPress = new JButton("Add Key Press");
    JTextField keyReleaseKey = new JTextField("Key");
    JButton addKeyRelease = new JButton("Add Key Release");
    JTextField delayTime = new JTextField("0.0");
    JButton addDelay = new JButton("Add Delay");
    JPanel[] blank = new JPanel[30];

    Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        new EasyMacro();
    }

    public EasyMacro() {

        userInput.useDelimiter("\\n");

        for (int i = 0; i < 30; i++) {
            blank[i] = new JPanel();
        }

        buildWindow();

    }

    public void buildWindow() {

        window.setSize(600,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        // Actions buttons
        playback.setLayout(new GridLayout(1,9));

        // Playback buttons layout

        for (int i = 0; i < 6; i++) {
            playback.add(blank[i]);
        }
        playback.add(pauseButton);
        playback.add(playButton);
        playback.add(stopButton);

        pauseButton.addActionListener(this);
        playButton.addActionListener(this);
        stopButton.addActionListener(this);

        window.add(playback, BorderLayout.NORTH);

        // Timeline layout
        timeline.setLayout(new BorderLayout());
        timelineList.setLayout(new BorderLayout());

        // Timeline action list
        scrollTimeline.setViewportView(timelineActionList);

        timelineActionList.setLayoutOrientation(JList.VERTICAL);

        timelineList.add(scrollTimeline);
        timeline.add(timelineList);

        // Order change buttons layout
        changeOrder.setLayout(new GridLayout(1,7));

        changeOrder.add(blank[9]);
        changeOrder.add(blank[10]);
        changeOrder.add(moveUp);
        changeOrder.add(moveDown);
        changeOrder.add(remove);
        changeOrder.add(blank[11]);
        changeOrder.add(blank[12]);

        moveUp.addActionListener(this);
        moveDown.addActionListener(this);
        remove.addActionListener(this);

        timeline.add(changeOrder, BorderLayout.SOUTH);

        window.add(timeline, BorderLayout.CENTER);

        // Add functions layout
        buttons.setLayout(new GridLayout(4,0));

        // Add mouse click layout
        mouseClick.setLayout(new GridLayout(1,7));

        mouseClick.add(blank[6]);
        mouseClick.add(xMouseCoord);
        mouseClick.add(coordsXLabel);
        mouseClick.add(yMouseCoord);
        mouseClick.add(blank[7]);
        mouseClick.add(clickType);
        mouseClick.add(addMouseClick);
        mouseClick.add(blank[8]);

        xMouseCoord.setHorizontalAlignment(JTextField.CENTER);
        xMouseCoord.addActionListener(this);
        coordsXLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yMouseCoord.setHorizontalAlignment(JTextField.CENTER);
        yMouseCoord.addActionListener(this);
        addMouseClick.addActionListener(this);

        buttons.add(mouseClick,0);

        // Add key press layout
        keyPress.setLayout(new GridLayout(1,5));

        keyPress.add(blank[13]);
        keyPress.add(keyPressKey);
        keyPress.add(blank[14]);
        keyPress.add(addKeyPress);
        keyPress.add(blank[15]);

        keyPressKey.setHorizontalAlignment(JTextField.CENTER);
        addKeyPress.addActionListener(this);

        buttons.add(keyPress,1);

        // Add key release layout
        keyRelease.setLayout(new GridLayout(1,5));

        keyRelease.add(blank[16]);
        keyRelease.add(keyReleaseKey);
        keyRelease.add(blank[17]);
        keyRelease.add(addKeyRelease);
        keyRelease.add(blank[18]);

        keyReleaseKey.setHorizontalAlignment(JTextField.CENTER);
        addKeyRelease.addActionListener(this);

        buttons.add(keyRelease,2);

        // Add delay layout
        delay.setLayout(new GridLayout(1,5));

        delay.add(blank[19]);
        delay.add(delayTime);
        delay.add(blank[20]);
        delay.add(addDelay);
        delay.add(blank[21]);

        delayTime.setHorizontalAlignment(JTextField.CENTER);
        addDelay.addActionListener(this);

        buttons.add(delay,3);

        window.add(buttons, BorderLayout.SOUTH);

        window.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}