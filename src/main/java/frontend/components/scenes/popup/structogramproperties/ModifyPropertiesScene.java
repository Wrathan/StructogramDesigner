package frontend.components.scenes.popup.structogramproperties;

import backend.entities.statements.functionoperations.FunctionAssignment;
import backend.entities.statements.functionoperations.FunctionCall;
import backend.entities.statements.functionoperations.FunctionDefinition;
import backend.entities.statements.functionoperations.FunctionOperation;
import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.Declaration;
import backend.entities.statements.varoperations.declaration.SimpleDeclaration;
import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.managers.QueryManager;
import frontend.components.panels.structogram.statementlists.StatementListVBox;
import frontend.components.panels.structogram.statements.SingleRowStatementVBox;
import frontend.components.panels.structogram.statements.condstat.CondStatVBox;
import frontend.components.panels.structogram.statements.loop.LoopVBox;
import frontend.stages.popup.structogramproperties.StructogramPropertiesStage;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Properties panel for an existing structogram.
 */
public class ModifyPropertiesScene extends StructogramPropertiesScene {

    /**
     * Constructor for ModifyPropertiesScene.
     * @param layout current scene's layout
     * @param frame current window
     */
    public ModifyPropertiesScene(VBox layout, StructogramPropertiesStage frame) {
        super(layout, frame);

        initDataFieldValues();
    }

    /**
     * Fill properties fields with values of the structogram.
     */
    private void initDataFieldValues(){
        fileNameTextField.setText(StructogramFactory.getStructogram().getFileName());
        funcNameTextField.setText(StructogramFactory.getStructogram().getFuncName());
        typeChoiceBox.setValue(StructogramFactory.getStructogram().getFuncType().toString());

        if (!StructogramFactory.getStructogram().getPrecondition().equals("")){
            precondTextField.setText(StructogramFactory.getStructogram().getPrecondition());
        }
        if (!StructogramFactory.getStructogram().getPostcondition().equals("")) {
            postcondTextField.setText(StructogramFactory.getStructogram().getPostcondition());
        }

        for (int i = 0; i < StructogramFactory.getStructogram().getParameterList().size(); i++){
            addParameterHBox(parametersFlowPane);
            HBox parameterHBox = (HBox) parametersFlowPane.getChildren().get(i);
            ArrayList<Declaration> parameterList = StructogramFactory.getStructogram().getParameterList();

            ((ChoiceBox) parameterHBox.getChildren().get(0)).setValue(parameterList.get(i).getVarType().toString());
            ((TextField) parameterHBox.getChildren().get(1)).setText(parameterList.get(i).getVarName());
            ((CheckBox) parameterHBox.getChildren().get(2)).setSelected(parameterList.get(i) instanceof ArrayDeclaration);
        }
    }

    /**
     * Overridden method, which modifying properties of an existing structogram.
     * @throws IllegalArgumentException when a mandatory field is empty
     */
    @Override
    protected void initStructogramProperties() throws IllegalArgumentException{
        throwExceptionWhenMandatoryTextFieldEmpty();
        if (!StructogramFactory.getStructogram().getFileName().equals(fileNameTextField.getText())){
            StructogramFactory.getStructogram().setFileName(fileNameTextField.getText());
        }
        if (!StructogramFactory.getStructogram().getFuncName().equals(funcNameTextField.getText())){
            StructogramFactory.getStructogram().setFunctionName(funcNameTextField.getText());
        }
        if (StructogramFactory.getStructogram().getFuncType() != FuncType.valueOf(typeChoiceBox.getValue().toString().toUpperCase())) {
            StructogramFactory.getStructogram().setFuncType(FuncType.valueOf(typeChoiceBox.getValue().toString().toUpperCase()));
        }
        if (!StructogramFactory.getStructogram().getPrecondition().equals(precondTextField.getText())) {
            StructogramFactory.getStructogram().setPrecondition(precondTextField.getText());
        }
        if (!StructogramFactory.getStructogram().getPostcondition().equals(postcondTextField.getText())) {
            StructogramFactory.getStructogram().setPostcondition(postcondTextField.getText());
        }

        ArrayList<Declaration> entityParameterList = StructogramFactory.getStructogram().getParameterList();
        ArrayList<Declaration> newParameterList = getParameterList();

        ArrayList<Declaration> oldParameterList = (ArrayList<Declaration>) StructogramFactory.getStructogram().getParameterList().clone();

        if (entityParameterList.size() >= newParameterList.size()) {
            for (int i = 0; i < newParameterList.size(); i++) {
                if (areListItemsSame(entityParameterList.get(i), newParameterList.get(i))) {
                    entityParameterList.set(i, newParameterList.get(i));
                }
            }
            entityParameterList.subList(newParameterList.size(), entityParameterList.size()).clear();
        } else {
            for (int i = 0; i < newParameterList.size(); i++){
                if (i < entityParameterList.size()){
                    if (areListItemsSame(entityParameterList.get(i), newParameterList.get(i))) {
                        entityParameterList.set(i, newParameterList.get(i));
                    }
                } else {
                    entityParameterList.add(newParameterList.get(i));
                }
            }
        }

        boolean isRecursiveCalls = updateRecursiveCallFuncNames(QueryManager.getStructogramWrapperVBox());

        if (isRecursiveCalls && !areParameterTypesSame(oldParameterList, StructogramFactory.getStructogram().getParameterList())){
            GuiManager.openErrorStage("Parameter types modified.\nPlease reconsider all function calls.");
        }

        window.close();
    }

    private boolean areParameterTypesSame(ArrayList<Declaration> oldParameterList, ArrayList<Declaration> newParameterList){
        if (oldParameterList.size() != newParameterList.size()){
            return false;
        }

        for (int i = 0; i < oldParameterList.size(); i++){
            if (oldParameterList.get(i).getVarType() != newParameterList.get(i).getVarType()){
                return false;
            }
            if ((oldParameterList.get(i) instanceof SimpleDeclaration && newParameterList.get(i) instanceof  ArrayDeclaration) ||
                    (oldParameterList.get(i) instanceof ArrayDeclaration && newParameterList.get(i) instanceof  SimpleDeclaration)){
                return false;
            }
        }

        return  true;
    }

    /**
     * Renames recursive function calls in the figure, if current structogram has renamed.
     * @param statementListVBox current statement block
     */
    private boolean updateRecursiveCallFuncNames(StatementListVBox statementListVBox){
        boolean isRecursiveCall = false;
        for (Node statementVBox : statementListVBox.getChildren()){
            if (statementVBox instanceof SingleRowStatementVBox &&
                    ((SingleRowStatementVBox) statementVBox).getStatementEntity() instanceof FunctionOperation &&
                    ((FunctionOperation) ((SingleRowStatementVBox) statementVBox).getStatementEntity()).getFunction().equals(StructogramFactory.getStructogram())){

                SingleRowStatementVBox singleRowStatementVBox = ((SingleRowStatementVBox) statementVBox);
                FunctionOperation funcOpEntity = (FunctionOperation) singleRowStatementVBox.getStatementEntity();

                if (funcOpEntity instanceof FunctionDefinition){
                    singleRowStatementVBox.updateFunctionDefinitionString();

                } else if (funcOpEntity instanceof FunctionAssignment){
                    singleRowStatementVBox.updateFunctionAssignmentString();

                } else if (funcOpEntity instanceof FunctionCall){
                    singleRowStatementVBox.updateFunctionCallString();
                }

                if (!isRecursiveCall){
                    isRecursiveCall = true;
                }

            } else if (statementVBox instanceof LoopVBox){
                boolean isInnerCall = updateRecursiveCallFuncNames(((LoopVBox) statementVBox).getStatementListVBox());

                if (isInnerCall && !isRecursiveCall){
                    isRecursiveCall = true;
                }

            } else if (statementVBox instanceof CondStatVBox) {
                ArrayList<StatementListVBox> condStatStatementListVBoxes = ((CondStatVBox) statementVBox).getStatementListVBoxArrayList();
                for (StatementListVBox vBox : condStatStatementListVBoxes) {
                    boolean isInnerCall = updateRecursiveCallFuncNames(vBox);

                    if (isInnerCall && !isRecursiveCall){
                        isRecursiveCall = true;
                    }
                }
            }
        }
        return isRecursiveCall;
    }

    /**
     * Checks that the current, and the new parameter values are the same, or not.
     * @param entityParameter current entity parameter
     * @param newParameter new entity parameter
     * @return state of equivalence
     */
    private boolean areListItemsSame(Declaration entityParameter, Declaration newParameter){
        return ((entityParameter instanceof SimpleDeclaration && newParameter instanceof ArrayDeclaration) ||
                (entityParameter instanceof ArrayDeclaration && newParameter instanceof SimpleDeclaration)) ||
                !entityParameter.getVarName().equals(newParameter.getVarName()) ||
                entityParameter.getVarType() != newParameter.getVarType();
    }
}
