package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Helpers.Account;
import Helpers.StateManager;
import Helpers.StateManager.State;
import data.Accounts;
import java.awt.Color;
import java.awt.Font;

public class MainMenu {

	public static void main(String[] args) {
		StateManager.update();
	}

	public JFrame frame;
	private JTextField Username;
	private String name, pass;
	private JPasswordField Password;

	/**
	 * @wbp.parser.entryPoint
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 355, 140);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Username = new JTextField();
		Username.setForeground(new Color(0, 0, 128));
		Username.setBackground(new Color(224, 255, 255));
		Username.setBounds(86, 12, 148, 20);
		frame.getContentPane().add(Username);
		Username.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(0, 51, 51));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(10, 15, 66, 14);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 51, 51));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(10, 46, 66, 14);
		frame.getContentPane().add(lblPassword);

		JButton Login = new JButton("Login");
		Login.setBackground(new Color(72, 209, 204));
		Login.setForeground(new Color(0, 51, 51));
		Login.setFont(new Font("Tahoma", Font.BOLD, 11));
		Login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				name = Username.getText();
				pass = Password.getText();
				if (name.isEmpty())
					Accounts.infoBox("Username field is empty!", "Error");
				else if (pass.isEmpty())
					Accounts.infoBox("Password field is empty!", "Error");
				else if (!Accounts.searchAccount(name, pass, false))
					Accounts.infoBox("Username or Password incorect!", "Error");
				else {
					Username.setText("");
					Password.setText("");
					Account.setName(name);
					if (Accounts.isAdmin())
						StateManager.setState(State.ADMIN);
					else
						StateManager.setState(State.USER);

					StateManager.update();
				}
			}
		});
		Login.setBounds(244, 11, 91, 52);
		frame.getContentPane().add(Login);

		JButton btnCreateNew = new JButton("Create New");
		btnCreateNew.setBackground(new Color(72, 209, 204));
		btnCreateNew.setForeground(new Color(0, 51, 51));
		btnCreateNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StateManager.setState(State.CREATE);
				StateManager.update();
			}
		});
		btnCreateNew.setBounds(86, 74, 148, 23);
		frame.getContentPane().add(btnCreateNew);

		Password = new JPasswordField();
		Password.setForeground(new Color(0, 0, 128));
		Password.setBackground(new Color(224, 255, 255));
		Password.setBounds(86, 43, 148, 20);
		frame.getContentPane().add(Password);
	}
}
