public class SuccessRate {

    /**
     * Runs all test cases inside a TestSuite and calculates success rate.
     *
     * @param suite The TestSuite to evaluate
     * @param coord Reference to COORD so we can use traceTestCase()
     * @param programFolder Folder where the student program is located
     * @param debug Debug log
     * @return Success rate as a percentage (0–100)
     */
    public static double getTestSuiteSuccessRate(TestSuite suite, 
                                                 COORD coord, 
                                                 String programFolder, 
                                                 StringBuilder debug) 
    {
        if (suite == null) return 0.0;

        int total = suite.listTC.getCount();
        if (total == 0) return 0.0;

        int passed = 0;

        // Run each test case
        for (TestCase tc : suite.listTC) {
            String output = coord.traceTestCase(tc.title, programFolder, debug);

            // PASS if exact match
            if (output != null && output.trim().equals(String.valueOf(tc.Exoutput))) {
                passed++;
            }
        }

        return (passed * 100.0) / total;
    }

    /**
     * Formats success rate with two decimals.
     */
    public static String formatRate(double rate) {
        return String.format("%.2f%%", rate);
    }
}
