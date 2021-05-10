package frontend.stages.popup.serialization;

import frontend.components.scenes.popup.serialization.LoadScene;

/**
 * Window, where the user can load the actual structogram.
 */
public class LoadStage extends SerializationStage {

    /**
     * Constructor for LoadStage.
     */
    public LoadStage() {
        setTitle("Load structogram");
        contentScene = new LoadScene(layout, this);
        setScene(contentScene);
    }
}
