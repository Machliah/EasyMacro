public class MacroEvent {

    private String mActionType;
    private int mDelay;

    // Delay

    public MacroEvent(String actionType, double delay) {

        mActionType = actionType;

    }

    // Key press/release

    public MacroEvent(String actionType, char key) {

        mActionType = actionType;

    }

    // Mouse click

    public MacroEvent(String actionType, int xCoord, int yCoord, int clickType) {

        mActionType = actionType;

    }

    public String getActionType() {
        return mActionType;
    }

}