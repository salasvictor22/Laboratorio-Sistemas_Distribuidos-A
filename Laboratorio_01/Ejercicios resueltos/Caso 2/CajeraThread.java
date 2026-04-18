public class CajeraThread extends Thread {

    private String nombre;
    private Cliente cliente;
    private long initialTime;

    public CajeraThread() {
    }

    // Constructor que inicializa la cajera, el cliente y el tiempo inicial
    public CajeraThread(String nombre, Cliente cliente, long initialTime) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método que se ejecuta al iniciar el hilo
    @Override
    public void run() {

        // Inicio del procesamiento de la compra
        System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000
                + "seg");

        // Recorrido de los productos del cliente
        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {

            // Se procesa cada producto simulando el tiempo con sleep
            this.esperarXsegundos(cliente.getCarroCompra()[i]);

            // Mensaje de producto procesado con tiempo transcurrido
            System.out.println("Procesado el producto " + (i + 1)
                    + " del cliente " + this.cliente.getNombre() + "->Tiempo: "
                    + (System.currentTimeMillis() - this.initialTime) / 1000
                    + "seg");

        }

        // Fin del procesamiento del cliente
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000
                + "seg");

    }

    // Método para simular el tiempo de espera por producto
    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}