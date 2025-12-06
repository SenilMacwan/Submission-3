public class SuccessRate 
{

    // Calculate success rate for a single TestCase
    public static double getTestCaseSuccessRate(TestCase tc) 
  {
        if (tc == null) return 0.0;
        return tc.isPassed() ? 100.0 : 0.0;
    }

    // Calculate success rate for a TestSuite
    public static double getTestSuiteSuccessRate(TestSuite suite) 
  {
        if (suite == null) return 0.0;

        int total = suite.getTotalTestCases();
        if (total == 0) return 0.0;

        int passed = suite.getPassedTestCases();
        return ((double) passed / total) * 100.0;
    }

    // Calculate overall success rate of all TestSuites in the project
    public static double getOverallSuccessRate(ListOfTestSuite list) 
  {
        if (list == null || list.getCount() == 0) return 0.0;

        int total = 0;
        int passed = 0;

       for (int i = 0; i < list.getCount(); i++) 
       {
            TestSuite suite = list.getSuiteAt(i);
            total += suite.getTotalTestCases();
            passed += suite.getPassedTestCases();
        }


        if (total == 0) return 0.0;

        return ((double) passed / total) * 100.0;
    }

    // Clean formatted string for UI printing
    public static String formatRate(double rate)
  {
        return String.format("%.2f%%", rate);
    }
}
