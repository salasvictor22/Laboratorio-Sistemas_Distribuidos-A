import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class CalculatorClient {

    public static void main(String[] args) {

        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        try {

            Calculator c = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");

            System.out.println("Resta: " + c.sub(num1, num2));

            System.out.println("Suma: " + c.add(num1, num2));

            System.out.println("Multiplicación: " + c.mul(num1, num2));

            System.out.println("División: " + c.div(num1, num2));

        }

        catch (MalformedURLException murle) {

            System.out.println("MalformedURLException");
            System.out.println(murle);

        }

        catch (RemoteException re) {

            System.out.println("RemoteException");
            System.out.println(re);

        }

        catch (NotBoundException nbe) {

            System.out.println("NotBoundException");
            System.out.println(nbe);

        }

        catch (ArithmeticException ae) {

            System.out.println("ArithmeticException");
            System.out.println(ae);

        }
    }
}