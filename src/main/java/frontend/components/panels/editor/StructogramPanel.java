package frontend.components.panels.editor;

import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.Declaration;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.factorys.StructogramFactory;
import frontend.components.managers.SelectionManager;
import frontend.components.panels.structogram.statementlists.StructogramWrapperVBox;
import frontend.components.scenes.EditorScene;
import frontend.stages.EditorStage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Panel, where the actual structogram's figue is.
 * The user can select, remove, and insert items.
 */
public class StructogramPanel extends VBox {

    private final EditorStage window;
    private final EditorScene scene;
    private GridPane structogramGridPane;
    private StructogramWrapperVBox structogramWrapperVBox;
    private static VBox structogramFigureVBox;
    private Label functionLabel;
    private String functionString;

    /**
     * Constructor for StructogramPanel.
     * @param frame current Stage
     * @param scene current Scene
     */
    public StructogramPanel(EditorStage frame, EditorScene scene) {
        this.window = frame;
        this.scene = scene;

        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(0, 80, 0, 0));

        initStructogramFigure();

        getChildren().addAll(structogramGridPane);
    }

    /**
     * Initialize visible structogram object.
     */
    private void initStructogramFigure(){
        structogramFigureVBox = new VBox();
        structogramGridPane = new GridPane();
        structogramGridPane.setAlignment(Pos.CENTER);

        structogramFigureVBox.setAlignment(Pos.CENTER);
        structogramFigureVBox.setSpacing(10);
        structogramFigureVBox.setPadding(new Insets(5, 5, 5, 5));

        initFunctionLabel();

        structogramWrapperVBox = new StructogramWrapperVBox(StructogramFactory.getStructogram().getStatementList());

        structogramFigureVBox.getChildren().add(structogramWrapperVBox);
        structogramGridPane.getChildren().add(structogramFigureVBox);
        structogramFigureVBox.setVisible(false);

        SelectionManager.setInitialValues();
        SelectionManager.setSelectedList(structogramWrapperVBox);
    }

    /**
     * Initialize the header of the figure, contains
     * the structogram type, name, and parameters.
     */
    private void initFunctionLabel(){
        functionLabel = FxComponentFactory.generateLabel(structogramFigureVBox, "functionLabel");
        functionLabel.setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10;" +
                "-fx-padding: 2"
        );

        setFunctionString();
    }

    /**
     * Set the function string, which contains
     * the structogram type, name, and parameters.
     */
    public void setFunctionString(){
        String parameterString = "";
        for (Declaration declaration : StructogramFactory.getStructogram().getParameterList()){
            if (parameterString.equals("")){
                parameterString = declaration.getVarType() + arrayMark(declaration) + " " + declaration.getVarName();
            } else {
                parameterString = parameterString + ", " + declaration.getVarType() + arrayMark(declaration) + " " + declaration.getVarName();
            }
        }

        functionString = StructogramFactory.getStructogram().getFuncType() + " " + StructogramFactory.getStructogram().getFuncName() + "(" + parameterString + ")";
        functionLabel.setText(functionString);
    }

    /**
     * Set array mark to a parameter in the figure's header,
     * in cases, where the parameter is an array
     * @param declaration current parameter
     * @return array mark, or empty string, depending on parameter type
     */
    private String arrayMark(Declaration declaration){
        if (declaration instanceof ArrayDeclaration){
            return "[]";
        } else {
            return "";
        }
    }

    /**
     * Gets the visible wrapper object of structogram figure objects.
     * @return visible wrapper object of structogram figure objects
     */
    public StructogramWrapperVBox getStructogramWrapperVBox(){
        return structogramWrapperVBox;
    }

    /**
     * Gets the visible structogram object.
     * @return visible structogram object
     */
    public VBox getStructogramFigureVBox(){
        return structogramFigureVBox;
    }

    /**
     * Gets that the structogram figure is currently visible, or not.
     * @return is figure visible
     */
    public boolean isStructogramFigureVisible(){
        return structogramFigureVBox.isVisible();
    }

    /**
     * Sets the structogram figure visibility.
     * @param isVisible is figure visible
     */
    public void setStructogramFigureVisibility(boolean isVisible){
        structogramFigureVBox.setVisible(isVisible);
    }
}
