import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class CodeTesterUI extends Application {

    private java.util.List<StudentResults> lastResultsV1;
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

        Label title = new Label("CODE TESTER");
        title.setFont(Font.font("Arial", 36));
        title.setTextFill(Color.BLACK);
        titleBox.getChildren().add(title);
        root.setTop(titleBox);

        // ===================== RIGHT INFO =====================
        VBox infoBox = new VBox(5);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setPadding(new Insets(10, 25, 0, 0));
        BorderPane.setAlignment(infoBox, Pos.TOP_RIGHT);

        Label info1 = new Label("CS 2043");
        Label info2 = new Label("Team 10");
        Label info3 = new Label("Group Members");

        info1.setFont(Font.font(15));
        info2.setFont(Font.font(15));
        info3.setFont(Font.font(15));

        infoBox.getChildren().addAll(info1, info2, info3);
        root.setRight(infoBox);

        // ===================== BOTTOM LAYOUT =====================
        HBox bottom = new HBox();
        bottom.setSpacing(40);
        bottom.setPadding(new Insets(30, 40, 40, 40));

        // ===================== SCROLLABLE OUTPUT AREA =====================
        Label msgText = new Label("Click a button...");
        msgText.setFont(Font.font("Arial", 18));
        msgText.setTextFill(Color.rgb(40, 40, 70));
        msgText.setWrapText(true);

        VBox msgContent = new VBox(msgText);
        msgContent.setAlignment(Pos.TOP_LEFT);
        msgContent.setPadding(new Insets(20));
        msgContent.setPrefWidth(520);

        ScrollPane msgBox = new ScrollPane(msgContent);
        msgBox.setFitToWidth(true);
        msgBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        msgBox.setStyle("-fx-background-color: transparent;");
        msgBox.setPrefWidth(550);
        msgBox.setPrefHeight(430);

        // ===================== BUTTON PANEL =====================
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

        Button btnCreateTestCase     = new Button("Create Test Case");
        Button btnLoadTestCase       = new Button("Load Test Case");
        Button btnCreateTestSuite    = new Button("Create Test Suite");
        Button btnSaveTestCase       = new Button("Save Test Case");
        Button btnAddToSuite         = new Button("Add Test Case to Suite");
        Button btnTraceTestCase      = new Button("Trace Test Case");
        Button btnPrintTestCase      = new Button("Print Test Case");
        Button btnRunTestSuite       = new Button("Run Test Suite");
        Button btnRunTestSuiteV2     = new Button("Run Test Suite\non Different Folder");
        Button btnLoadResults        = new Button("Load Results");
        Button btnCompareSuccess     = new Button("Compare Success Rate");
        Button btnSingleSuccess      = new Button("Student Success Rate");
        Button btnSaveLatestResults = new Button("Save Results");
        applyCoolButtonStyle(btnSaveLatestResults);
        btnSaveLatestResults.setDisable(true);   // disabled until something runs

        
        applyCoolButtonStyle(btnCreateTestCase);
        applyCoolButtonStyle(btnLoadTestCase);
        applyCoolButtonStyle(btnCreateTestSuite);
        applyCoolButtonStyle(btnSaveTestCase);
        applyCoolButtonStyle(btnAddToSuite);
        applyCoolButtonStyle(btnTraceTestCase);
        applyCoolButtonStyle(btnPrintTestCase);
        applyCoolButtonStyle(btnRunTestSuite);
        applyCoolButtonStyle(btnRunTestSuiteV2);
        applyCoolButtonStyle(btnLoadResults);
        applyCoolButtonStyle(btnCompareSuccess);
        applyCoolButtonStyle(btnSingleSuccess);

        // ===================== BUTTON HANDLERS =====================

        btnCreateTestCase.setOnAction(e -> {
            try {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setHeaderText("Enter Test Case Title:");
                String titleTC = dialog.showAndWait().orElse("");

                dialog.setHeaderText("Enter Input:");
                int input = Integer.parseInt(dialog.showAndWait().orElse("0"));

                dialog.setHeaderText("Enter Expected Output:");
                int expected = Integer.parseInt(dialog.showAndWait().orElse("0"));

                StringBuilder debug = new StringBuilder();
                COORD.NewTestCase(titleTC, input, expected, debug);
                msgText.setText(debug.toString());

            } catch (Exception ex) {
                msgText.setText("Invalid input.");
            }
        });

        btnLoadTestCase.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter File Name:");
            String file = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            COORD.LoadTestCase(file, debug);
            msgText.setText(debug.toString());
        });

        btnCreateTestSuite.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Suite Name:");
            String suite = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            COORD.CreateTestSuite(suite, debug);
            msgText.setText(debug.toString());
        });

        btnSaveTestCase.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter TestCase Title:");
            String titleTC = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Enter File Name:");
            String file = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            COORD.SaveTestCase(titleTC, file, debug);
            msgText.setText(debug.toString());
        });

        btnAddToSuite.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Suite Name:");
            String suite = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Test Case Name:");
            String tc = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            COORD.addTestCaseToTestSuite(suite, tc, debug);
            msgText.setText(debug.toString());
        });

        btnTraceTestCase.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Test Case Title:");
            String titleTC = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Folder Path:");
            String folder = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();
            String out = COORD.traceTestCase(titleTC, folder, debug);
            msgText.setText(debug + "\nOUTPUT:\n" + out);
        });

        btnPrintTestCase.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Test Case Title:");
            String titleTC = dialog.showAndWait().orElse("");

            msgText.setText(COORD.printTC(titleTC));
        });

        // Simple Run Test Suite + save V1 results
        btnRunTestSuite.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Suite Name:");
            String suite = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Root Folder Path:");
            String folder = dialog.showAndWait().orElse("");

            // This is the log that ExecuteTestSuite writes into
            StringBuilder debug = new StringBuilder();
            ExecuteTestSuite runner = new ExecuteTestSuite();
            try {
                runner.runSuite(suite, folder, debug);
            } catch (java.io.IOException ioe) {
                ioe.printStackTrace();
                debug.append("\nIOException while running suite.\n");
            }

            // Optional: also compute per-student results for saving
            StringBuilder debug2 = new StringBuilder();
            List<StudentResults> results =
                    COORD.runTestSuiteOnFolder(suite, folder, debug2);

             // Store for "Save Results" button
            lastResultsV1 = results;

            if (results != null && !results.isEmpty()) {
                btnSaveLatestResults.setDisable(false);
                // if you want to show debug2 as well, uncomment:
                // debug.append("\n").append(debug2);
            } else {
                btnSaveLatestResults.setDisable(true);
                debug.append("\nNo results to save.\n");
            }

            Platform.runLater(() -> msgText.setText(debug.toString()));
        });

        // Run Test Suite on different folder (V2) + save V2 results
        btnRunTestSuiteV2.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Suite Name:");
            String suite = dialog.showAndWait().orElse("");

            if (suite == null || suite.trim().isEmpty()) {
                msgText.setText("No suite name provided.");
                return;
            }

            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Select Different Root Folder");
            File folder = chooser.showDialog(primaryStage);

            if (folder == null) {
                msgText.setText("No folder selected.");
                return;
            }

            StringBuilder debug = new StringBuilder();

            List<StudentResults> resultsV2 =
                    COORD.runTestSuiteOnFolder(suite, folder.getAbsolutePath(), debug);

            if (resultsV2 != null && !resultsV2.isEmpty()) {
                COORD.saveStudentResults(resultsV2, "results_v2.ser", debug);
            } else {
                debug.append("No results to save.\n");
            }

            msgText.setText(debug.toString());
        });

        // Load serialized results and display summary
        btnLoadResults.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select Results File");
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Serialized Results (.ser)", "*.ser")
            );

            File file = chooser.showOpenDialog(primaryStage);

            if (file == null) {
                msgText.setText("No results file selected.");
                return;
            }

            StringBuilder debug = new StringBuilder();
            List<StudentResults> results =
                    COORD.loadStudentResults(file.getAbsolutePath(), debug);

            StringBuilder out = new StringBuilder();
            out.append(debug).append("\n");

            if (results == null || results.isEmpty()) {
                out.append("No student results found in file.");
            } else {
                out.append("Loaded results for ")
                        .append(results.size()).append(" students:\n\n");

                for (StudentResults sr : results) {
                    int total = sr.getTotalTests();
                    int passed = sr.getPassedTests();
                    double rate = (total > 0) ? (passed * 100.0 / total) : 0.0;

                    out.append("Student: ").append(sr.getStudentName()).append("\n");
                    out.append("Passed: ").append(passed)
                            .append(" / ").append(total)
                            .append(String.format(" (%.1f%%)", rate)).append("\n\n");
                }
            }

            msgText.setText(out.toString());
        });

        // Compare success rate between two student folders
        btnCompareSuccess.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Test Suite Name:");
            String suiteName = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Enter Student 1 Folder Path:");
            String student1 = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Enter Student 2 Folder Path:");
            String student2 = dialog.showAndWait().orElse("");

            String result = COORD.compareStudents(suiteName, student1, student2);

            msgText.setText(result);
        });
        
        btnSingleSuccess.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Test Suite Name:");
            String suiteName = dialog.showAndWait().orElse("");

            dialog.setHeaderText("Enter Student Folder Path:");
            String studentFolder = dialog.showAndWait().orElse("");

            StringBuilder debug = new StringBuilder();

            // Run test suite on this one folder only
            List<StudentResults> results =
                    COORD.runTestSuiteOnFolder(suiteName, studentFolder, debug);

            if (results == null || results.isEmpty()) {
                msgText.setText("No results found.\n" + debug);
                return;
            }

            StudentResults sr = results.get(0);

            int total = sr.getTotalTests();
            int passed = sr.getPassedTests();
            double rate = (total == 0) ? 0 : (passed * 100.0 / total);

            String out =
                    "Student: " + sr.getStudentName() + "\n" +
                    "Passed: " + passed + " / " + total + "\n" +
                    String.format("Success Rate: %.1f%%", rate);

            msgText.setText(out);
        });
        
        btnSaveLatestResults.setOnAction(e -> {
            StringBuilder dbg = new StringBuilder();

            if (lastResultsV1 == null || lastResultsV1.isEmpty()) {
                msgText.setText("No results available to save.");
                return;
            }

            COORD.saveStudentResults(lastResultsV1, "results_v1.ser", dbg);
            msgText.setText("Saved results to results_v1.ser\n" + dbg);
        });



        // Add buttons
        buttonBox.getChildren().addAll(
            btnCreateTestCase, btnLoadTestCase, btnCreateTestSuite,
            btnSaveTestCase, btnAddToSuite, btnTraceTestCase,
            btnPrintTestCase, btnRunTestSuite, btnRunTestSuiteV2,
            btnLoadResults, btnCompareSuccess, btnSingleSuccess,
            btnSaveLatestResults
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
                "-fx-background-color: linear-gradient(to bottom, #f8f8f8, #e0e0e0);" +
                "-fx-border-color: #999;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );
        b.setOnMouseEntered(e -> b.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffffff, #d8e2ff);" +
                        "-fx-border-color: #666;"
        ));
        b.setOnMouseExited(e -> b.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #f8f8f8, #e0e0e0);" +
                        "-fx-border-color: #999;"
        ));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
