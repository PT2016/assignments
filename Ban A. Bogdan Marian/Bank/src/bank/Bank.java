package bank;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import model.Account;
import model.AccountType;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;
import observers.SavingObserver;
import observers.SpendingObserver;

@SuppressWarnings("serial")
public class Bank implements BankProc, Serializable {
	private List<Person> persons = new ArrayList<>();
	private Hashtable<Long, List<Account>> hashtable = new Hashtable<>();
	Person person;

	public Bank() {
		person = addPerson("Mihai", AccountType.SPENDING);
	}

	@Override
	public Person addPerson(String name, AccountType type) {
		assert isWellFormed();
		SecureRandom random = new SecureRandom();
		long id = random.nextInt(10000000);
		Person person = new Person(name, id);
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		persons.add(person);
		addHolderAccount(person.getId(), type);
		assert isWellFormed();
		return person;
	}

	@Override
	public void removePerson(long id) {
		assert isWellFormed();
		if (id < 0) {
			throw new IllegalArgumentException("Person cannot be null");
		}
		if (hashtable.containsKey(id)) {
			hashtable.remove(id);
		}
		for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
			Person value = iterator.next();
			if (value.getId() == id) {
				iterator.remove();
			}

		}
	}

	@Override
	public Account addHolderAccount(long id, AccountType type) {
		assert isWellFormed();
		Account account = null;
		if (id < 0) {
			throw new IllegalArgumentException("Person cannot be null");
		}
		if (type == AccountType.SAVING) {
			account = new SavingAccount();
			account.addObserver(new SavingObserver());
		} else if (type == AccountType.SPENDING) {
			account = new SpendingAccount();
			account.addObserver(new SpendingObserver());
		}
		if (hashtable.containsKey(id)) {
			hashtable.get(id).add(account);
		} else {
			List<Account> accounts = new ArrayList<>();
			accounts.add(account);
			hashtable.put(id, accounts);
		}
		assert isWellFormed();
		return account;
	}

	@Override
	public void removeHolderAccount(long id, long accountId) {
		assert isWellFormed();
		if (id < 0 || accountId < 0) {
			throw new IllegalArgumentException("Person or account cannot be null");
		}
		if (hashtable.containsKey(id)) {
			for (Iterator<Account> iterator = hashtable.get(id).iterator(); iterator.hasNext();) {
				Account value = iterator.next();
				if (value.getAccountId() == accountId) {
					iterator.remove();
				}

			}
		}
		assert isWellFormed();
	}

	@Override
	public long readAccountData(long id, long accountId) {
		assert isWellFormed();
		if (id < 0 || accountId < 0) {
			throw new IllegalArgumentException("Person or account cannot be null");
		}
		Account account = getAccount(id, accountId);
		assert isWellFormed();
		return account.getSum();
	}

	@Override
	public void writeAccountData(long id, long accountId, long sum) {
		assert isWellFormed();
		if (id < 0 || accountId < 0) {
			throw new IllegalArgumentException("Person or account cannot be null");
		}
		assert sum >= 0;
		Account account = getAccount(id, accountId);
		Person person = getPersonById(id);
		account.setSum(sum, person.getName());
		assert isWellFormed();
	}

	@Override
	public List<Long> reportGenerator() {
		assert isWellFormed();
		List<Long> list = new ArrayList<>();
		// System.out.println("Ids of the persons who have at least one account
		// with 0 lei:");
		for (Entry<Long, List<Account>> entry : hashtable.entrySet()) {
			for (Account currentAccount : entry.getValue()) {
				if (currentAccount.getSum() == 0) {
					// System.out.println(entry.getKey());
					list.add(entry.getKey());
				}
			}
		}
		assert isWellFormed();
		return list;
	}

	public Account getAccount(long id, long accountId) {
		if (hashtable.containsKey(id)) {
			for (Account currentAccount : hashtable.get(id)) {
				if (currentAccount.getAccountId() == accountId) {
					return currentAccount;
				}
			}
		} else {
			System.out.println("Does not exist");
		}
		return null;
	}

	private boolean isWellFormed() {
		for (Entry<Long, List<Account>> entry : hashtable.entrySet()) {
			if (entry.getValue().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public Object[][] getAllEntriesForTable() {
		assert isWellFormed();
		Object[][] result = new Object[getTotalEntriesForTable()][5];
		int i = 0;
		for (Entry<Long, List<Account>> entry : hashtable.entrySet()) {
			for (Account currentAccount : entry.getValue()) {
				result[i][0] = getPersonById(entry.getKey()).getName();
				result[i][1] = entry.getKey();
				result[i][2] = currentAccount.getAccountId();
				result[i][3] = getAccountType(currentAccount);
				result[i][4] = currentAccount.getSum();
				i++;
			}

		}
		assert isWellFormed();
		return result;
	}

	public Object[][] getPersonEntriesForTable(Person person) {
		assert isWellFormed();
		Object[][] result = new Object[getTotalEntriesForTable()][5];
		int i = 0;
		if (hashtable.containsKey(person.getId())) {
			for (Account currentAccount : hashtable.get(person.getId())) {
				result[i][0] = person.getName();
				result[i][1] = person.getId();
				result[i][2] = currentAccount.getAccountId();
				result[i][3] = getAccountType(currentAccount);
				result[i][4] = currentAccount.getSum();
				i++;
			}

		}
		assert isWellFormed();
		return result;
	}

	private int getTotalEntriesForTable() {
		int i = 0;
		for (Entry<Long, List<Account>> entry : hashtable.entrySet()) {
			for (@SuppressWarnings("unused")
			Account currentAccount : entry.getValue()) {
				i++;
			}
		}
		return i;
	}

	private AccountType getAccountType(Account account) {
		if (account instanceof SpendingAccount) {
			return AccountType.SPENDING;
		}
		return AccountType.SAVING;
	}

	private Person getPersonById(Long id) {
		for (Person person : persons) {
			if (person.getId() == id) {
				return person;
			}
		}
		return null;
	}

	public Hashtable<Long, List<Account>> getHashtable() {
		return hashtable;
	}

	public void setHashtable(Hashtable<Long, List<Account>> hashtable) {
		this.hashtable = hashtable;
	}

	// public void removePersons(long id) {
	// List<Person> clone = new ArrayList<>();
	// clone.addAll(persons);
	// for (Person person : persons) {
	// if (hashtable.containsKey(id)) {
	// clone.remove(person);
	// }
	// }
	// persons = clone.clone();
	// }

	public Person getPerson() {
		return person;
	}

}
