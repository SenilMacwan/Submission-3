import java.io.File;

public class SuccessRateCalculator {

    public static class Result {
        public int passed;
        public int total;

        public Result(int passed, int total) {
            this.passed = passed;
            this.total = total;
        }

        public String toString() {
            return passed + " / " + total;
        }
    }

    /**
     * Calculates the success rate for one student folder.
     */
    public static Result calculateForStudent(TestSuite suite, String studentFolderPath) {

        File studentFolder = new File(studentFolderPath);
        if (!studentFolder.exists() || !studentFolder.isDirectory()) {
            return new Result(0, suite.listTC.getCount());  // cannot run → 0 passed
        }

        // Find .java file
        ExecuteTestSuite runner = new ExecuteTestSuite();

        File codeFile = runner.findMainFile(studentFolder);
        if (codeFile == null) {
            return new Result(0, suite.listTC.getCount());  
        }

        String className = codeFile.getName().replace(".java", "");

        // Step 1: Compile program
        if (!runner.compileProgram(codeFile)) {
            return new Result(0, suite.listTC.getCount()); // failed compilation → 0 passed
        }

        // Step 2: Run all test cases
        int passed = 0;
        int total = suite.listTC.getCount();

        for (TestCase tc : suite.listTC) {

            String output = runner.runProgram(studentFolder, className, String.valueOf(tc.input));
            if (output == null) continue;

            try {
                int actual = Integer.parseInt(output.trim());
                if (actual == tc.Exoutput) {
                    passed++;
                }
            } catch (Exception ignored) {
                // non-int output → fail
            }
        }

        return new Result(passed, total);
    }
}
