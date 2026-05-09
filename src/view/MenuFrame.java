package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import apis.Config;
import view.presenter.MenuPresenter;

public class MenuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MenuPresenter presenter;
	private int[] sliderValues = new int[3];
	private JLabel[] valueLabels = new JLabel[3];
	private final String[] sliderValueLabels = {"USD ", "% ", "USD "};
	private final int[] sliderMinimums = {0, 0, 0
	};
	private final int[] sliderMaximums = {
			Config.getMaxCostPorKilometros(),
			100,
			Config.getCrossProvinceCablingCost()
	};
	
	public MenuFrame() {
		
		setTitle("Programacion III - Trabajo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 536);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		Icon backgroundIcon = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setBounds(-175, 0, 772, 531);
		crearLabels();
		crearSliders();
		crearBoton();
		contentPane.add(backgroundLabel);
	}

	private void crearSliders() {
		for (int i = 0; i < sliderValues.length; i++) {
			final int index = i;
			JSlider slider = new JSlider();
			slider.setBounds(122, 115 + 107 * i, 307, 26);
			slider.setMinimum(sliderMinimums[i]);
			slider.setMaximum(sliderMaximums[i]);
			slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					sliderValues[index] = slider.getValue();
					valueLabels[index].setText(sliderValueLabels[index] + sliderValues[index]);
				}
			});
			contentPane.add(slider);
			
			JLabel valueLabel = new JLabel(
					sliderValueLabels[i] + sliderMinimums[i]
			);
			valueLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
			valueLabel.setBounds(437, 115 + 107 * i, 200, 26);
			
			valueLabels[i] = valueLabel;
			contentPane.add(valueLabel);
		}
	}

	private void crearBoton() {
		JButton btnSelectCities = new JButton("Seleccionar ciudades");
		btnSelectCities.setBackground(new Color(192, 192, 192));
		btnSelectCities.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelectCities.setBounds(175, 438, 256, 48);
		btnSelectCities.addActionListener(e -> presenter.seleccionarCiudades());
		contentPane.add(btnSelectCities);
	}

	private void crearLabels() {
		JLabel lblTitle1 = createLabel("Conectando Ciudades", 23, 41, 25, 500,42);
		JLabel lblTitle2 = createLabel("Costo del kilometro de cableado", 14, 117, 78, 334, 26);
		JLabel lblTitle3 = createLabel("Aumentar si la conexion tiene + 300km", 14, 117, 166, 334, 26);
		JLabel lblExtraCost = createLabel("Costo extra si las localidades son de distintas provincias", 14, 77, 292, 420, 26);
		contentPane.add(lblTitle1);
		contentPane.add(lblTitle2);
		contentPane.add(lblTitle3);
		contentPane.add(lblExtraCost);
	}

	public void setPresenter(MenuPresenter presenter) {
		this.presenter = presenter;
	}

	public double[] getSliderValues() {
		double[] values = new double[sliderValues.length];
		for (int i = 0; i < sliderValues.length; i++)
			values[i] = sliderValues[i];
		return values;
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

	public void cerrar() {
		dispose();
	}

	private JLabel createLabel(String text,int fontSize,int x,int y,int width,int height) {
		JLabel label = new JLabel(text);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, fontSize));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(x, y, width, height);
		return label;
	}
}