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

    String[] mouseButtonTypes = {"left button","right button","middle button"};
    String[] pressOrRelease = {"Press","Release"};

    ArrayList<MacroEvent> macroEventsList = new ArrayList<>();
    DefaultListModel actionTypesTimeline = new DefaultListModel();

    int selected;
    Object swapping;

    JFrame window = new JFrame("EasyMacro");

    Container playback = new Container();
    Container timelineList = new Container();
    Container changeOrder = new Container();
    Container mouseAction = new Container();
    Container keyAction = new Container();
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
    JComboBox mouseButton = new JComboBox(mouseButtonTypes);
    JComboBox mousePressOrRelease = new JComboBox(pressOrRelease);
    JButton addMouseAction = new JButton("Add Mouse Click");
    JTextField keyActionKey = new JTextField("Key");
    JButton addKeyAction = new JButton("Add Key Press");
    JComboBox keyPressOrRelease = new JComboBox(pressOrRelease);
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
        buttons.setLayout(new GridLayout(3,0));

        // Add mouse click layout
        mouseAction.setLayout(new GridLayout(1,7));

        mouseAction.add(blank[6]);
        mouseAction.add(xMouseCoord);
        mouseAction.add(coordsXLabel);
        mouseAction.add(yMouseCoord);
        mouseAction.add(mouseButton);
        mouseAction.add(mousePressOrRelease);
        mouseAction.add(addMouseAction);
        mouseAction.add(blank[7]);

        xMouseCoord.setHorizontalAlignment(JTextField.CENTER);
        xMouseCoord.addActionListener(this);
        coordsXLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yMouseCoord.setHorizontalAlignment(JTextField.CENTER);
        yMouseCoord.addActionListener(this);
        addMouseAction.addActionListener(this);

        buttons.add(mouseAction,0);

        // Add key action layout
        keyAction.setLayout(new GridLayout(1,5));

        keyAction.add(blank[13]);
        keyAction.add(blank[22]);
        keyAction.add(keyActionKey);
        keyAction.add(blank[14]);
        keyAction.add(blank[23]);
        keyAction.add(keyPressOrRelease);
        keyAction.add(addKeyAction);
        keyAction.add(blank[15]);

        keyActionKey.setHorizontalAlignment(JTextField.CENTER);
        addKeyAction.addActionListener(this);

        buttons.add(keyAction,1);

        // Add delay layout
        delay.setLayout(new GridLayout(1,5));

        delay.add(blank[19]);
        delay.add(blank[24]);
        delay.add(delayTime);
        delay.add(blank[20]);
        delay.add(blank[25]);
        delay.add(blank[26]);
        delay.add(addDelay);
        delay.add(blank[21]);

        delayTime.setHorizontalAlignment(JTextField.CENTER);
        addDelay.addActionListener(this);

        buttons.add(delay,2);

        window.add(buttons, BorderLayout.SOUTH);

        window.setVisible(true);

    }

    public void removeAction() {

        macroEventsList.remove(selected);
        actionTypesTimeline.remove(selected);

    }

    public void moveActionUp() {

        try {
            swapping = macroEventsList.get(selected - 1);
            macroEventsList.set(selected - 1, macroEventsList.get(selected));
            macroEventsList.set(selected, (MacroEvent) swapping);
            swapping = actionTypesTimeline.get(selected - 1);
            actionTypesTimeline.set(selected - 1, actionTypesTimeline.get(selected));
            actionTypesTimeline.set(selected, swapping);
        } catch (Exception ArrayIndexOutOfBoundsException) {
            System.out.println("Can't move action up from here!");
        }

    }

    public void moveActionDown() {

        try {
            swapping = macroEventsList.get(selected + 1);
            macroEventsList.set(selected + 1, macroEventsList.get(selected));
            macroEventsList.set(selected, (MacroEvent) swapping);
            swapping = actionTypesTimeline.get(selected + 1);
            actionTypesTimeline.set(selected + 1, actionTypesTimeline.get(selected));
            actionTypesTimeline.set(selected, swapping);
        } catch (Exception ArrayIndexOutOfBoundsException) {
            System.out.println("Can't move action down from here!");
        }

    }

    public void addMouseAction(Object actionType, int xCoord, int yCoord, int mouseButtonN) {

        macroEventsList.add(new MacroEvent((String) actionType, xCoord, yCoord, mouseButtonN+1));
        actionTypesTimeline.addElement(macroEventsList.get(macroEventsList.size()-1).getActionType() + " " + mouseButton.getSelectedItem());

    }

    public void addKeyAction(Object actionType, char key) {

        macroEventsList.add(new MacroEvent((String) actionType, key));
        actionTypesTimeline.addElement(macroEventsList.get(macroEventsList.size()-1).getActionType() + " '" + key + "'");

    }

    public void addDelay(double delay) {

        macroEventsList.add(new MacroEvent("delay", delay));
        actionTypesTimeline.addElement(delay + " seconds of " + macroEventsList.get(macroEventsList.size()-1).getActionType());

    }

    public void runMacro() throws AWTException, InterruptedException {

        Robot robot = new Robot();

        for (int i = 0; i < macroEventsList.size(); i++) {
            if (macroEventsList.get(i).getDelayTime() > 0) {
                System.out.println("Delay of " + macroEventsList.get(i).getDelayTime() + " seconds");
            } else if ((int) macroEventsList.get(i).getKey() != 0) {
                System.out.println(macroEventsList.get(i).getActionType() + " '" + macroEventsList.get(i).getKey() + "'");
                robot.delay(5000);
                robot.keyPress(macroEventsList.get(i).getKey());
            } else if (macroEventsList.get(i).getMouseButtonN() != 0) {
                System.out.println(macroEventsList.get(i).getActionType() + " mouse button " + macroEventsList.get(i).getMouseButtonN() + " at (" + macroEventsList.get(i).getXCoord() + ", " + macroEventsList.get(i).getYCoord() + ")");
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(playButton)) {
            try {
                runMacro();
            } catch (AWTException | InterruptedException awtException) {
                awtException.printStackTrace();
            }
        }

        if (e.getSource().equals(remove)) {
            removeAction();
        } else if (e.getSource().equals(moveUp)) {
            moveActionUp();
        } else if (e.getSource().equals(moveDown)) {
            moveActionDown();
        }

        if (e.getSource().equals(addMouseAction)) {
            try {
                addMouseAction(mousePressOrRelease.getSelectedItem(), Integer.parseInt(xMouseCoord.getText()), Integer.parseInt(yMouseCoord.getText()), mouseButton.getSelectedIndex());
            } catch (Exception NumberFormatException) {
                JDialog error = new JDialog(window, "Error!");
                JLabel dialogLabel = new JLabel("Please enter valid mouse coordinates.", SwingConstants.CENTER);
                error.add(dialogLabel);
                error.setSize(300,100);
                error.setVisible(true);
            }
        } else if (e.getSource().equals(addKeyAction)) {
            addKeyAction(keyPressOrRelease.getSelectedItem(), keyActionKey.getText().charAt(0));
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