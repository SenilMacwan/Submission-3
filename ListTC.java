import java.util.ArrayList;

/**
 * a list of test cases
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
        ArrayList<TestCase> list = new ArrayList<>();
    }
    
    public static void add(TestCase t)
    {
        list.add(t);
    }
    
}
