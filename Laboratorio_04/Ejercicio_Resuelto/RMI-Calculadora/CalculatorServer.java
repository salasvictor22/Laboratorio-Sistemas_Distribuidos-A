import java.rmi.Naming;

public class CalculatorServer {

    public CalculatorServer() {

        try {

            Calculator c = new CalculatorImpl();

            Naming.rebind("rmi://localhost:1099/CalculatorService", c);

            System.out.println("Servidor RMI iniciado");

        } catch (Exception e) {

            System.out.println("Error: " + e);

        }
    }

    public static void main(String[] args) {

        new CalculatorServer();

    }
}