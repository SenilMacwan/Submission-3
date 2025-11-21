import java.util.ArrayList;

public class ListOfTestSuites 
{

    private ArrayList<TestSuite> suites;

    public ListOfTestSuites() 
  {
        suites = new ArrayList<>();
    }

    public void add(TestSuite ts) 
  {
        suites.add(ts);
    }

    public TestSuite search(String name) 
  {
        for (TestSuite t : suites) 
        {
            if (t.name.equals(name)) 
            {
                return t;
            }
        }
        return null;
    }
}
