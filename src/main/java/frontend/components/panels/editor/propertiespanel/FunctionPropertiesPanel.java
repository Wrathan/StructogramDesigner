package frontend.components.panels.editor.propertiespanel;

import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.Declaration;
import backend.entities.structograms.Structogram;
import backend.serialization.Serialize;
import frontend.GuiManager;
import frontend.emuns.StatementType;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.managers.SelectionManager;
import frontend.components.panels.editor.propertiespanel.parameterspanel.function.FunctionParametersPanel;
import frontend.components.tableviews.FunctionTableView;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Panel, where the user can add properties to function calls.
 */
public class FunctionPropertiesPanel extends StatementPropertiesPanel {

    private final String[] INSERT_TYPES = new String[]{"Simple call", "Definition", "Assignment"};
    private VBox tableVBox, insertTypeVBox, insertParametersVBox, functionVarsVBox;
    private HBox insertPropertiesHBox;
    private FlowPane functionVarsFlowPane;
    private FunctionTableView functionTableView;
    private Label tableLabel, insertTypeLabel, funcParameterLabel;
    private ChoiceBox insertTypeChoiceBox;
    private FunctionParametersPanel parametersPanel;
    private Structogram activeStructogram;

    /**
     *Constructor for FunctionPropertiesPanel.
     */
    public FunctionPropertiesPanel() {
        insertPropertiesHBox = new HBox();
        insertPropertiesHBox.setSpacing(40);
        setAlignment(Pos.TOP_LEFT);

        initTable();
        initProperties();
        initFunctionVarPanel();

        getChildren().add(insertPropertiesHBox);

        SelectionManager.setStatementType(StatementType.FUNCTION);
    }

    /**
     * Initialize table of already saved functions.
     */
    private void initTable(){
        tableVBox = new VBox();

        tableVBox.setSpacing(5);
        tableVBox.setAlignment(Pos.TOP_LEFT);

        tableLabel = FxComponentFactory.generateLabel(tableVBox, "Functions");
        functionTableView = FxComponentFactory.generateFunctionTableView(tableVBox, 600, 300, "functionTableView");
        functionTableView.setMinHeight(180);
        functionTableView.loadData(Serialize.getConfigHomePath() + "/saves/");
        functionTableView.loadData(Serialize.getConfigHomePath() + "/basicfunctions/");

        functionTableView.setRowFactory(e -> initRowFactory());

        getChildren().add(functionTableView);
    }

    /**
     * Initialize row factory for table.
     * @return current row
     */
    private TableRow<Structogram> initRowFactory() {
        TableRow<Structogram> row = new TableRow<>();
        row.setOnMouseClicked(e -> activeStructogram = row.getItem());
        return row;
    }

    /**
     * Initialize properties for panel.
     */
    private void initProperties() {
        insertTypeVBox = new VBox();
        insertParametersVBox = new VBox();

        insertParametersVBox.setSpacing(10);

        insertTypeLabel = FxComponentFactory.generateLabel(insertTypeVBox, "Insertion type:", "insertTypeLabel");
        insertTypeChoiceBox = FxComponentFactory.generateChoiceBox(insertTypeVBox, INSERT_TYPES, INSERT_TYPES[0], "insertTypeChoiceBox");

        insertTypeChoiceBox.setOnAction(e -> initParameters());

        parametersPanel = new FunctionParametersPanel(insertTypeChoiceBox);

        insertParametersVBox.getChildren().addAll(insertTypeVBox, parametersPanel);
        insertPropertiesHBox.getChildren().add(insertParametersVBox);
    }

    /**
     * Initialize function variables panel.
     */
    private void initFunctionVarPanel() {
        functionVarsVBox = new VBox();
        functionVarsFlowPane = new FlowPane(Orientation.VERTICAL);

        functionVarsVBox.setSpacing(10);
        functionVarsFlowPane.setVgap(10);
        functionVarsFlowPane.setVgap(5);

        funcParameterLabel = FxComponentFactory.generateLabel(functionVarsVBox, "Function parameters:", "funcParameterLabel");

        functionTableView.setOnMouseClicked(e -> setFunctionVarPanel());

        functionVarsVBox.getChildren().add(functionVarsFlowPane);
        insertPropertiesHBox.getChildren().add(functionVarsVBox);
    }

    /**
     * Set function variables panel.
     */
    private void setFunctionVarPanel(){
        if (activeStructogram != null) {
            ArrayList<Declaration> parameterList = activeStructogram.getParameterList();

            functionVarsFlowPane.getChildren().clear();
            for (Declaration parameter : parameterList){
                initFunctionVariable(parameter);
            }
        }
    }

    /**
     * Initialize function variable panel.
     * @param parameter current parameter
     */
    private void initFunctionVariable(Declaration parameter) {
        VBox functionVarVBox = new VBox();
        StringBuilder parameterSb = new StringBuilder();

        parameterSb.append(parameter.getVarType());
        if (parameter instanceof ArrayDeclaration){
            parameterSb.append("[]");
        }
        parameterSb.append(" ").append(parameter.getVarName()).append(":");

        Label funcVarLabel = FxComponentFactory.generateLabel(functionVarVBox, parameterSb.toString(), "funcVarLabel");
        TextField funcVarTextField = FxComponentFactory.generateTextField(functionVarVBox, "funcVarTextField" + (functionVarsFlowPane.getChildren().size() + 1), 80, 20);

        functionVarsFlowPane.getChildren().add(functionVarVBox);
        GuiManager.updatePane(functionVarsVBox, 1, functionVarsFlowPane);
    }

    /**
     * Init parameters panel.
     */
    private void initParameters() {
        parametersPanel = new FunctionParametersPanel(insertTypeChoiceBox);
        GuiManager.updatePane(insertParametersVBox, 1, parametersPanel);
    }

    /**
     * Gets parameter text fields.
     * @return current parameter text fields
     */
    public ArrayList<TextField> getParameterTextFieldArrayList(){
        ArrayList<TextField> parameterTextFieldArrayList = new ArrayList<>();

        for (Node item : functionVarsFlowPane.getChildren()){
            parameterTextFieldArrayList.add((TextField) ((VBox) item).getChildren().get(1));
        }

        return parameterTextFieldArrayList;
    }

    /**
     * Gets insertion type choice box.
     * @return current insertion type choice box
     */
    public ChoiceBox getInsertTypeChoiceBox() {
        return insertTypeChoiceBox;
    }

    /**
     * Gets parameters panel.
     * @return current parameters panel
     */
    public FunctionParametersPanel getParametersPanel() {
        return parametersPanel;
    }

    /**
     * Gets function table view.
     * @return current function table view
     */
    public FunctionTableView getFunctionTableView() {
        return functionTableView;
    }

    /**
     * Gets selected row from table.
     * @return current selected row from table
     */
    public Structogram getActiveStructogram() {
        return activeStructogram;
    }
}
