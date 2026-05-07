package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


public class MenuFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int[] sliderValues = new int[3];
	private JLabel[] valueLabels = new JLabel[3];
	private final String[] sliderValueLabels = { "USD ", "% ", "USD " };
	private final int[] sliderMinimums = { 0, 0, 0 };
	private final int[] sliderMaximums = { Config.getMaxCostPorKilometros(), 100, Config.getCrossProvinceCablingCost() };

	public MenuFrame() {
		setTitle("\"Programacion III - Trabajo\"");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Icon backgroundIcon = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setBounds(-175, 0, 772, 531);
		
		for (int i = 0; i < sliderValues.length; i++) {
			final int index = i;
			JSlider slider = new JSlider();
			slider.setBounds(122, 115 + 107 * i, 307, 26);
			slider.setMinimum(sliderMinimums[i]);
			slider.setMaximum(sliderMaximums[i]);
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					sliderValues[index] = slider.getValue();
					valueLabels[index].setText(sliderValueLabels[index] + sliderValues[index]);
				}
			});
			contentPane.add(slider);

			JLabel valueLabel = new JLabel(sliderValueLabels[i] + sliderMinimums[i]);
			valueLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
			valueLabel.setBounds(437, 115 + 107 * i, 200, 26);
			valueLabels[i] = valueLabel;
			contentPane.add(valueLabel);

			JButton btnSelectCities = new JButton("Seleccionar ciudades");
			btnSelectCities.setBackground(new Color(192, 192, 192));
			btnSelectCities.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConexionFrame launch;
					try {
						double[] sliderValuesDouble = new double[3];
						for (int i = 0; i < sliderValuesDouble.length; i++) {
							sliderValuesDouble[i] = (double) sliderValues[i];
						}
						if (sliderValuesDouble[0] == 0) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar el costo por kilometro", "Mensaje",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							dispose();
							launch = new ConexionFrame(sliderValuesDouble[0], sliderValuesDouble[1],
									sliderValuesDouble[2]);
							launch.setResizable(false);
							launch.setVisible(true);
							launch.setLocationRelativeTo(null);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			btnSelectCities.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnSelectCities.setBounds(175, 438, 256, 48);
			contentPane.add(btnSelectCities);

			JLabel lblTitle1 = createLabel("Conectando Ciudades", 23, 41, 25, 500, 42);
			JLabel lblTitle2 = createLabel("Costo del kilometro de cableado", 14, 117, 78, 334, 26);
			JLabel lblTitle3 = createLabel("Aumentar si la conexion tiene + 300km", 14, 117, 166, 334, 26);
			contentPane.add(lblTitle1);
			contentPane.add(lblTitle2);
			contentPane.add(lblTitle3);
		}

		JLabel lblExtraCost = createLabel("Costo extra si las localidades son de distintas provincias", 14, 77, 292,420, 26);
		contentPane.add(lblExtraCost);
		contentPane.add(backgroundLabel);
	}

	private JLabel createLabel(String text, int fontSize, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, fontSize));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(x, y, width, height);
		return label;
	}
}
