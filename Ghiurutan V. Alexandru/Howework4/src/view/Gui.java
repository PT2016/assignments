package view;

import javax.swing.*;

import model.Bank;
import model.Person;

import java.awt.event.*;


public class Gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField userField, passwordField;
	private JPasswordField passwordArea;
	private JTextArea userArea;
	private JPanel user, password, action, input;
	private JButton logIn;
	private static String ADMIN_USERNAME = "Admin";
	private static String ADMIN_PASSWORD = "Admin";
	private Bank bank;
	private Person customer;

	public Gui() {
		this.setTitle("Bank application");
		initializeFields();
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		bank = Bank.getInstance();
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeFields() {
		user = new JPanel();
		userField = new JTextField("User:");
		userField.setEnabled(false);
		userArea = new JTextArea(1, 10);
		user.add(userField);
		user.add(userArea);

		password = new JPanel();
		passwordField = new JTextField("Password:");
		passwordField.setEnabled(false);
		passwordArea = new JPasswordField(10);
		password.add(passwordField);
		password.add(passwordArea);
		input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		input.add(user);
		input.add(password);

		action = new JPanel();
		logIn = new JButton("Log in");
		logIn.addActionListener(this);
		action.add(logIn);

		this.add(input);
		this.add(action);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logIn) {
			String userText = userArea.getText();
			String passwordText = String.copyValueOf(passwordArea.getPassword());
			if (userText.equals(ADMIN_USERNAME) || passwordText.equals(ADMIN_PASSWORD)) {
				this.dispose();
				new AdminView();
			} else if (userText.equals("") || passwordText.equals("")) {
				JOptionPane.showMessageDialog(this, "You didn't complete all the fields.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if ((customer = bank.getCustomer(userText, passwordText)) != null) {
				this.dispose();
				new CustomerView(customer);
			} else {
				JOptionPane.showMessageDialog(this, "Invalid userName or password!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
