import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Optional;
/*******************************************************
*Handles the user interface for the application.
*
* CS 2043 / Group 10
*******************************************************/

public class CodeTesterUI extends Application
{
    COORD coord = new COORD();  // MASTER CONTROLLER

    @Override
    public void start(Stage primaryStage)
    {
        // Main layout
        BorderPane root = new BorderPane();

        // Title
        Text title = new Text("CODE TESTER");
        title.setFont(Font.font("Arial", 36));
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        // Top right info
        VBox infoBox = new VBox();
        infoBox.setAlignment(Pos.TOP_RIGHT);
        infoBox.getChildren().addAll
        (
                new Text("CS 2043"),
                new Text("Team 10"),
                new Text("Group Project")
        );
        root.setRight(infoBox);

        // Buttons
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button btnCreateTestCase = new Button("Create Test Case");
        Button btnLoadTestCase = new Button("Load Test Case");
        Button btnCreateTestSuite = new Button("Create Test Suite");
        Button btnSaveTestCase = new Button("Save Test Case");
        Button btnAddToSuite = new Button("Add Test Case to Test Suite");
        Button btnRunSuite = new Button("Run Test Suite");

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


        // =====================================================
        // BUTTON LOGIC
        // =====================================================

        // Create Test Case
        btnCreateTestCase.setOnAction(e -> 
        {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter test case in format: title,input,expected");
            Optional<String> result = d.showAndWait();

            result.ifPresent(data -> 
            {
                try 
                {
                    String[] parts = data.split(",");
                    coord.NewTestCase(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    System.out.println("Created Test Case: " + parts[0]);
                } 
                catch (Exception ex) 
                {
                    System.out.println("Invalid format.");
                }
            });
        });

        // Load Test Case from file
        btnLoadTestCase.setOnAction(e -> 
        {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter filename to load:");
            Optional<String> result = d.showAndWait();

            result.ifPresent(filename -> coord.LoadTestCase(filename));
        });

        // Create Test Suite
        btnCreateTestSuite.setOnAction(e -> 
        {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter Test Suite Name:");
            Optional<String> result = d.showAndWait();

            result.ifPresent(name -> coord.NewTestSuite(name));
        });

        // Save Test Case
        btnSaveTestCase.setOnAction(e -> 
        {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter test case title to save:");
            Optional<String> result = d.showAndWait();

            result.ifPresent(title -> 
            {
                TestCase tc = ListTC.search(title);
                
                if (tc == null) 
                {
                    System.out.println("Test Case not found.");
                    return;
                }

                TextInputDialog d2 = new TextInputDialog();
                d2.setHeaderText("Enter filename to save into:");
                Optional<String> f = d2.showAndWait();

                f.ifPresent(file -> tc.save(file));
            });
        });

        // Add Test Case to Suite
        btnAddToSuite.setOnAction(e -> 
        {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter Suite Name:");
            Optional<String> s = d.showAndWait();

            if (!s.isPresent()) return;

            TextInputDialog d2 = new TextInputDialog();
            d2.setHeaderText("Enter Test Case Title:");
            Optional<String> t = d2.showAndWait();

            if (!t.isPresent()) return;

            coord.AddTestCaseToSuite(s.get(), t.get());
        });

        // Run Test Suite
        btnRunSuite.setOnAction(e -> 
        {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter Suite Name to Execute:");
            Optional<String> result = d.showAndWait();

            result.ifPresent(name -> coord.ExecuteSuite(name));
        });


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



