import java.rmi.*;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Lookup the remote object from the RMI Registry on port 1100
            Calculator calc = (Calculator) Naming.lookup("rmi://localhost:1100/CalculatorService");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nCalculator Menu:");
                System.out.println("1. Addition");
                System.out.println("2. Subtraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();
                
                if (choice == 5) {
                    System.out.println("Exiting...");
                    break;
                }
                
                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();
                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();
                
                switch (choice) {
                    case 1:
                        calc.add(num1, num2);  // Call the server's method
                        break;
                    case 2:
                        calc.subtract(num1, num2);  // Call the server's method
                        break;
                    case 3:
                        calc.multiply(num1, num2);  // Call the server's method
                        break;
                    case 4:
                        if (num2 == 0) {
                            System.out.println("Error: Cannot divide by zero.");
                        } else {
                            calc.divide(num1, num2);  // Call the server's method
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
