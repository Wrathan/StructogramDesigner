package frontend.stages;

import frontend.components.scenes.DashboardScene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Starting window of the program.
 */
public class DashboardStage extends Stage {

    private static DashboardScene contentScene;

    /**
     * Constructor for DashboardStage.
     */
    public DashboardStage(){
        initScreen();
    }

    /**
     * Initialize window.
     */
    private void initScreen(){
        setTitle("Structogram Designer");
        contentScene = new DashboardScene(new VBox(), this);
        initStyle(StageStyle.UTILITY);
        setResizable(false);
        setScene(contentScene);
    }
}
