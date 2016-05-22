package IO;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.Bank;
import models.Person;

public class LogInFrame extends GenericFrame implements ActionListener {

	public static final String GUEST = "Guest";
	public static final String ADMIN = "Admin";

	private JComboBox<String> jComboBox;
	private JLabel jLabelWelcome;
	public String selectedUser = ADMIN;

	public LogInFrame() {
		super();
		logInOutButton = new JButton("LOGIN");
		logInOutButton.setSize(new Dimension(30, 10));
		jLabelWelcome = new JLabel("Bank MANAGEMENT App ", SwingConstants.CENTER);

		String users[] = { ADMIN, GUEST };
		jComboBox = new JComboBox<String>(users);
		jComboBox.setBounds(50, 50, 90, 20);
		jComboBox.addActionListener(this);

		frame.add(jLabelWelcome);
		frame.add(jComboBox);
		frame.add(logInOutButton);
		frame.setLayout(new GridLayout(3, 1));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		@SuppressWarnings("rawtypes")
		JComboBox cb = (JComboBox) event.getSource();
		selectedUser = (String) cb.getSelectedItem();
		System.out.println(selectedUser);
	}

	// *** 1 ***/
	public void errorMessageLogIn() {
		JPanel myPanel = new JPanel();
		JLabel label = new JLabel("ERROR: invalid user");
		myPanel.add(label);

		JOptionPane.showConfirmDialog(null, myPanel, "LogIn:", JOptionPane.OK_CANCEL_OPTION);
	}

	// *** 2 ***/
	public Person queryLogInCustomer(Bank bank) {
		JTextField xField = new JTextField(10); // id
		JTextField yField = new JTextField(15); // name

		JPanel myPanel = new JPanel(new GridLayout(2, 1));
		myPanel.add(new JLabel("ID:"));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("NAME:"));
		myPanel.add(yField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter your log-in details",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String id, name;
			try {
				id = xField.getText();
			} catch (NumberFormatException ex) {
				id = new Random().toString(); // invalid customer
			}
			try {
				name = yField.getText();
			} catch (NumberFormatException ex) {
				name = new Random().toString(); // invalid customer
			}

			Person auxP = new Person(id, name);
			if (bank.bankMap.containsKey(auxP))
				return auxP;
			return null;
		}
		return null;
	}
}