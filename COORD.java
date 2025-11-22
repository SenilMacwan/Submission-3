/**
 * Write a description of class COORD here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class COORD extends ListTC
{
    // ADDED — list of test suites (for your part)
    public ListOfTestSuites listTS = new ListOfTestSuites();

    public void NewTestCase(String title, int input, int Exoutput)
    {
        TestCase myTC = new TestCase(title, input, Exoutput);
        add(myTC);
    }

    public void LoadTestCase(String fileName)
    {
        TestCase myTC = new TestCase("",0,0);
        myTC.initFromFile(fileName);
        add(myTC);
    }

    // ADDED — Create Test Suite (matches diagram)
    public void NewTestSuite(String name)
    {
        TestSuite ts = new TestSuite(name);
        listTS.add(ts);
        System.out.println("Created Test Suite: " + name);
    }

    // ADDED — Add Test Case to Suite (matches diagram)
    public void AddTestCaseToSuite(String suiteName, String title)
    {
        // Step 1: Search suite by name
        TestSuite ts = listTS.search(suiteName);

        if (ts == null)
        {
            System.out.println("Suite not found: " + suiteName);
            return;
        }

        // Step 2: Search test case by title
        TestCase tc = ListTC.search(title);

        if (tc == null)
        {
            System.out.println("Test Case not found: " + title);
            return;
        }

        // Step 3: Add TestCase to suite
        ts.addTestCase(tc);

        System.out.println("Added Test Case '" + title + "' to Suite '" + suiteName + "'");
    }
}

