
/**
 * a class for test cases.
 *
 * @author Senil Macwan 
 * @version 1.1
 */
public class TestCase
{
     public String title;
     public String input;
     public String Exoutput;
     
     public void createTC(String title, String input, String Exoutput)
     {
            TestCase T = new TestCase();
            T.title = title;
            T.input = input;
            T.Exoutput = Exoutput;
      }
     
     public static void main(String[] Args)
     {
            
     }
}
