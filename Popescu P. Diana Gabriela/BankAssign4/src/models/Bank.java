package models;

import java.io.Serializable;
import java.util.*;

public class Bank implements BankProc, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Map<Person, Set<Account>> bankMap;

	public Bank() {
		bankMap = new HashMap<>();
	}

	public void deposit(double sum, String accountID, Person person) {
		assert isWellFormed() : "The invariant check failed";
		assert (!accountID.equals("")) : "The Account is invalid";
		assert (person != null) : "The Person should not be null";
		Set<Account> accounts = bankMap.get(person);
		double initialSum = 1, finalSum = 2;
		boolean accountFound = false;
		for (Account account : accounts)
			if (account.ID.equals(accountID)) {
				initialSum = account.money;
				account.deposit(sum);
				finalSum = account.money;
				accountFound = true;
			}
		assert accountFound : "The ID should belong to at least one of the person's accounts";
		assert (initialSum < finalSum) : "The final sum should be larger than the initial sum";
		assert isWellFormed() : "The invariant check failed";
	}

	public void withdraw(double sum, String accountID, Person person) {
		assert isWellFormed() : "The invariant check failed";
		assert (!accountID.equals("")) : "The Account is invalid";
		assert (person != null) : "The Person object should not be null";
		Set<Account> accounts = bankMap.get(person);
		double initialSum = 1, finalSum = 2;
		boolean accountFound = false;
		for (Account account : accounts)
			if (account.ID.equals(accountID)) {
				initialSum = account.money;
				account.withdraw(sum);
				finalSum = account.money;
				accountFound = true;
			}
		assert accountFound : "The ID should belong to at least one of the person's accounts";
		assert (initialSum > finalSum) : "The final sum should smaller than the initial sum";
		assert isWellFormed() : "The invariant check failed";
	}

	public void addAccount(Person person, Account account) {
		assert isWellFormed() : "The invariant check failed";
		assert (person != null) : "The Person object should not be null";
		assert (account != null) : "The Account object should not be null";
		int size = 0;
		if (bankMap.containsKey(person)) {
			size = bankMap.get(person).size();
			bankMap.get(person).add(account);
		} else {
			Set<Account> accountSet = new HashSet<>();
			accountSet.add(account);
			bankMap.put(person, accountSet);
		}
		account.addObserver(person);
		assert (size + 1 == bankMap.get(person).size()) : "The Account set size should change";
		assert isWellFormed() : "The invariant check failed";
	}
	
	public void removeAccount(Person person, Account account) {
		assert isWellFormed() : "The invariant check failed";
		assert (person != null) : "The Person object should not be null";
		assert (account != null) : "The Account object should not be null";
		int size = 0;
		if (bankMap.containsKey(person)) {
			size = bankMap.get(person).size();
			Set<Account> accountSet = bankMap.get(person);
			accountSet.remove(account);
		}
		assert (size - 1 == bankMap.get(person).size()) : "The Account set size should change";
		assert isWellFormed() : "The invariant check failed";
	}
	
	public Set<Account> getAccounts(Person person) {
		assert isWellFormed() : "The invariant check failed";
		assert (person != null) : "The person should not be null";
		HashMap<Person, Set<Account>> oldMap = (HashMap<Person, Set<Account>>) bankMap;
		Set<Account> auxAccounts = new HashSet<Account>();

		if (bankMap.containsKey(person)) {
			auxAccounts.addAll(bankMap.get(person));
		}
		
		assert oldMap.equals(bankMap) : "The hash map should not change";
		assert isWellFormed() : "The invariant check failed";
		return auxAccounts;
	}

	public Set<Person> getHolders() {
		assert isWellFormed() : "The invariant check failed";
		HashMap<Person, Set<Account>> oldMap = (HashMap<Person, Set<Account>>) bankMap;
		Set<Person> auxPersons = new HashSet<Person>();

		for (Object o : bankMap.entrySet()) {
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry) o;
			auxPersons.add((Person) pair.getKey());
		}
		assert oldMap.equals(bankMap) : "The hash map should not change";
		assert isWellFormed() : "The invariant check failed";
		return auxPersons;
	}

	@Override
	public void addHolder(Person p) {
		assert isWellFormed() : "bank not well formed";
		assert p != null : "person NULL";

		if (!bankMap.containsKey(p)) {
			Set<Account> accounts = new HashSet<>();
			bankMap.put(p, accounts);
			System.out.println("added in BANK");
		}
		else{
			System.out.println("already existing holder");
		}
		assert bankMap.containsKey(p) : "bank doesn't contain " + p.toString();
		assert isWellFormed() : "bank not well formed";
	}

	@Override
	public void removeHolder(Person p) {
		assert isWellFormed() : "bank not well formed";
		assert p != null : "person NULL";

		assert bankMap.containsKey(p) : p.toString() + "is not a client";
		bankMap.remove(p);

		assert !bankMap.containsKey(p) : "bank contains " + p.toString();
		assert isWellFormed() : "bank not well formed";
	}

	public boolean isWellFormed() {
		//return true;/*TEST*/
		Set<Person> persons = bankMap.keySet();
		Set<Account> accounts;
		for (Person p : persons) {
			if (p.ID.equals("") || p.name.equals("")) {
				return false;
			}
			accounts = bankMap.get(p);
			for (Account a : accounts) {
				if (a.ID.equals("") || a.money < 0.0 || a.type.equals("")) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Bank))
			return false;
		Bank bank = (Bank) obj;
		return bankMap.equals(bank.bankMap);
	}
	
	@Override
	public String toString() {
		return "" + bankMap;
	}
	
	public int hashCode() {
        return bankMap.hashCode();
    }
}
