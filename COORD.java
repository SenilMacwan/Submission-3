
/**
 * Write a description of class COORD here.
 *
 * @author senil macwan
 * @version version 1.3
 */
public class COORD extends ListTC
{
    public void NewTestCase(String title, int input, int Exoutput)
    {
        TestCase myTC = new TestCase(title, input, Exoutput);
        add(myTC);
    }
    
    public void LoadTestCase(String fileName)
    {
        TestCase myTC = new TestCase("",0,0);
        myTC.initFromFile(fileName);
        add(myTC);
    }
    
    public void SAVETESTCASE(String Title, String Filename)
    {
        TestCase T = search(Title);
        T.save(Filename);
    }            
        
}
