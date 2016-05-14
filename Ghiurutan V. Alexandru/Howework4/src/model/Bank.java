package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import controller.GenerateReport;
import controller.InputOutput;

public class Bank implements Observer, BankProc {
	private Map<Person, ArrayList<Account>> bank;
	private Iterator<Person> iterator;
	private InputOutput inOut = new InputOutput();
	private static Bank instance;
	private GenerateReport generateReport;

	private Bank() {
		bank = inOut.deserializeBank();
		generateReport = new GenerateReport();
	}

	public static Bank getInstance() {
		if (instance == null) {
			instance = new Bank();
		}
		return instance;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(((Account) o).toString() + " was modified.");
	}

	@Override
	public void addPerson(Person person) {
		assert (!bank.containsKey(person)) : "Person already in the bank.";
		assert (person != null) : "Invalid person.";
		assert (isWellFormed()) : "Invariant modified.";
		int size = bank.size();
		bank.put(person, null);
		generateReport.printReport(person, "Wellcome to the bank.");
		inOut.serializeBank();
		assert (bank.size() == size + 1) : "Size of the bank wasn't modified.";
		assert (isWellFormed()) : "Invariant mofified.";

	}

	@Override
	public void removePerson(Person person) {
		assert bank.containsKey(person) : "Person not in the bank";
		assert (isWellFormed()) : "Invariant mofified.";
		int size = bank.size();
		bank.remove(person);
		generateReport.printReport(person, "Your account to the bank was deleted.");
		inOut.serializeBank();
		assert (bank.size() == size - 1) : "Size of the bank didn't change.";
		assert (isWellFormed()) : "Invariant mofified.";
	}

	@Override
	public void addHolderAssociatedAccount(Person person, Account account) {
		assert (bank.containsKey(person)) : "Person not in the bank.";
		assert (account.getMoney() >= 0) : "Not a valid sum of money in the account.";
		assert (isWellFormed()) : "Invariant mofified.";
		int size;
		if (bank.get(person) == null) {
			size = 0;
			ArrayList<Account> accounts = new ArrayList<>();
			accounts.add(account);
			bank.remove(person);
			person.setNrOfAccounts(person.getNrOfAccounts() + 1);
			bank.put(person, accounts);
			generateReport.printReport(person, "The account " + account + " was added to the bank.");
		} else {
			size = bank.get(person).size();
			ArrayList<Account> accounts = new ArrayList<>();
			accounts.addAll(bank.get(person));
			accounts.add(account);
			bank.remove(person);
			person.setNrOfAccounts(person.getNrOfAccounts() + 1);
			bank.put(person, accounts);
			generateReport.printReport(person, "The account " + account + " was added to the bank.");
		}
		inOut.serializeBank();
		assert (bank.get(person).size() == size + 1) : "Size of the bank didn't changed.";
		assert (isWellFormed()) : "Invariant mofified.";
	}

	@Override
	public void removeHolderAssociatedAccount(Person person, Account account) {
		assert (bank.containsKey(person)) : "Person not in the bank.";
		assert (bank.get(person).contains(account)) : "Person doesn't have such an account.";
		assert (isWellFormed()) : "Invariant mofified.";
		if (bank.containsKey(person)) {
			int size = bank.get(person).size();
			if (person.getNrOfAccounts() > 0) {
				ArrayList<Account> accounts = bank.get(person);
				if (accounts.contains(account)) {
					bank.remove(person);
					person.setNrOfAccounts(person.getNrOfAccounts() - 1);
					accounts.remove(account);
					bank.put(person, accounts);
					generateReport(person, "The account: " + account + " was deleted.");
				}
			}
			inOut.serializeBank();
			assert (bank.get(person).size() == size - 1) : "Size of the bank didn't modified.";
			assert (isWellFormed()) : "Invariant mofified.";
		}
	}

	@Override
	public void generateReport(Person person, String message) {
		generateReport.printReport(person, message);
	}

	public Person getCustomer(String userName, String password) {
		iterator = bank.keySet().iterator();
		while (iterator.hasNext()) {
			Person person = iterator.next();
			if (person.getUserName().equals(userName) && person.getPassword().equals(password)) {
				return person;
			}
		}
		return null;
	}

	public ArrayList<Object> getPersonSavingAccounts(Person person) {
		if (bank.containsKey(person)) {
			ArrayList<Object> acc = new ArrayList<>();
			ArrayList<Account> accounts = bank.get(person);
			if (accounts == null) {
				return null;
			}
			for (Account account : accounts) {
				if (account instanceof SavingAccount)
					acc.add(account);
			}
			return acc;
		}
		return null;
	}

	public ArrayList<Object> getPersonSpendingAccounts(Person person) {
		if (bank.containsKey(person)) {
			ArrayList<Object> acc = new ArrayList<>();
			ArrayList<Account> accounts = bank.get(person);
			if (accounts == null) {
				return null;
			}
			for (Account account : accounts) {
				if (account instanceof SpendingAccount)
					acc.add(account);
			}
			return acc;
		}
		return null;
	}

	public int getTotalNrOfPersons() {
		return bank.size();
	}

	public HashMap<Person, ArrayList<Account>> getBankContent() {
		return (HashMap<Person, ArrayList<Account>>) bank;
	}

	public boolean withdrawMoneyFromSpendingAccount(Person person, String accountName, double sumToExtract) {
		ArrayList<Account> accounts = bank.get(person);
		Account currentAccount;
		Iterator<Account> iterator = accounts.iterator();
		while (iterator.hasNext()) {
			currentAccount = iterator.next();
			if (currentAccount.getName().equals(accountName) && currentAccount instanceof SpendingAccount) {
				if (sumToExtract > currentAccount.getMoney()) {
					return false;
				} else {
					currentAccount.setMoney(currentAccount.getMoney() - sumToExtract);
					inOut.serializeBank();
					return true;
				}
			}
		}
		return false;
	}

	public boolean withdrawMoneyFromSavingAccount(Person person, String accountName, double sumToExtract) {
		ArrayList<Account> accounts = bank.get(person);
		Account currentAccount;
		Iterator<Account> iterator = accounts.iterator();
		while (iterator.hasNext()) {
			currentAccount = iterator.next();
			if (currentAccount.getName().equals(accountName) && currentAccount instanceof SavingAccount) {
				if (sumToExtract > currentAccount.getMoney()) {
					return false;
				} else {
					currentAccount.setMoney(currentAccount.getMoney() - sumToExtract);
					inOut.serializeBank();
					return true;
				}
			}
		}
		return false;
	}

	public boolean containsPerson(Person person) {
		return bank.containsKey(person);
	}

	public ArrayList<Object> getAllPersons() {
		Set<Person> persons = bank.keySet();
		ArrayList<Object> pers = new ArrayList<>();
		for (Person person : persons) {
			pers.add(person);
		}
		return pers;
	}

	public Account getAccount(Person person, String name, double money) {
		ArrayList<Account> accounts = bank.get(person);
		for (Account account : accounts) {
			int result = Double.compare(account.getMoney(), money);
			if (account.getName().equals(name) && result == 0) {
				return account;
			}
		}
		return null;
	}

	public boolean isWellFormed() {
		Set<Person> persons = bank.keySet();
		Iterator<Person> it = persons.iterator();
		while (it.hasNext()) {
			Person person = it.next();
			if (person.getFirstName().equals("") || person.getLastName().equals("")) {
				return false;
			}
		}
		return true;
	}
}
