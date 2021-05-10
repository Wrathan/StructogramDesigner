package frontend.components.scenes.popup.warningscenes;

import frontend.components.factorys.FxComponentFactory;
import frontend.stages.popup.ErrorStage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Appears, when the user want to to an operation,
 * which he/she can't do jet for some reasons.
 */
public class ErrorScene extends WarningScene {

    private final ErrorStage window;
    private final String message;
    private Button errorButton;

    /**
     * Constructor for ErrorScene.
     * @param layout current scene's layout
     * @param frame current window
     * @param message current message of the scene
     */
    public ErrorScene(VBox layout, ErrorStage frame, String message){
        super(layout, message);
        this.window = frame;
        this.message = message;

        initButtons();
    }

    /**
     * Initialize operation button.
     */
    private void initButtons() {
        errorButton = FxComponentFactory.generateButton(layout, "OK", "errorButton");
        errorButton.setOnAction(e -> window.close());
    }
}
