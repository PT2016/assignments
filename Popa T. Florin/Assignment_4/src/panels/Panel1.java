package panels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Panel1 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel error;
	public JTextField userField;
	public JLabel user;
	public JPasswordField passField;
	public JLabel password;
	public JButton login;

	public Panel1(){
		
		setLayout(null);
		
		error = new JLabel("Invalid Username or Password! Try again!");
		error.setForeground(Color.RED);
		error.setBounds(209, 381, 258, 16);
		error.setVisible(false);
		
		userField = new JTextField();
		userField.setBounds(249, 136, 150, 30);
		
		passField = new JPasswordField();
		passField.setBounds(249, 206, 150, 30);
		
		user = new JLabel("Username");
		user.setBounds(293, 112, 62, 16);
		
		password = new JLabel("Password");
		password.setBounds(294, 186, 59, 16);
		
		login = new JButton("Login");
		login.setBounds(269, 302, 117, 29);
		
		add(error);
		add(userField);
		add(passField);
		add(user);
		add(password);
		add(login);
	}
	
}
