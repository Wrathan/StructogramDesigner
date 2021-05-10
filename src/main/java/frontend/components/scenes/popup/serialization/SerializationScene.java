package frontend.components.scenes.popup.serialization;

import backend.entities.structograms.Structogram;
import backend.serialization.Serialize;
import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.scenes.popup.PopUpScene;
import frontend.components.tableviews.SerFunctionTableView;
import frontend.stages.popup.serialization.SerializationStage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.File;

/**
 * Abstract superclass for load/save scene.
 */
public abstract class SerializationScene extends PopUpScene {

    protected final SerializationStage window;
    protected final VBox layout;
    protected HBox functionsHBox, operationFieldsHBox;
    protected VBox imageVBox;
    protected SerFunctionTableView serFunctionTableView;
    protected TextField operationTextField;
    protected Button serializationButton;

    /**
     * Constructor for SerializationScene.
     * @param layout current scene's layout
     * @param frame current window
     */
    protected SerializationScene(VBox layout, SerializationStage frame) {
        super(layout, 750, 400);
        this.layout = layout;
        this.window = frame;

        initLayout();
        initFunctionsTable();
        initOperationTextField();
    }

    /**
     * Initialize layout.
     */
    private void initLayout() {
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);
        layout.setPadding(new Insets(20, 20, 20, 20));
    }

    /**
     * Initialize table of structogram objects.
     */
    private void initFunctionsTable(){
        functionsHBox = new HBox();
        imageVBox = new VBox();
        serFunctionTableView = FxComponentFactory.generateSerFunctionTableView(functionsHBox, layout.getWidth(), layout.getHeight()*0.8, "serFunctionTableView");
        serFunctionTableView.loadData(Serialize.getConfigHomePath() + "/saves");

        functionsHBox.setSpacing(30);

        functionsHBox.getChildren().add(imageVBox);
        serFunctionTableView.setRowFactory(e -> initRowFactory());

        layout.getChildren().add(functionsHBox);
    }

    /**
     * Initialize operation text field, which should contains
     * the name of the structogram, the user want to load/save.
     */
    private void initOperationTextField(){
        operationFieldsHBox = new HBox();
        operationFieldsHBox.setSpacing(30);
        operationFieldsHBox.setAlignment(Pos.BOTTOM_CENTER);

        operationTextField = FxComponentFactory.generateTextField(operationFieldsHBox, "operationTextField");
        serializationButton = FxComponentFactory.generateButton(operationFieldsHBox, "serializationButton");

        HBox.setHgrow(operationTextField, Priority.ALWAYS);

        serializationButton.setOnAction(e -> serialOperation());

        layout.getChildren().add(operationFieldsHBox);
    }

    /**
     * Initialize row factory for TableView.
     * @return current row
     */
    private TableRow<Structogram> initRowFactory() {
        TableRow<Structogram> row = new TableRow<>();
        row.setOnMouseClicked(e -> initFields(e, row.getItem()));
        return row;
    }

    /**
     * Initialize image of currently selected structogram
     * in the table, and shows it next to the table
     * @param e current mouse event
     * @param structogram current structogram
     */
    private void initFields(MouseEvent e, Structogram structogram) {
        if (structogram != null){
            File file = new File(Serialize.getConfigHomePath() + "/saves/images/" + structogram.getFileName() + ".png");
            ImageView imageView = new ImageView(new Image(file.toURI().toString()));
            imageView.setId("imageView");
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            if (imageVBox.getChildren().size() == 0){
                imageVBox.getChildren().add(imageView);
            } else {
                GuiManager.updatePane(imageVBox, 0, imageView);
            }
            operationTextField.setText(structogram.getFileName());
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2){
                serialOperation();
            }
        }
    }

    /**
     * Serialization operation for actual serialization button of subclass.
     */
    protected abstract void serialOperation();
}