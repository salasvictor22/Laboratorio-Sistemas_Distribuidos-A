import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {

    public static void main(String[] args) {

        try {

            TarjetaCreditoImpl objetoRemoto = new TarjetaCreditoImpl();

            Registry registro = LocateRegistry.createRegistry(1099);

            registro.rebind("TarjetaService", objetoRemoto);

            System.out.println("Servidor RMI ejecutandose...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}