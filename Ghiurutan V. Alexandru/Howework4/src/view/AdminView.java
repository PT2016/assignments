package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Person;
import model.SavingAccount;
import model.SpendingAccount;
import model.Bank;

public class AdminView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3774434228678895653L;
	private JButton addPerson, removePerson, addAccount, displaySavingAccounts, displaySpendingAccounts, logOut;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JPanel operations, fields, south;
	private JTextField firstNameField, lastNameField, usernameField, passwordField, ageField, nrOfAccountsField;
	private Bank bank;
	private Object[][] rows;
	private String[] columns;
	private String firstName, lastName, userName, password;
	private int age, nrOfAccounts;
	private static String ERROR = "Error";
	private JTable jT;

	public AdminView() {
		this.setTitle("Admin view");
		scrollPane = new JScrollPane();
		bank = Bank.getInstance();
		jT = new JTable();
		operations = new JPanel();
		operations.setLayout(new BoxLayout(operations, BoxLayout.X_AXIS));
		addPerson = new JButton("Add person");
		addPerson.addActionListener(this);

		removePerson = new JButton("Remove person");
		removePerson.addActionListener(this);

		addAccount = new JButton("Add account");
		addAccount.addActionListener(this);

		displaySavingAccounts = new JButton("Display saving accounts");
		displaySavingAccounts.addActionListener(this);

		displaySpendingAccounts = new JButton("Display spending accounts");
		displaySpendingAccounts.addActionListener(this);

		logOut = new JButton("Log out");
		logOut.addActionListener(this);

		firstNameField = new JTextField("First name", 10);
		lastNameField = new JTextField("Last name", 10);
		usernameField = new JTextField("Username", 10);
		passwordField = new JTextField("Password", 10);
		ageField = new JTextField("Age", 10);
		nrOfAccountsField = new JTextField("Nr of Accounts", 10);

		fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.X_AXIS));
		fields.add(firstNameField);
		fields.add(lastNameField);
		fields.add(usernameField);
		fields.add(passwordField);
		fields.add(ageField);
		fields.add(nrOfAccountsField);

		operations.add(addPerson);
		operations.add(removePerson);
		operations.add(addAccount);
		operations.add(displaySavingAccounts);
		operations.add(displaySpendingAccounts);
		operations.add(logOut);

		south = new JPanel();
		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		south.add(fields);
		south.add(operations);
		this.add(south, BorderLayout.SOUTH);
		initializeNorthSection();
		this.setSize(900, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeNorthSection() {
		table = createGeneralTable(bank.getAllPersons());
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
	}

	private JTable createGeneralTable(Collection<Object> data) {
		if (data == null) {
			return null;
		}
		int listLength;
		Object[] list = data.toArray();
		if (list.length != 0) {
			Field[] fields = list[0].getClass().getDeclaredFields();
			Field.setAccessible(fields, true);
			rows = new Object[list.length][fields.length - 1];
			Object[] row = new Object[fields.length - 1];
			columns = new String[fields.length - 1];
			int i = 0;
			for (Field field : fields) {
				if (i == columns.length) {
					break;
				}
				columns[i++] = field.getName();
			}
			tableModel = new DefaultTableModel(rows, columns);
			tableModel.setRowCount(0);
			jT.setModel(tableModel);
			jT.setEnabled(false);
			listLength = list.length;
			for (i = 0; i < listLength; i++) {
				int j = 0;
				for (Field field : fields) {
					if (j == row.length) {
						break;
					}
					try {
						Object fieldValue = field.get(list[i]);
						row[j++] = fieldValue;
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				tableModel.addRow(row);
			}
		}
		return jT;
	}

	private void updateTable() {
		table = createGeneralTable(bank.getAllPersons());
	}

	private boolean checkFields() {
		if (firstNameField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter the first name of a person.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (lastNameField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter the last name of a person.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (usernameField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter the username of a person.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (passwordField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter the password of a person.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (ageField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter the age of a person.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (nrOfAccountsField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter the nr of accounts of a person.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	private void addAccount(Person person) {
		JFrame frame = new JFrame();
		frame.setTitle("Add account");
		frame.setSize(500, 500);
		JPanel panel = new JPanel();
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JRadioButton saving, spending;
		ButtonGroup buttonGroup = new ButtonGroup();
		JButton create = new JButton("Create Account");

		saving = new JRadioButton("Saving Account");
		saving.setBounds(100, 50, 100, 30);
		spending = new JRadioButton("Spending Account");
		spending.setBounds(100, 100, 100, 30);
		buttonGroup.add(saving);
		buttonGroup.add(spending);
		dataPanel.add(saving);
		dataPanel.add(spending);

		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == create) {
					if (saving.isSelected()) {
						double money = Double.valueOf(JOptionPane.showInputDialog(frame,
								"Give the money of the account.", "Money", JOptionPane.INFORMATION_MESSAGE));
						double gain = Double.valueOf(JOptionPane.showInputDialog(frame, "Give the gain of the account.",
								"Gain", JOptionPane.INFORMATION_MESSAGE));
						int year = Integer.valueOf(
								JOptionPane.showInputDialog(frame, "Give the year when the gain should be applied.",
										"Year", JOptionPane.INFORMATION_MESSAGE));
						int month = Integer.valueOf(
								JOptionPane.showInputDialog(frame, "Give the month when the gain should be applied.",
										"Month", JOptionPane.INFORMATION_MESSAGE));
						int day = Integer.valueOf(
								JOptionPane.showInputDialog(frame, "Give the day when the gain should be applied.",
										"Day", JOptionPane.INFORMATION_MESSAGE));
						bank.addHolderAssociatedAccount(person, new SavingAccount(money, gain, year, month, day));

					} else {
						double money = Double.valueOf(JOptionPane.showInputDialog(frame,
								"Give the money of the account.", "Money", JOptionPane.INFORMATION_MESSAGE));
						bank.addHolderAssociatedAccount(person, new SpendingAccount(money));
					}
					updateTable();
				}
			}
		});
		dataPanel.add(create);
		frame.add(panel);
		frame.add(dataPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addPerson) {
			if (checkFields()) {
				firstName = firstNameField.getText();
				lastName = lastNameField.getText();
				userName = usernameField.getText();
				password = passwordField.getText();
				age = Integer.valueOf(ageField.getText());
				nrOfAccounts = Integer.valueOf(nrOfAccountsField.getText());
				bank.addPerson(new Person(firstName, lastName, userName, password, age, nrOfAccounts));
				updateTable();

			}
		} else if (e.getSource() == removePerson) {
			if (checkFields()) {
				firstName = firstNameField.getText();
				lastName = lastNameField.getText();
				userName = usernameField.getText();
				password = passwordField.getText();
				age = Integer.valueOf(ageField.getText());
				nrOfAccounts = Integer.valueOf(nrOfAccountsField.getText());
				bank.removePerson(new Person(firstName, lastName, userName, password, age, nrOfAccounts));
				updateTable();
			}
		} else if (e.getSource() == addAccount) {
			if (checkFields()) {
				firstName = firstNameField.getText();
				lastName = lastNameField.getText();
				userName = usernameField.getText();
				password = passwordField.getText();
				age = Integer.valueOf(ageField.getText());
				nrOfAccounts = Integer.valueOf(nrOfAccountsField.getText());
				Person person = new Person(firstName, lastName, userName, password, age, nrOfAccounts);
				if (bank.containsPerson(person)) {
					addAccount(person);
					updateTable();
				}
			}
		} else if (e.getSource() == displaySavingAccounts) {
			if (checkFields()) {
				firstName = firstNameField.getText();
				lastName = lastNameField.getText();
				userName = usernameField.getText();
				password = passwordField.getText();
				age = Integer.valueOf(ageField.getText());
				nrOfAccounts = Integer.valueOf(nrOfAccountsField.getText());
				Person person = new Person(firstName, lastName, userName, password, age, nrOfAccounts);
				new SavingAccountsView(person, bank.getPersonSavingAccounts(person));
				updateTable();
			}
		} else if (e.getSource() == displaySpendingAccounts) {
			if (checkFields()) {
				firstName = firstNameField.getText();
				lastName = lastNameField.getText();
				userName = usernameField.getText();
				password = passwordField.getText();
				age = Integer.valueOf(ageField.getText());
				nrOfAccounts = Integer.valueOf(nrOfAccountsField.getText());
				Person person = new Person(firstName, lastName, userName, password, age, nrOfAccounts);
				new SpendingAccountsView(person, bank.getPersonSpendingAccounts(person));
				updateTable();
			}
		} else if (e.getSource() == logOut) {
			this.dispose();
			new Gui();
		}
	}
}
