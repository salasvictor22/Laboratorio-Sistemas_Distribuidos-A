public class MainRunnable implements Runnable {

    private Cliente cliente;
    private Cajera cajera;
    private long initialTime;

    // Constructor: recibe los datos necesarios para ejecutar el proceso en paralelo
    public MainRunnable(Cliente cliente, Cajera cajera, long initialTime) {
        this.cajera = cajera;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }
    public static void main(String[] args) {

        Cliente cliente1 = new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
        Cliente cliente2 = new Cliente("Cliente 2", new int[] { 1, 3, 5, 1, 1 });

        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");

        // Tiempo inicial para medir cuánto tarda todo el proceso
        long initialTime = System.currentTimeMillis();

        // Se crean los procesos (Runnable) para cada cliente
        Runnable proceso1 = new MainRunnable(cliente1, cajera1, initialTime);
        Runnable proceso2 = new MainRunnable(cliente2, cajera2, initialTime);

        // Se ejecutan en paralelo usando hilos (multithreading)
        new Thread(proceso1).start();
        new Thread(proceso2).start();
    }
    // Método que se ejecuta cuando inicia el hilo
    @Override
    public void run() {

        this.cajera.procesarCompra(this.cliente, this.initialTime);
    }
}