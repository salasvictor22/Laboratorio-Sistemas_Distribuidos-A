
public class Main {

    public static void main(String[] args) {

        CubbyHole c = new CubbyHole();

        Productor p1 = new Productor(c, 1);
        Consumidor c1 = new Consumidor(c, 1);

        p1.start();
        c1.start();
    }
}
