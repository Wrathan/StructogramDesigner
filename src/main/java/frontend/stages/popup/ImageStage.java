package frontend.stages.popup;

import frontend.components.scenes.popup.ImageScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Window, which contains an image of a structogram.
 */
public class ImageStage extends PopUpStage {

    private static ImageScene contentScene;

    /**
     * Constructor for ImageStage.
     * @param imageView current image
     */
    public ImageStage(ImageView imageView) {
        initScreen(imageView);
    }

    /**
     * Initialize window.
     * @param imageView current image
     */
    private void initScreen(ImageView imageView) {
        VBox layout = new VBox();

        setTitle("Structogram image");
        contentScene = new ImageScene(layout,this, imageView);
        setScene(contentScene);
    }
}
