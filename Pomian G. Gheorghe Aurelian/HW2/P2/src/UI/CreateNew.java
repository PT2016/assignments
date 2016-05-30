package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Helpers.StateManager;
import Helpers.StateManager.State;
import data.Accounts;
import java.awt.Font;

public class CreateNew {

	public JFrame frame;
	private JTextField Username;
	private JPasswordField SCode;
	private JLabel lblSecurityCode;
	private JButton Create;
	private JButton btnBack;
	private String name, pass1, pass2, code;
	private boolean admin;
	private JPasswordField Password1;
	private JPasswordField Password2;
	/**
	 * Create the application.
	 */
	public CreateNew() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 450, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Username = new JTextField();
		Username.setForeground(new Color(135, 206, 250));
		Username.setBackground(new Color(224, 255, 255));
		Username.setBounds(121, 11, 188, 20);
		frame.getContentPane().add(Username);
		Username.setColumns(10);
		
		Password1 = new JPasswordField();
		Password1.setForeground(new Color(135, 206, 250));
		Password1.setBackground(new Color(224, 255, 255));
		Password1.setBounds(121, 36, 188, 20);
		frame.getContentPane().add(Password1);
		
		Password2 = new JPasswordField();
		Password2.setForeground(new Color(135, 206, 250));
		Password2.setBackground(new Color(224, 255, 255));
		Password2.setBounds(121, 61, 188, 20);
		frame.getContentPane().add(Password2);
		
		JLabel label = new JLabel("Username");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setForeground(new Color(0, 51, 51));
		label.setBounds(10, 14, 101, 14);
		frame.getContentPane().add(label);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setForeground(new Color(0, 51, 51));
		lblPassword.setBounds(10, 39, 101, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password");
		lblRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRepeatPassword.setForeground(new Color(0, 51, 51));
		lblRepeatPassword.setBounds(10, 64, 101, 14);
		frame.getContentPane().add(lblRepeatPassword);
		
		JCheckBox chckbxAdmin = new JCheckBox("Admin");
		chckbxAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxAdmin.setForeground(new Color(0, 51, 51));
		chckbxAdmin.setBackground(new Color(135, 206, 235));
		chckbxAdmin.setSelected(false);
		chckbxAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAdmin.isSelected()){
					chckbxAdmin.setSelected(true);
					SCode.setEnabled(true);
				} else {
					chckbxAdmin.setSelected(false);
					SCode.setEnabled(false);
				}
			}
		});
		chckbxAdmin.setBounds(6, 85, 101, 23);
		frame.getContentPane().add(chckbxAdmin);
		
		SCode = new JPasswordField();
		SCode.setForeground(new Color(135, 206, 250));
		SCode.setBackground(new Color(224, 255, 255));
		SCode.setEnabled(false);
		SCode.setColumns(10);
		SCode.setBounds(121, 86, 77, 20);
		frame.getContentPane().add(SCode);
		
		lblSecurityCode = new JLabel("Security Code");
		lblSecurityCode.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSecurityCode.setForeground(new Color(0, 51, 51));
		lblSecurityCode.setBounds(208, 89, 101, 14);
		frame.getContentPane().add(lblSecurityCode);
		
		Create = new JButton("Create");
		Create.setFont(new Font("Tahoma", Font.BOLD, 11));
		Create.setForeground(new Color(0, 51, 51));
		Create.setBackground(new Color(72, 209, 204));
		Create.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				admin = chckbxAdmin.isSelected();
				name = Username.getText();
				pass1 = Password1.getText();
				pass2 = Password2.getText();
				if(name.isEmpty())
					Accounts.infoBox("Username field is empty!", "Error");
				else if(Accounts.searchAccount(name, "", true))
					Accounts.infoBox("Username is already taken!", "Error");
				else if(pass1.isEmpty())
					Accounts.infoBox("Password field is empty!", "Error");
				else if(!Objects.equals(pass1,pass2))
					Accounts.infoBox("Passwords do not match!", "Error");
				else if(admin) {
					code = SCode.getText();
					if(Objects.equals(code,"admin")){
						Accounts.addAccount(name, pass1, admin);
						Accounts.infoBox("Admin account created!", "Succesfull");
						Username.setText("");
						Password1.setText("");
						Password2.setText("");
						SCode.setText("");
						StateManager.setState(State.MAINMENU);
						StateManager.update();
					} else
						Accounts.infoBox("Security code invalid!", "Error");
				} else {
					Accounts.addAccount(name, pass1, false);
					Accounts.infoBox("User account created!", "Succesfull");
					Username.setText("");
					Password1.setText("");
					Password2.setText("");
					SCode.setText("");
					StateManager.setState(State.MAINMENU);
					StateManager.update();
				}
					
			}
		});
		Create.setBounds(341, 10, 91, 43);
		frame.getContentPane().add(Create);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setForeground(new Color(0, 51, 51));
		btnBack.setBackground(new Color(72, 209, 204));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StateManager.setState(State.MAINMENU);
				StateManager.update();
			}
		});
		btnBack.setBounds(10, 115, 91, 23);
		frame.getContentPane().add(btnBack);
		

	}
}
