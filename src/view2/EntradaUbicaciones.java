package view2;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import view.presenter.UbicacionPresenter;

import javax.swing.JComboBox;
import java.awt.Color;

public class EntradaUbicaciones {

	private JFrame frame;
	private JTable table;
	private UbicacionPresenter presenter;
	private JComboBox<String> comboCiudades;
	private DefaultTableModel tableModel;

	public EntradaUbicaciones() {
		initialize();
	}

	public void setPresenter(UbicacionPresenter presenter) {
        this.presenter = presenter;
    }
	private void initialize() {
		frame = new JFrame();
        frame.setTitle("Selección de Localidades");
        frame.setBounds(100, 100, 700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        Icon backgroundIcon = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setBounds(0, 0, 700, 500);

       //lista de ciudades
        JLabel lblSeleccion = new JLabel("      SELECCIONE UNA CIUDAD");
        lblSeleccion.setForeground(new Color(255, 255, 255));
        lblSeleccion.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSeleccion.setBounds(20, 29, 200, 20);
        frame.getContentPane().add(lblSeleccion);

        comboCiudades = new JComboBox<>();
        comboCiudades.setBounds(20, 60, 220, 25);
        frame.getContentPane().add(comboCiudades);
        //boton de agregar ciudad

        JButton btnAgregar = new JButton("Agregar Ciudad");
        btnAgregar.setBounds(60, 100, 140, 30);
        frame.getContentPane().add(btnAgregar);
        
        btnAgregar.addActionListener(e -> {
            if (presenter != null) presenter.agregarCiudadPulsado(comboCiudades.getSelectedIndex());
        });
        
     // Nuevos componentes para ingreso manual
        JTextField txtCiudad;
        JTextField txtProvincia;
        JTextField txtLatitud;
        JTextField txtLongitud;
                
        JLabel lblManual = new JLabel("AGREGAR MANUALMENTE");
        lblManual.setForeground(Color.WHITE);
        lblManual.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblManual.setBounds(20, 150, 200, 20);
        frame.getContentPane().add(lblManual);

        // Campo Nombre
        JLabel lblCiudad = new JLabel("Ciudad:");
        lblCiudad.setForeground(Color.WHITE);
        lblCiudad.setBounds(20, 180, 80, 20);
        frame.getContentPane().add(lblCiudad);
        txtCiudad = new JTextField();
        txtCiudad.setBounds(90, 180, 150, 20);
        frame.getContentPane().add(txtCiudad);

        // Campo Provincia
        JLabel lblProvincia = new JLabel("Provincia:");
        lblProvincia.setForeground(Color.WHITE);
        lblProvincia.setBounds(20, 210, 80, 20);
        frame.getContentPane().add(lblProvincia);
        txtProvincia = new JTextField();
        txtProvincia.setBounds(90, 210, 150, 20);
        frame.getContentPane().add(txtProvincia);

        // Campo Latitud
        JLabel lblLatitud = new JLabel("Latitud   -54 < x < -22:");
        lblLatitud.setForeground(Color.WHITE);
        lblLatitud.setBounds(20, 240, 160, 20);
        frame.getContentPane().add(lblLatitud);
        txtLatitud = new JTextField();
        txtLatitud.setBounds(180, 240, 80, 20);
        frame.getContentPane().add(txtLatitud);

        // Campo Longitud
        JLabel lblLongitud = new JLabel("Longitud  -70 < x < -53:");
        lblLongitud.setForeground(Color.WHITE);
        lblLongitud.setBounds(20, 270, 160, 20);
        frame.getContentPane().add(lblLongitud);
        txtLongitud = new JTextField();
        txtLongitud.setBounds(180, 270, 80, 20);
        frame.getContentPane().add(txtLongitud);

        // Botón Agregar Manual
        JButton btnAgregarManual = new JButton("Agregar Manual");
        btnAgregarManual.setBounds(60, 310, 140, 30);
        frame.getContentPane().add(btnAgregarManual);

        // Acción del botón
        btnAgregarManual.addActionListener(e -> {
            if (presenter != null) presenter.agregarCiudadManualPulsado(
                    txtCiudad.getText(),
                    txtProvincia.getText(),
                    txtLatitud.getText(),
                    txtLongitud.getText()
                );
        });
                
        // tabla
        JLabel lblTabla = new JLabel("Ciudades seleccionadas");
        lblTabla.setForeground(new Color(255, 255, 255));
        lblTabla.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTabla.setBounds(300, 19, 200, 20);
        frame.getContentPane().add(lblTabla);

        tableModel = new DefaultTableModel(new Object[]{"Ciudad", "Provincia"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 50, 350, 300);
        frame.getContentPane().add(scrollPane);
        //boton eliminar ciudad
        JButton btnEliminar = new JButton("Eliminar Ciudad");
        btnEliminar.setBounds(400, 360, 150, 30);
        frame.getContentPane().add(btnEliminar);
        btnEliminar.addActionListener(e -> {
            if (presenter != null) presenter.eliminarCiudadPulsado(table.getSelectedRow());
        });
        //boton generar conexion. Pasa a la siguiente pantalla
        JButton btnGenerar = new JButton("Generar Conexión");
        btnGenerar.setBounds(500, 410, 150, 40);
        frame.getContentPane().add(btnGenerar);
        btnGenerar.addActionListener(e -> {
            if (presenter != null) presenter.generarConexionPulsado();
        });
        
        frame.getContentPane().add(backgroundLabel);
        }
        //metodos utiles para el presenter
        
        public void cargarCombo(String[] ciudades) {
            comboCiudades.removeAllItems();
            for (String c : ciudades) comboCiudades.addItem(c);
        }

        public void agregarFilaTabla(String ciudad, String provincia) {
            tableModel.addRow(new Object[]{ciudad, provincia});
        }

        public void eliminarFilaTabla(int fila) {
            if (fila != -1) tableModel.removeRow(fila);
        }

        public JFrame getFrame() { return frame; }
        
        public void mostrarError(String msj) {
            JOptionPane.showMessageDialog(frame, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }

}