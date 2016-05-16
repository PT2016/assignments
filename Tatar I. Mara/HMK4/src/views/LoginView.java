package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import controller.Serializer;
import models.Bank;
import models.Person;

public class LoginView extends JFrame implements ActionListener {

	Serializer io;
	Bank bank;
	private String admin;
	private String adminPass;
	private JFrame frame;
	private JButton loginButton;
	private JButton registerButton;
	private JTextField userText;
	private JPasswordField passwordText;
	private JRadioButton adminButton, userButton;

	public LoginView() {
		io = new Serializer();
		bank = io.deserializeBank();
		admin = "admin";
		adminPass = "admin";
		frame = new JFrame("Log In");
		frame.setSize(600, 400);
		frame.setLayout(new BorderLayout());

		// NORTH PANEL
		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(232, 189, 128));
		ImageIcon myPicture = new ImageIcon(getClass().getResource("gringotts1.jpg"));
		JLabel picLabel = new JLabel(myPicture);
		northPanel.add(picLabel);
		frame.add(northPanel, BorderLayout.NORTH);

		// CENTRE PANEL
		JPanel centrePanel = new JPanel();
		centrePanel.setBackground(new Color(232, 189, 128));
		frame.add(centrePanel, BorderLayout.CENTER);
		JPanel gridPanel = new JPanel(new GridLayout(2, 1));
		gridPanel.setBackground(new Color(232, 189, 128));
		JPanel panel1 = new JPanel(new GridLayout(2, 1));
		panel1.setBackground(new Color(232, 189, 128));
		JPanel panel2 = new JPanel(new GridLayout(2, 2));
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		panel2.setBorder(loweredbevel);
		panel2.setBackground(new Color(232, 189, 128));
		gridPanel.add(panel1);
		gridPanel.add(panel2);
		gridPanel.setPreferredSize(new Dimension(300, 100));
		centrePanel.add(gridPanel);

		// ADMIN BUTTON
		adminButton = new JRadioButton("Goblin");
		adminButton.setBackground(new Color(232, 189, 128));
		// adminButton.setSelected(true);
		adminButton.addActionListener(this);

		// USER BUTTON
		userButton = new JRadioButton("Wizard");
		userButton.setBackground(new Color(232, 189, 128));
		userButton.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
		group.add(adminButton);
		group.add(userButton);
		panel1.add(adminButton);
		panel1.add(userButton);
		panel1.setBorder(loweredbevel);

		JLabel userLabel = new JLabel(" Name      ");
		panel2.add(userLabel);

		userText = new JTextField();
		userText.setBackground(new Color(255, 255, 230));
		panel2.add(userText);

		JLabel passwordLabel = new JLabel(" Pass     ");
		panel2.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBackground(new Color(255, 255, 230));
		panel2.add(passwordText);

		// SOUTH PANEL
		JPanel southPanel = new JPanel();
		southPanel.setBackground(new Color(81, 52, 21));
		frame.add(southPanel, BorderLayout.SOUTH);
		loginButton = new JButton("enter");
		loginButton.setBackground(new Color(255, 255, 230));
		loginButton.addActionListener(this);
		southPanel.add(loginButton);

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public boolean checkAdmin(String username, String pass) {
		if (username.compareTo(admin) == 0)
			if (pass.compareTo(adminPass) == 0)
				return true;
		return false;
	}

	public Person login(String name, String pin) {
		Set<Person> persons = bank.getBankData().keySet();
		for (Person p : persons) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (adminButton.isSelected()) {
			if (e.getSource() == loginButton) {
				if (checkAdmin(userText.getText(), new String(passwordText.getPassword()))) {
					new AdminViewClients();
					this.dispose();
					repaint();
				} else {
					JOptionPane.showMessageDialog(null,
							"Stop, stranger, but take heed\nOf what awaits the sin of greed\nFor those who take, but do not earn,\nMust pay most dearly in their turn.\nSo if you seek beneath our floors\nA treasure that was never yours,\nThief, you have been warned, beware\nOf finding more than treasure there.");
				}

			}
		} else if (userButton.isSelected()) {
			if (e.getSource() == loginButton) {
				Person client = login(userText.getText(), new String(passwordText.getPassword()));
				if (client != null) {
					new ClientView(client);
					this.setVisible(false);
					repaint();

				} else {
					JOptionPane.showMessageDialog(null,
							"Please register first at our office on Diagon Alley street no 37,London,England");
					repaint();
				}
			}
		}
	}

}
