package pt.ObserverBank.basicObjects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

/**
 * Holds info about clients and their accounts in a HashMap
 * 
 * @author Chiti
 *
 */
public class Bank implements BankProc,Serializable {
	private static final long serialVersionUID = -4890879950890782117L;
	private int id = 0;
	private HashMap<Person, HashSet<Account>> database;

	public Bank() {
		database = new HashMap<Person, HashSet<Account>>();
	}

	@Override
	public void addPerson(int id, String name) {
		assert (name != null && id >= 0) : "Cannot add with null name and/or negative id!";
		int preSize = database.size();
		if (isWellFormed()) {
			Person p = new Person(id, name);
			if (database.containsKey(p))
				infoBox("Person already exists!", "Add person error.");
			else {
				database.put(p, new HashSet<Account>());
				int postSize=database.size();
				assert (postSize==preSize+1):"Not a valid addition!";
			}
		}
	}

	@Override
	public void removePerson(int id, String name) {
		assert (name != null && id >= 0) : "Cannot delete if null name and/or negative id!";
		int preSize = database.size();
		if (isWellFormed()) {
			Person p = new Person(id, name);
			if (database.containsKey(p)) {
				database.remove(p);
				int postSize=database.size();
				assert (postSize==preSize-1):""+"Not a valid deletion!";
			} else
				infoBox("Person does not exist!", "Remove person error.");
		}
	}

	@Override
	public void addAccount(Person person, double sum, AccountType type) {
		assert (person!=null && sum>0):"Cannot add acount if null name and/or negative sum!";
		if (isWellFormed())
			if (database.containsKey(person)) {
				Account a;
				switch (type) {
				case SAVINGS:
					a = new SavingAccount(id, sum);
					HashSet<Account> list = (HashSet<Account>) database.get(person);
					int presize=list.size();
					list.add(a);
					int postsize=list.size();
					assert (postsize==presize+1):"Invalid add acount!";
					id++;
					database.put(person, list);
					break;

				case SPENDINGS:
					a = new SpendingAccount(id, sum);
					HashSet<Account> list1 = (HashSet<Account>) database.get(person);
					int presize1=list1.size();
					list1.add(a);
					int postsize1=list1.size();
					assert (postsize1==presize1+1):"Invalid add acount!";
					id++;
					database.put(person, list1);
					break;
				}
			} else
				infoBox("Person does not exist!", "Add account error.");
	}

	@Override
	public void removeAccount(Person person, int accountId, AccountType type) {
		assert (person!=null && accountId>=0):"Cannot add acount if null name and/or negative id!";
		if (isWellFormed())
			if (database.containsKey(person)) {
				HashSet<Account> list = (HashSet<Account>) database.get(person);
				int presize=list.size();
				switch (type) {
				case SAVINGS:
					SavingAccount delete = new SavingAccount(0, 0);
					for (Account a : list)
						if (a.getId() == accountId)
							delete = (SavingAccount) a;
					list.remove(delete);
					int postsize=list.size();
					if (!delete.equals(new SavingAccount(0,0)))
						assert (postsize==presize-1):"Invalid remove acount!";
					database.put(person, list);
					break;

				case SPENDINGS:
					SpendingAccount delete1 = new SpendingAccount(0, 0);
					for (Account a : list)
						if (a.getId() == accountId)
							delete1 = (SpendingAccount) a;
					list.remove(delete1);
					int postsize1=list.size();
					if (!delete1.equals(new SpendingAccount(0,0)))
						assert (postsize1==presize-1):"Invalid remove acount!";
					database.put(person, list);
					break;
				}
			} else
				infoBox("Person does not exist!", "Remove account error.");
	}

	@Override
	public void withdraw(Person person, int accountId, double sum) {
		assert (person != null && accountId >= 0 && sum > 0) : "Cannot withdraw if null name and/or negative id/sum!";
		if (isWellFormed())
			if (database.containsKey(person)) {
				HashSet<Account> list = (HashSet<Account>) database.get(person);
				for (Account a : list)
					if (a.getId() == accountId) {
						double preBalance=a.inquire();
						a.withdraw(sum);
						double postBalance=a.inquire();
						assert (preBalance-sum-0.001<postBalance && postBalance<preBalance-sum+0.001):"Invalid withdraw!";
					}
			} else
				infoBox("Person does not exist!", "Withdraw error.");
	}

	@Override
	public void applyInterest(Person person, int accountId, double interest) {
		if (isWellFormed())
			if (database.containsKey(person)) {
				{
					HashSet<Account> list = (HashSet<Account>) database.get(person);
					for (Account a : list)
						if (a.getId() == accountId)
							if (a.getClass().toString().equals("class pt.ObserverBank.basicObjects.SavingAccount"))
								a.add((a.inquire() * interest) / 100);
				}
			}

	}

	@Override
	public void deposit(Person person, int accountId, double sum) {
		assert (person != null && accountId >= 0 && sum > 0) : "Cannot withdraw if null name and/or negative id/sum!";
		if (isWellFormed())
			if (database.containsKey(person)) {
				HashSet<Account> list = (HashSet<Account>) database.get(person);
				for (Account a : list)
					if (a.getId() == accountId)
					{
						double preBalance=a.inquire();
						a.add(sum);
						double postBalance=a.inquire();
						assert (preBalance+sum-0.001<postBalance && postBalance<preBalance+sum+0.001):"Invalid deposit!";
					}
			}
	}

	/**
	 * @return the database
	 */
	public HashMap<Person, HashSet<Account>> getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	public void setDatabase(HashMap<Person, HashSet<Account>> database) {
		this.database = database;
	}

	/**
	 * method for the class invariant
	 * @return
	 */
	private boolean isWellFormed() {
		if (database == null)
			return false;
		return true;
	}
	
	public Account getAccount(Person p,int accountId){
		if(isWellFormed())
			if (database.containsKey(p))
			{
				HashSet<Account> list=database.get(p);
				for (Account a:list)
					if (a.getId()==accountId)
						return a;
			}
		return null;
	}

	public String listClient(Person person) {
		StringBuilder sb = new StringBuilder();
		if (isWellFormed())
			if (database.containsKey(person)) {
				HashSet<Account> list = (HashSet<Account>) database.get(person);
				sb.append(person.toString() + ":\n");
				for (Account a : list)
					sb.append(a.toString() + ";\n");
			}else infoBox("No such client!","List Error.");
		return sb.toString();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<Person> keys = database.keySet();
		for (Person key : keys) {
			sb.append("_________________________________________________________________\n");
			HashSet<Account> list = (HashSet<Account>) database.get(key);
			sb.append(key.toString() + ":\n");
			for (Account a : list)
				sb.append(a.toString() + ";\n");
		}
		return sb.toString();
	}

	/**
	 * used to tell the user what happend
	 * 
	 * @param infoMessage
	 * @param titleBar
	 */
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
}
