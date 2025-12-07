import java.util.ArrayList;
import java.util.Iterator;

public class ListTC implements Iterable<TestCase> {

    private ArrayList<TestCase> list;
    public String name;

    public ListTC() {
        name = "temp";
        list = new ArrayList<>();
    }

    public void add(TestCase t) {
        if (t != null) {
            list.add(t);
        }
    }

    public TestCase search(String Title) {
        for (TestCase t : list) {
            if (t.title.equals(Title)) {
                return t;
            }
        }
        return null;
    }
    
    public int getCount()
    {
        return list.size();
    }
    
    public TestCase getAt(int i)
    {
        if(i > list.size())
        {   return null;}
        
        else
        {   return list.get(i); }
    }

    @Override
    public Iterator<TestCase> iterator() {
        return list.iterator();
    }
}
