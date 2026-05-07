package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import model.Ciudad;
import model.ConectarCiudades;

public class ConexionFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldProvince;
	private JTextField textFieldLatitude;
	private JTextField textFieldLongitude;
	private List<Ciudad> ciudades;
	private List<Ciudad> ciudadesSeleccionadas;
	private ConectarCiudades conectarCiudades;
	private DefaultTableModel modelTable;

	public ConexionFrame(double costPerKilometer, double increaseLongDistanceCost, double fixedCrossProvincialCost)
			throws Exception {

		setTitle("Programacion III - Conectando Ciudades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 559);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		Icon backgroundIcon = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setBounds(0, 0, 772, 531);

		ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/icono.png"));
		setIconImage(icono.getImage());

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ciudadesSeleccionadas = new ArrayList<Ciudad>();
		conectarCiudades = new ConectarCiudades(costPerKilometer, increaseLongDistanceCost, fixedCrossProvincialCost);
		modelTable = new DefaultTableModel(new Object[] { "Ciudad", "Provincia" }, 0);

		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(299, 21, 326, 400);
		contentPane.add(scrollPane);
		table.setModel(modelTable);
		table.setDefaultEditor(Object.class, null);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(32, 52, 220, 22);
		contentPane.add(comboBox);

		ciudades = conectarCiudades.buscarCiudad();
		for (Ciudad ciudad : ciudades) {
			comboBox.addItem(ciudad.getNombre() + " , " + ciudad.getProvincia());
		}

		createLabel("SELECCIONE UNA CIUDAD", 14, 44, 27, 253, 14);
		createLabel("AGREGAR CIUDAD NO LISTADA", 14, 45, 168, 253, 22);
		createLabel("Ciudad", 15, 15, 236, 220, 22);
		createLabel("Provincia", 15, 15, 296, 220, 22);
		createLabel("Latitud   -54 < x < -22", 14, 15, 338, 170, 22);
		createLabel("Longitud   -70 < x < -53", 14, 15, 371, 170, 22);
		createLabel("Lista de ciudades seleccionadas", 15, 343, 26, 257, 14);

		textFieldName = createTextField(100, 234, 150, 20, 10, JTextField.CENTER);
		textFieldProvince = createTextField(100, 299, 150, 20, 10, JTextField.CENTER);
		textFieldLatitude = createTextField(195, 340, 57, 20, 10, JTextField.CENTER);
		textFieldLongitude = createTextField(195, 373, 57, 20, 10, JTextField.CENTER);

		entryValidation(textFieldLatitude);
		entryValidation(textFieldLongitude);

		JButton btnAddListedCity = new JButton("Agregar Ciudad");
		btnAddListedCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != -1) {
					Ciudad ciudad = ciudades.get(comboBox.getSelectedIndex());
					if (!ciudadesSeleccionadas.contains(ciudad)) {
						ciudadesSeleccionadas.add(ciudad);
						addCityInTable(ciudad.getNombre(), ciudad.getProvincia());
					} else {
						showMessageDialog("La ciudad ya ha sido agregada.");
					}
				}
			}
		});
		btnAddListedCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddListedCity.setBounds(74, 106, 126, 36);
		contentPane.add(btnAddListedCity);

		JButton btnDeleteCity = new JButton("Eliminar Ciudad");
		btnDeleteCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					showMessageDialog("La ciudad fue eliminada con exito.");
					ciudadesSeleccionadas.remove(table.getSelectedRow());
					modelTable.removeRow(table.getSelectedRow());
				}
			}
		});

		btnDeleteCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeleteCity.setBounds(405, 432, 126, 36);
		contentPane.add(btnDeleteCity);

		JButton btnAddUnlistedCity = new JButton("Agregar Ciudad");
		btnAddUnlistedCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (missingCityData()) {
					showMessageDialog("Faltan datos necesarios para agregar la ciudad");
				} else {
					try {
						Ciudad newCiudad = createCity();
						if (ciudadesSeleccionadas.contains(newCiudad)) {
							showMessageDialog("Ya agrego esta ciudad, agregue otra");
						} else {
							ciudadesSeleccionadas.add(newCiudad);
							if (ciudadesSeleccionadas.size() != 0) {
								addCityInTable(textFieldName.getText(), textFieldProvince.getText());
							}
						}
					} catch (InvalidParameterException ex) {
						showMessageDialog("Alguno de los datos ingresados no es correcto");
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (JSONException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddUnlistedCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddUnlistedCity.setBounds(74, 428, 126, 36);
		contentPane.add(btnAddUnlistedCity);

		JButton btnMST = new JButton("Generar \r\nConexion");
		btnMST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ciudadesSeleccionadas.size() == 0) {
					showMessageDialog("Seleccione almenos una ciudad");
				} else {
					//WeightedGraph mst = null;
					try {
						//mst = connectingCities.minimumSpanningTree(selectedCities);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					dispose();
					//MapaFrame mapScreen = new MapaFrame(mst);
					//mapScreen.setResizable(false);
					//mapScreen.setVisible(true);
				}
			}
		});
		btnMST.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMST.setBounds(635, 167, 126, 53);
		contentPane.add(btnMST);
		
		addHoverEffect(btnMST, Color.GREEN);
		addHoverEffect(btnDeleteCity, Color.RED);
		
		contentPane.add(backgroundLabel);
	}

	// Metodos Auxiliares
	private void createLabel(String text, int fontSize, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		Font font = new Font("Tahoma", Font.BOLD, fontSize);
		label.setFont(font);
		label.setBounds(x, y, width, height);
		contentPane.add(label);
	}

	private JTextField createTextField(int x, int y, int width, int height, int columns, int horizontalAlignment) {
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(horizontalAlignment);
		textField.setColumns(columns);
		textField.setBounds(x, y, width, height);
		contentPane.add(textField);
		return textField;
	}
	
	private Ciudad createCity() throws InvalidParameterException, NumberFormatException, JSONException, IOException {
		return conectarCiudades.crearCiudad(textFieldName.getText(), textFieldProvince.getText(),
				Double.parseDouble(textFieldLatitude.getText()), Double.parseDouble(textFieldLongitude.getText()));
	}
	
	private void addCityInTable(String city, String province) {
		Object[] row = { city, province };
		modelTable.addRow(row);
	}
	
	private boolean missingCityData() {
		return textFieldName.getText().equals("") || textFieldProvince.getText().equals("")
				|| textFieldLatitude.getText().equals("") || textFieldLongitude.getText().equals("");
	}

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

	private void addHoverEffect(JButton button, Color hoverColor) {
	    button.addMouseListener(new MouseAdapter() {
	        public void mouseEntered(MouseEvent e) {
	            button.setBackground(hoverColor);
	        }
	        
	        public void mouseExited(MouseEvent e) {
	            button.setBackground(UIManager.getColor("Button.background"));
	        }
	    });
	}

	private void entryValidation(JTextField jText) {
		jText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				// valida que la entrada sea un valor numeral o "-" o "." y de maxima longitud 8
				boolean numeros = (key >= 48 && key <= 57) || key == 45 | key == 46;
				if ((!numeros || jText.getText().trim().length() == 8)) {
					e.consume();
				}
			}
		});
	}
}
