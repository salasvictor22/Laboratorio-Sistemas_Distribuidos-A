
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConversorMoneda extends Remote {

    double convertirADolares(double montoSoles) throws RemoteException;

    double convertirAEuros(double montoSoles) throws RemoteException;
}
