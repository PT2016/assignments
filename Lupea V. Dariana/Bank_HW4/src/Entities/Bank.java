package Entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Bank implements BankInterface, Serializable {
	private static final long serialVersionUID = 1L;
	private Map<Person, Set<Account>> list;

	public Bank() {

		list = new HashMap<Person, Set<Account>>();
	}

	/**
	 * Method used for checking if the bank is well-formed
	 * 
	 * @return
	 */
	public boolean isWellFormed() {
		for (Entry<Person, Set<Account>> entry : list.entrySet()) {
			if (entry.getValue() == null && entry.getValue().isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * Method used for adding a an account for a person (creating one)
	 */
	@Override
	public void addAccForPerson(Person p, Account assocAcc) {
		assert isWellFormed() : "The bank is not well formed";
		assert p != null : "Person null";
		assert assocAcc != null : "Acc null";
		assocAcc.addObserver(p);
		int presize;

		if (list.containsKey(p)) {

			Set<Account> set = list.get(p);
			presize = set.size();
			set.add(assocAcc);
			System.out.println("New account added for: " + p.toString());

		} else {
			presize = 0;
			Set<Account> set = new HashSet<Account>();
			set.add(assocAcc);
			list.put(p, set);
			System.out.println("Account added for " + p.toString());
		}

		int postSize = list.get(p).size();

		assert presize - postSize == -1 : "Account not added";// size must
																// increase
																// by 1
		assert isWellFormed() : "The bank is not well formed";
	}

	/**
	 * Method used for depositing money to an account
	 */
	@Override
	public void depositMoney(double sum, int accId, Person p) {

		assert isWellFormed() : "The bank is not well formed";
		assert p != null : "Person null";
		assert accId != 0 : "Invalid account";
		assert sum > 0 : "Insufficient money ";
		double initialMoney = 0;
		double afterDepositMoney = 1;
		if (list.containsKey(p)) {
			int found = 0;
			System.out.println("Person found: " + p);
			Set<Account> account1 = list.get(p);

			for (Account acc : account1) {
				if (acc.getAccId() == accId) {
					System.out.println("Account found");
					found = 1;
					initialMoney = acc.getMoney();
					acc.addMoney(sum);
					afterDepositMoney = acc.getMoney();
				}
			}
			if (found == 0) {
				System.out.println("Account not found");
			}
		} else {
			System.out.println("Person does not exist in our database");
		}

		assert initialMoney < afterDepositMoney : "The sum was not added";
		assert isWellFormed() : "The bank is not well formed";
	}

	/**
	 * Method used for withdrawing money to an account
	 */
	@Override
	public void withdrawMoney(double sum, int accId, Person p) {
		assert isWellFormed() : "The bank is not well formed";
		assert p != null : "Person null";
		assert accId != 0 : "Invalid account";
		assert sum > 0 : "Insufficient money";

		double initialMoney = 0;
		double afterWithdrawalMoney = 1;
		if (list.containsKey(p)) {
			System.out.println("Person found: " + p);
			Set<Account> account1 = list.get(p);

			for (Account acc : account1) {
				if (acc.getAccId() == accId) {
					System.out.println("Account found...");

					if (acc.getMoney() >= sum) {
						System.out.println("Enough money in your account...");
						initialMoney = acc.getMoney();
						acc.withdrawMoney(sum);
						afterWithdrawalMoney = acc.getMoney();
					} else {
						System.out.println("You do NOT have enough money in your account...");
					}
				}
			}
		}

		assert initialMoney > afterWithdrawalMoney : "The sum was not withdrawn";
		assert isWellFormed() : "The bank is not well formed";
	}

	/**
	 * Method used for deleting a person from the bank + all the corresponding
	 * accounts
	 */

	@Override
	public void deletePerson(Person p) {
		assert isWellFormed() : "The bank is not well formed";
		assert p != null : "Person null";

		int initialSize = list.size();
		int afterDeleteSize = 0;

		if (list.containsKey(p)) {
			System.out.println("Person found: " + p);

			list.remove(p);
			afterDeleteSize = list.size();
			System.out.println("Person removed: " + p);
		} else {
			System.out.println("Person " + p + "does not exist in our database...");
		}

		assert initialSize > afterDeleteSize : "Person has not been removed";
		assert isWellFormed() : "The bank is not well formed";
	}

	/**
	 * Method used for deleting an account
	 */

	@Override
	public void deleteAccount(int accId, Person p) {
		assert isWellFormed() : "The bank is not well formed";
		assert p != null : "Person null";
		assert accId != 0 : "Invalid account";

		int initialSize = 0;
		int afterDeleteSize = 0;
		if (list.containsKey(p)) {
			System.out.println("Person found: " + p);
			Set<Account> account1 = list.get(p);
			initialSize = account1.size();
			for (Account acc : account1) {
				if (acc.getAccId() == accId) {
					account1.remove(acc);
					afterDeleteSize = account1.size();
					System.out.println("Account deleted");

				}
			}
		} else {
			System.out.println("Account not found");
		}
		assert initialSize > afterDeleteSize;
		assert isWellFormed() : "The bank is not well formed";
	}

	/**
	 * Method used for finding all the persons which have at least one account
	 */
	@Override
	public Set<Person> findAllPersons() {
		assert isWellFormed() : "The bank is not well formed";
		Set<Person> persons = new HashSet<Person>();
		Iterator<Entry<Person, Set<Account>>> it = list.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Person, Set<Account>> pair = it.next();
			persons.add(pair.getKey());

		}
		assert isWellFormed() : "The bank is not well formed";
		return persons;

	}

	/**
	 * Method used for finding all the accounts in the bank
	 */
	@Override
	public Set<Account> findAllAccounts() {
		assert isWellFormed() : "The bank is not well formed";
		Set<Account> accounts = new HashSet<Account>();
		Iterator<Entry<Person, Set<Account>>> it = list.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Person, Set<Account>> pair = it.next();
			Set<Account> accForOnePerson = pair.getValue();

			System.out.println("Accounts of: " + pair.getKey().toString());
			for (Account acc : accForOnePerson) {
				System.out.println(acc.toString());
				accounts.add(acc);
			}

		}
		assert isWellFormed() : "The bank is not well formed";
		return accounts;

	}

	/**
	 * Method used for finding the sold balance for a certain account in the
	 * bank
	 * 
	 * @param accId
	 *            Account id
	 * @param p
	 *            Person which own the account( holder)
	 * @return
	 */
	public double findAccount(int accId, Person p) {
		double money = 0;
		if (list.containsKey(p)) {
			System.out.println("Person found: " + p);
			Set<Account> account1 = list.get(p);

			for (Account acc : account1) {
				if (acc.getAccId() == accId) {
					money = acc.getMoney();
				}
			}

		} else {
			System.out.println("Person not found: " + p);
			money = -0.00;
		}
		return money;

	}

	public Map<Person, Set<Account>> getList() {
		return this.list;
	}

	@Override
	public String toString() {
		return "Bank [bank info=" + list + "]";
	}

}
