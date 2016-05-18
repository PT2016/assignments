package Control;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BankEntity.AllDataTable;
import BankEntity.Bank;
import BankEntity.SpecificClientTable;
import GraphicUserInterface.OutputFrame;

public class Actions {

	private Bank bank;

	public Actions() {
		bank = new Bank();
	}

	public void addAClient() {
		JTextField nameField = new JTextField(10);
		JTextField passwordField = new JPasswordField(10);
		JTextField accountTypeField = new JTextField(10);

		Object[] message = { "Client name", nameField, "Password", passwordField, "Account type", accountTypeField };

		int confirmation = JOptionPane.showConfirmDialog(null, message, "Add a new Client",
				JOptionPane.OK_CANCEL_OPTION);

		if (confirmation == JOptionPane.OK_OPTION) {
			boolean validInput = true;
			if (nameField.getText().isEmpty())
				validInput = false;
			if (passwordField.getText().isEmpty())
				validInput = false;
			if (accountTypeField.getText().isEmpty())
				validInput = false;
			if (validInput) {
				bank.addClient(nameField.getText(), passwordField.getText(), accountTypeField.getText());
				JOptionPane.showMessageDialog(null, "Client successfully added !", "Success !",
						JOptionPane.INFORMATION_MESSAGE);

				AllDataTable adt = new AllDataTable();
				new OutputFrame(adt.getTable(), "All Clients Data");

			} else {
				JOptionPane.showMessageDialog(null, "Invalid input", "ERROR: invalid input", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void removeAClient() {
		String clientName = JOptionPane.showInputDialog("Name of the client");
		if (!clientName.equals(null)) {
			bank.removeClient(clientName);

			AllDataTable adt = new AllDataTable();
			new OutputFrame(adt.getTable(), "All Clients Data");
		} else {
			JOptionPane.showMessageDialog(null, "Pleae snter a name", "ERROR: input", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void viewAllClients() {
		AllDataTable adt = new AllDataTable();
		new OutputFrame(adt.getTable(), "All Clients Data");
	}

	public void viewOneClient() {

		String name = JOptionPane.showInputDialog("Client's name");
		if (!name.equals(null)) {
			SpecificClientTable sct = new SpecificClientTable(name);
			new OutputFrame(sct.getTable(), "All Clients Data");
		}
	}

	public void addAnAccount() {

		JTextField nameField = new JTextField(10);
		JTextField typeField = new JTextField(10);

		Object[] message = { "Client's name", nameField, "Account type", typeField };

		int confirmation = JOptionPane.showConfirmDialog(null, message, "Add a new account",
				JOptionPane.OK_CANCEL_OPTION);
		boolean validInput = true;
		if (confirmation == JOptionPane.OK_OPTION) {
			if (nameField.getText().equals(null))
				validInput = false;
			if (typeField.getText().equals(null))
				validInput = false;
			if (validInput) {
				bank.addAccount(nameField.getText(), typeField.getText());

				JOptionPane.showMessageDialog(null, "Account successfully added !", "Success !",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void removeAnAccount() {
		JTextField nameField = new JTextField(10);
		JTextField idField = new JTextField(10);

		Object[] message = { "Client's name", nameField, "Account ID", idField };

		int confirmation = JOptionPane.showConfirmDialog(null, message, "Remove an account",
				JOptionPane.OK_CANCEL_OPTION);
		boolean validInput = true;
		if (confirmation == JOptionPane.OK_OPTION) {
			if (nameField.getText().equals(null))
				validInput = false;
			if (idField.getText().equals(null))
				validInput = false;
			if (validInput) {
				bank.removeAccount(nameField.getText(), idField.getText());

				JOptionPane.showMessageDialog(null, "Account successfully removed !", "Success !",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void addDatMoney(String client) {

		JTextField sumField = new JTextField(10);
		JTextField accountField = new JTextField(10);

		Object[] message = { "Sum to be added", sumField, "Account ID", accountField };

		int confirmation = JOptionPane.showConfirmDialog(null, message, "Add money", JOptionPane.OK_CANCEL_OPTION);

		if (confirmation == JOptionPane.OK_OPTION) {
			if ((!sumField.getText().equals(null)) && (!accountField.getText().equals(null))) {

				double money = 0.0;

				try {
					money = Double.parseDouble(sumField.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid sum !", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				bank.addCash(client, accountField.getText(), money);
			}
		}
	}

	public void takeDatMoney(String client) {

		JTextField sumField = new JTextField(10);
		JTextField accountField = new JTextField(10);

		Object[] message = { "Sum to be withdrawn", sumField, "Account ID", accountField };

		int confirmation = JOptionPane.showConfirmDialog(null, message, "Withdraw money", JOptionPane.OK_CANCEL_OPTION);

		if (confirmation == JOptionPane.OK_OPTION) {
			if ((!sumField.getText().equals(null)) && (!accountField.getText().equals(null))) {

				double money = 0.0;

				try {
					money = Double.parseDouble(sumField.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid sum !", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				bank.withdrawCash(client, accountField.getText(), money);
			}
		}
	}

	public void viewClientDetails(String client) {

		bank.generateReport(client);
	}
}