import java.util.Scanner;

public class MyProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read two integers from input
        int a = 0, b = 2;
        if (sc.hasNextInt()) {
            a = sc.nextInt();
        }
        
        sc.close();

        // Multiply them
        int result = a * b;

        // Print the result
        System.out.println(result);
    }
}
