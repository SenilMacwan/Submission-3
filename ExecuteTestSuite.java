import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/***********************************************************
* Handles the execution logic for running the test suites.
*
*@author Abdullah Tauqir
*@Project CS-2043/Group 10
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
    public void runSuite(String suiteName, String rootFolder)
    {
        TestSuite ts = coord.listTS.search(suiteName);

        if (ts == null)
        {
            System.out.println("Test Suite not found: " + suiteName);
            return;
        }

        File root = new File(rootFolder);

        if (!root.exists() || !root.isDirectory())
        {
            System.out.println("Invalid folder: " + rootFolder);
            return;
        }

        System.out.println("Running Test Suite: " + suiteName);
        System.out.println("Root folder: " + rootFolder);
        System.out.println("-----------------------------------------");

        // loop over all subfolders (each subfolder is a different program)
        for (File sub : root.listFiles())
        {
            if (!sub.isDirectory()) continue;

            System.out.println("\nProgram folder: " + sub.getName());

            // find .java file inside the subfolder
            File[] javaFiles = sub.listFiles((dir, name) -> name.endsWith(".java"));

            if (javaFiles == null || javaFiles.length == 0)
            {
                System.out.println("No .java file found inside " + sub.getName());
                continue;
            }

            File codeFile = javaFiles[0];
            String className = codeFile.getName().replace(".java", "");

            System.out.println("Found program: " + className);

            // compile program
            if (!compileProgram(codeFile))
            {
                System.out.println("Compilation failed for: " + className);
                continue;
            }

            // run all test cases
            for (TestCase tc : ts.listTC)
            {
                System.out.println("  Test Case: " + tc.title);
                System.out.println("  Input: " + tc.input);

                String output = runProgram(sub, className, tc.input);

                if (output == null)
                {
                    System.out.println("   ERROR: Program crashed");
                    continue;
                }

                System.out.println("  Output: " + output.trim());
                System.out.println("  Expected: " + tc.Exoutput);

                if (output.trim().equals(String.valueOf(tc.Exoutput)))
                {
                    System.out.println("  RESULT: PASS");
                }
                else
                {
                    System.out.println("  RESULT: FAIL");
                }
                System.out.println();
            }
        }
    }

    // compile code using javac
    private boolean compileProgram(File javaFile)
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

    // run program using java className <input>
    private String runProgram(File folder, String className, int input)

    {

        try
        {
            String[] cmd = {"java", className, String.valueOf(input)};

            Process p = Runtime.getRuntime().exec(cmd, null, folder);
 
            BufferedReader br = new BufferedReader(

                    new InputStreamReader(p.getInputStream()));
 
            String line = br.readLine();

            return line;  // program prints 1-line output

        }

        catch (Exception e)

        {
            System.out.println("Runtime error: " + e);

            return null;

        }
    
    }
}
