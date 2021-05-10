package frontend.components.panels.editor.propertiespanel;

import frontend.GuiManager;
import frontend.components.panels.editor.propertiespanel.parameterspanel.condstat.CondStatParametersPanel;
import frontend.components.managers.SelectionManager;
import frontend.emuns.StatementType;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.panels.editor.propertiespanel.parameterspanel.ParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.condstat.IfParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.condstat.SwitchParametersPanel;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add properties to conditional statements.
 */
public class CondStatPropertiesPanel extends StatementPropertiesPanel{

    private final String[] COND_STATEMENT_TYPES = new String[]{"if", "switch"};
    private final VBox condStatementTypeVBox;
    private final Label condStatementLabel;
    private final ChoiceBox condStatementChoiceBox;
    private CondStatParametersPanel parametersPanel;

    /**
     * Constructor for CondStatPropertiesPanel.
     */
    public CondStatPropertiesPanel(){
        condStatementTypeVBox = new VBox();
        parametersPanel = new IfParametersPanel();

        condStatementLabel = FxComponentFactory.generateLabel(condStatementTypeVBox, "Statement type:", "condStatementLabel");
        condStatementChoiceBox = FxComponentFactory.generateChoiceBox(condStatementTypeVBox, COND_STATEMENT_TYPES, COND_STATEMENT_TYPES[0], "condStatementChoiceBox");

        getChildren().addAll(condStatementTypeVBox, parametersPanel);

        condStatementChoiceBox.setOnAction(e -> initCondStatementParametersVBox());

        SelectionManager.setStatementType(StatementType.COND_STATEMENT);
    }

    /**
     *Initialize parameters panel for conditional statement.
     */
    private void initCondStatementParametersVBox() {
        if (condStatementChoiceBox.getValue().equals("if")) {
            parametersPanel = new IfParametersPanel();
        }
        else if (condStatementChoiceBox.getValue().equals("switch")) {
            parametersPanel = new SwitchParametersPanel();
        }
        GuiManager.updatePane(this, 1, parametersPanel);
    }

    /**
     * Gets conditional statement choice box.
     * @return current conditional statement choice box
     */
    public ChoiceBox getCondStatementChoiceBox() {
        return condStatementChoiceBox;
    }

    /**
     * Gets parameters panel for conditional statement.
     * @return current parameters panel for conditional statement
     */
    public ParametersPanel getParametersPanel() {
        return parametersPanel;
    }
}
