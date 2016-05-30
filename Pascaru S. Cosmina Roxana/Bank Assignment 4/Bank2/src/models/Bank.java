package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Bank implements BankProc, Serializable, Observer {

	Random randomGenerator = new Random();

	private ArrayList<Person> persons;
	private ArrayList<Account> accounts;
	private Hashtable<Person, ArrayList<Account>> hashtable;
	private String observerReport;

	public Bank() {
		persons = new ArrayList<Person>();
		accounts = new ArrayList<Account>();
		hashtable = new Hashtable<Person, ArrayList<Account>>();
	}

	@Override
	public void addPerson(Person p) {
		assert isWellFormed();
		assert p != null;
		int size = persons.size();
		persons.add(p);
		hashtable.put(p, new ArrayList<Account>());
		serialize();
		assert persons.size() == persons.size() + 1;
		assert isWellFormed();
	}

	@Override
	public void removePerson(Person p) {
		assert isWellFormed();
		assert p != null;
		assert hashtable.containsKey(p);
		int size = hashtable.size();

		persons.remove(p);
		hashtable.remove(p, hashtable.get(p));

		assert !hashtable.containsKey(p);
		assert hashtable.size() == size - 1;
		assert isWellFormed();

	}

	@Override
	public Account addHolderAccounts(Person p, String type, String pin) {
		Account account = null;
		assert isWellFormed();
		assert p != null;
		assert type != null;
		assert pin != null;
		int size = accounts.size();

		if (type.equals("spendings")) {
			SpendingsAccount spendingsAccount = new SpendingsAccount(randomGenerator.nextInt(100), p.getFirstName(),
					p.getLastName(), pin, 0);
			account = spendingsAccount;
			accounts.add(spendingsAccount);
			hashtable.get(p).add(account);
		} else if (type.equals("savings")) {
			SavingsAccount savingsAccount = new SavingsAccount(randomGenerator.nextInt(100), p.getFirstName(),
					p.getLastName(), pin, 0);
			account = savingsAccount;
			accounts.add(savingsAccount);
			hashtable.get(p).add(account);
		}

		assert accounts.size() == size + 1;
		assert isWellFormed();
		return account;
	}

	@Override
	public void removeHolderAccounts(Person p, Account a) {
		assert isWellFormed();
		assert p != null;
		assert a != null;
		int size = accounts.size();

		hashtable.get(p).remove(a);
		accounts.remove(a);
		assert accounts.size() == size - 1;
		assert isWellFormed();

	}

	@Override
	public void readAccountsData(Person p, Account a) {
		assert isWellFormed();
		assert p != null;
		assert a != null;
		if (p != null && a != null) {
			for (Iterator<Account> iterator = hashtable.get(p).iterator(); iterator.hasNext();) {
				Account value = iterator.next();
				if (value.getID() == a.getID()) {
					System.out.println(value.getID() + " " + value.getTotal());
				}

			}
		} else {
			System.out.println("Account or person does not exist");
		}
		assert isWellFormed();
	}

	@Override
	public void writeAccountsData(Person p, Account a, int data) {
		assert isWellFormed();
		assert p != null;
		assert a != null;
		assert data != 0;
		int total = a.getTotal();
		for (Iterator<Account> iterator = hashtable.get(p).iterator(); iterator.hasNext();) {
			Account value = iterator.next();
			if (value.getID() == a.getID()) {
				value.deposit(data);
			}

		}
		assert a.getTotal() != total;
		assert isWellFormed();
	}

	@Override
	public void generateReport() {
		String data = null;
		for (Account account : accounts) {
			data = account.getID() + " " + account.getTotal() + "\n";
			System.out.println(data);
		}

	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public ArrayList<Person> getClients() {
		return persons;
	}

	public Hashtable<Person, ArrayList<Account>> getHashtable() {
		return hashtable;
	}

	private boolean isWellFormed() {
		for (Entry<Person, ArrayList<Account>> entry : hashtable.entrySet()) {
			if (entry.getValue().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("bank.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public Bank deserialize() {
		Bank bank = null;
		try {
			FileInputStream fileIn = new FileInputStream("bank.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			bank = (Bank) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return bank;
	}

	@Override
	public void update(Observable o, Object arg) {
		observerReport = "\n" + "A modification has occured at: " + ((Account) o).getID() + "\n" + "Owner: "
				+ ((Account) o).getOwnerFirstName() + " " + ((Account) o).getOwnerLastName() + "\n" + "New total: "
				+ ((Account) o).getTotal();
		serialize();
	}

	public String getObserverReport() {
		return observerReport;
	}

}
