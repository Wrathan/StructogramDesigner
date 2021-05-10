package frontend.components.scenes.popup.structogramproperties;

import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.stages.popup.structogramproperties.StructogramPropertiesStage;
import javafx.scene.layout.VBox;

/**
 * Properties panel for a new structogram.
 */
public class NewPropertiesScene extends StructogramPropertiesScene {

    /**
     * Constructor for NewPropertiesScene.
     * @param layout current scene's layout
     * @param frame current window
     */
    public NewPropertiesScene(VBox layout, StructogramPropertiesStage frame) {
        super(layout, frame);
    }

    /**
     * Overridden method, which initialize properties of a new structogram.
     * @throws IllegalArgumentException when a mandatory field is empty
     */
    @Override
    protected void initStructogramProperties() throws IllegalArgumentException {
        throwExceptionWhenMandatoryTextFieldEmpty();
        StructogramFactory.generateNewStructogram(fileNameTextField.getText(), funcNameTextField.getText(), FuncType.valueOf(typeChoiceBox.getValue().toString().toUpperCase()), precondTextField.getText(), postcondTextField.getText(), getParameterList());
        window.close();
        GuiManager.openEditorStage();
    }
}
