import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CodeTesterUI extends Application 
{

    @Override
    public void start(Stage primaryStage)
    {
        // Main layout
        BorderPane root = new BorderPane();

        // Big bold title at top center
        Text title = new Text("CODE TESTER");
        title.setFont(Font.font("Arial", 36));
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        // Top right info text
        VBox infoBox = new VBox();
        infoBox.setAlignment(Pos.TOP_RIGHT);
        Text info1 = new Text("CS 2263");
        Text info2 = new Text("Team 10");
        Text info3 = new Text("Group Project");
        infoBox.getChildren().addAll(info1, info2, info3);
        root.setRight(infoBox);

        // Vertical buttons
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button btnCreateTestCase = new Button("Create Test Case");
        Button btnLoadTestCase = new Button("Load Test Case");
        Button btnCreateTestSuite = new Button("Create Test Suite");
        Button btnSaveTestCase = new Button("Save Test Case");
        Button btnAddToSuite = new Button("Add Test Case to Test Suite");
        Button btnRunSuite = new Button("Run Test Suite"); // Should eventually run ExecuteTestSuite.java

        buttonBox.getChildren().addAll
            (
                btnCreateTestCase,
                btnLoadTestCase,
                btnCreateTestSuite,
                btnSaveTestCase,
                btnAddToSuite,
                btnRunSuite
        );

        root.setCenter(buttonBox);

        // Scene setup
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Code Tester");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

