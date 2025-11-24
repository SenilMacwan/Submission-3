import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;

public class CodeTesterUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setBackground(new Background(
                new BackgroundFill(Color.rgb(245, 245, 245), CornerRadii.EMPTY, Insets.EMPTY)
        ));

        // ===================== TITLE =====================
        VBox titleBox = new VBox();
        titleBox.setPadding(new Insets(30, 0, 10, 0));
        titleBox.setAlignment(Pos.CENTER);

        Text title = new Text("CODE TESTER");
        title.setFont(Font.font("Arial", 36));
        title.setFill(Color.BLACK);
        titleBox.getChildren().add(title);
        root.setTop(titleBox);

        // ===================== RIGHT INFO =====================
        VBox infoBox = new VBox(5);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setPadding(new Insets(10, 25, 0, 0));
        BorderPane.setAlignment(infoBox, Pos.TOP_RIGHT);

        Text info1 = new Text("CS 2043");
        Text info2 = new Text("Team 10");
        Text info3 = new Text("Group Members");

        info1.setFill(Color.rgb(60, 60, 60));
        info2.setFill(Color.rgb(60, 60, 60));
        info3.setFill(Color.rgb(60, 60, 60));

        info1.setFont(Font.font(15));
        info2.setFont(Font.font(15));
        info3.setFont(Font.font(15));

        infoBox.getChildren().addAll(info1, info2, info3);
        root.setRight(infoBox);

        // ===================== BOTTOM: MSG + BUTTONS =====================
        HBox bottom = new HBox();
        bottom.setSpacing(40);
        bottom.setPadding(new Insets(30, 40, 40, 40));

        // ------ LEFT MESSAGE BOX ------
        VBox msgBox = new VBox();
        msgBox.setAlignment(Pos.TOP_LEFT);
        msgBox.setPadding(new Insets(20));
        msgBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(225, 235, 245),
                new CornerRadii(10),
                Insets.EMPTY
        )));
        DropShadow msgShadow = new DropShadow();
        msgShadow.setRadius(12);
        msgShadow.setColor(Color.gray(0.5, 0.3));
        msgBox.setEffect(msgShadow);

        Text msgText = new Text("Click a button...");
        msgText.setFont(Font.font("Arial", 18));
        msgText.setFill(Color.rgb(40, 40, 70));
        msgBox.getChildren().add(msgText);

        // ------ RIGHT BUTTON PANEL ------
        VBox buttonBox = new VBox(12);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(25));
        buttonBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(220, 230, 255),
                new CornerRadii(15),
                Insets.EMPTY
        )));
        DropShadow shadow = new DropShadow();
        shadow.setRadius(18);
        shadow.setColor(Color.gray(0.6, 0.35));
        buttonBox.setEffect(shadow);

        Button btnCreateTestCase = new Button("Create Test Case");
        Button btnLoadTestCase = new Button("Load Test Case");
        Button btnCreateTestSuite = new Button("Create Test Suite");
        Button btnSaveTestCase = new Button("Save Test Case");
        Button btnAddToSuite = new Button("Add Test Case to Test Suite");
        Button btnTraceTestCase = new Button("Trace test case");

        applyCoolButtonStyle(btnCreateTestCase);
        applyCoolButtonStyle(btnLoadTestCase);
        applyCoolButtonStyle(btnCreateTestSuite);
        applyCoolButtonStyle(btnSaveTestCase);
        applyCoolButtonStyle(btnAddToSuite);
        applyCoolButtonStyle(btnTraceTestCase);

        // ===================== BUTTON HANDLERS WITH INT INPUT =====================
        btnCreateTestCase.setOnAction(e -> {
            try {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setHeaderText("Enter Test Case Title:");
                String TCtitle = dialog.showAndWait().orElse("");

                dialog.setHeaderText("Enter Test Case Input (int):");
                int TCinput = Integer.parseInt(dialog.showAndWait().orElse("0"));

                dialog.setHeaderText("Enter Expected Output (int):");
                int TCoutput = Integer.parseInt(dialog.showAndWait().orElse("0"));

                StringBuilder debug = new StringBuilder();
                boolean success = COORD.NewTestCase(TCtitle, TCinput, TCoutput, debug);
                msgText.setText(debug.toString());
            } catch (Exception ex) {
                msgText.setText("❌ Invalid input. Please enter an integer.\n" + ex.getMessage());
            }
        });

        btnLoadTestCase.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Test Case File Name:");
            String fileName = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            boolean success = COORD.LoadTestCase(fileName, debug);
            msgText.setText(debug.toString());
        });

        btnCreateTestSuite.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Test Suite Name:");
            String TSname = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            boolean success = COORD.CreateTestSuite(TSname, debug);
            msgText.setText(debug.toString());
        });

        btnSaveTestCase.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Test Case Name to Save:");
            String tcName = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Enter File Name (.txt):");
            String fileName = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            boolean success = COORD.SaveTestCase(tcName, fileName, debug);
            msgText.setText(debug.toString());
        });

        btnAddToSuite.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Test Case Name:");
            String tcName = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Enter Test Suite Name:");
            String tsName = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            boolean success = COORD.addTestCaseToTestSuite(tsName, tcName, debug);
            msgText.setText(debug.toString());
        });

        btnTraceTestCase.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Test Case Name:");
            String tcName = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Enter Program Folder Path:");
            String folder = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            String output = COORD.traceTestCase(tcName, folder, debug);
            debug.append("\n=== Trace Output ===\n").append(output);
            msgText.setText(debug.toString());
        });
        

Button btnRunTestSuite = new Button("Run Test Suite");
applyCoolButtonStyle(btnRunTestSuite);

btnRunTestSuite.setOnAction(e -> {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setHeaderText("Enter Test Suite Name:");
    String tsName = dialog.showAndWait().orElse("");

    dialog.setHeaderText("Enter Root Program Folder Path:");
    String folder = dialog.showAndWait().orElse("");

    StringBuilder debug = new StringBuilder();
    ExecuteTestSuite runner = new ExecuteTestSuite(new COORD());
    runner.runSuite(tsName, folder, debug);

    msgText.setText(debug.toString());
});



buttonBox.getChildren().add(btnRunTestSuite);


        buttonBox.getChildren().addAll(
                btnCreateTestCase,
                btnLoadTestCase,
                btnCreateTestSuite,
                btnSaveTestCase,
                btnAddToSuite,
                btnTraceTestCase
        );

        bottom.getChildren().addAll(msgBox, buttonBox);
        HBox.setHgrow(msgBox, Priority.ALWAYS);
        root.setBottom(bottom);

        Scene scene = new Scene(root, 900, 550);
        primaryStage.setTitle("Code Tester");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void applyCoolButtonStyle(Button b) {
        b.setPrefWidth(180);
        b.setFont(Font.font("Arial", 14));
        b.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #f8f8f8, #e0e0e0); " +
                "-fx-border-color: #999; " +
                "-fx-border-radius: 6; " +
                "-fx-background-radius: 6;"
        );
        b.setOnMouseEntered(e -> b.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffffff, #d8e2ff); " +
                "-fx-border-color: #666; " +
                "-fx-border-radius: 6; " +
                "-fx-background-radius: 6;"
        ));
        b.setOnMouseExited(e -> b.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #f8f8f8, #e0e0e0); " +
                "-fx-border-color: #999; " +
                "-fx-border-radius: 6; " +
                "-fx-background-radius: 6;"
        ));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
