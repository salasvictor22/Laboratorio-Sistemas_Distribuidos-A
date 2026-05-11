
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServidorConversor {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            ConversorMoneda conversor = new ConversorMonedaImpl();

            Naming.rebind("rmi://localhost:1099/ConversorMonedaService", conversor);

            System.out.println("Servidor RMI del Conversor de Moneda iniciado correctamente.");
            System.out.println("Servicio registrado como: ConversorMonedaService");

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
