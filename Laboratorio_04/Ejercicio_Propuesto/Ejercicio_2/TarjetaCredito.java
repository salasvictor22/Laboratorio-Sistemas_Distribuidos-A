import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TarjetaCredito extends Remote {

    String consultarSaldo(String numeroTarjeta) throws RemoteException;

    String realizarPago(String numeroTarjeta, double monto) throws RemoteException;

}