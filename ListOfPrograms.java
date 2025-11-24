import java.util.ArrayList;

public class ListOfPrograms
{
    // ---------- Instance Data ----------
    private ArrayList<Program> programs;

    // ---------- Constructor ----------
    public ListOfPrograms()
    {
        programs = new ArrayList<>();
    }

    // ---------- Methods ----------

    // Add a new program to the list
    public void addProgram(Program p)
    {
        programs.add(p);
    }

    // Get a program by name (returns null if not found)
    public Program getProgram(String name)
    {
        for (Program p : programs)
        {
            if (p.getName().equalsIgnoreCase(name))
                return p;
        }
        return null;
    }

    // Check if a program exists by name
    public boolean exists(String name)
    {
        return getProgram(name) != null;
    }

    // Return how many programs exist
    public int size()
    {
        return programs.size();
    }

    // Return list of all program names
    public ArrayList<String> getProgramNames()
    {
        ArrayList<String> names = new ArrayList<>();
        for (Program p : programs)
            names.add(p.getName());
        return names;
    }

    // String representation
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Programs:\n");
        for (Program p : programs)
        {
            sb.append(" - ").append(p.getName()).append("\n");
        }
        return sb.toString();
    }
}
