
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConversorMonedaImpl extends UnicastRemoteObject implements ConversorMoneda {

    private static final double TASA_DOLAR = 3.52;
    private static final double TASA_EURO = 4.07;

    public ConversorMonedaImpl() throws RemoteException {
        super();
    }

    @Override
    public double convertirADolares(double montoSoles) throws RemoteException {
        return montoSoles / TASA_DOLAR;
    }

    @Override
    public double convertirAEuros(double montoSoles) throws RemoteException {
        return montoSoles / TASA_EURO;
    }
}
