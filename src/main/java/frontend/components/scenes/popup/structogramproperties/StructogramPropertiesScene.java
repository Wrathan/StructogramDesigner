package frontend.components.scenes.popup.structogramproperties;

import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.Declaration;
import backend.entities.statements.varoperations.declaration.SimpleDeclaration;
import backend.enums.FuncType;
import backend.enums.VarType;
import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.scenes.popup.PopUpScene;
import frontend.stages.popup.structogramproperties.StructogramPropertiesStage;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * The user can add properties to the structogram entity.
 */
public abstract class StructogramPropertiesScene extends PopUpScene {

    protected final VBox layout;
    protected final StructogramPropertiesStage window;
    protected VBox fileNameVBox, funcNameVBox, typeVBox, optionalVBox, conditionsVBox, precondVBox, postcondVBox, parameterListVBox;
    protected HBox nameAndTypeHBox, operationButtonHBox;
    protected FlowPane parametersFlowPane;
    protected Label fileNameLabel, funcNameLabel, typeLabel, optionalLabel, precondLabel, postcondLabel;
    protected TextField fileNameTextField, funcNameTextField, precondTextField, postcondTextField;
    protected ChoiceBox typeChoiceBox;
    protected Button addParameterButton, okButton, cancelButton;

    /**
     * Constructor for StructogramPropertiesScene.
     * @param layout current scene's layout
     * @param frame current window
     */
    protected StructogramPropertiesScene(VBox layout, StructogramPropertiesStage frame) {
        super(layout, 600, 700);
        this.layout = layout;
        this.window = frame;
        
        initPropertiesLayout();
    }

    /**
     * Initialize layout.
     */
    private void initPropertiesLayout() {
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(40);
        layout.setPadding(new Insets(40, 10, 20, 10));

        initRequiredFields();
        initOptionalFields();
        initOperationButtons();
    }

    /**
     * Initialize fields, what user must fills with data.
     */
    private void initRequiredFields(){
        nameAndTypeHBox = new HBox();
        nameAndTypeHBox.setAlignment(Pos.CENTER);
        nameAndTypeHBox.setSpacing(10);

        fileNameVBox = new VBox();
        fileNameLabel = FxComponentFactory.generateLabel(fileNameVBox, "File name:", "fileNameLabel");
        fileNameTextField = FxComponentFactory.generateTextField(fileNameVBox,"fileNameTextField", 120, 20);

        funcNameVBox = new VBox();
        funcNameLabel = FxComponentFactory.generateLabel(funcNameVBox, "Function name:", "funcNameLabel");
        funcNameTextField = FxComponentFactory.generateTextField(funcNameVBox, "funcNameTextField", 140, 20);

        typeVBox = new VBox();
        typeLabel = FxComponentFactory.generateLabel(typeVBox, "Type:", "typeLabel");
        typeChoiceBox = FxComponentFactory.generateChoiceBox(typeVBox, "typeChoiceBox", FXCollections.observableArrayList(FuncType.values()));

        nameAndTypeHBox.getChildren().addAll(fileNameVBox, funcNameVBox, typeVBox);

        layout.getChildren().add(nameAndTypeHBox);
    }

    /**
     * Initialize fields, what user doesn't must fill with data.
     */
    private void initOptionalFields(){
        optionalVBox = new VBox();
        optionalVBox.setAlignment(Pos.CENTER);
        optionalVBox.setSpacing(15);
        optionalLabel = FxComponentFactory.generateLabel(optionalVBox, "Optional elements", "optionalLabel");
        optionalLabel.setStyle("-fx-underline: true");

        conditionsVBox = new VBox();
        conditionsVBox.setSpacing(15);
        conditionsVBox.setPadding(new Insets(0, 100, 0, 100));

        precondVBox = new VBox();
        precondLabel = FxComponentFactory.generateLabel(precondVBox, "Precondition:", "precondLabel");
        precondTextField = FxComponentFactory.generateTextField(precondVBox, "precondTextField");

        postcondVBox = new VBox();
        postcondLabel = FxComponentFactory.generateLabel(postcondVBox, "Postcondition:", "postcondLabel");
        postcondTextField = FxComponentFactory.generateTextField(postcondVBox, "postcondTextField");

        conditionsVBox.getChildren().addAll(precondVBox, postcondVBox);

        parameterListVBox = new VBox();
        parameterListVBox.setSpacing(10);
        parameterListVBox.setAlignment(Pos.CENTER);
        addParameterButton = FxComponentFactory.generateButton(parameterListVBox, "Add parameter", "addParameterButton");

        parametersFlowPane = new FlowPane(Orientation.VERTICAL);
        parametersFlowPane.setHgap(15);
        parametersFlowPane.setAlignment(Pos.TOP_CENTER);
        parametersFlowPane.setPadding(new Insets(5, 0, 0, 0));
        parameterListVBox.getChildren().add(parametersFlowPane);

        addParameterButton.setOnAction(e -> addParameterHBox(parametersFlowPane));

        optionalVBox.getChildren().addAll(conditionsVBox, parameterListVBox);
        layout.getChildren().add(optionalVBox);
    }

    /**
     * Initialize operation buttons.
     */
    private void initOperationButtons(){
        operationButtonHBox = new HBox();
        operationButtonHBox.setSpacing(30);
        operationButtonHBox.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setVgrow(operationButtonHBox, Priority.ALWAYS);

        okButton = FxComponentFactory.generateButton(operationButtonHBox, "OK", "okButton");
        cancelButton = FxComponentFactory.generateButton(operationButtonHBox, "Cancel", "cancelButton");

        okButton.setOnAction(e -> {
            try{
                initStructogramProperties();
            } catch (NullPointerException | IllegalArgumentException ex){
                ex.printStackTrace();
                GuiManager.openErrorStage("Please fill all mandatory fields.");
            }
        });
        cancelButton.setOnAction(e -> window.close());

        layout.getChildren().add(operationButtonHBox);
    }

    /**
     * Add panel for a new parameters data.
     * @param parametersFlowPane parent of parameter panels
     */
    protected void addParameterHBox(FlowPane parametersFlowPane){
        int flowPaneSize = parametersFlowPane.getChildren().size();
        if (flowPaneSize < 20){
            HBox parameterHBox = new HBox();
            ChoiceBox paramTypeChoiceBox = FxComponentFactory.generateChoiceBox(parameterHBox, "paramTypeChoiceBox" + (flowPaneSize + 1), FXCollections.observableArrayList(VarType.values()));
            TextField paramTextField = FxComponentFactory.generateTextField(parameterHBox, "paramTextField" + (flowPaneSize + 1));
            CheckBox isArrayCheckBox = FxComponentFactory.generateCheckBox(parameterHBox, "[]", "isArrayCheckBox" + (flowPaneSize + 1));
            Button removeParamButton = FxComponentFactory.generateButton(parameterHBox, "-", "removeParamButton" + (flowPaneSize + 1), 20, 20);

            parameterHBox.setAlignment(Pos.CENTER);
            parameterHBox.setSpacing(5);
            parameterHBox.setPadding(new Insets(0, 0, 5, 0));
            paramTypeChoiceBox.setMaxWidth(70);
            paramTextField.setMinWidth(120);
            paramTextField.setPromptText((parametersFlowPane.getChildren().size() + 1) + ". parameter");

            removeParamButton.setOnAction(e -> removeParameter(parametersFlowPane, parameterHBox));

            parametersFlowPane.getChildren().add(parameterHBox);
        }
    }

    /**
     * Remove a specified parameter panel
     * @param parametersFlowPane parent of parameter panels
     * @param parameterHBox current parameter panel, what need to remove
     */
    private void removeParameter(FlowPane parametersFlowPane, HBox parameterHBox) {
        int parameterIndex = parametersFlowPane.getChildren().indexOf(parameterHBox);
        int parametersFlowPaneSize = parametersFlowPane.getChildren().size();

        parametersFlowPane.getChildren().remove(parameterHBox);

        for (int i = parameterIndex; i < parametersFlowPaneSize - 1; i++){
            ((TextField) ((HBox) parametersFlowPane.getChildren().get(i)).getChildren().get(1)).setPromptText((i + 1) + ". parameter");
            ((HBox) parametersFlowPane.getChildren().get(i)).getChildren().get(0).setId("paramTypeChoiceBox" + (i + 1));
            ((HBox) parametersFlowPane.getChildren().get(i)).getChildren().get(1).setId("paramTextField" + (i + 1));
            ((HBox) parametersFlowPane.getChildren().get(i)).getChildren().get(2).setId("isArrayCheckBox" + (i + 1));
            ((HBox) parametersFlowPane.getChildren().get(i)).getChildren().get(3).setId("removeParamButton" + (i + 1));
        }
    }

    /**
     * Gets list of backend entities of parameters.
     * @return current list of backend entities of parameters
     */
    protected ArrayList<Declaration> getParameterList(){
        ArrayList<Declaration> parameterList = new ArrayList<>();

        for (int i = 0; i < parametersFlowPane.getChildren().size(); i++){

            boolean isArray = ((CheckBox) ((HBox) parametersFlowPane.getChildren().get(i)).getChildren().get(2)).isSelected();
            VarType varType = VarType.valueOf(((ChoiceBox) ((HBox) parametersFlowPane.getChildren().get(i)).getChildren().get(0)).getValue().toString().toUpperCase());
            String varName = ((TextField) ((HBox) parametersFlowPane.getChildren().get(i)).getChildren().get(1)).getText();

            if (isArray){
                parameterList.add(new ArrayDeclaration(varName, varType));
            } else {
                parameterList.add(new SimpleDeclaration(varName, varType));
            }
        }

        return parameterList;
    }

    /**
     * Checks that every mandatory field are filled with data.
     * @throws IllegalArgumentException when a mandatory field is empty
     */
    protected void throwExceptionWhenMandatoryTextFieldEmpty() throws IllegalArgumentException{
        if (fileNameTextField.getText().equals("") || funcNameTextField.getText().equals("")){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < parametersFlowPane.getChildren().size(); i++){
            if (getParameterList().get(i).getVarName().equals("")){
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Abstract function of initialize properties for structogram.
     * @throws IllegalArgumentException when a mandatory field is empty
     */
    protected abstract void initStructogramProperties() throws IllegalArgumentException;
}
