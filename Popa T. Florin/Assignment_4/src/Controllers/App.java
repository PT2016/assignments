package Controllers;

import javax.swing.JFrame;

import models.GUI;

public class App {

	public static void main(String[] args) {
		
		GUI window = new GUI();
		window.frame.setVisible(true);
		window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.frame.setResizable(false);
		window.frame.setLocationRelativeTo(null);
		window.frame.setTitle("BankApp");
		
	}
	
}
