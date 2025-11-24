import java.util.ArrayList;

/**
 * A list of test suites
 * Handles adding and searching test suites safely.
 */
public class ListTS {

    private ArrayList<TestSuite> suites;
    public String name;

    public ListTS() {
        name = "temp";
        suites = new ArrayList<>();
    }

    // Add a test suite safely
    public void add(TestSuite ts) {
        if (ts != null) {
            suites.add(ts);
        }
    }

    // Search by test suite name
    public TestSuite search(String name) {
        if (name == null) return null;
        for (TestSuite t : suites) {
            if (t != null && name.equals(t.name)) {
                return t;
            }
        }
        return null;  // return null if not found
    }

    // Optional: get all suites as iterable
    public Iterable<TestSuite> getSuites() {
        return suites;
    }
}
