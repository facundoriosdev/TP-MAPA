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