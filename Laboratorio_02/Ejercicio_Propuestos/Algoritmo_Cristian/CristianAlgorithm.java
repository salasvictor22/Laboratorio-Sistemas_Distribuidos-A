
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

// Clase Servidor
class Server {

    public LocalTime getTime() {
        return LocalTime.now();
    }
}

// Clase Cliente
class Client {

    private LocalTime localTime;
    private Server server;

    public Client(Server server) {
        this.server = server;
        this.localTime = LocalTime.now();
    }

    public LocalTime sendRequest() {
        LocalTime t0 = LocalTime.now();

        LocalTime serverTime = server.getTime();

        LocalTime t1 = LocalTime.now();

        long rttMillis = ChronoUnit.MILLIS.between(t0, t1);
        long estimatedDelay = rttMillis / 2;

        return serverTime.plus(estimatedDelay, ChronoUnit.MILLIS);
    }

    public void adjustClock() {
        LocalTime adjustedTime = sendRequest();
        displayTimes(adjustedTime);
        localTime = adjustedTime;
    }

    private void displayTimes(LocalTime adjustedTime) {
        LocalTime now = LocalTime.now();
        long rttMillis = ChronoUnit.MILLIS.between(now, adjustedTime);
        System.out.println("Local time before adjustment: " + localTime);
        System.out.println("Adjusted local time:        " + adjustedTime);
        System.out.println("Approximate adjustment:     " + ChronoUnit.MILLIS.between(localTime, adjustedTime) + " ms");
        System.out.println("---------------------------------------------------");
    }
}

// Clase principal
public class CristianAlgorithm {

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        Client client = new Client(server);

        System.out.println("=== Cristian Algorithm Simulation ===");

        Thread.sleep(1000);

        for (int i = 0; i < 5; i++) {
            client.adjustClock();
            Thread.sleep(1000);
        }
    }
}
