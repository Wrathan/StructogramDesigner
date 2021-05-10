package frontend.stages.popup;

import frontend.components.scenes.popup.warningscenes.ConfirmScene;
import javafx.scene.layout.VBox;

/**
 * Window for confirmation of a user interaction.
 */
public class ConfirmStage extends PopUpStage {

    private static ConfirmScene contentScene;
    private boolean agreed;

    /**
     * Constructor for ConfirmStage.
     * @param message current message
     */
    public ConfirmStage(String message){
        initScreen(message);
    }

    /**
     * Initialize window.
     * @param message current message
     */
    private void initScreen(String message) {
        setTitle("Confirm");
        contentScene = new ConfirmScene(new VBox(), this, message);
        setScene(contentScene);
    }

    /**
     * Gets agreed state.
     * @return current agreed state
     */
    public boolean isAgreed() {
        return agreed;
    }

    /**
     * Sets agreed state.
     * @param agreed current agreed state
     */
    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }
}
