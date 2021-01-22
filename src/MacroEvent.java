public class MacroEvent {

    private String mActionType;
    private int mDelay;

    // Delay

    public MacroEvent(String actionType, int delay) {

        mActionType = actionType;

    }

    // Key press/release

    public MacroEvent(String actionType, int delay, char key) {

        mActionType = actionType;

    }

    // Mouse click

    public MacroEvent(String actionType, int delay, char key, int xCoord, int yCoord, int clickType) {

        mActionType = actionType;

    }

    public String getActionType() {
        return mActionType;
    }

}