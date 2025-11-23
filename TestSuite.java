/**************************************************
* This program demonstrate test suite by following
* test case.
*
*@author Abdullah Tauqir
*@Project CS-2043/Group 10
***************************************************/ 

import java.util.ArrayList;

public class TestSuite 
{
 // Instance Variables
    public String name;
    public ArrayList<TestCase> listTC;
    // Constructor
    public TestSuite(String name) 
  {     // Assign the suite name
        this.name = name;
        // Create an empty list of TestCases
        this.listTC = new ArrayList<>();
  }
   // method for adding testcases 
    //This method is called whenever the user creates or loads
    public void addTestCase(TestCase tc)
  {
        listTC.add(tc);
  }
}

