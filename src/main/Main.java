package main;

import model.Calculador;
import model.Grafo;
import view.MenuFrame;

public class Main {

	public static void main(String[] args) {
		Grafo mapa= new Grafo();
		Calculador calculadorMST = new Calculador();
		MenuFrame a = new MenuFrame();
		a.setVisible(true);
		a.setLocationRelativeTo(null);
	}

}
