import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ExecuteTestSuite
{
    public ExecuteTestSuite() {
        // COORD data is static; nothing to store here
    }

    public void runSuite(String suiteName, String rootFolder, StringBuilder outputLog) throws java.io.IOException {

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

        // USE THE MAIN-FILE FINDER (SAME LOGIC USED BY SUCCESS-RATE CALCULATOR)
        File mainFile = findMainFile(sub);

        if (mainFile == null) {
            outputLog.append("No Java file with main(String[]) found in ")
                     .append(sub.getName()).append("\n");
            continue;
        }

        String className = mainFile.getName().replace(".java", "");

        outputLog.append("Found program: ").append(className).append("\n");

        if (!compileProgram(mainFile)) {
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


    public boolean compileProgram(File javaFile) {
    try {
        String folder = javaFile.getParentFile().getAbsolutePath();

        ProcessBuilder pb = new ProcessBuilder(
            "javac",
            "-cp", folder,      // classpath fixed
            javaFile.getName()
        );

        pb.directory(javaFile.getParentFile());
        pb.redirectErrorStream(true);

        Process p = pb.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        return p.waitFor() == 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    public String runProgram(File folder, String className, String input) {
    try {
        ProcessBuilder pb = new ProcessBuilder(
            "java",
            "-cp", folder.getAbsolutePath(),   // FIX
            className
        );

        pb.directory(folder);
        pb.redirectErrorStream(true);

        Process p = pb.start();

        if (input != null && !input.isEmpty()) {
            p.getOutputStream().write((input + "\n").getBytes());
            p.getOutputStream().flush();
        }
        p.getOutputStream().close();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            result.append(line).append("\n");
        }

        p.waitFor();
        return result.toString().trim();

    } catch (Exception e) {
        System.out.println("Runtime error: " + e);
        return null;
    }
}

public File findMainFile(File folder) {
    File[] javaFiles = folder.listFiles((dir, name) -> name.endsWith(".java"));
    if (javaFiles == null) return null;

    try {
        for (File f : javaFiles) {
            // read file text
            String content = java.nio.file.Files.readString(f.toPath());

            // crude but effective check
            if (content.contains("public static void main(String[] args)")) {
                return f;
            }
        }
    } catch (Exception e) {
        return null;
    }

    return null; // none found
}

}