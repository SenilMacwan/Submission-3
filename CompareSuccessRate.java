/**************************************************
 * CompareSuccessRate Class
 * 
 * This class compares the success rate of two
 * TestSuites using the existing SuccessRate class.
 *
 * @author Abdullah Tauqir
 * @Project CS-2043 / Group 10
 **************************************************/

public class CompareSuccessRate 
{
    /**
     * Compares two TestSuites by their success rate.
     * Returns a clean, readable message.
     */
    public static String compare(TestSuite a, String folderA,
                                 TestSuite b, String folderB,
                                 COORD coord, StringBuilder debug)
    {
        // Safety checks
        if (a == null || b == null)
            return "One or both TestSuites are missing.";

        double rateA = SuccessRate.getTestSuiteSuccessRate(a);
        double rateB = SuccessRate.getTestSuiteSuccessRate(b);

        // Formatting using existing SuccessRate formatter
        String rA = SuccessRate.formatRate(rateA);
        String rB = SuccessRate.formatRate(rateB);

        // Comparison logic
        if (rateA > rateB)
        {
            return a.name + " has a higher success rate (" + rA + 
                   ") than " + b.name + " (" + rB + ").";
        }
        else if (rateA < rateB)
        {
            return b.name + " has a higher success rate (" + rB + 
                   ") than " + a.name + " (" + rA + ").";
        }
        else
        {
            return "Both TestSuites have the same success rate (" + rA + ").";
        }
    }
}
