import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/***********************************************************
* Handles the execution logic for running the test suites
* with int input/output.
*
* @author Senil Macwan
***********************************************************/
public class ExecuteTestSuite
{
    private COORD coord;

    public ExecuteTestSuite(COORD c)
    {
        this.coord = c;
    }

    /**
     * Runs the given test suite on all subfolders inside the root directory.
     */
   public void runSuite(String suiteName, String rootFolder, StringBuilder outputLog) {
    TestSuite ts = COORD.listOfTestSuite.search(suiteName);

    if (ts == null) {
        outputLog.append("Test Suite not found: ").append(suiteName).append("\n");
        return;
    }

    File root = new File(rootFolder);

    if (!root.exists() || !root.isDirectory()) {
        outputLog.append("Invalid folder: ").append(rootFolder).append("\n");
        return;
    }

    outputLog.append("Running Test Suite: ").append(suiteName).append("\n");
    outputLog.append("Root folder: ").append(rootFolder).append("\n");
    outputLog.append("-----------------------------------------\n");

    for (File sub : root.listFiles()) {
        if (!sub.isDirectory()) continue;

        outputLog.append("\nProgram folder: ").append(sub.getName()).append("\n");

        File[] javaFiles = sub.listFiles((dir, name) -> name.endsWith(".java"));

        if (javaFiles == null || javaFiles.length == 0) {
            outputLog.append("No .java file found inside ").append(sub.getName()).append("\n");
            continue;
        }

        File codeFile = javaFiles[0];
        String className = codeFile.getName().replace(".java", "");

        outputLog.append("Found program: ").append(className).append("\n");

        if (!compileProgram(codeFile)) {
            outputLog.append("Compilation failed for: ").append(className).append("\n");
            continue;
        }

        for (TestCase tc : ts.listTC) {
            outputLog.append("  Test Case: ").append(tc.title).append("\n");
            outputLog.append("  Input: ").append(tc.input).append("\n");

            String output = runProgram(sub, className, String.valueOf(tc.input));

            if (output == null) {
                outputLog.append("   ERROR: Program crashed\n");
                continue;
            }

            outputLog.append("  Output: ").append(output.trim()).append("\n");
            outputLog.append("  Expected: ").append(tc.Exoutput).append("\n");

            try {
                int actual = Integer.parseInt(output.trim());
                if (actual == tc.Exoutput) {
                    outputLog.append("  RESULT: PASS\n");
                } else {
                    outputLog.append("  RESULT: FAIL\n");
                }
            } catch (NumberFormatException e) {
                outputLog.append("  RESULT: FAIL (non-integer output)\n");
            }

            outputLog.append("\n");
        }
    }
}


    // Compile program using javac
    protected boolean compileProgram(File javaFile)
    {
        try
        {
            Process p = Runtime.getRuntime().exec(
                "javac " + javaFile.getName(),
                null,
                javaFile.getParentFile()
            );
            p.waitFor();
            return p.exitValue() == 0;
        }
        catch (Exception e)
        {
            System.out.println("Compilation error: " + e);
            return false;
        }
    }

    // Run program using java className <input>
    protected String runProgram(File folder, String className, String input)
    {
        try
        {
            String[] cmd = {"java", className, input};

            Process p = Runtime.getRuntime().exec(cmd, null, folder);

            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream())
            );

            String line = br.readLine(); // program prints 1-line integer output

            return line;
        }
        catch (Exception e)
        {
            System.out.println("Runtime error: " + e);
            return null;
        }
    }
}
