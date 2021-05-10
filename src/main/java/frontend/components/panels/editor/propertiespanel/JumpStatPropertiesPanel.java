package frontend.components.panels.editor.propertiespanel;

import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.panels.editor.propertiespanel.parameterspanel.jumpstat.JumpStatParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.jumpstat.ReturnParametersPanel;
import frontend.components.managers.SelectionManager;
import frontend.emuns.StatementType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can jump statement parameters.
 */
public class JumpStatPropertiesPanel extends StatementPropertiesPanel{

    private final String[] JUMP_TYPES = new String[]{"return", "break", "continue"};
    private final VBox jumpStatTypeVBox;
    private final Label jumpStatTypeLabel;
    private final ChoiceBox jumpStatTypeChoiceBox;
    private JumpStatParametersPanel parametersPanel;

    /**
     * Constructor for JumpStatPropertiesPanel.
     */
    public JumpStatPropertiesPanel(){
        jumpStatTypeVBox = new VBox();
        parametersPanel = new ReturnParametersPanel();

        jumpStatTypeLabel = FxComponentFactory.generateLabel(jumpStatTypeVBox, "Statement type:", "jumpStatTypeLabel");
        jumpStatTypeChoiceBox = FxComponentFactory.generateChoiceBox(jumpStatTypeVBox, JUMP_TYPES, JUMP_TYPES[0], "jumpStatTypeChoiceBox");

        getChildren().addAll(jumpStatTypeVBox, parametersPanel);

        jumpStatTypeChoiceBox.setOnAction(e -> initJumpStatTypeParametersVBox());

        SelectionManager.setStatementType(StatementType.JUMP_STATEMENT);
    }

    /**
     * Initialize jump statement parameters panel
     */
    private void initJumpStatTypeParametersVBox() {
        if (jumpStatTypeChoiceBox.getValue().equals("return")){
            parametersPanel = new ReturnParametersPanel();
            getChildren().add(parametersPanel);
            GuiManager.updatePane(this, 1, parametersPanel);
        }
        else if (!jumpStatTypeChoiceBox.getValue().equals("return") && getChildren().size() > 1) {
            GuiManager.removePane(this, 1);
        }
    }

    /**
     * Gets jump statement type choice box.
     * @return current jump statement type choice box
     */
    public ChoiceBox getJumpStatTypeChoiceBox() {
        return jumpStatTypeChoiceBox;
    }

    /**
     * Gets parameters panel.
     * @return current parameters panel
     */
    public JumpStatParametersPanel getParametersPanel() {
        return parametersPanel;
    }
}
