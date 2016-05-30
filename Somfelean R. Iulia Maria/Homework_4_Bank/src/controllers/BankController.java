package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;
import utilities.IllegalProcedureException;
import utilities.InsufficientMoney;
import view.BankView;

public class BankController {

	private BankView bankView;
	private Bank bank;

	public BankController(BankView bankView, Bank bank) {

		this.bank = bank;
		this.bankView = bankView;

		bankView.fillTablePersons(bank.getBankData());
		bankView.fillTableAccounts(bank.getBankData());

		this.bankView.addAddPersonButtonListener(new AddPersonButtonListener());
		this.bankView.addRemovePersonButtonListener(new RemovePersonButtonListener());
		this.bankView.addAddAccountButtonListener(new AddAccountButtonListener());
		this.bankView.addRemoveAccountButtonListener(new RemoveAccountButtonListener());
		this.bankView.addWithdrawButtonListener(new WithdrawButtonListener());
		this.bankView.addDepositButtonListener(new DepositButtonListener());
		this.bankView.addGenerateStatementButtonListener(new GenerateStatementButtonListener());
		this.bankView.addTablePersonsActionListener(new TablePersonsActionListener());
		this.bankView.addTablePersonsActionListener(new TableAccountsActionListener());

	}

	class AddPersonButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			Person person = new Person(bankView.getFirstName(), bankView.getLastName(), bankView.getSsid(),
					bankView.getPhoneNo());
			try {
				bank.addPerson(person);

			} catch (IllegalProcedureException e) {
				e.printStackTrace();
			}

			bank = bank.writeAccountsData();
			bankView.fillTablePersons(bank.getBankData());

		}

	}

	class RemovePersonButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (bankView.getPersonsTable().getSelectedRow() != -1) {

				Person person = new Person(
						bankView.getModelPersons().getValueAt(bankView.getPersonsTable().getSelectedRow(), 2)
								.toString(),
						bankView.getModelPersons().getValueAt(bankView.getPersonsTable().getSelectedRow(), 1)
								.toString(),
						bankView.getModelPersons().getValueAt(bankView.getPersonsTable().getSelectedRow(), 0)
								.toString(),
						bankView.getModelPersons().getValueAt(bankView.getPersonsTable().getSelectedRow(), 3)
								.toString());

				try {
					bank.removePerson(bank.getPersonBySsid(bankView.getModelPersons()
							.getValueAt(bankView.getPersonsTable().getSelectedRow(), 0).toString()));
					bank = bank.writeAccountsData();
					System.out.println("REMOVE" + bank.toString());
					bankView.getModelPersons().removeRow(bankView.getPersonsTable().getSelectedRow());

				} catch (IllegalProcedureException e) {
					e.printStackTrace();
				}

				bankView.fillTablePersons(bank.getBankData());

			}

		}

	}

	class AddAccountButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (bankView.getPersonsTable().getSelectedRow() != -1) {
				if (bankView.getSavingRadioButton().isSelected()) {

					Person person = bank.getPersonBySsid(bankView.getModelPersons()
							.getValueAt(bankView.getPersonsTable().getSelectedRow(), 0).toString());
					Account account = new SavingAccount(bankView.getBalance());

					try {
						bank.addAccount(person, account);

					} catch (IllegalProcedureException e) {
						e.printStackTrace();
					}
				} else if (bankView.getSpendingRadioButton().isSelected()) {

					Person person = bank.getPersonBySsid(bankView.getModelPersons()
							.getValueAt(bankView.getPersonsTable().getSelectedRow(), 0).toString());
					Account account = new SpendingAccount(bankView.getBalance());

					try {
						bank.addAccount(person, account);

					} catch (IllegalProcedureException e) {
						e.printStackTrace();
					}
				}

				bank = bank.writeAccountsData();
				bankView.fillTableAccounts(bank.getBankData());
			}

		}

	}

	class RemoveAccountButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			if (bankView.getAccountsTable().getSelectedRow() != -1) {
				
				String accNo = bankView.getModelAccounts().getValueAt(bankView.getAccountsTable().getSelectedRow(), 0)
						.toString();

				Account account = bank.getAccountByNr(accNo);
				Person person = bank.getPersonByAccount(accNo);
				try {
					bank.removeAccount(person, account);
				} catch (IllegalProcedureException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bank = bank.writeAccountsData();
				bankView.fillTableAccounts(bank.getBankData());
			}
		}

	}

	class WithdrawButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (bankView.getAccountsTable().getSelectedRow() != -1) {

				String accNo = bankView.getModelAccounts().getValueAt(bankView.getAccountsTable().getSelectedRow(), 0)
						.toString();

				Account account = bank.getAccountByNr(accNo);
				try {
					account.withdraw(bankView.getAmount());
				} catch (InsufficientMoney e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bank.readAccountsData();

				bank = bank.writeAccountsData();
				bankView.fillTableAccounts(bank.getBankData());
			}

		}

	}

	class DepositButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (bankView.getAccountsTable().getSelectedRow() != -1) {

				String accNo = bankView.getModelAccounts().getValueAt(bankView.getAccountsTable().getSelectedRow(), 0)
						.toString();

				Account account = bank.getAccountByNr(accNo);
				account.deposit(bankView.getAmount());
				bank.readAccountsData();

				bank = bank.writeAccountsData();
				bankView.fillTableAccounts(bank.getBankData());
			}
		}

	}

	class GenerateStatementButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			bank.generateStatement();
		}

	}

	class TablePersonsActionListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class TableAccountsActionListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
