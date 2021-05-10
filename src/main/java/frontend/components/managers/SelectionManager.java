package frontend.components.managers;

import frontend.emuns.StatementType;
import frontend.components.panels.structogram.StructogramElementVBox;
import frontend.components.panels.structogram.statementlists.StatementListVBox;
import frontend.components.panels.structogram.statements.StatementVBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Stack;

/**
 * Static manager class, which handle the selection events in the visual object of the structogram.
 * Uses a stack to determine, which is the innermost panel the mouse is standing on.
 * Store the selected element, the list, where the selected element is (if there are no element,
 * then store only the list), and the index of selected element.
 */
public final class SelectionManager {

    private static Stack<StructogramElementVBox> structogramElementVBoxStack = new Stack<>();
    private static StatementVBox selectedStatement;
    private static StatementListVBox selectedList;
    private static int selectedIndex;
    private static StatementType statementType;

    /**
     * Determines what happen, when the mouse enter into a panel.
     * The background of it changes, indicating that the user can select that element.
     * If there are a selected element, then keep its color.
     * @param structogramElementVBox structogram visual object panel
     */
    public static void mouseEntered(StructogramElementVBox structogramElementVBox){
        if (structogramElementVBoxStack.size() > 0){
            structogramElementVBoxStack.peek().setBackground(Background.EMPTY);
        }

        structogramElementVBoxStack.push(structogramElementVBox);
        structogramElementVBoxStack.peek().setBackground(mouseOnElementBackground());
        setSelectedStructogramElementVBoxColor(123, 162, 199);
    }

    /**
     * Determines what happen, when the mouse exits from a panel.
     * The background of it changes back to colorless.
     * If there are a selected element, then keep its color.
     */
    public static void mouseExited(){
        structogramElementVBoxStack.peek().setBackground(Background.EMPTY);
        structogramElementVBoxStack.pop();

        if (!structogramElementVBoxStack.empty()) {
           structogramElementVBoxStack.peek().setBackground(mouseOnElementBackground());
        }
        setSelectedStructogramElementVBoxColor(123, 162, 199);
    }

    /**
     * Determines what happen, when the mouse clicks on a panel.
     * The background of it changes to darker, than when the mouse simply standing on it,
     * and the selected panel will be in the selectedStatement variable.
     * @param structogramElementVBox structogram visual object panel
     */
    public static void mouseClicked(StructogramElementVBox structogramElementVBox){
        if (structogramElementVBox == structogramElementVBoxStack.peek()) {
            if (structogramElementVBox instanceof StatementVBox) {
                if (selectedStatement != structogramElementVBoxStack.peek()) {
                    if (selectedStatement != null) {
                        selectedStatement.setBackground(Background.EMPTY);
                    }
                    else if (selectedList != null) {
                        selectedList.setBackground(Background.EMPTY);
                    }
                    selectedStatement = (StatementVBox) structogramElementVBoxStack.peek();
                    selectedStatement.setBackground(mouseClickedBackground());
                    selectedList = (StatementListVBox) structogramElementVBoxStack.elementAt(structogramElementVBoxStack.size() - 2);
                    selectedIndex = selectedList.getChildren().indexOf(selectedStatement);
                }
                else {
                    selectedStatement.setBackground(mouseOnElementBackground());
                    selectedStatement = null;
                    selectedList = null;
                }
            }
            else if (structogramElementVBox instanceof StatementListVBox) {
                if (selectedList != structogramElementVBoxStack.peek()) {
                    if (selectedStatement != null) {
                        selectedStatement.setBackground(Background.EMPTY);
                    }
                    else if (selectedList != null) {
                        selectedList.setBackground(Background.EMPTY);
                    }
                    selectedStatement = null;
                    selectedList = (StatementListVBox) structogramElementVBoxStack.peek();
                    selectedList.setBackground(mouseClickedBackground());
                }
                else {
                    selectedList.setBackground(mouseOnElementBackground());
                    selectedList = null;
                }
            }
        }
    }

    /**
     * Gets the background of the visual element, when the mouse is standing on it.
     * @return bright background
     */
    private static Background mouseOnElementBackground(){
        return new Background(new BackgroundFill(Color.rgb(209, 225, 240), CornerRadii.EMPTY, Insets.EMPTY));
    }

    /**
     * Gets the background of the visual element, when the mouse is clinking on it.
     * @return dark background
     */
    private static Background mouseClickedBackground(){
        return new Background(new BackgroundFill(Color.rgb(123, 162, 199), CornerRadii.EMPTY, Insets.EMPTY));
    }

    /**
     * Sets the selected visual element's color.
     * @param r red rgb code
     * @param g green rgb code
     * @param b blue rgb code
     */
    private static void setSelectedStructogramElementVBoxColor(int r, int g, int b){
        if (selectedStatement != null){
            selectedStatement.setBackground(new Background(new BackgroundFill(Color.rgb(r, g, b), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (selectedList != null){
            selectedList.setBackground(new Background(new BackgroundFill(Color.rgb(r, g, b), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /**
     * Gets the selected visual element.
     * @return current selected visual element
     */
    public static StatementVBox getSelectedStatement() {
        return selectedStatement;
    }

    /**
     * Sets the selected visual element.
     * @param selectedStatement current selected visual element
     */
    public static void setSelectedStatement(StatementVBox selectedStatement) {
        if (SelectionManager.selectedStatement == null){
            if (SelectionManager.selectedList != null){
                SelectionManager.selectedList.setBackground(Background.EMPTY);
            }
        } else {
            SelectionManager.selectedStatement.setBackground(Background.EMPTY);
        }
        SelectionManager.selectedStatement = selectedStatement;
        setSelectedStructogramElementVBoxColor(123, 162, 199);
    }

    /**
     * Gets the selected list.
     * @return current selected list
     */
    public static StatementListVBox getSelectedList() {
        return selectedList;
    }

    /**
     * Sets the selected list.
     * @param selectedList current selected list
     */
    public static void setSelectedList(StatementListVBox selectedList) {
        if (SelectionManager.selectedList != null){
            SelectionManager.selectedList.setBackground(Background.EMPTY);
        }
        SelectionManager.selectedList = selectedList;
    }

    /**
     * Gets selected visual element type.
     * @return current visual element type
     */
    public static StatementType getStatementType() {
        return statementType;
    }

    /**
     * Sets selected visual element type.
     * @param statementType current visual element type
     */
    public static void setStatementType(StatementType statementType) {
        SelectionManager.statementType = statementType;
    }

    /**
     * Gets the index of the selected visual element, in the list, which contains it.
     * @return current selected visual element index
     */
    public static int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Sets the index of the selected visual element, in the list, which contains it.
     * @param selectedIndex current selected visual element index
     */
    public static void setSelectedIndex(int selectedIndex) {
        SelectionManager.selectedIndex = selectedIndex;
    }

    /**
     * Sets the initial values for SelectionManager variables.
     */
    public static void setInitialValues(){
        selectedIndex = 0;
        if (selectedList != null){
            if (selectedStatement != null){
                selectedStatement.setBackground(Background.EMPTY);
                selectedStatement = null;
                selectedList = null;
            } else {
                selectedList.setBackground(Background.EMPTY);
                selectedList = null;
            }
        }
    }

    /**
     * Clears visual object's background.
     */
    public static void clearBackground(){
        if (selectedList != null){
            if (selectedStatement != null){
                selectedStatement.setBackground(Background.EMPTY);
            } else {
                selectedList.setBackground(Background.EMPTY);
            }
        }
    }

    /**
     * Refill visual object's backgrounds.
     */
    public static void refillBackground(){
        if (selectedList != null){
            if (selectedStatement != null){
                selectedStatement.setBackground(mouseClickedBackground());
            } else {
                selectedList.setBackground(mouseClickedBackground());
            }
        }
    }
}
