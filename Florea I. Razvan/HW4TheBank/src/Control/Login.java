package Control;

import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import AccountEntities.Account;
import BankEntity.Bank;
import UserEntities.Person;

public class Login {

	private JRadioButton adminButton;
	private JRadioButton userButton;
	private ButtonGroup group;

	public String askForUserType() {
		adminButton = new JRadioButton("Administrator");
		userButton = new JRadioButton("Regular User");
		group = new ButtonGroup();
		group.add(adminButton);
		group.add(userButton);

		Object[] message = { adminButton, userButton };

		int confirmation = JOptionPane.showConfirmDialog(null, message, "Choose Type", JOptionPane.OK_CANCEL_OPTION);

		String option = "";

		if (confirmation == JOptionPane.OK_OPTION) {
			option = checkOption();
		}
		return option;
	}

	private String checkOption() {

		String option = "";

		if (adminButton.isSelected()) {
			option = "administrator";
		} else if (userButton.isSelected()) {
			option = "user";
		} else {
			JOptionPane.showMessageDialog(null, "Please select a type !", "ERROR: selection",
					JOptionPane.ERROR_MESSAGE);
			askForUserType();
		}
		return option;
	}

	public String authenticateClient() {

		JTextField nameField = new JTextField(10);
		JTextField passwordField = new JPasswordField(10);

		String theClient = null;

		Object[] message = { "Client name", nameField, "Client password", passwordField };

		int confirmation = JOptionPane.showConfirmDialog(null, message, "Authenticate Client",
				JOptionPane.OK_CANCEL_OPTION);

		if (confirmation == JOptionPane.OK_OPTION) {
			if ((!nameField.getText().equals(null)) && (!passwordField.getText().equals(null))) {
				Bank bank = new Bank();
				bank.updateBankData();
				boolean clientFound = false;

				for (Map.Entry<Person, List<Account>> entry : bank.getBankData().entrySet()) {
					if (entry.getKey().getName().equals(nameField.getText())) {
						if (entry.getKey().getPassword().equals(passwordField.getText())) {
							clientFound = true;
							theClient = nameField.getText();
							break;
						} else {
							JOptionPane.showMessageDialog(null, "Incorrect password !", "Error: password",
									JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}
					}
				}

				if (!clientFound) {
					JOptionPane.showMessageDialog(null, "No client found. Input data might be wrong.",
							"ERROR: authenticate", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		}

		return theClient;
	}
}
