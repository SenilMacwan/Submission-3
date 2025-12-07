import java.util.Scanner;
import java.io.File;

public class TestCase {
    public String title;
    public int input;
    public int Exoutput;

    public TestCase(String title, int input, int Exoutput) {
        this.title = title;
        this.input = input;
        this.Exoutput = Exoutput;
    }

    public void initFromFile(String fileName) {
         try {
            Scanner sc = new Scanner(new File(fileName));

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

    public void save(String fileName) {
        try {
            java.io.PrintWriter pw = new java.io.PrintWriter(fileName);

            pw.println(this.title);        // write title
            pw.println(this.input);        // write integer input
            pw.println(this.Exoutput);     // write integer expected output

            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving test case to file: " + e.getMessage());
        }
    }
    
       /* public boolean isPassed() 
    {
    return this.status.equalsIgnoreCase("PASS");
    }*/

    public String toStringTC() {
        return "Title: " + title +
               "\nInput: " + input +
               "\nExpected: " + Exoutput;
    }
}
