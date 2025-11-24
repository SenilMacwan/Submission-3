import java.util.ArrayList;
import java.util.Iterator;

/**
 * a list of test cases
 *
 * @author senil
 * @version 1.3
 */
public class ListTC implements Iterable<TestCase> {

  private ArrayList<TestCase> list;  // instance list, not static
  public String name;

  public ListTC() {
    name = "temp";
    list = new ArrayList<>();  // initialize the internal ArrayList
  }

  // add a test case
  public void add(TestCase t) {
    if (t != null) {
      list.add(t);
    }
  }

  // search by title
  public TestCase search(String Title) {
    for (TestCase t : list) {
      if (t.title.equals(Title)) {
        return t;
      }
    }
    return null;  // return null if not found
  }

  @Override
  public Iterator<TestCase> iterator() {
    return list.iterator();
  }
}
