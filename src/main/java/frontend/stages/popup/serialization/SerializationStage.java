package frontend.stages.popup.serialization;

import frontend.stages.popup.PopUpStage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Window, where the user can do serialization tools.
 */
public abstract class SerializationStage extends PopUpStage {

    protected static Scene contentScene;
    protected VBox layout;

    /**
     * Constructor for SerializationStage.
     */
    public SerializationStage() {
        initScreen();
    }

    /**
     * Initialize window.
     */
    private void initScreen() {
        layout = new VBox();
    }
}
