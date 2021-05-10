package frontend.components.scenes.popup;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Abstract superclass of popup scenes.
 */
public abstract class PopUpScene extends Scene {

    /**
     * Constructor for PopUpScene.
     * @param layout current scene's layout
     * @param width current width
     * @param height current height
     */
    protected PopUpScene(Parent layout, int width, int height){
        super(layout, width, height);
    }
}
