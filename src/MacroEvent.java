public class MacroEvent {

    private String mActionType;
    private double mDelay;
    private char mKey;
    private int mXCoord;
    private int mYCoord;
    private int mMouseButtonN;

    // Delay

    public MacroEvent(String actionType, double delay) {

        mActionType = actionType;
        mDelay = delay;

    }

    // Key action

    public MacroEvent(String actionType, char key) {

        mActionType = actionType;
        mKey = key;

    }

    // Mouse action

    public MacroEvent(String actionType, int xCoord, int yCoord, int mouseButtonN) {

        mActionType = actionType;
        mXCoord = xCoord;
        mYCoord = yCoord;
        mMouseButtonN = mouseButtonN;

    }

    public String getActionType() {
        return mActionType;
    }

    public Double getDelayTime() {
        return mDelay;
    }

    public char getKey() {
        return mKey;
    }

    public int getXCoord() {
        return mXCoord;
    }

    public int getYCoord() {
        return mYCoord;
    }

    public int getMouseButtonN() {
        return mMouseButtonN;
    }

}