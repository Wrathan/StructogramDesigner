package frontend.components.panels.editor.propertiespanel;

import frontend.GuiManager;
import frontend.components.panels.editor.propertiespanel.parameterspanel.ParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.loop.LoopParametersPanel;
import frontend.components.managers.SelectionManager;
import frontend.emuns.StatementType;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.panels.editor.propertiespanel.parameterspanel.loop.ForEachParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.loop.ForParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.loop.WhileParametersPanel;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add loop properties.
 */
public class LoopPropertiesPanel extends StatementPropertiesPanel{

    private final String[] LOOP_TYPES = new String[]{"for", "for-each", "while"};
    private final VBox loopTypeVBox;
    private final Label loopTypeLabel;
    private final ChoiceBox loopTypeChoiceBox;
    private LoopParametersPanel loopParametersPanel;

    /**
     * Constructor for LoopPropertiesPanel.
     */
    public LoopPropertiesPanel(){
        loopTypeVBox = new VBox();
        loopParametersPanel = new ForParametersPanel();

        loopTypeLabel = FxComponentFactory.generateLabel(loopTypeVBox, "Loop type:", "loopTypeLabel");
        loopTypeChoiceBox = FxComponentFactory.generateChoiceBox(loopTypeVBox, LOOP_TYPES, LOOP_TYPES[0], "loopTypeChoiceBox");

        getChildren().addAll(loopTypeVBox, loopParametersPanel);

        loopTypeChoiceBox.setOnAction(e -> initLoopParametersVBox());

        SelectionManager.setStatementType(StatementType.LOOP);
    }

    /**
     * Initialize loop parameters panel.
     */
    private void initLoopParametersVBox() {
        if (loopTypeChoiceBox.getValue().equals("for")) {
            loopParametersPanel = new ForParametersPanel();
        }
        else if (loopTypeChoiceBox.getValue().equals("for-each")) {
            loopParametersPanel = new ForEachParametersPanel();
        }
        else if (loopTypeChoiceBox.getValue().equals("while")) {
            loopParametersPanel = new WhileParametersPanel();
        }
        GuiManager.updatePane(this, 1, loopParametersPanel);
    }

    /**
     * Gets loop type choice box.
     * @return current loop type choice box
     */
    public ChoiceBox getLoopTypeChoiceBox() {
        return loopTypeChoiceBox;
    }

    /**
     * Gets loop parameters panel.
     * @return current loop parameters panel
     */
    public ParametersPanel getLoopParametersPanel() {
        return loopParametersPanel;
    }
}
