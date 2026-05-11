package view2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import view.presenter.MenuPresenter;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Complete los datos");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(143, 21, 135, 27);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblCosto = new JLabel("Costo por KM (USD)");
		lblCosto.setBounds(10, 73, 95, 14);
		frame.getContentPane().add(lblCosto);
		
		JLabel lblAumento = new JLabel("Porcentaje de aumento");
		lblAumento.setBounds(10, 125, 137, 14);
		frame.getContentPane().add(lblAumento);
		
		JLabel lblFijo = new JLabel("Costo fijo interprovincial");
		lblFijo.setBounds(10, 170, 123, 38);
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
		txtCostoKm.setBounds(157, 70, 86, 20);
		frame.getContentPane().add(txtCostoKm);
		txtCostoKm.setColumns(10);
		
		txtCostoInterprovincial = new JTextField();
		txtCostoInterprovincial.setBounds(157, 122, 86, 20);
		frame.getContentPane().add(txtCostoInterprovincial);
		txtCostoInterprovincial.setColumns(10);
		
		txtPorcentajeAumento = new JTextField();
		txtPorcentajeAumento.setBounds(157, 179, 86, 20);
		frame.getContentPane().add(txtPorcentajeAumento);
		txtPorcentajeAumento.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("(Pasando los 300KM)");
		lblNewLabel_4.setBounds(10, 139, 106, 14);
		frame.getContentPane().add(lblNewLabel_4);

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
