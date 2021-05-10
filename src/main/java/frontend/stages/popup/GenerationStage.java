package frontend.stages.popup;

import frontend.components.scenes.popup.GenerationScene;
import javafx.scene.layout.HBox;

/**
 * Window for code generation items.
 */
public class GenerationStage extends PopUpStage {

    private static GenerationScene contentScene;

    /**
     * Constructor for GenerationStage.
     */
    public GenerationStage(){
        initScreen();
    }

    /**
     * Initialize window.
     */
    private void initScreen() {
        setTitle("Code generator");
        contentScene = new GenerationScene(new HBox(),this);
        setScene(contentScene);
    }
}
