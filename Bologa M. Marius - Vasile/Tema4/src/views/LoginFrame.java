package views;

import java.awt.GridLayout;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controllers.AdminFrameController;
import controllers.SerializableManager;
import controllers.UserFrameController;
import models.Account;
import models.Bank;
import models.Person;

public class LoginFrame extends JPanel {
	private static JRadioButton userButton;
	private JRadioButton adminButton;
	private static String userName;
	private String user;
	private static final long serialVersionUID = 1L;
	private static String[] buttonNames = { "Login", "Cancel" };
	private Bank bank = new Bank();
	private SerializableManager manager = new SerializableManager();

	@SuppressWarnings("deprecation")
	public void createLoginWindow() throws ParseException {
		bank.setContent(manager.deserializeBank().getContent());
		JPanel loginPanel;
		userButton = new JRadioButton("User");
		userButton.setSelected(true);
		adminButton = new JRadioButton("Admin");
		adminButton.setSelected(false);
		ButtonGroup group = new ButtonGroup();
		group.add(userButton);
		group.add(adminButton);

		JLabel usernameLabel = new JLabel("Username:", JLabel.LEFT);
		JTextField usernameText = new JTextField(10);
		JLabel passwordLabel = new JLabel("Password:", JLabel.LEFT);
		JPasswordField passwordText = new JPasswordField(10);
		loginPanel = new JPanel(false);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		JPanel logPanel = new JPanel();
		logPanel.setLayout(new GridLayout(0, 3));
		logPanel.add(new JLabel("Login as:"));
		logPanel.add(userButton);
		logPanel.add(adminButton);
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(0, 1));
		namePanel.add(usernameLabel);
		namePanel.add(usernameText);
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new GridLayout(0, 1));
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordText);
		loginPanel.add(logPanel);
		loginPanel.add(namePanel);
		loginPanel.add(passwordPanel);
		int option = JOptionPane.showOptionDialog(null, loginPanel, "Login Order ", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, buttonNames, buttonNames[0]);
		if ((option == 0) && (adminButton.isSelected()) && (usernameText.getText().equals("admin"))
				&& (passwordText.getText().equals("1111"))) {
			adminButton.setSelected(true);
			new AdminFrameController();
		} else if ((option == 0) && (userButton.isSelected())) {
			Iterator<Entry<Person, ArrayList<Account>>> iterator = bank.getContent().entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Person, ArrayList<Account>> entry = iterator.next();
				if (usernameText.getText().equals(entry.getKey().getName())
						&& (passwordText.getText().equals("1234"))) {
					userButton.setSelected(true);
					setUserName(usernameText.getText());
					new UserFrameController();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Wrong username or password!");
			createLoginWindow();
		}
	}

	public static JRadioButton getUserButton() {
		return userButton;
	}

	public void setAdminButton(JRadioButton adminButton) {
		this.adminButton = adminButton;
	}

	public static String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		LoginFrame.userName = userName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
