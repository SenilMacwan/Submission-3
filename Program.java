import java.io.*;

public class Program {

    private String fileName;   // name of the .java program file

    public Program(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return fileName;
    }

    /**
     * Compiles the program file.
     */
    public boolean compile() {
        try {
            Process p = Runtime.getRuntime().exec("javac " + fileName);
            p.waitFor();

            return p.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Executes the program with the provided input.
     * Returns program output as a String.
     */
    public String execute(String input) {
        try {
            // remove .java → class name
            String className = fileName.replace(".java", "");

            Process p = Runtime.getRuntime().exec("java " + className);

            // ---- SEND INPUT ----
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(p.getOutputStream())
            );
            writer.write(input);
            writer.newLine();
            writer.flush();
            writer.close();

            // ---- READ OUTPUT ----
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(p.getInputStream())
            );

            String result = reader.readLine();
            reader.close();

            p.waitFor();
            return result == null ? "" : result;

        } catch (Exception e) {
            return "RUNTIME_ERROR";
        }
    }
}
