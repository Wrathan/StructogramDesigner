package frontend.stages;

import frontend.components.scenes.EditorScene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Window of editor.
 */
public class EditorStage extends Stage {

    private static EditorScene contentScene;

    /**
     * Constructor for EditorStage.
     */
    public EditorStage(){
        initScreen();
    }

    /**
     * Initialize window.
     */
    private void initScreen() {
        setTitle("Editor - Structogram Designer");
        contentScene = new EditorScene(new BorderPane(), this);
        setMaximized(true);
        setScene(contentScene);
    }
}