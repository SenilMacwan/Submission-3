import java.io.File;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class COORD {

    // Persistent global storage (created ONCE, never replaced)
    public static final ListTC listOfTestCase;
    public static final ListTS listOfTestSuite;

    // Static initializer (runs only once in JVM lifetime)
    static {
        listOfTestCase = new ListTC();
        listOfTestSuite = new ListTS();
    }

    // ============================================
    // Create Test Case
    // ============================================
    public static boolean NewTestCase(String title, int input, int Exoutput, StringBuilder debug) {
        try {
            debug.append("Creating TestCase: ").append(title).append("\n");

            if (title == null || title.isEmpty()) {
                debug.append("Error: Title is empty.\n");
                return false;
            }

            TestCase myTC = new TestCase(title, input, Exoutput);
            listOfTestCase.add(myTC);

            debug.append("TestCase added successfully.\n");
            return true;

        } catch (Exception e) {
            debug.append("Exception in NewTestCase: ").append(e).append("\n");
            return false;
        }
    }

    // ============================================
    // Load Test Case from File
    // ============================================
    public static boolean LoadTestCase(String fileName, StringBuilder debug) {
        try {
            debug.append("Loading TestCase from file: ").append(fileName).append("\n");

            if (fileName == null || fileName.isEmpty()) {
                debug.append("Error: File name is empty.\n");
                return false;
            }

            TestCase myTC = new TestCase("", 0, 0);
            myTC.initFromFile(fileName);

            listOfTestCase.add(myTC);

            debug.append("TestCase loaded successfully.\n");
            return true;

        } catch (Exception e) {
            debug.append("Exception in LoadTestCase: ").append(e).append("\n");
            return false;
        }
    }

    // ============================================
    // Create Test Suite
    // ============================================
    public static boolean CreateTestSuite(String Name, StringBuilder debug) {
        try {
            debug.append("Creating TestSuite: ").append(Name).append("\n");

            if (Name == null || Name.isEmpty()) {
                debug.append("Error: Suite name is empty.\n");
                return false;
            }

            TestSuite myTS = new TestSuite(Name);
            listOfTestSuite.add(myTS);

            debug.append("TestSuite added successfully.\n");
            return true;

        } catch (Exception e) {
            debug.append("Exception in CreateTestSuite: ").append(e).append("\n");
            return false;
        }
    }

    // ============================================
    // Save Test Case
    // ============================================
    public static boolean SaveTestCase(String Title, String Filename, StringBuilder debug) {
        try {
            debug.append("Saving TestCase '").append(Title)
                 .append("' to file ").append(Filename).append("\n");

            TestCase T = listOfTestCase.search(Title);
            if (T == null) {
                debug.append("Error: TestCase not found.\n");
                return false;
            }

            T.save(Filename);
            debug.append("Saved successfully.\n");
            return true;

        } catch (Exception e) {
            debug.append("Exception in SaveTestCase: ").append(e).append("\n");
            return false;
        }
    }

    // ============================================
    // Add Test Case to Test Suite
    // ============================================
    public static boolean addTestCaseToTestSuite(String tsName, String tcName, StringBuilder debug) {
        try {
            debug.append("Adding TestCase '").append(tcName)
                 .append("' to TestSuite '").append(tsName).append("'\n");

            TestSuite tempTS = listOfTestSuite.search(tsName);
            TestCase tempTC = listOfTestCase.search(tcName);

            if (tempTS == null) {
                debug.append("Error: TestSuite not found.\n");
                return false;
            }
            if (tempTC == null) {
                debug.append("Error: TestCase not found.\n");
                return false;
            }

            tempTS.addTestCase(tempTC);
            debug.append("Added successfully.\n");

            return true;

        } catch (Exception e) {
            debug.append("Exception in addTestCaseToTestSuite: ").append(e).append("\n");
            return false;
        }
    }

    // ============================================
    // Trace Test Case
    // ============================================
    public static String traceTestCase(String testCaseTitle, String programFolder, StringBuilder debug) {
        try {
            debug.append("Tracing TestCase: ").append(testCaseTitle)
                 .append(" in folder ").append(programFolder).append("\n");

            TestCase tc = listOfTestCase.search(testCaseTitle);
            if (tc == null) {
                debug.append("Error: TestCase not found.\n");
                return "TestCase not found.";
            }

            File folder = new File(programFolder);
            File[] javaFiles = folder.listFiles((dir, name) -> name.endsWith(".java"));

            if (javaFiles == null || javaFiles.length == 0) {
                debug.append("Error: No Java files.\n");
                return "No Java file found.";
            }

            File codeFile = javaFiles[0];
            String className = codeFile.getName().replace(".java", "");

            ExecuteTestSuite runner = new ExecuteTestSuite();

            debug.append("Compiling ").append(codeFile.getName()).append("\n");
            if (!runner.compileProgram(codeFile)) {
                debug.append("Compilation failed.\n");
                return "Compilation failed.";
            }

            debug.append("Running program...\n");

            String output = runner.runProgram(folder, className, String.valueOf(tc.input));

            debug.append("Program completed.\n");

            return (output == null ? "Runtime error." : output);

        } catch (Exception e) {
            debug.append("Exception in traceTestCase: ").append(e).append("\n");
            return "Error tracing test case.";
        }
    }

    // ============================================
    // Print Test Case
    // ============================================
    public static String printTC(String Title) {
        TestCase printable = listOfTestCase.search(Title);
        return (printable == null) ? "TestCase not found." : printable.toStringTC();
    }

    // ============================================
    // Saving results of running a test suite
    // ============================================
    public static boolean saveStudentResults(List<StudentResults> results,String fileName, StringBuilder debug)
    {
        try 
        {
            debug.append("Saving student results to file: ")
                 .append(fileName).append("\n");

            if (results == null || results.isEmpty()) 
            {
                debug.append("Error: No results to save.\n");
                return false;
            }

            if (fileName == null || fileName.isEmpty()) 
            {
                debug.append("Error: File name is empty.\n");
                return false;
            }

            // Serializing the list of results to a file
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) 
            {
                out.writeObject(results);
            }

            debug.append("Student results saved successfully.\n");
            return true;

        } 
        catch (Exception e) 
        {
            debug.append("Exception in saveStudentResults: ")
                 .append(e).append("\n");
            return false;
        }
    }

    // ============================================
    // Run TestSuite on a root folder of student programs
    // ============================================
    public static List<StudentResults> runTestSuiteOnFolder(String suiteName, String rootFolderPath,String x, StringBuilder debug) 
    {
        List<StudentResults> resultsList = new ArrayList<>();

        try 
        {
            debug.append("Running TestSuite '").append(suiteName)
                 .append("' on folder: ").append(rootFolderPath).append("\n");

            // Find the TestSuite by name
            TestSuite suite = listOfTestSuite.search(suiteName);
            if (suite == null) 
            {
                debug.append("Error: TestSuite not found.\n");
                return resultsList;
            }

            File root = new File(rootFolderPath);
            File[] studentFolders = root.listFiles(File::isDirectory);

            if (studentFolders == null || studentFolders.length == 0) 
            {
                debug.append("Error: No student folders found.\n");
                return resultsList;
            }

            ExecuteTestSuite runner = new ExecuteTestSuite();

            // Loop through each student's folder
            for (File studentFolder : studentFolders) 
            {

                String studentName = studentFolder.getName();
                debug.append("Testing student: ").append(studentName).append("\n");

                int total = suite.getTotalTestCases();
                int passed = 0;
                Map<String, Boolean> perTestResult = new HashMap<>();

                // For each TestCase in the TestSuite
                for (TestCase tc : suite.listTC) 
                {

                    File[] javaFiles = studentFolder.listFiles((dir, name) -> name.endsWith(".java"));
                    if (javaFiles == null || javaFiles.length == 0) 
                    {
                        debug.append("No Java file for ").append(studentName).append("\n");
                        perTestResult.put(tc.title, false);
                        continue;
                    }

                    File codeFile = javaFiles[0];
                    String className = codeFile.getName().replace(".java", "");

                    // Compile
                    if (!runner.compileProgram(codeFile)) 
                    {
                        debug.append("Compilation failed for ").append(studentName).append("\n");
                        perTestResult.put(tc.title, false);
                        continue;
                    }

                    // Run program with test case input
                    String output = runner.runProgram(studentFolder, className, String.valueOf(tc.input));
                    boolean correct = (output != null && output.trim().equals(String.valueOf(tc.Exoutput)));
                    perTestResult.put(tc.title, correct);

                    if (correct) 
                        passed++;
                }

                // Create the StudentResult object
                StudentResults sr = new StudentResults(studentName, studentFolder.getAbsolutePath(), total, passed, perTestResult);
                resultsList.add(sr);
            }

        } 
        catch (Exception e) 
        {
            debug.append("Exception in runTestSuiteOnFolder: ").append(e).append("\n");
        }

        return resultsList;
    }

   /*  public double getSuiteSuccessRate(TestSuite suite) {
    return SuccessRate.getTestSuiteSuccessRate(suite);
}

/*public double getOverallSuccessRate() {
    return SuccessRate.getOverallSuccessRate(allSuites);
}*/

}