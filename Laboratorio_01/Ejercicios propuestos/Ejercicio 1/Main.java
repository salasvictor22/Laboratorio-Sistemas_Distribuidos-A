public class Main {
    public static void main(String[] args) {
        CubbyHole cub = new CubbyHole();

        Productor productor = new Productor(cub, 1);
        Consumidor consumidor = new Consumidor(cub, 1);

        productor.start();
        consumidor.start();
    }
}