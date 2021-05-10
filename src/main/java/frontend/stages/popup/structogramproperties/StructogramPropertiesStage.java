package frontend.stages.popup.structogramproperties;

import frontend.components.scenes.popup.structogramproperties.StructogramPropertiesScene;
import frontend.stages.popup.PopUpStage;
import javafx.scene.layout.VBox;

/**
 * Window, where the user can set structogram properties.
 */
public abstract class StructogramPropertiesStage extends PopUpStage {

    protected static StructogramPropertiesScene contentScene;
    protected VBox layout;

    /**
     * Constructor for StructogramPropertiesStage.
     */
    public StructogramPropertiesStage() {
        layout = new VBox();

        setTitle("Structogram properties");
    }
}
