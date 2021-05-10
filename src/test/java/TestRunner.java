import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import testclasses.backend.SerializeEntityTests;
import testclasses.frontend.factorys.FxComponentFactoryTest;
import testclasses.frontend.factorys.StructogramGenerationTests;
import testclasses.frontend.javafx.dashboardpanel.DashboardPanelItemTests;
import testclasses.frontend.javafx.dashboardpanel.DashboardPanelTests;
import testclasses.frontend.javafx.editorpanel.MenuTests;
import testclasses.frontend.javafx.editorpanel.StatementPanelItemTests;
import testclasses.frontend.javafx.editorpanel.figure.SelectionTests;
import testclasses.frontend.javafx.editorpanel.figure.StructureTests;
import testclasses.frontend.javafx.editorpanel.generationpanel.CodeGenerationTests;
import testclasses.frontend.javafx.editorpanel.insertion.CondStatInsertionTests;
import testclasses.frontend.javafx.editorpanel.insertion.FunctionOperationTests;
import testclasses.frontend.javafx.editorpanel.insertion.JumpStatTests;
import testclasses.frontend.javafx.editorpanel.insertion.LoopInsertionTests;
import testclasses.frontend.javafx.editorpanel.serializationpanels.LoadPanelPanelTests;
import testclasses.frontend.javafx.editorpanel.serializationpanels.SavePanelPanelTests;
import testclasses.frontend.javafx.editorpanel.updatepanel.UpdatePanelTests;

@RunWith(JUnitPlatform.class)
@SelectClasses({DashboardPanelItemTests.class, DashboardPanelTests.class, StatementPanelItemTests.class,
        testclasses.frontend.javafx.editorpanel.insertion.VarOperationInsertionTests.class, LoopInsertionTests.class, CondStatInsertionTests.class,
        JumpStatTests.class, FunctionOperationTests.class, LoadPanelPanelTests.class, SavePanelPanelTests.class,
        UpdatePanelTests.class, MenuTests.class, SelectionTests.class, StructureTests.class, CodeGenerationTests.class})
class JUnit5TestSuiteTestFxTests
{
}

@RunWith(JUnitPlatform.class)
@SelectClasses({FxComponentFactoryTest.class, StructogramGenerationTests.class})
class JUnit5TestSuiteFactoryTests
{
}

@RunWith(JUnitPlatform.class)
@SelectClasses(SerializeEntityTests.class)
class JUnit5TestSuiteSerializationTests
{
}
