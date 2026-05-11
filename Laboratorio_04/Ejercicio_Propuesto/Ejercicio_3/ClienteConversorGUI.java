
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

public class ClienteConversorGUI extends JFrame {

    private ConversorMoneda conversor;

    private JTextField txtMonto;
    private JLabel lblResultadoDolares;
    private JLabel lblResultadoEuros;
    private JLabel lblEstado;

    private final DecimalFormat formato = new DecimalFormat("#,##0.00");

    public ClienteConversorGUI() {
        configurarVentana();
        conectarServidorRMI();
        construirInterfaz();
    }

    private void configurarVentana() {
        setTitle("Cliente RMI - Conversor de Moneda");
        setSize(620, 530);
        setMinimumSize(new Dimension(560, 390));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void conectarServidorRMI() {
        try {
            conversor = (ConversorMoneda) Naming.lookup("rmi://localhost:1099/ConversorMonedaService");
        } catch (Exception e) {
            conversor = null;
        }
    }

    private void construirInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

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
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel titulo = new JLabel("Conversor de Moneda RMI");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(33, 37, 41));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitulo = new JLabel("Conversion remota de soles a dolares y euros");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitulo.setForeground(new Color(108, 117, 125));
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(subtitulo, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelContenido() {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
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
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        JLabel lblMonto = new JLabel("Monto en soles:");
        lblMonto.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblMonto.setForeground(new Color(52, 58, 64));

        txtMonto = new JTextField();
        txtMonto.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtMonto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        panel.add(lblMonto, BorderLayout.NORTH);
        panel.add(txtMonto, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 18, 0));
        panel.setOpaque(false);

        JPanel tarjetaDolares = crearTarjetaResultado(
                "Dolares",
                "USD 0.00",
                new Color(232, 245, 233),
                new Color(27, 94, 32)
        );

        JPanel tarjetaEuros = crearTarjetaResultado(
                "Euros",
                "EUR 0.00",
                new Color(232, 240, 254),
                new Color(25, 80, 160)
        );

        lblResultadoDolares = (JLabel) tarjetaDolares.getComponent(1);
        lblResultadoEuros = (JLabel) tarjetaEuros.getComponent(1);

        panel.add(tarjetaDolares);
        panel.add(tarjetaEuros);

        return panel;
    }

    private JPanel crearTarjetaResultado(String titulo, String valorInicial, Color fondo, Color colorTexto) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(fondo);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(73, 80, 87));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblValor = new JLabel(valorInicial);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblValor.setForeground(colorTexto);
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);

        tarjeta.add(lblTitulo, BorderLayout.NORTH);
        tarjeta.add(lblValor, BorderLayout.CENTER);

        return tarjeta;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 12, 0));
        panel.setOpaque(false);

        JButton btnConvertir = crearBoton("Convertir", new Color(13, 110, 253), Color.WHITE);
        JButton btnLimpiar = crearBoton("Limpiar", new Color(108, 117, 125), Color.WHITE);
        JButton btnSalir = crearBoton("Salir", new Color(220, 53, 69), Color.WHITE);

        btnConvertir.addActionListener(e -> convertirMoneda());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnSalir.addActionListener(e -> System.exit(0));

        panel.add(btnConvertir);
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
        boton.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));

        return boton;
    }

    private JPanel crearPanelEstado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        lblEstado = new JLabel();
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);

        if (conversor != null) {
            lblEstado.setText("Estado: conectado al servidor RMI");
            lblEstado.setForeground(new Color(25, 135, 84));
        } else {
            lblEstado.setText("Estado: no conectado. Verifique que el servidor RMI este ejecutandose.");
            lblEstado.setForeground(new Color(220, 53, 69));
        }

        panel.add(lblEstado, BorderLayout.CENTER);

        return panel;
    }

    private void convertirMoneda() {
        if (conversor == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo conectar con el servidor RMI.\nPrimero ejecute ServidorConversor.",
                    "Error de conexion",
                    JOptionPane.ERROR_MESSAGE
            );
            conectarServidorRMI();
            actualizarEstadoConexion();
            return;
        }

        String textoMonto = txtMonto.getText().trim();

        if (textoMonto.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un monto en soles.",
                    "Dato requerido",
                    JOptionPane.WARNING_MESSAGE
            );
            txtMonto.requestFocus();
            return;
        }

        try {
            double montoSoles = Double.parseDouble(textoMonto);

            if (montoSoles < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "El monto no puede ser negativo.",
                        "Monto invalido",
                        JOptionPane.WARNING_MESSAGE
                );
                txtMonto.requestFocus();
                return;
            }

            double dolares = conversor.convertirADolares(montoSoles);
            double euros = conversor.convertirAEuros(montoSoles);

            lblResultadoDolares.setText("USD " + formato.format(dolares));
            lblResultadoEuros.setText("EUR " + formato.format(euros));

            lblEstado.setText("Conversion realizada correctamente.");
            lblEstado.setForeground(new Color(25, 135, 84));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un valor numerico valido.\nEjemplo: 150 o 150.50",
                    "Formato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
            txtMonto.requestFocus();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrio un error al invocar el metodo remoto:\n" + e.getMessage(),
                    "Error RMI",
                    JOptionPane.ERROR_MESSAGE
            );

            conversor = null;
            actualizarEstadoConexion();
        }
    }

    private void limpiarCampos() {
        txtMonto.setText("");
        lblResultadoDolares.setText("USD 0.00");
        lblResultadoEuros.setText("EUR 0.00");
        txtMonto.requestFocus();

        actualizarEstadoConexion();
    }

    private void actualizarEstadoConexion() {
        if (conversor != null) {
            lblEstado.setText("Estado: conectado al servidor RMI");
            lblEstado.setForeground(new Color(25, 135, 84));
        } else {
            lblEstado.setText("Estado: no conectado. Verifique que el servidor RMI este ejecutandose.");
            lblEstado.setForeground(new Color(220, 53, 69));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteConversorGUI ventana = new ClienteConversorGUI();
            ventana.setVisible(true);
        });
    }
}
