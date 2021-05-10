package frontend.stages.popup.serialization;

import frontend.components.scenes.popup.serialization.SaveScene;

/**
 * Window, where the user can save the actual structogram.
 */
public class SaveStage extends SerializationStage {

    /**
     * Constructor for SaveStage.
     */
    public SaveStage() {
        setTitle("Save structogram");
        contentScene = new SaveScene(layout, this);
        setScene(contentScene);
    }
}
