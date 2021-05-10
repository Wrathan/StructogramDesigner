package frontend.components.panels.editor;

import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.scenes.EditorScene;
import frontend.stages.EditorStage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Panel, which contains the menus.
 */
public class MenuBarPanel extends VBox {

    private final EditorStage window;
    private final EditorScene scene;
    private final MenuBar menuBar;
    private Menu fileMenu, helpMenu;
    private MenuItem newMenuItem, loadMenuItem, saveMenuItem, exitMenuItem, docMenuItem;


    /**
     * Constructor for MenuBarPanel.
     * @param frame current Stage
     * @param scene current Scene
     */
    public MenuBarPanel(EditorStage frame, EditorScene scene) {
        this.window = frame;
        this.scene = scene;
        this.menuBar = new MenuBar();

        initMenus();
        initMenuItems();

        getChildren().add(menuBar);
    }

    /**
     * Initialize menus.
     */
    private void initMenus() {
        fileMenu = FxComponentFactory.generateMenu(menuBar,"File", "fileMenu");
        helpMenu = FxComponentFactory.generateMenu(menuBar,"Help", "helpMenu");
    }

    /**
     * Initialize menu items.
     */
    private void initMenuItems() {
        newMenuItem = FxComponentFactory.generateMenuItem(fileMenu,"New", "newMenuItem");
        loadMenuItem = FxComponentFactory.generateMenuItem(fileMenu, "Load", "loadMenuItem");
        saveMenuItem = FxComponentFactory.generateMenuItem(fileMenu, "Save", "saveMenuItem");
        exitMenuItem = FxComponentFactory.generateMenuItem(fileMenu,"Exit", "exitMenuItem");
        docMenuItem = FxComponentFactory.generateMenuItem(helpMenu,"Javadoc", "docMenuItem");

        newMenuItem.setOnAction(e -> GuiManager.openNewPropertiesStage());
        saveMenuItem.setOnAction(e -> GuiManager.openSaveStage());
        loadMenuItem.setOnAction(e -> GuiManager.openLoadStage());
        exitMenuItem.setOnAction(e -> GuiManager.exitProgram());
        docMenuItem.setOnAction(e -> {
            try{
                GuiManager.openJavaDoc();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
