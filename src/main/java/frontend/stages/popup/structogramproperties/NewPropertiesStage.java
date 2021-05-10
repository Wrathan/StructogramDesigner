package frontend.stages.popup.structogramproperties;

import frontend.components.scenes.popup.structogramproperties.NewPropertiesScene;

/**
 * Window, where the user can set new structogram properties.
 */
public class NewPropertiesStage extends StructogramPropertiesStage {

    /**
     * Constructor for NewPropertiesStage.
     */
    public NewPropertiesStage() {
        contentScene = new NewPropertiesScene(layout, this);
        setScene(contentScene);
    }
}
