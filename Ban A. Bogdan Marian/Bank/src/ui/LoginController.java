package ui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bank.Bank;

public class LoginController {
	private static final String DEFAULT_PATH = "C:\\bank.ser";
	private AdminPanel adminPanel;
	private PersonPanel personPanel;
	private Bank bank;

	public LoginController() {
		loadDataFromDisk();
		if (bank == null) {
			bank = new Bank();
		}
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(false);
		UserType result = login();

		if (result.equals(UserType.ADMIN)) {
			adminPanel = new AdminPanel(this, frame);
			frame.setVisible(true);
			frame.setTitle("Admin");
			frame.add(adminPanel, BorderLayout.CENTER);
		} else if (result.equals(UserType.CUSTOMER)) {
			personPanel = new PersonPanel(this, frame,bank.getPerson());
			frame.setVisible(true);
			frame.setTitle("Customer");
			frame.add(personPanel, BorderLayout.CENTER);
		}

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				saveDataToDisk(bank);
			}
		});

	}

	private void loadDataFromDisk() {
		try {
			FileInputStream fileIn = new FileInputStream(DEFAULT_PATH);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.bank = (Bank) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			System.out.println("File not found");
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("OrderTree class was not found");
			c.printStackTrace();
			return;
		}
	}

	protected void saveDataToDisk(Object object) {
		try {

			FileOutputStream fileOut = new FileOutputStream(DEFAULT_PATH);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + DEFAULT_PATH + "\n");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	private UserType login() {
		JLabel usernameLabel = new JLabel("Username");
		JTextField user = new JTextField();
		JLabel passwordLabel = new JLabel("Password");
		JPasswordField pass = new JPasswordField();
		Object[] ob = { usernameLabel, user, passwordLabel, pass };
		int result = JOptionPane.showConfirmDialog(null, ob, "Login", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			if (user.getText().equals("admin")
					&& (Arrays.equals(pass.getPassword(), new char[] { '1', '2', '3', '4', '5' }))) {
				return UserType.ADMIN;
			} else if (user.getText().equals("person")
					&& (Arrays.equals(pass.getPassword(), new char[] { '1', '2', '3' }))) {
				return UserType.CUSTOMER;
			} else {
				return login();
			}
		}
		return UserType.NONE;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
