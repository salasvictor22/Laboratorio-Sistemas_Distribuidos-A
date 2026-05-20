
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.rmi.Naming;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CalculatorClient extends JFrame {

    private Calculator calculator;

    private JTextField txtNumero1;
    private JTextField txtNumero2;

    private JLabel lblMultiplicacion;
    private JLabel lblDivision;
    private JLabel lblPotencia;
    private JLabel lblEstado;

    private final DecimalFormat formato = new DecimalFormat("#,##0.00");

    public CalculatorClient() {
        configurarVentana();
        conectarServidorRMI();
        construirInterfaz();
    }

    private void configurarVentana() {
        setTitle("Cliente RMI - Calculadora RPC");
        setSize(760, 560);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void conectarServidorRMI() {
        try {
            calculator = (Calculator) Naming.lookup("rmi://localhost:1099/CalculatorService");
        } catch (Exception e) {
            calculator = null;
        }
    }

    private void construirInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        JPanel panelTitulo = crearPanelTitulo();
        JPanel panelContenido = crearPanelContenido();
        JPanel panelEstado = crearPanelEstado();

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);
        panelPrincipal.add(panelEstado, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));

        JLabel titulo = new JLabel("Calculadora RPC Tradicional");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(new Color(33, 37, 41));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitulo = new JLabel("Cliente RMI para multiplicacion, division y potencia");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitulo.setForeground(new Color(108, 117, 125));
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(subtitulo, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelContenido() {
        JPanel panel = new JPanel(new BorderLayout(0, 22));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        JPanel panelEntrada = crearPanelEntrada();
        JPanel panelResultados = crearPanelResultados();
        JPanel panelBotones = crearPanelBotones();

        panel.add(panelEntrada, BorderLayout.NORTH);
        panel.add(panelResultados, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelEntrada() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 18, 12));
        panel.setOpaque(false);

        JLabel lblNumero1 = crearEtiquetaEntrada("Primer numero:");
        JLabel lblNumero2 = crearEtiquetaEntrada("Segundo numero:");

        txtNumero1 = crearCampoTexto();
        txtNumero2 = crearCampoTexto();

        panel.add(lblNumero1);
        panel.add(txtNumero1);
        panel.add(lblNumero2);
        panel.add(txtNumero2);

        return panel;
    }

    private JLabel crearEtiquetaEntrada(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(new Color(52, 58, 64));
        return label;
    }

    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        return campo;
    }

    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 18, 0));
        panel.setOpaque(false);

        JPanel tarjetaMultiplicacion = crearTarjetaResultado(
                "Multiplicacion",
                "0.00",
                new Color(232, 245, 233),
                new Color(27, 94, 32)
        );

        JPanel tarjetaDivision = crearTarjetaResultado(
                "Division",
                "0.00",
                new Color(232, 240, 254),
                new Color(25, 80, 160)
        );

        JPanel tarjetaPotencia = crearTarjetaResultado(
                "Potencia",
                "0.00",
                new Color(255, 243, 205),
                new Color(153, 102, 0)
        );

        lblMultiplicacion = (JLabel) tarjetaMultiplicacion.getComponent(1);
        lblDivision = (JLabel) tarjetaDivision.getComponent(1);
        lblPotencia = (JLabel) tarjetaPotencia.getComponent(1);

        panel.add(tarjetaMultiplicacion);
        panel.add(tarjetaDivision);
        panel.add(tarjetaPotencia);

        return panel;
    }

    private JPanel crearTarjetaResultado(String titulo, String valorInicial, Color fondo, Color colorTexto) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(fondo);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(73, 80, 87));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblValor = new JLabel(valorInicial);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblValor.setForeground(colorTexto);
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);

        tarjeta.add(lblTitulo, BorderLayout.NORTH);
        tarjeta.add(lblValor, BorderLayout.CENTER);

        return tarjeta;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 12, 0));
        panel.setOpaque(false);

        JButton btnCalcular = crearBoton("Calcular", new Color(13, 110, 253), Color.WHITE);
        JButton btnReconectar = crearBoton("Reconectar", new Color(25, 135, 84), Color.WHITE);
        JButton btnLimpiar = crearBoton("Limpiar", new Color(108, 117, 125), Color.WHITE);
        JButton btnSalir = crearBoton("Salir", new Color(220, 53, 69), Color.WHITE);

        btnCalcular.addActionListener(e -> calcularOperaciones());
        btnReconectar.addActionListener(e -> reconectarServidor());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnSalir.addActionListener(e -> System.exit(0));

        panel.add(btnCalcular);
        panel.add(btnReconectar);
        panel.add(btnLimpiar);
        panel.add(btnSalir);

        return panel;
    }

    private JButton crearBoton(String texto, Color fondo, Color colorTexto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(fondo);
        boton.setForeground(colorTexto);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(13, 10, 13, 10));

        return boton;
    }

    private JPanel crearPanelEstado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        lblEstado = new JLabel();
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);

        actualizarEstadoConexion();

        panel.add(lblEstado, BorderLayout.CENTER);

        return panel;
    }

    private void calcularOperaciones() {

        if (calculator == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay conexion con el servidor RMI.\nPrimero ejecute CalculatorServer.",
                    "Error de conexion",
                    JOptionPane.ERROR_MESSAGE
            );

            conectarServidorRMI();
            actualizarEstadoConexion();
            return;
        }

        String textoNumero1 = txtNumero1.getText().trim();
        String textoNumero2 = txtNumero2.getText().trim();

        if (textoNumero1.isEmpty() || textoNumero2.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese ambos numeros para realizar las operaciones.",
                    "Datos requeridos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {

            double a = Double.parseDouble(textoNumero1);
            double b = Double.parseDouble(textoNumero2);

            // Inicio de medicion de tiempo
            long inicio = System.nanoTime();

            // Invocaciones remotas
            double multiplicacion = calculator.multiply(a, b);
            double potencia = calculator.power(a, b);

            double division = 0;

            try {
                division = calculator.divide(a, b);
                lblDivision.setText(formato.format(division));

            } catch (Exception ex) {

                lblDivision.setText("Error");

                JOptionPane.showMessageDialog(
                        this,
                        "No se puede dividir entre cero.",
                        "Division invalida",
                        JOptionPane.WARNING_MESSAGE
                );
            }

            // Fin de medicion de tiempo
            long fin = System.nanoTime();

            // Tiempo en milisegundos
            double tiempoMs = (fin - inicio) / 1_000_000.0;

            // Consumo de memoria
            Runtime runtime = Runtime.getRuntime();

            double memoriaUsadaMB
                    = (runtime.totalMemory() - runtime.freeMemory())
                    / (1024.0 * 1024.0);

            // Mostrar resultados en consola
            System.out.println("----------------------------------");
            System.out.println("Tiempo de respuesta RPC: " + tiempoMs + " ms");
            System.out.println("Consumo de memoria RPC: " + memoriaUsadaMB + " MB");
            System.out.println("----------------------------------");

            // Mostrar resultados en interfaz
            lblMultiplicacion.setText(formato.format(multiplicacion));
            lblPotencia.setText(formato.format(potencia));

            lblEstado.setText("Operaciones realizadas correctamente mediante RPC tradicional.");
            lblEstado.setForeground(new Color(25, 135, 84));

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese valores numericos validos.\nEjemplo: 8 o 8.5",
                    "Formato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrio un error al invocar el metodo remoto:\n" + e.getMessage(),
                    "Error RMI",
                    JOptionPane.ERROR_MESSAGE
            );

            calculator = null;
            actualizarEstadoConexion();
        }
    }

    private void reconectarServidor() {
        conectarServidorRMI();
        actualizarEstadoConexion();

        if (calculator != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Conexion establecida correctamente con el servidor RMI.",
                    "Conexion exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo conectar con el servidor RMI.\nVerifique que CalculatorServer este ejecutandose.",
                    "Sin conexion",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpiarCampos() {
        txtNumero1.setText("");
        txtNumero2.setText("");

        lblMultiplicacion.setText("0.00");
        lblDivision.setText("0.00");
        lblPotencia.setText("0.00");

        txtNumero1.requestFocus();

        actualizarEstadoConexion();
    }

    private void actualizarEstadoConexion() {
        if (lblEstado == null) {
            return;
        }

        if (calculator != null) {
            lblEstado.setText("Estado: conectado al servidor RMI.");
            lblEstado.setForeground(new Color(25, 135, 84));
        } else {
            lblEstado.setText("Estado: no conectado. Verifique que el servidor RMI este ejecutandose.");
            lblEstado.setForeground(new Color(220, 53, 69));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorClient ventana = new CalculatorClient();
            ventana.setVisible(true);
        });
    }
}
