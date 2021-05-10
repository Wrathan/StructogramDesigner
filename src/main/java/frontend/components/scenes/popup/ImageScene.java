package frontend.components.scenes.popup;

import frontend.stages.popup.ImageStage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Scene, wheere shows up a saved image of a structogram.
 */
public class ImageScene extends PopUpScene {

    private VBox layout;
    private ImageStage window;

    /**
     * Constructor for ImageScene.
     * @param layout current scene's layout
     * @param frame current window
     * @param imageView current image
     */
    public ImageScene(VBox layout, ImageStage frame, ImageView imageView){
        super(layout, 600, 600);
        this.layout = layout;
        this.window = frame;

        layout.getChildren().add(imageView);
    }
}
