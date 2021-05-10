package testclasses.frontend.javafx.dashboardpanel;

import frontend.GuiManager;
import frontend.stages.popup.serialization.LoadStage;
import frontend.stages.popup.structogramproperties.NewPropertiesStage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardPanelItemTests extends DashboardPanelTests {

    @Test
    public void checkButtonsText(){
        FxAssert.verifyThat("#newButton", LabeledMatchers.hasText("New"));
        FxAssert.verifyThat("#loadButton", LabeledMatchers.hasText("Load"));
        FxAssert.verifyThat("#exitButton", LabeledMatchers.hasText("Exit"));
    }

    @Test
    public void checkNumberOfButtons(){
        assertEquals(GuiManager.getScreen().getScene().getRoot().getChildrenUnmodifiable().size(), 3);
    }

    @Test
    public void openNewPropertiesStage(FxRobot robot){
        robot.clickOn("#newButton");
        assertTrue(getTopModalStage(robot) instanceof NewPropertiesStage);
    }

    @Test
    public void openLoadPropertiesStage(FxRobot robot){
        robot.clickOn("#loadButton");
        assertTrue(getTopModalStage(robot) instanceof LoadStage);
    }
}
