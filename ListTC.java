import java.util.ArrayList;

/**
 * a list of test cases, ListTC.java
 *
 * @author senil macwan
 * @version 1.2
 */
public class ListTC 
{
    public static String name;
    public static ArrayList<TestCase> list;
    
    public ListTC()
{
    name = "temp";
    list = new ArrayList<>();
}

    
    public static void add(TestCase t)
    {
        list.add(t);
    }

    public static TestCase search(String title)
    {
        if (list == null) return null;

        for (TestCase t : list)
        {
            if (t.title.equals(title))
            {
                return t;
            }
        }
        return null;
    }
}
