import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;

public class TarjetaCreditoImpl extends UnicastRemoteObject implements TarjetaCredito {

    private HashMap<String, Double> tarjetas;

    protected TarjetaCreditoImpl() throws RemoteException {
        super();

        tarjetas = new HashMap<>();

        tarjetas.put("123456789", 5000.0);
        tarjetas.put("987654321", 9000.0);
    }

    @Override
    public String consultarSaldo(String numeroTarjeta) throws RemoteException {

        if (tarjetas.containsKey(numeroTarjeta)) {
            return "Saldo disponible: S/ " + tarjetas.get(numeroTarjeta);
        } else {
            return "Tarjeta no encontrada";
        }
    }

    @Override
    public String realizarPago(String numeroTarjeta, double monto) throws RemoteException {

        if (tarjetas.containsKey(numeroTarjeta)) {

            double saldo = tarjetas.get(numeroTarjeta);

            if (saldo >= monto) {

                saldo -= monto;
                tarjetas.put(numeroTarjeta, saldo);

                return "Pago realizado correctamente. Nuevo saldo: S/ " + saldo;

            } else {
                return "Saldo insuficiente";
            }

        } else {
            return "Tarjeta no encontrada";
        }
    }
}
