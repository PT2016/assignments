package BankEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import AccountEntities.Account;
import AccountEntities.SavingAccount;
import AccountEntities.SpendingAccount;
import GraphicUserInterface.OutputFrame;
import UserEntities.Person;
import UserEntities.User;
import Utilities.Helper;

public class Bank implements BankProc {

	private Helper helper;
	private Map<Person, List<Account>> bankData;

	public Bank() {
		helper = new Helper();
		updateBankData();
	}

	public void updateBankData() {
		bankData = new HashMap<Person, List<Account>>();
		helper.readFromSerFile(bankData, "BankData.ser");
	}

	public Map<Person, List<Account>> getBankData() {
		return bankData;
	}

	public boolean isWellFormed() {

		boolean allGood = true;

		for (Map.Entry<Person, List<Account>> entry : bankData.entrySet()) {
			if ((!entry.getKey().equals(null)) && (entry.getValue().equals(null))) {
				allGood = false;
			}
		}
		return allGood;
	}

	@Override
	public void addAccount(String name, String accountType) {
		assert ((isWellFormed()) && (name != null) && (accountType != null)) : "Error at entering addAccount";

		for (Map.Entry<Person, List<Account>> entry : bankData.entrySet()) {
			if (entry.getKey().getName().equals(name)) {
				Account account = null;
				if (accountType.equals("saving")) {
					account = new SavingAccount(helper.generateAccountID(name), name);
					entry.getValue().add(account);
					helper.updateSerFile(bankData, "BankData.ser");
				} else if (accountType.equals("spending")) {
					account = new SpendingAccount(helper.generateAccountID(name), name);
					entry.getValue().add(account);
					helper.updateSerFile(bankData, "BankData.ser");
				}
			}
		}

		assert (isWellFormed()) : "Error at exiting addAccount";
	}

	@Override
	public void removeAccount(String name, String accountID) {
		assert ((isWellFormed()) && (name != null) && (accountID != null)) : "Error at entering removeAccount";
		
		for (Map.Entry<Person, List<Account>> entry : bankData.entrySet()) {
			if (entry.getKey().getName().equals(name)) {
				for (Account account : entry.getValue()) {
					if (account.getID().equals(accountID)) {
						entry.getValue().remove(account);
						helper.updateSerFile(bankData, "BankData.ser");
						break;
					}
				}
				break;
			}
		}
		assert (isWellFormed()) : "Error at exiting removeAccount";
	}

	@Override
	public void addClient(String name, String password, String accountType) {
		assert ((isWellFormed()) && (name != null) && (password != null) && (accountType != null)) : 
								"Error at entering addClient";
		
		Person client = new User(name, password);
		Account account = null;
		if (accountType.equals("saving"))
			account = new SavingAccount(helper.generateAccountID(name), name);
		else if (accountType.equals("spending"))
			account = new SpendingAccount(helper.generateAccountID(name), name);

		if (!account.equals(null)) {
			List<Account> associatedAccounts = new ArrayList<Account>();
			associatedAccounts.add(account);
			bankData.put(client, associatedAccounts);

			helper.updateSerFile(bankData, "BankData.ser");
		}
		
		assert(isWellFormed()):"Error at exiting addClient";
	}

	@Override
	public void removeClient(String name) {
		assert ((isWellFormed()) && (name != null)) : "Error at entering removeClient";
		
		for (Map.Entry<Person, List<Account>> entry : bankData.entrySet()) {
			if (entry.getKey().getName().equals(name)) {
				bankData.remove(entry.getKey());
				helper.updateSerFile(bankData, "BankData.ser");
				break;
			}
		}
		assert(isWellFormed()):"Error at exiting removeClient";
	}

	@Override
	public void addCash(String clientName, String accountID, double sum) {
		assert((isWellFormed()) && (clientName != null) && (accountID != null) && (sum >= 0.0)):
				"Error at entering addCash";
		
		boolean accountFound = false;

		for (Map.Entry<Person, List<Account>> entry : bankData.entrySet()) {
			if (entry.getKey().getName().equals(clientName)) {
				for (Account account : entry.getValue()) {
					if (account.getID().equals(accountID)) {
						accountFound = true;
						account.addMoney(sum);
						helper.updateSerFile(bankData, "BankData.ser");
						JOptionPane.showMessageDialog(null, "Successfull operation !", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}

				if (accountFound)
					break;
			}
		}

		if (!accountFound) {
			JOptionPane.showMessageDialog(null, "Account not found !", "Error", JOptionPane.ERROR_MESSAGE);
		}
		assert(isWellFormed()):"Error at exiting addCash";
	}

	@Override
	public void withdrawCash(String clientName, String accountID, double sum) {
		assert((isWellFormed()) && (clientName != null) && (accountID != null) && (sum >= 0.0)):
			"Error at entering withdrawCash";
		
		double newSum = 0 - sum;

		addCash(clientName, accountID, newSum);

		assert(isWellFormed()):"Error at exiting withdrawCash";
	}

	@Override
	public void generateReport(String client) {
		assert((isWellFormed()) && (client != null)):"Error at entering generateReport";
		
		SpecificClientTable sct = new SpecificClientTable(client);
		new OutputFrame(sct.getTable(), "All Clients Data");
		
		assert(isWellFormed()):"Error at exiting generateReport";
	}

}
