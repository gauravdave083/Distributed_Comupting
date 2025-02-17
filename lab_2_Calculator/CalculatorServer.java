import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class CalculatorServer extends UnicastRemoteObject implements Calculator {

    protected CalculatorServer() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {
        double result = a + b;
        System.out.println("Addition result: " + result);  // Output on server
        return result;
    }

    public double subtract(double a, double b) throws RemoteException {
        double result = a - b;
        System.out.println("Subtraction result: " + result);  // Output on server
        return result;
    }

    public double multiply(double a, double b) throws RemoteException {
        double result = a * b;
        System.out.println("Multiplication result: " + result);  // Output on server
        return result;
    }

    public double divide(double a, double b) throws RemoteException {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero.");  // Error message on server
            throw new ArithmeticException("Cannot divide by zero.");
        }
        double result = a / b;
        System.out.println("Division result: " + result);  // Output on server
        return result;
    }

    public static void main(String[] args) {
        try {
            // Create an RMI registry on port 1100
            Registry registry = LocateRegistry.createRegistry(1100);
            // Create the server object
            CalculatorServer server = new CalculatorServer();
            // Bind the server to the registry
            Naming.rebind("//localhost:1100/CalculatorService", server);
            System.out.println("Calculator Server is running on port 1100...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
