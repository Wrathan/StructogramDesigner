package frontend.components.scenes.popup.serialization;

import backend.serialization.Serialize;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.stages.popup.serialization.SerializationStage;
import javafx.scene.layout.VBox;

/**
 * The user can save a structogram entity in this scene.
 */
public class LoadScene extends SerializationScene {

    /**
     * Constructor for LoadScene.
     * @param layout current scene's layout
     * @param frame current window
     */
    public LoadScene(VBox layout, SerializationStage frame) {
        super(layout, frame);
        serializationButton.setText("Load");
    }

    /**
     * Overridden method, calls the backend's loading method,
     * if there is a selected structogram entity in table.
     */
    @Override
    protected void serialOperation() {
        if (operationTextField.getText().equals("")) {
            GuiManager.openErrorStage("Please select an item.");
        } else {
            StructogramFactory.loadStructogram(Serialize.getConfigHomePath() + "/saves/" + operationTextField.getText() + ".ser");
        }
    }
}
