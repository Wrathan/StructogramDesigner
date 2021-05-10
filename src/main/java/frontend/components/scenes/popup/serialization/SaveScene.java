package frontend.components.scenes.popup.serialization;

import backend.entities.structograms.Structogram;
import backend.serialization.Serialize;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.stages.popup.serialization.SerializationStage;
import javafx.scene.layout.VBox;

/**
 * The user can save a structogram entity in this scene.
 */
public class SaveScene extends SerializationScene {

    /**
     * Constructor for SaveScene.
     * @param layout current scene's layout
     * @param frame current window
     */
    public SaveScene(VBox layout, SerializationStage frame) {
        super(layout, frame);

        operationTextField.setText(StructogramFactory.getStructogram().getFileName());
        serializationButton.setText("Save");
    }

    /**
     * Overridden method, calls the backend's saving method,
     * if there are no already saved structogram with this filename.
     */
    @Override
    protected void serialOperation() {
        boolean alreadySaved = false;
        for (Structogram structogram : serFunctionTableView.getItems()){
            if (structogram.getFileName().equals(operationTextField.getText())){
                alreadySaved = true;
                break;
            }
        }
        if (!alreadySaved || GuiManager.openConfirmStage("Structogram with this name already exists.\nDo you want to rewrite it?")){
            String name = operationTextField.getText();
            StructogramFactory.getStructogram().setFileName(name);
            Serialize.save(name);
            window.close();
        }
    }
}
