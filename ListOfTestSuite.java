import java.util.ArrayList;
/**************************************************************
* Maintains a list of all test suites available in the project
*
* CS 2043 / Group 10
**************************************************************/

public class ListOfTestSuites 
{

    private ArrayList<TestSuite> suites;
   // Constructor
    public ListOfTestSuites() 
  {
        suites = new ArrayList<>();
    }
    //Adds a TestSuite to the collection
    public void add(TestSuite ts) 
  {
        suites.add(ts);
    }
  // Searches the list for a TestSuite with the given name
    public TestSuite search(String name) 
  {
        for (TestSuite t : suites) 
        {   // Compare suite names
            if (t.name.equals(name)) 
            {   // Return the matching TestSuite
                return t;
            }
        }
       // If no match found, return null
        return null;
    }
}
