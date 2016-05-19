package bank;

import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import account.Account;
import user.Person;

public class Bank implements BankProc, Serializable {

	private Account ac;

	public void setAc(Account ac) {
		this.ac = ac;
	}

	public Account getAc() {
		return ac;
	}

	private Hashtable<String, LinkedList<Account>> accounts;

	public Bank() {
		accounts = new Hashtable<String, LinkedList<Account>>();
	}

	public void addAccount(Account a) {
		assert (a != null) && (a.getMainHolder().getCNP() != null) : "adding a new cont Invalid data";
		assert isWellFormed() : "Error: adding a new Account";
		int nr = getNrAccounts();
		if (accounts.containsKey(a.getMainHolder().getCNP())) {
			a.setID(getNrAccounts());
			if (searchName(a.getMainHolder().getCNP()) != null)
				a.getMainHolder().setName(searchName(a.getMainHolder().getCNP()));
			accounts.get(a.getMainHolder().getCNP()).add(a);
		} else {
			LinkedList<Account> l = new LinkedList<Account>();
			a.setID(getNrAccounts());
			l.add(a);
			accounts.put((a.getMainHolder().getCNP()), l);
		}
		assert nr + 1 == getNrAccounts() : "Error: adding a new Account";
		assert isWellFormed() : "Error: adding a new Account";
	}

	public boolean removeAccount(Account a) {
		assert (a != null) && (a.getMainHolder().getCNP() != null) : "remove a cont Invalid data";
		assert isWellFormed() : "Error: adding a new Account";
		int nr = getNrAccounts();
		int ok = 0;
		if (accounts.containsKey(a.getMainHolder().getCNP())) {
			for (int i = 0; i < accounts.get(a.getMainHolder().getCNP()).size(); i++) {
				if (accounts.get(a.getMainHolder().getCNP()).get(i).getID() == a.getID()) {
					{
						if (accounts.get(a.getMainHolder().getCNP()).size() == 1) {
							removeHolder(accounts.get(a.getMainHolder().getCNP()).get(0).getMainHolder());
							return true;
						}
						accounts.get(a.getMainHolder().getCNP())
								.remove(accounts.get(a.getMainHolder().getCNP()).get(i));
						ok = 1;
					}
				}
			}
			if (ok == 0) {
				return false;
			}
			for (int i = 0; i < accounts.get(a.getMainHolder().getCNP()).size(); i++) {
				accounts.get(a.getMainHolder().getCNP()).get(i).setID(i);
			}
		}
		assert nr - 1 == getNrAccounts() : "Error: removing a new Account";
		assert isWellFormed() : "Error: removing a new Account";
		return false;
	}

	public int getNrAccounts() {

		Collection<LinkedList<Account>> collection = accounts.values();
		Iterator<LinkedList<Account>> iterator = collection.iterator();
		LinkedList<Account> list = new LinkedList<Account>();
		int nr = 0;
		while (iterator.hasNext()) {
			list = iterator.next();
			for (int i = 0; i < list.size(); i++) {
				nr++;
			}
		}
		return nr;
	}

	public void print() {
		Collection<LinkedList<Account>> collection = accounts.values();
		Iterator<LinkedList<Account>> iterator = collection.iterator();
		LinkedList<Account> list = new LinkedList<Account>();

		while (iterator.hasNext()) {
			list = iterator.next();
			for (int i = 0; i < list.size(); i++) {
				System.out.print("Main HOLDER:" + list.get(i).getMainHolder().getCNP() + " ID:" + list.get(i).getID()
						+ " MONEY:" + list.get(i).getMoney() + " HOLDERS:");
				for (int j = 0; j < list.get(i).getPerson().size(); j++) {
					System.out.print(list.get(i).getPerson().get(j).getCNP() + " "
							+ list.get(i).getPerson().get(j).getName() + " ");
				}
				System.out.print("\n");
			}
		}
	}

	public void addPerson(Person p, Account a) {
		assert (a != null) && (a.getMainHolder().getCNP() != null) : " addPerson invalid account";
		assert (p != null) && (p.getCNP() != null) : "addPerson invalid person";
		assert isWellFormed() : "Error: adding a new Account";
		int nr1 = 0, nr2 = 0;
		if(searchName(p.getCNP())!=null){
			p.setName(searchName(p.getCNP()));
		}
		if (accounts.containsKey(a.getMainHolder().getCNP())) {
			for (int i = 0; i < accounts.get(a.getMainHolder().getCNP()).size(); i++) {
				if (accounts.get(a.getMainHolder().getCNP()).get(i).getID() == a.getID()) {
					nr1 = accounts.get(a.getMainHolder().getCNP()).get(i).getPerson().size();
					accounts.get(a.getMainHolder().getCNP()).get(i).addPers(p);
					nr2 = accounts.get(a.getMainHolder().getCNP()).get(i).getPerson().size();
				}
			}
		}
		assert nr1 + 1 == nr2 : "Error: adding a person";
		assert isWellFormed() : "Error: adding a person";
	}

	public boolean removePerson(Person p, Account a) {
		assert (a != null) && (a.getMainHolder().getCNP() != null) : " removePerson invalid account";
		assert (p != null) && (p.getCNP() != null) : "removePerson invalid person";
		assert isWellFormed() : "Error: adding a new Account";
		int nr1 = 0, nr2 = 0;
		if (accounts.containsKey(a.getMainHolder().getCNP())) {
			for (int i = 0; i < accounts.get(a.getMainHolder().getCNP()).size(); i++) {
				if (accounts.get(a.getMainHolder().getCNP()).get(i).getID() == a.getID())
					for (int j = 0; j < accounts.get(a.getMainHolder().getCNP()).get(i).getPerson().size(); j++)
						if (accounts.get(a.getMainHolder().getCNP()).get(i).getPerson().get(j).getCNP().equals(p.getCNP())) {
							if(accounts.get(a.getMainHolder().getCNP()).get(i).getPerson().size()==1){
								removeAccount(a);
								return true;
							}
							nr1 = accounts.get(a.getMainHolder().getCNP()).get(i).getPerson().size();
							accounts.get(a.getMainHolder().getCNP()).get(i)
									.deletePers(accounts.get(a.getMainHolder().getCNP()).get(i).getPerson().get(j));
							nr2 = accounts.get(a.getMainHolder().getCNP()).get(i).getPerson().size();

							return true;
						}
			}
		}
		assert nr1 - 1 == nr2 : "Error: removing a person";
		assert isWellFormed() : "Error: removing a person";
		return false;
	}

	public boolean removeHolder(Person p) {
		assert (p != null) && (p.getCNP() != null) : "removeHolder invalid person";
		assert isWellFormed() : "Error: adding a new Account";
		int nr1 = 0, nr2 = 0;
		nr1 = accounts.size();
		if (accounts.containsKey(p.getCNP())) {
			accounts.remove(p.getCNP());
			return true;
		}
		nr2 = accounts.size();
		assert nr1 <= nr2 : "Error: removeHolder";
		assert isWellFormed() : "Error: removeHolder";
		return false;
	}

	public boolean search(String CNP, int ID) {
		Collection<LinkedList<Account>> collection = accounts.values();
		Iterator<LinkedList<Account>> iterator = collection.iterator();
		LinkedList<Account> list = new LinkedList<Account>();
		while (iterator.hasNext()) {
			list = iterator.next();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getID() == ID) {
					for (int j = 0; j < list.get(i).getPerson().size(); j++) {
						if (list.get(i).getPerson().get(j).getCNP().equals(CNP)) {
							setAc(list.get(i));
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isWellFormed() {
		int i, n = 0;
		Collection<LinkedList<Account>> collection = accounts.values();
		Iterator<LinkedList<Account>> iterator = collection.iterator();
		LinkedList<Account> list = new LinkedList<Account>();
		while (iterator.hasNext()) {
			list = iterator.next();
			n++;
			if (list.isEmpty()) {
				return false;

			} else {
				for (i = 0; i < list.size(); i++)
					if (!(list.get(i) instanceof Account)) {
						return false;
					}
			}
		}
		if (n == accounts.size())
			return true;
		else
			return false;
	}

	public Collection<LinkedList<Account>> getAccounts() {
		return accounts.values();
	}

	public String getInfo(int n) {
		Iterator<LinkedList<Account>> iterator = accounts.values().iterator();
		LinkedList<Account> list;
		String s = "";
		while (iterator.hasNext()) {
			list = iterator.next();
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getID() == n) {
					s = "MONEY:" + s + list.get(j).getTotalSum() + "\nHOLDERS: ";
					for (int i = 0; i < list.get(j).getPerson().size(); i++) {
						s = s + list.get(j).getPerson().get(i).getCNP() + " " + list.get(j).getPerson().get(i).getName()
								+ " ";
					}
				}
			}
		}
		return s;
	}

	public String searchName(String CNP) {
		Collection<LinkedList<Account>> collection = accounts.values();
		Iterator<LinkedList<Account>> iterator = collection.iterator();
		LinkedList<Account> list = new LinkedList<Account>();
		while (iterator.hasNext()) {
			list = iterator.next();
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.get(i).getPerson().size(); j++) {
					if (list.get(i).getPerson().get(j).getCNP().equals(CNP)) {
						return list.get(i).getPerson().get(j).getName();
					}
				}

			}
		}
		return null;
	}
	
	public Person getMainHolder(int ID){
		Iterator<LinkedList<Account>> iterator = accounts.values().iterator();
		LinkedList<Account> list;
		while (iterator.hasNext()) {
			list = iterator.next();
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getID() == ID) {
					return list.get(j).getMainHolder();
					}
				}
			}
		return null;
	}
}