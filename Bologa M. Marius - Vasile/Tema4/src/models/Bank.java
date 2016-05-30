package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import models.utilities.AdminReports;

public class Bank implements BancProc, Serializable {
	private static final long serialVersionUID = 1L;
	private HashMap<Person, ArrayList<Account>> content;

	public Bank() {
		this.content = new HashMap<Person, ArrayList<Account>>();
	}

	@Override
	public void addPerson(Person p) {
		assert isWellFormed();
		assert p != null;
		assert p.getName() != null;
		int n = getNumberOfPersons();
		if (!content.containsKey(p)) {
			content.put(p, new ArrayList<Account>());
		}
		assert (getNumberOfPersons() != 0) && (getNumberOfPersons() == n + 1);
		assert isWellFormed();
	}

	@Override
	public void deletePerson(Person p) {
		assert isWellFormed();
		assert getNumberOfPersons() != 0 && getNumberOfAccounts() != 0;
		assert p != null;
		assert content.containsKey(p);
		int n = getNumberOfAccounts();
		if (content.containsKey(p)) {
			content.remove(p);
		}
		assert !(content.containsKey(p));
		assert getNumberOfAccounts() == n - 1;
		assert isWellFormed();

	}

	@Override
	public void addAccountToHolder(Account a, Person p) {
		assert isWellFormed();
		assert p != null && a != null;
		assert content.get(p) != null;
		int n = getNumberOfAccounts();
		if (content.containsKey(p)) {
			content.get(p).add(a);
			a.addObserver(p);
		} else {
			addPerson(p);
			content.get(p).add(a);
			a.addObserver(p);
		}
		assert getNumberOfAccounts() == n + 1;
		assert isWellFormed();

	}

	@Override
	public void deleteAccountToHolder(Account a, Person p) {
		assert isWellFormed();
		assert p != null && a != null;
		assert content.get(p) != null;
		int n = getNumberOfAccounts();
		if (content.containsKey(p)) {
			content.get(p).remove(a);
		}
		assert getNumberOfAccounts() == n - 1;
		assert isWellFormed();

	}

	public HashMap<Person, ArrayList<Account>> getContent() {
		return content;
	}

	public void setContent(HashMap<Person, ArrayList<Account>> content) {
		this.content = content;
	}

	@Override
	public void generateReports() {
		new AdminReports();

	}

	public int getNumberOfPersons() {
		return content.size();
	}

	public int getNumberOfAccounts() {
		int size = 0;
		Iterator<Entry<Person, ArrayList<Account>>> iterator = content.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, ArrayList<Account>> entry = iterator.next();
			size = size + entry.getValue().size();
		}

		return size;
	}

	private boolean isWellFormed() {
		int persons = getNumberOfPersons();
		int accounts = getNumberOfAccounts();

		int persnum = 0;
		int accnum = 0;

		Iterator<Entry<Person, ArrayList<Account>>> iterator = content.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<Person, ArrayList<Account>> entry = iterator.next();
			persnum++;
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (entry.getValue().get(i) instanceof Account)
					accnum++;
			}
		}

		return ((persnum == persons) && (accnum == accounts));
	}
}
