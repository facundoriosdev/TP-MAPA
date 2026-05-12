package view2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import view.presenter.MenuPresenter;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class Menu {

	private JFrame frame;
	
	private JTextField txtCostoKm;
	private JTextField txtCostoInterprovincial;
	private JTextField txtPorcentajeAumento;
	private MenuPresenter presenter;

	public Menu() {
		initialize();
	}
	public void setPresenter(MenuPresenter presenter) {
        this.presenter = presenter;
    }

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("TP2 - Programación III");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Icon backgroundIcon = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setBounds(-175, 0, 772, 531);
		
		JLabel lblTitulo = new JLabel("Complete los datos");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(143, 21, 200, 27);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblCosto = new JLabel("Costo por KM");
		lblCosto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCosto.setForeground(new Color(255, 255, 255));
		lblCosto.setBounds(10, 73, 200, 14);
		frame.getContentPane().add(lblCosto);
		
		JLabel lblAumento = new JLabel("Porcentaje de aumento");
		lblAumento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAumento.setForeground(new Color(255, 255, 255));
		lblAumento.setBounds(10, 125, 200, 14);
		frame.getContentPane().add(lblAumento);
		
		JLabel lblFijo = new JLabel("Costo fijo interprovincial");
		lblFijo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFijo.setForeground(new Color(255, 255, 255));
		lblFijo.setBounds(10, 170, 200, 38);
		frame.getContentPane().add(lblFijo);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(e -> {
            if (presenter != null) {
                presenter.onIniciarPulsado();
            }
        });
		btnIniciar.setBounds(157, 227, 89, 23);
		frame.getContentPane().add(btnIniciar);
		
		txtCostoKm = new JTextField();
		txtCostoKm.setBounds(192, 70, 86, 20);
		frame.getContentPane().add(txtCostoKm);
		txtCostoKm.setColumns(10);
		
		txtPorcentajeAumento = new JTextField();
		txtPorcentajeAumento.setBounds(192, 122, 86, 20);
		frame.getContentPane().add(txtPorcentajeAumento);
		txtPorcentajeAumento.setColumns(10);
		
		txtCostoInterprovincial = new JTextField();
		txtCostoInterprovincial.setBounds(192, 179, 86, 20);
		frame.getContentPane().add(txtCostoInterprovincial);
		txtCostoInterprovincial.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("(Pasando los 300KM)");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(10, 145, 123, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		frame.getContentPane().add(backgroundLabel);
	}

	public String getCostoKm() { return txtCostoKm.getText(); }
	public String getCostoInterprovincial() { return txtCostoInterprovincial.getText(); }
	public String getPorcentajeAumento() { return txtPorcentajeAumento.getText(); }
	public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }
	public void cerrar() {
	    frame.dispose();
	}

	public JFrame getFrame() {
	    return frame;
	}
	

} 