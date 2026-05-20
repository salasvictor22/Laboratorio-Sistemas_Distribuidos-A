
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            Calculator calculator = new CalculatorImpl();

            Naming.rebind("rmi://localhost:1099/CalculatorService", calculator);

            System.out.println("Servidor RPC tradicional iniciado correctamente.");
            System.out.println("Servicio disponible en: rmi://localhost:1099/CalculatorService");

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
