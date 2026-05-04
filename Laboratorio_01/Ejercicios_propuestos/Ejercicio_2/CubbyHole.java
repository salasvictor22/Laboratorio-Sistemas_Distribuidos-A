
class CubbyHole {

    private int contents;
    private boolean available = false;

    public synchronized void put(int value) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        contents = value;
        available = true;
        notify();
    }

    public synchronized int get() {
        // El consumidor adquier el monitor
        while (available == false) {
            try {
                wait(); // espera que el Productor invoque a
                notify();
            } catch (InterruptedException e) {
            }
        }
        available = false;
        notify();
        return contents;
        // el Consumidor libera el monitor

    }

}
