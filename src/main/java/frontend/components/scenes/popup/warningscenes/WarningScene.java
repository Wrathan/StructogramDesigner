package frontend.components.scenes.popup.warningscenes;

import frontend.components.factorys.FxComponentFactory;
import frontend.components.scenes.popup.PopUpScene;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * Abstract superclass of warning scenes (e.g error, confirm)
 */
public abstract class WarningScene extends PopUpScene {

    protected final VBox layout;
    protected final String message;
    protected Label messageLabel;

    /**
     * Constructor for WarningScene.
     * @param layout current scene's layout
     * @param message current message of the scene
     */
    protected WarningScene(VBox layout, String message){
        super(layout, 300, 120);
        this.layout = layout;
        this.message = message;

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);

        initMessage();
    }

    /**
     * Initialize warning message.
     */
    private void initMessage(){
        messageLabel = FxComponentFactory.generateLabel(layout, message, "messageLabel");
        messageLabel.setTextAlignment(TextAlignment.CENTER);
    }


}
