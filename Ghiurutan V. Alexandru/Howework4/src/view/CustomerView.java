package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

@SuppressWarnings("serial")
public class CustomerView extends JFrame implements ActionListener {
	private JButton takeMoney, displaySavingAccounts, displaySpendingAccounts, logOut, viewBankAccountDetails;
	private Bank bank;
	private JPanel operations;
	private Person person;
	private String[] selectionValues = { "Spending account", "Saving account" };
	private String initialSelection = "Spending account";

	public CustomerView(Person person) {
		this.setTitle("Customer view");
		this.setSize(900, 400);
		bank = Bank.getInstance();
		this.person = person;
		operations = new JPanel();
		operations.setLayout(new BoxLayout(operations, BoxLayout.X_AXIS));
		takeMoney = new JButton("Take money");
		takeMoney.addActionListener(this);
		displaySavingAccounts = new JButton("Display saving accounts");
		displaySavingAccounts.addActionListener(this);
		displaySpendingAccounts = new JButton("Display spending accounts");
		displaySpendingAccounts.addActionListener(this);
		viewBankAccountDetails = new JButton("View Bank account details");
		viewBankAccountDetails.addActionListener(this);
		logOut = new JButton("Log out");
		logOut.addActionListener(this);
		operations.add(takeMoney);
		operations.add(displaySpendingAccounts);
		operations.add(displaySavingAccounts);
		operations.add(viewBankAccountDetails);
		operations.add(logOut);
		this.add(operations, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == takeMoney) {
			JDialog.setDefaultLookAndFeelDecorated(true);
			String result = String.valueOf(
					JOptionPane.showInputDialog(this, "From which type of account do you want to extract money?",
							"Quiz", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection));
			if (result.equals(selectionValues[0])) {
				String accountName = JOptionPane.showInputDialog(this, "Give the name of the Spending account:");
				Double sumToExtract = Double
						.valueOf(JOptionPane.showInputDialog(this, "Give the sum that you want to extract:"));
				if (sumToExtract > SpendingAccount.getMaximumSumToWithdraw()) {
					JOptionPane.showMessageDialog(this,
							"The sum is greater than the maximum sum that you are allowed to withdraw.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					bank.withdrawMoneyFromSpendingAccount(person, accountName, sumToExtract);
				}
			} else {
				String accountName = JOptionPane.showInputDialog(this, "Give the name of the Saving account:");
				Double sumToExtract = Double
						.valueOf(JOptionPane.showInputDialog(this, "Give the sum that you want to extract:"));
				if (sumToExtract < SavingAccount.getMinimumSumToWithdraw()) {
					JOptionPane.showMessageDialog(this,
							"The sum is smaller than the minimum sum that you are allowed to withdraw.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					bank.withdrawMoneyFromSavingAccount(person, accountName, sumToExtract);
				}
			}
		} else if (e.getSource() == displaySavingAccounts) {
			new SavingAccountsView(person, bank.getPersonSavingAccounts(person));
		} else if (e.getSource() == displaySpendingAccounts) {
			new SpendingAccountsView(person, bank.getPersonSpendingAccounts(person));
		} else if (e.getSource() == viewBankAccountDetails) {
			String firstName = person.getFirstName();
			String lastName = person.getLastName();
			String userName = person.getUserName();
			String password = person.getPassword();
			int age = Integer.valueOf(person.getAge());
			int nrOfAccounts = Integer.valueOf(person.getNrOfAccounts());
			JOptionPane.showMessageDialog(this,
					"First name: " + firstName + ", last name: " + lastName + ", userName: " + userName + ", password: "
							+ password + ", age: " + age + ", numberOfAccounts: " + nrOfAccounts,
					"Bank account data", JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == logOut) {
			this.dispose();
			new Gui();
		}

	}
}
