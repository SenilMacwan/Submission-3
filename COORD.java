
/**
 * Write a description of class COORD here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
}
