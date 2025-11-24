import java.io.File;

public class COORD {

  protected static ListTC listOfTestCase = new ListTC();
  protected static ListTS listOfTestSuite = new ListTS();

  // ===== Create Test Case =====
  public static boolean NewTestCase(String title, int input, int Exoutput, StringBuilder debug) {
    try {
      debug.append("Creating TestCase: " + title + "\n");
      if (title == null || title.isEmpty()) {
        debug.append("Error: Title is empty.\n");
        return false;
      }
      TestCase myTC = new TestCase(title, input, Exoutput);
      listOfTestCase.add(myTC);
      debug.append("TestCase added to list successfully.\n");
      return true;
    } catch (Exception e) {
      debug.append("Exception in NewTestCase: " + e + "\n");
      return false;
    }
  }

  // ===== Load Test Case from File =====
  public static boolean LoadTestCase(String fileName, StringBuilder debug) {
    try {
      debug.append("Loading TestCase from file: " + fileName + "\n");
      if (fileName == null || fileName.isEmpty()) {
        debug.append("Error: File name is empty.\n");
        return false;
      }
      TestCase myTC = new TestCase("", 0, 0);
      myTC.initFromFile(fileName);
      listOfTestCase.add(myTC);
      debug.append("TestCase loaded and added successfully.\n");
      return true;
    } catch (Exception e) {
      debug.append("Exception in LoadTestCase: " + e + "\n");
      return false;
    }
  }

  // ===== Create Test Suite =====
  public static boolean CreateTestSuite(String Name, StringBuilder debug) {
    try {
      debug.append("Creating TestSuite: " + Name + "\n");
      if (Name == null || Name.isEmpty()) {
        debug.append("Error: Name is empty.\n");
        return false;
      }
      TestSuite myTS = new TestSuite(Name);
      listOfTestSuite.add(myTS);
      debug.append("TestSuite added successfully.\n");
      return true;
    } catch (Exception e) {
      debug.append("Exception in CreateTestSuite: " + e + "\n");
      return false;
    }
  }

  // ===== Save Test Case =====
  public static boolean SaveTestCase(String Title, String Filename, StringBuilder debug) {
    try {
      debug.append("Saving TestCase '" + Title + "' to file: " + Filename + "\n");
      if (Title == null || Filename == null) {
        debug.append("Error: Title or Filename is null.\n");
        return false;
      }
      TestCase T = listOfTestCase.search(Title);
      if (T == null) {
        debug.append("Error: TestCase not found in list.\n");
        return false;
      }
      T.save(Filename);
      debug.append("TestCase saved successfully.\n");
      return true;
    } catch (Exception e) {
      debug.append("Exception in SaveTestCase: " + e + "\n");
      return false;
    }
  }

  // ===== Add Test Case to Test Suite =====
  public static boolean addTestCaseToTestSuite(String tsName, String tcName, StringBuilder debug) {
    try {
      debug.append("Adding TestCase '" + tcName + "' to TestSuite '" + tsName + "'\n");
      if (tsName == null || tcName == null) {
        debug.append("Error: Names cannot be null.\n");
        return false;
      }
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
      debug.append("TestCase added to TestSuite successfully.\n");
      return true;
    } catch (Exception e) {
      debug.append("Exception in addTestCaseToTestSuite: " + e + "\n");
      return false;
    }
  }

  // ===== Trace Test Case =====
  public static String traceTestCase(String testCaseTitle, String programFolder, StringBuilder debug) {
    try {
      debug.append("Tracing TestCase: " + testCaseTitle + " in folder: " + programFolder + "\n");
      if (testCaseTitle == null || programFolder == null || testCaseTitle.isEmpty() || programFolder.isEmpty()) {
        debug.append("Error: Invalid inputs.\n");
        return "Invalid input.";
      }
      TestCase tc = listOfTestCase.search(testCaseTitle);
      if (tc == null) {
        debug.append("Error: TestCase not found.\n");
        return "Test Case not found.";
      }
      File folder = new File(programFolder);
      File[] javaFiles = folder.listFiles((dir, name) -> name.endsWith(".java"));
      if (javaFiles == null || javaFiles.length == 0) {
        debug.append("Error: No Java files in folder.\n");
        return "No Java file in folder.";
      }

      File codeFile = javaFiles[0];
      String className = codeFile.getName().replace(".java", "");

      ExecuteTestSuite runner = new ExecuteTestSuite(new COORD());
      debug.append("Compiling " + codeFile.getName() + "...\n");
      if (!runner.compileProgram(codeFile)) {
        debug.append("Compilation failed.\n");
        return "Compilation failed.";
      }

      debug.append("Running program with input: " + tc.input + "\n");
      String output = runner.runProgram(folder, className, Integer.toString(tc.input)); // pass as string
      debug.append("Program run complete.\n");

      return output == null ? "Runtime error." : output;

    } catch (Exception e) {
      debug.append("Exception in traceTestCase: " + e + "\n");
      return "Error tracing test case.";
    }
  }
}
