package frontend.components.scenes;

import frontend.components.managers.QueryManager;
import frontend.components.panels.editor.MenuBarPanel;
import frontend.components.panels.editor.StatementPanel;
import frontend.components.panels.editor.StructogramPanel;
import frontend.components.panels.editor.UpdatePanel;
import frontend.components.panels.editor.propertiespanel.StatementPropertiesPanel;
import frontend.stages.EditorStage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

/**
 * Main scene for editing a structogram.
 */
public class EditorScene extends Scene {

    private BorderPane layout;
    private EditorStage window;
    private MenuBarPanel menuBarPanel;
    private StatementPanel statementPanel;
    private StructogramPanel structogramPanel;
    private UpdatePanel updatePanel;

    /**
     * Constructor for EditorScene.
     * @param layout current scene's layout
     * @param frame current window
     */
    public EditorScene(BorderPane layout, EditorStage frame) {
        super(layout, 1200, 800);
        this.layout = layout;
        this.window = frame;

        initEditorLayout();
    }

    /**
     * Initialize layout.
     */
    private void initEditorLayout() {
        menuBarPanel = new MenuBarPanel(window, this);
        statementPanel = new StatementPanel(window, this);
        structogramPanel = new StructogramPanel(window, this);
        updatePanel = new UpdatePanel(window, this);

        layout.setTop(menuBarPanel);
        layout.setLeft(statementPanel);
        layout.setRight(structogramPanel);
        layout.setBottom(updatePanel);

        QueryManager.setScene(this);
    }

    /**
     * Gets the menubar panel.
     * @return menubar panel
     */
    public MenuBarPanel getMenuBarPanel() {
        return menuBarPanel;
    }

    /**
     * Gets statement panel, where the user can select
     * statement type, and gives parameters to it.
     * @return statement panel
     */
    public StatementPanel getStatementPanel() {
        return statementPanel;
    }

    /**
     * Gets statement properties panel, where the user can
     * gives parameters to selected statements.
     * @return statement properties panel
     */
    public StatementPropertiesPanel getStatementPropertiesPanel() {
        return statementPanel.getStatementPropertiesPanel();
    }

    /**
     * Gets structogram panel, where the user can seethe visual
     * object of the structogram, and selecting statements in it
     * @return structogram panel
     */
    public StructogramPanel getStructogramPanel() {
        return structogramPanel;
    }

    /**
     * Gets the update panel, where the user can insert statements
     * to the structogram, modify structogram prorperties, and
     * call generation scene.
     * @return update panel
     */
    public UpdatePanel getUpdatePanel() {
        return updatePanel;
    }
}