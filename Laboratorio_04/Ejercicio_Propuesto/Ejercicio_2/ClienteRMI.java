import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteRMI {

    public static void main(String[] args) {

        try {

            Registry registro = LocateRegistry.getRegistry("localhost", 1099);

            TarjetaCredito servicio = (TarjetaCredito) registro.lookup("TarjetaService");

            Scanner sc = new Scanner(System.in);

            System.out.println("Ingrese numero de tarjeta:");
            String tarjeta = sc.nextLine();

            System.out.println(servicio.consultarSaldo(tarjeta));

            System.out.println("Ingrese monto a pagar:");
            double monto = sc.nextDouble();

            System.out.println(servicio.realizarPago(tarjeta, monto));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}