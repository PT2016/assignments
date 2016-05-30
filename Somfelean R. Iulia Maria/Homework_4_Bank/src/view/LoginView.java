package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame{

	private JTextField userText;
	private JPasswordField passwordText;
	private JButton loginButton;
	
	public LoginView(){
		super("Log In");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel loginPanel = new JPanel();
		this.add(loginPanel);
		placeComponents(loginPanel);
		
		this.setVisible(true);
	}

	private void placeComponents(JPanel loginPanel) {

		loginPanel.setLayout(null);
		

		JLabel userLabel = new JLabel("SSID: ");
		userLabel.setBounds(10, 10, 80, 25);
		loginPanel.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		loginPanel.add(userText);

		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(10, 40, 80, 25);
		loginPanel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		loginPanel.add(passwordText);

		loginButton = new JButton("Log In");
		loginButton.setBounds(100, 80, 80, 25);
		loginPanel.add(loginButton);
		
	}
	
	public String getUserText() {
		return userText.getText();
	}
	
	public String getPasswordText() {
		return passwordText.getText();
	}
	
	public void loginActionListener(ActionListener listenForLogin){
		loginButton.addActionListener(listenForLogin);
	}
}
