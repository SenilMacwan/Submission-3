import java.io.File;
import java.util.Scanner;

/**
 * a class for test cases with integer input/output.
 *
 * @author Senil Macwan
 * @version 1.2
 */
public class TestCase {
    public String title;
    public int input;       // changed from String to int
    public int Exoutput;    // changed from String to int

    // Constructor
    public TestCase(String title, int input, int Exoutput) {
        this.title = title;
        this.input = input;
        this.Exoutput = Exoutput;
    }

    // Load test case from file
    public void initFromFile(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));

            this.title = sc.nextLine();          // read title
            if (sc.hasNextInt()) {
                this.input = sc.nextInt();       // read integer input
            }
            if (sc.hasNextInt()) {
                this.Exoutput = sc.nextInt();    // read integer expected output
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading test case from file: " + e.getMessage());
        }
    }

    // Save test case to file
    public void save(String filename) {
        try {
            java.io.PrintWriter pw = new java.io.PrintWriter(filename);

            pw.println(this.title);        // write title
            pw.println(this.input);        // write integer input
            pw.println(this.Exoutput);     // write integer expected output

            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving test case to file: " + e.getMessage());
        }
    }
}
