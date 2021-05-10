package frontend.stages.popup;

import frontend.components.scenes.popup.warningscenes.ErrorScene;
import javafx.scene.layout.VBox;

/**
 * Window for showing an error of user interaction.
 */
public class ErrorStage extends PopUpStage {

    private static ErrorScene contentScene;

    /**
     * Constructor for ErrorStage.
     * @param message current message
     */
    public ErrorStage(String message){
        initScreen(message);
    }

    /**
     * Initialize window.
     * @param message current message
     */
    private void initScreen(String message) {
        setTitle("Error");
        contentScene = new ErrorScene(new VBox(),this, message);
        setScene(contentScene);
    }
}
