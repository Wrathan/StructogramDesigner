package frontend.stages.popup.structogramproperties;

import frontend.components.scenes.popup.structogramproperties.ModifyPropertiesScene;

/**
 * Window, where the user can modify existing structogram properties.
 */
public class ModifyPropertiesStage extends StructogramPropertiesStage {

    /**
     * Constructor for ModifyPropertiesStage.
     */
    public ModifyPropertiesStage() {
        contentScene = new ModifyPropertiesScene(layout, this);
        setScene(contentScene);
    }
}
