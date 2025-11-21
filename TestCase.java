import java.io.File;
import java.util.Scanner;
/**
 * a class for test cases.
 *
 * @author Senil Macwan 
 * @version 1.1
 */
public class TestCase
{
     public String title;
     public int input;
     public int Exoutput;
     
     public TestCase(String title, int input, int Exoutput)
     {
            this.title = title;
            this.input = input;
            this.Exoutput = Exoutput;
     }
     
     public void initFromFile(String filename)
     {
         try
         {
             Scanner sc = new Scanner(new File(filename));
 
             // Matches the sequence diagram: loading data after object is created
             this.title = sc.nextLine();                 // read title
             this.input = Integer.parseInt(sc.nextLine());      // read input
             this.Exoutput = Integer.parseInt(sc.nextLine());   // read expected output
 
             sc.close();
         }
         catch (Exception e) 
         {
             System.out.println("Error loading test case from file: " + e.getMessage());
         }
     }
     
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
    
     
     public static void main(String[] Args)
     {
            
     }
}
