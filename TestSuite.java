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

    public String name;
    public ListTC listTC;

    public TestSuite(String name) 
  {
        this.name = name;
        listTC = new ListTC();
  }

    public void addTestCase(TestCase tc)
  {
        listTC.add(tc);
    }

    public int getTotalTestCases() {
    return testCases.getCount();
}

public int getPassedTestCases() {
    int count = 0;
    for (int i = 0; i < testCases.getCount(); i++) {
        if (testCases.getAt(i).isPassed()) {
            count++;
        }
    }
    return count;
}
}
