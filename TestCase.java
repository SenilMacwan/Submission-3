import java.io.File;
import java.util.Scanner;
/******************************************************************
 * Defines the TestCase class representing individual test cases.
 *
 * @author Senil Macwan 
 * @version 1.1
 ****************************************************************/
public class TestCase
{     
     // Instance variables
     public String title;
     public int input;
     public int Exoutput;

     // Constructor method
     public TestCase(String title, int input, int Exoutput)
     {
            this.title = title;
            this.input = input;
            this.Exoutput = Exoutput;
     }

     // Method to read and load a test case title, input and expected output
     public void initFromFile(String filename)
     {
         try
         {
             Scanner sc = new Scanner(new File(filename));
 
             // Matches the sequence diagram: loading data after object is created
             this.title = sc.nextLine();                        // read title
             this.input = Integer.parseInt(sc.nextLine());      // read input
             this.Exoutput = Integer.parseInt(sc.nextLine());   // read expected output
 
             sc.close();
         }
         catch (Exception e) 
         {
             System.out.println("Error loading test case from file: " + e.getMessage());
         }
     }

     // Method to write and save the test case title, input and expected output
     public void save(String filename)
     {
         try
         {
             java.io.PrintWriter pw = new java.io.PrintWriter(filename);
 
             pw.println(this.title);      // write title
             pw.println(this.input);      // write input
             pw.println(this.Exoutput);   // write expected output
 
             pw.close();
         }
         catch (Exception e)
         {
             System.out.println("Error saving test case to file: " + e.getMessage());
         }
     }
}
