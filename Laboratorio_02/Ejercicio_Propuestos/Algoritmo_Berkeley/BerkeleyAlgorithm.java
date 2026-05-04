import java.util.ArrayList;
import java.util.List;

// Clase que representa cada nodo (computadora)
class Node {
    private int clock;

    // Constructor
    public Node(int clock) {
        this.clock = clock;
    }

    // Obtener el tiempo actual
    public int getClock() {
        return clock;
    }

    // Ajustar el reloj
    public void adjustClock(int offset) {
        this.clock += offset;
    }
}

// Clase principal
public class BerkeleyAlgorithm {

    public static void main(String[] args) {

        List<Node> nodes = new ArrayList<>();

        // Crear nodos con tiempos diferentes (simulación)
        nodes.add(new Node(10));
        nodes.add(new Node(20));
        nodes.add(new Node(30));
        nodes.add(new Node(40));
        nodes.add(new Node(50));

        // Elegimos el primer nodo como coordinador
        Node master = nodes.get(0);

        // Mostrar tiempos iniciales
        System.out.println("=== TIEMPOS INICIALES ===");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println("Nodo " + i + ": " + nodes.get(i).getClock());
        }

        // Paso 1: obtener suma de todos los relojes
        int sum = 0;
        for (Node n : nodes) {
            sum += n.getClock();
        }

        // Paso 2: calcular promedio
        int average = sum / nodes.size();
        System.out.println("\nPromedio calculado: " + average);

        // Paso 3: ajustar cada reloj
        System.out.println("\n=== AJUSTES ===");
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            int offset = average - n.getClock();
            n.adjustClock(offset);

            System.out.println("Nodo " + i + " ajusta en: " + offset);
        }

        // Mostrar tiempos finales
        System.out.println("\n=== TIEMPOS SINCRONIZADOS ===");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println("Nodo " + i + ": " + nodes.get(i).getClock());
        }
    }
}