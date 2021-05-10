package frontend.components.scenes;

import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import frontend.stages.DashboardStage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Starting scene of the program, the user can create a new
 * structogram, load an existing one, or exit prom the program.
 */
public class DashboardScene extends Scene {

    private final VBox layout;
    private final DashboardStage window;
    private Button newButton, loadButton, exitButton;

    /**
     * Constructorr for DashboardScene.
     * @param layout current scene's layout
     * @param frame current window
     */
    public DashboardScene(VBox layout, DashboardStage frame){
        super(layout, 250, 300);
        this.layout = layout;
        this.window = frame;
        initDashBoardScene();
        initButtons();
    }

    /**
     * Initialize scene.
     */
    private void initDashBoardScene() {
        layout.setPadding(new Insets(15, 20, 15, 20));
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #3973ac");
    }

    /**
     * Initialize operation buttons.
     */
    private void initButtons() {
        newButton = FxComponentFactory.generateButton(layout, "New", "newButton", 150, 30);
        loadButton = FxComponentFactory.generateButton(layout, "Load", "loadButton", 150, 30);
        exitButton = FxComponentFactory.generateButton(layout, "Exit", "exitButton", 150, 30);

        newButton.setOnAction(e -> GuiManager.openNewPropertiesStage());
        loadButton.setOnAction(e -> GuiManager.openLoadStage());
        exitButton.setOnAction(e -> GuiManager.exitProgram());
    }
}
