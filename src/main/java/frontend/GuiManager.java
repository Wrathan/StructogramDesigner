package frontend;

import backend.serialization.Serialize;
import frontend.stages.DashboardStage;
import frontend.stages.EditorStage;
import frontend.stages.popup.ConfirmStage;
import frontend.stages.popup.ErrorStage;
import frontend.stages.popup.GenerationStage;
import frontend.stages.popup.ImageStage;
import frontend.stages.popup.serialization.LoadStage;
import frontend.stages.popup.serialization.SaveStage;
import frontend.stages.popup.serialization.SerializationStage;
import frontend.stages.popup.structogramproperties.ModifyPropertiesStage;
import frontend.stages.popup.structogramproperties.NewPropertiesStage;
import frontend.stages.popup.structogramproperties.StructogramPropertiesStage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Contains methods for graphical user interface.
 */
public class GuiManager extends Application {

    private static Stage screen;

    /**
     * Overridden start method for the JavaFX application.
     * @param primaryStage primary stage of application
     */
    @Override
    public void start(Stage primaryStage){
        Serialize.setHome();
        primaryStage = new DashboardStage();
        screen = primaryStage;
        primaryStage.show();
    }

    /**
     * Opens a new editor window.
     * @return editor window
     */
    public static Stage openEditorStage(){
        if (screen != null){
            screen.close();
        }
        screen = new EditorStage();
        screen.show();
        return screen;
    }

    /**
     * Opens a new error window.
     * @param message current error message
     */
    public static void openErrorStage(String message){
        ErrorStage errorStage = new ErrorStage(message);
        errorStage.initOwner(screen);
        errorStage.showAndWait();
    }

    /**
     * Opens a new generation window.
     */
    public static void openGenerationStage(){
        GenerationStage generationStage = new GenerationStage();
        generationStage.initOwner(screen);
        generationStage.showAndWait();
    }

    /**
     * Opens structogram properties window for a new structogram.
     * @return structogram properties window
     */
    public static StructogramPropertiesStage openNewPropertiesStage(){
        StructogramPropertiesStage structogramPropertiesStage = new NewPropertiesStage();
        structogramPropertiesStage.initOwner(screen);
        structogramPropertiesStage.showAndWait();

        return structogramPropertiesStage;
    }

    /**
     * Opens structogram properties window for an existingstructogram.
     */
    public static void openModifyPropertiesStage(){
        StructogramPropertiesStage structogramPropertiesStage = new ModifyPropertiesStage();
        structogramPropertiesStage.initOwner(screen);
        structogramPropertiesStage.showAndWait();
    }

    /**
     * Opens a new loading window.
     */
    public static void openLoadStage(){
        SerializationStage serializationStage = new LoadStage();
        serializationStage.initOwner(screen);
        serializationStage.showAndWait();
    }

    /**
     * Opens a new saving window.
     */
    public static void openSaveStage(){
        SerializationStage serializationStage = new SaveStage();
        serializationStage.initOwner(screen);
        serializationStage.showAndWait();
    }

    /**
     * Opens a new image window.
     * @param imageView current image
     */
    public static void openImageStage(ImageView imageView){
        ImageStage imageStage = new ImageStage(imageView);
        imageStage.initOwner(screen);
        imageStage.showAndWait();
    }

    /**
     * Opens a new confirmation window.
     * @param text current text of window
     * @return current confirmation state
     */
    public static boolean openConfirmStage(String text){
        ConfirmStage confirmStage = new ConfirmStage(text);
        confirmStage.initOwner(screen);
        confirmStage.showAndWait();
        return confirmStage.isAgreed();
    }

    /**
     * Opens Javadoc in the default browser of the computer
     * @throws IOException when javadoc file not found
     */
    public static void openJavaDoc() throws IOException {
        File htmlFile = new File(Serialize.getConfigHomePath() + "/javadoc/index.html");
        Desktop.getDesktop().browse(htmlFile.toURI());
    }

    /**
     * Updates current panel.
     * @param pane current panel's parent
     * @param index index of modifiable panel
     * @param newChild new panel
     */
    public static void updatePane(Pane pane, int index, Node newChild){
        pane.getChildren().set(index, newChild);
    }

    /**
     * Removes panel.
     * @param pane current panel's parent
     * @param index index of removable panel
     */
    public static void removePane(Pane pane, int index){
        pane.getChildren().remove(index);
    }

    /**
     * Exits application.
     */
    public static void exitProgram(){
        Platform.exit();
    }

    /**
     * Gets current window.
     * @return current window
     */
    public static Stage getScreen() {
        return screen;
    }

    /**
     * Sets current window.
     * @param screen current window
     */
    public static void setScreen(Stage screen) {
        GuiManager.screen = screen;
    }
}
