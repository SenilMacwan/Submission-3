import java.io.Serializable;
import java.util.Map;
/**
 * A class that will help store student results.
 *
 * @author CS 2043 Group 10
 * @version (6th Nov 2025)
 */

public class StudentResults implements Serializable
{
    // Name of student submitting the folder
    private String studentName;
    
    // Path to the coded folder
    private String folderPath;
    
    // Number of test cases in the test suite and how many passed
    private int totalTests;
    private int passedTests;
    
    private Map<String, Boolean> testResults;
    
    // Constructor Method
    public StudentResults( String studentName, String folderPath, int totalTests, int passedTests, Map <String,Boolean> testResults)
    {
        this.studentName = studentName;
        this.folderPath = folderPath;
        this.totalTests = totalTests;
        this.passedTests = passedTests;
        this.testResults = testResults;
    }
    
    // Accessor Methods
    public String getStudentName() { return studentName; }

    public String getFolderPath() { return folderPath; }

    public int getTotalTests() { return totalTests; }

    public int getPassedTests() { return passedTests; }

    public Map<String, Boolean> getTestResults() { return testResults; }
    
}