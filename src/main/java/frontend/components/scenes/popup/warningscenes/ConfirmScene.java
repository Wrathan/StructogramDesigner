package frontend.components.scenes.popup.warningscenes;

import frontend.components.factorys.FxComponentFactory;
import frontend.stages.popup.ConfirmStage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The user can confirm in this scene, that he/she really
 * want to do the actual operation, ot not.
 */
public class ConfirmScene extends WarningScene {

    private final VBox layout;
    private final ConfirmStage window;
    private HBox buttonsHBox;
    private Button yesButton, noButton;

    /**
     * Constructor for ConfirmScene.
     * @param layout current scene's layout
     * @param frame current window
     * @param message current message of the scene
     */
    public ConfirmScene(VBox layout, ConfirmStage frame, String message){
        super(layout, message);
        this.layout = layout;
        this.window = frame;

        initButtons();
    }

    /**
     * Initialize operation buttons.
     */
    private void initButtons() {
        buttonsHBox = new HBox();
        buttonsHBox.setSpacing(20);
        buttonsHBox.setAlignment(Pos.CENTER);

        yesButton = FxComponentFactory.generateButton(buttonsHBox, "Yes", "yesButton");
        noButton = FxComponentFactory.generateButton(buttonsHBox, "No", "noButton");

        yesButton.setOnAction(e -> setAgreed(true, window));
        noButton.setOnAction(e -> setAgreed(false, window));

        layout.getChildren().add(buttonsHBox);
    }

    /**
     * Sets agreed state.
     * @param agreed current agreed state
     * @param window current owner window
     */
    private static void setAgreed(boolean agreed, ConfirmStage window){
        window.setAgreed(agreed);
        window.close();
    }
}
