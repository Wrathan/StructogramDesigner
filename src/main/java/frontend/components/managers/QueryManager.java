package frontend.components.managers;

import backend.entities.structograms.Structogram;
import frontend.components.panels.editor.propertiespanel.CondStatPropertiesPanel;
import frontend.components.panels.editor.propertiespanel.FunctionPropertiesPanel;
import frontend.components.panels.editor.propertiespanel.LoopPropertiesPanel;
import frontend.components.panels.editor.propertiespanel.VarOperationPropertiesPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.condstat.IfParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.condstat.SwitchParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.loop.WhileParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.ArrayAssignmentVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value.ArrayVarValuesVBox;
import frontend.components.panels.structogram.statementlists.StructogramWrapperVBox;
import frontend.components.scenes.EditorScene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Static manager class, which contains the necessary queries from StatementPanel's TestFields, ChoiceBoxes, etc...
 */
public final class QueryManager{

    private static EditorScene scene;

    /**
     * Gets variable operation type from statementPropertiesPanel.
     * @return current variable operation type
     */
    public static String getVarOperationType(){
        return ((ChoiceBox) scene.lookup("#operationTypeChoiceBox")).getValue().toString();
    }

    /**
     * Gets variable operation subtype from statementPropertiesPanel.
     * @return current variable operation subtype
     */
    public static String getVarOperationSubType(){
        return ((RadioButton) ((VarOperationPropertiesPanel) scene.getStatementPropertiesPanel()).getRadioButtonToggleGroup().getSelectedToggle()).getText();
    }

    /**
     * Gets array assignment type from statementPropertiesPanel.
     * @return current array assignment type
     */
    public static String getArrayAssignmentType(){
        return ((RadioButton) ((ArrayAssignmentVBox) ((VarOperationPropertiesPanel) scene.getStatementPropertiesPanel()).getParametersPanel().getVarValueVBox()).getArrayAssignmentTypeToggleGroup().getSelectedToggle()).getText();
    }

    /**
     * Gets variable type from statementPropertiesPanel.
     * @return current variable type
     */
    public static String getVarType() {
        return ((ChoiceBox) scene.lookup("#varTypeChoiceBox")).getValue().toString();
    }

    /**
     * Gets variable name from statementPropertiesPanel.
     * @return current variable name
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getVarName() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#varNameTextField")).getText());
    }

    /**
     * Gets simple variable value from statementPropertiesPanel.
     * @return current simple variable value
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getSimpleVarValue() throws IllegalArgumentException{
        return getTextIfNotEmpty((((TextField) scene.lookup("#varValueTextField")).getText()));
    }

    /**
     * Gets array element's value from statementPropertiesPanel.
     * @return current array element's value
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getArraySimpleVarValue() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#varValueTextField")).getText());
    }


    /**
     * Gets array size from statementPropertiesPanel.
     * @return current array size
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getArrayVarSize() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#sizeTextField")).getText());
    }

    /**
     * Gets array element's index from statementPropertiesPanel.
     * @return current array item index
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getArrayItemIndex() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#itemIndexTextField")).getText());
    }

    /**
     * Gets array items' value from statementPropertiesPanel for array definition.
     * @return current array items' value
     * @throws IllegalArgumentException if text field is empty
     */
    public static ArrayList<TextField> getDefArrayItemTextFields() throws IllegalArgumentException{
        return ((ArrayVarValuesVBox) (((VarOperationPropertiesPanel) scene.getStatementPropertiesPanel()).getParametersPanel()).getVarValueVBox()).getArrayItemTextFields();
    }

    /**
     * Gets array items' value from statementPropertiesPanel for array item definition.
     * @return current array items' value
     * @throws IllegalArgumentException if text field is empty
     */
    public static ArrayList<TextField> getAssignArrayItemTextFields() throws IllegalArgumentException{
        return ((ArrayVarValuesVBox) ((ArrayAssignmentVBox) ((VarOperationPropertiesPanel) scene.getStatementPropertiesPanel()).getParametersPanel().getVarValueVBox()).getInnerVarValueVBox()).getArrayItemTextFields();
    }

    /**
     * Gets condition for conditional statement.
     * @return current conditional statement type
     */
    public static String getCondStatType(){
        return ((ChoiceBox) scene.lookup("#condStatementChoiceBox")).getValue().toString();
    }

    /**
     * Gets else if branch conditions from statementPropertiesPanel.
     * @return current else if branch conditions
     * @throws IllegalArgumentException if text field is empty
     */
    public static ArrayList<TextField> getIfConditionTextFields() throws IllegalArgumentException{
        return ((IfParametersPanel) ((CondStatPropertiesPanel) scene.getStatementPropertiesPanel()).getParametersPanel()).getConditionTextFieldArrayList();
    }

    /**
     * Gets is there else branch for if from statementPropertiesPanel.
     * @return is there else branch for current if
     */
    public static boolean getElseCheckBoxStatus(){
        return ((CheckBox) scene.lookup("#elseBranchCheckBox")).isSelected();
    }

    /**
     * Gets switch expression from statementPropertiesPanel.
     * @return current switch expression
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getSwitchExpression() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#switchExprTextField")).getText());
    }

    /**
     * Gets case values from statementPropertiesPanel.
     * @return current case values
     * @throws IllegalArgumentException if text field is empty
     */
    public static ArrayList<TextField> getCaseExpressionTextFields() throws IllegalArgumentException{
        return ((SwitchParametersPanel) ((CondStatPropertiesPanel) scene.getStatementPropertiesPanel()).getParametersPanel()).getCaseTextFieldArrayList();
    }

    /**
     * Gets is there default case for switch from statementPropertiesPanel.
     * @return is there default case for current switch
     */
    public static boolean getDefaultCaseCheckBoxStatus(){
        return ((CheckBox) scene.lookup(("#defaultCaseCheckBox"))).isSelected();
    }

    /**
     * Gets loop type from statementPropertiesPanel.
     * @return current loop type
     */
    public static String getLoopType(){
        return ((ChoiceBox) scene.lookup("#loopTypeChoiceBox")).getValue().toString();
    }

    /**
     * Gets for loop variable name from statementPropertiesPanel.
     * @return current for loop variable name
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getForLoopVarName() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#varNameTextField")).getText());
    }

    /**
     * Gets for loop start variable from statementPropertiesPanel.
     * @return current for loop start variable
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getForLoopStart() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#startIntTextField")).getText());
    }

    /**
     * Gets for loop end variable from statementPropertiesPanel.
     * @return current for loop end variable
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getForLoopEnd() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#endIntTextField")).getText());
    }

    /**
     * Gets for-each loop variable type from statementPropertiesPanel.
     * @return current for-each loop variable type
     */
    public static String getForEachLoopVarType(){
        return ((ChoiceBox) scene.lookup("#varTypeChoiceBox")).getValue().toString();
    }

    /**
     * Gets for-each loop item variable name from statementPropertiesPanel.
     * @return current for_each loop item variable name
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getForEachLoopItemName() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#actualItemTextField")).getText());
    }

    /**
     * Gets for-each loop array variable name from statementPropertiesPanel.
     * @return current for-each loop array variable name
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getForEachLoopArrayVarName() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#arrayVarTextField")).getText());
    }

    /**
     * Gets while loop condition from statementPropertiesPanel.
     * @return current while loop condition
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getWhileLoopCondition() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#conditionTextField")).getText());
    }

    /**
     * Gets while loop subtype from statementPropertiesPanel.
     * @return current while loop type
     */
    public static String getWhileLoopType(){
        return ((RadioButton) ((WhileParametersPanel) ((LoopPropertiesPanel) scene.getStatementPropertiesPanel()).getLoopParametersPanel()).getRadioButtonToggleGroup().getSelectedToggle()).getText();
    }

    /**
     * Gets jump statement type from statementPropertiesPanel.
     * @return current jump statement type
     */
    public static String getJumpStatementType(){
        return ((ChoiceBox) scene.lookup("#jumpStatTypeChoiceBox")).getValue().toString();
    }

    /**
     * Gets return expression from statementPropertiesPanel.
     * @return current return expression
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getReturnExpression() throws IllegalArgumentException{
        return ((TextField) scene.lookup("#expressionTextField")).getText();
    }

    /**
     * Gets is check postcondition for actual return from statementPropertiesPanel.
     * @return is check postcondition
     */
    public static boolean getCheckPostconditionCheckBox(){
        return ((CheckBox) scene.lookup("#checkPostCondCheckBox")).isSelected();
    }

    /**
     * Gets function insertion type from statementPropertiesPanel.
     * @return current function insertion type
     */
    public static String getFunctionInsertType(){
        return ((ChoiceBox) scene.lookup("#insertTypeChoiceBox")).getValue().toString();
    }

    /**
     * Gets variable name, which gets a function call value from statementPropertiesPanel.
     * @return current function variable name
     * @throws IllegalArgumentException if text field is empty
     */
    public static String getFunctionVarName() throws IllegalArgumentException{
        return getTextIfNotEmpty(((TextField) scene.lookup("#varNameTextField")).getText());
    }

    /**
     * Gets variable type, which gets a function call value from statementPropertiesPanel.
     * @return current function variable type
     */
    public static String getFunctionVarType(){
        return ((ChoiceBox) scene.lookup("#varTypeChoiceBox")).getValue().toString();
    }

    /**
     * Gets defined variable name, which gets a function call value from statementPropertiesPanel, from table.
     * @return current function
     */
    public static Structogram getSelectedFunction(){
        return ((FunctionPropertiesPanel) scene.getStatementPropertiesPanel()).getActiveStructogram();
    }

    /**
     * Gets selected function parameters.
     * @return current function parameter list
     * @throws IllegalArgumentException if text field is empty
     */
    public static ArrayList<String> getFunctionParameterList() throws IllegalArgumentException{
        return getStringsFromTextFields(((FunctionPropertiesPanel) scene.getStatementPropertiesPanel()).getParameterTextFieldArrayList());
    }

    /**
     * Gets the wrapper item of visual structogram objects.
     * @return wrapper item of visual structogram object
     */
    public static StructogramWrapperVBox getStructogramWrapperVBox(){
        return scene.getStructogramPanel().getStructogramWrapperVBox();
    }

    /**
     * Gets the visual structogram object.
     * @return visual structogram object
     */
    public static VBox getStructogramFigureVBox(){
        return scene.getStructogramPanel().getStructogramFigureVBox();
    }

    /**
     * Gets list of strings from list of text fields.
     * @param textFieldArrayList current text field list
     * @return current text list
     * @throws IllegalArgumentException if text field is empty
     */
    public static ArrayList<String> getStringsFromTextFields(ArrayList<TextField> textFieldArrayList) throws IllegalArgumentException{
        ArrayList<String> stringArrayList = new ArrayList<>();

        for (TextField textField : textFieldArrayList){
            stringArrayList.add(getTextIfNotEmpty(textField.getText()));
        }

        return stringArrayList;
    }

    /**
     * Gets scene object.
     * @return current scene
     */
    public static EditorScene getScene() {
        return scene;
    }

    /**
     * Sets scene object.
     * @param scene current scene
     */
    public static void setScene(EditorScene scene) {
        QueryManager.scene = scene;
    }

    /**
     * Get text, if parameter is not empty string.
     * @param text current text
     * @return current text
     * @throws IllegalArgumentException if text field is empty
     */
    private static String getTextIfNotEmpty(String text) throws IllegalArgumentException{
        if (text.equals("")){
            throw new IllegalArgumentException();
        }
        return text;
    }
}
