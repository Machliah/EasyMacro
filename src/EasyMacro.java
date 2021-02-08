import javax.crypto.Mac;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Scanner;

public class EasyMacro implements ActionListener, ListSelectionListener {

    String[] mouseClickTypes = {"Left Click","Right Click","Middle Click"};

    ArrayList<MacroEvent> macroEventsList = new ArrayList<>();
    DefaultListModel actionTypesTimeline = new DefaultListModel();

    int selected;
    Object swapping;

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
    JList timelineActionList;
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

        macroEventsList.add(new MacroEvent("delay",5));
        macroEventsList.add(new MacroEvent("key press",'g'));
        macroEventsList.add(new MacroEvent("key release",'h'));
        macroEventsList.add(new MacroEvent("mouse click",435,345,1));

        for (int i = 0; i < macroEventsList.size(); i++) {
            actionTypesTimeline.addElement(macroEventsList.get(i).getActionType());
        }

        timelineActionList = new JList(actionTypesTimeline);

        buildWindow();

    }

    public void buildWindow() {

        window.setSize(700,600);
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
        timelineActionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        timelineActionList.addListSelectionListener(this);

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

    public void removeAction() {

        macroEventsList.remove(selected);
        actionTypesTimeline.remove(selected);

    }

    public void moveActionUp() {

        swapping = macroEventsList.get(selected-1);
        macroEventsList.set(selected-1, macroEventsList.get(selected));
        macroEventsList.set(selected, (MacroEvent) swapping);
        swapping = actionTypesTimeline.get(selected-1);
        actionTypesTimeline.set(selected-1, actionTypesTimeline.get(selected));
        actionTypesTimeline.set(selected, swapping);

    }

    public void moveActionDown() {

        swapping = macroEventsList.get(selected+1);
        macroEventsList.set(selected+1, macroEventsList.get(selected));
        macroEventsList.set(selected, (MacroEvent) swapping);
        swapping = actionTypesTimeline.get(selected+1);
        actionTypesTimeline.set(selected+1, actionTypesTimeline.get(selected));
        actionTypesTimeline.set(selected, swapping);

    }

    public void addMouseClick(int xCoord, int yCoord, int clickType) {

        macroEventsList.add(new MacroEvent("mouse click", xCoord, yCoord, clickType));
        actionTypesTimeline.addElement(macroEventsList.get(macroEventsList.size()-1).getActionType());

    }

    public void addKeyPress(char key) {

        macroEventsList.add(new MacroEvent("key press", key));
        actionTypesTimeline.addElement(macroEventsList.get(macroEventsList.size()-1).getActionType());

    }

    public void addKeyRelease(char key) {

        macroEventsList.add(new MacroEvent("key release", key));
        actionTypesTimeline.addElement(macroEventsList.get(macroEventsList.size()-1).getActionType());

    }

    public void addDelay(double delay) {

        macroEventsList.add(new MacroEvent("delay", delay));
        actionTypesTimeline.addElement(macroEventsList.get(macroEventsList.size()-1).getActionType());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(remove)) {
            removeAction();
        } else if (e.getSource().equals(moveUp)) {
            moveActionUp();
        } else if (e.getSource().equals(moveDown)) {
            moveActionDown();
        }

        if (e.getSource().equals(addMouseClick)) {
            try {
                addMouseClick(Integer.parseInt(xMouseCoord.getText()), Integer.parseInt(yMouseCoord.getText()), clickType.getSelectedIndex());
            } catch (Exception NumberFormatException) {
                JDialog error = new JDialog(window, "Error!");
                JLabel dialogLabel = new JLabel("Please enter valid mouse coordinates.", SwingConstants.CENTER);
                error.add(dialogLabel);
                error.setSize(300,100);
                error.setVisible(true);
            }
        } else if (e.getSource().equals(addKeyPress)) {
            addKeyPress(keyPressKey.getText().charAt(0));
        } else if (e.getSource().equals(addKeyRelease)) {
            addKeyRelease(keyReleaseKey.getText().charAt(0));
        } else if (e.getSource().equals(addDelay)) {
            addDelay(Double.parseDouble(delayTime.getText()));
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (!e.getValueIsAdjusting()) {

            if (timelineActionList.getSelectedIndex() == -1) {
                remove.setEnabled(false);
                moveUp.setEnabled(false);
                moveDown.setEnabled(false);
            } else {
                selected = timelineActionList.getSelectedIndex();
                remove.setEnabled(true);
                moveDown.setEnabled(true);
                moveUp.setEnabled(true);
            }
        }

    }

}