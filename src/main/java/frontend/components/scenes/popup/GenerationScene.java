package frontend.components.scenes.popup;

import backend.enums.Language;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.factorys.StructogramFactory;
import frontend.stages.popup.GenerationStage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.CodeArea;

/**
 * The user can choose, which language he/she want
 * to generate, and copy the generated code from the scene.
 */
public class GenerationScene extends PopUpScene {

    private HBox layout;
    private VBox buttonVBox;
    private CodeArea codeArea;
    private GenerationStage window;
    private Label languageLabel;
    private Button javaButton, cppButton, pythonButton;

    /**
     * Constructor for GenerationScene.
     * @param layout current scene's layout
     * @param frame current window
     */
    public GenerationScene(HBox layout, GenerationStage frame) {
        super(layout, 900, 500);
        this.layout = layout;
        this.window = frame;

        initGenerationLayout();
    }

    /**
     * Initialize layout.
     */
    private void initGenerationLayout() {
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));

        initButtons();

        codeArea = FxComponentFactory.generateCodeArea(layout, "codeArea", layout.getWidth()*0.85, layout.getHeight());
        codeArea.setEditable(false);
    }

    /**
     * Initialize operation buttons.
     */
    private void initButtons(){
        buttonVBox = new VBox();
        buttonVBox.setSpacing(10);

        javaButton = FxComponentFactory.generateButton(buttonVBox, "Java", "javaButton", layout.getWidth()*0.15, 40);
        cppButton = FxComponentFactory.generateButton(buttonVBox, "C++", "cppButton", layout.getWidth()*0.15, 40);
        pythonButton = FxComponentFactory.generateButton(buttonVBox, "Python", "pythonButton", layout.getWidth()*0.15, 40);

        Tooltip.install(pythonButton, new Tooltip(
                "Python does not contains switch statement, and do..while loop.\n" +
                "These statements are emulated.\n" +
                "-switch: if statement\n" +
                "-do_while: endless loop, with an if at its end,\n" +
                "which contains the original loop condition,\n" +
                "and a break statement in its body.\n" +
                "In the case os declarations, the program generate definitions\n" +
                "with initial values ('', \"\", 0) for this language."));

        javaButton.setOnAction(e -> generateCode(Language.JAVA));
        cppButton.setOnAction(e -> generateCode(Language.CPP));
        pythonButton.setOnAction(e -> generateCode(Language.PYTHON));

        layout.getChildren().add(buttonVBox);
    }

    /**
     * Call the generateCode method from factory, and replaces
     * the code area text to the actual generated code.
     * @param language current language
     */
    private void generateCode(Language language) {
        codeArea.replaceText(StructogramFactory.generateCode(language));
    }
}
