package test;

import org.junit.Test;

import model.Account;
import model.Bank;
import model.Person;
import model.SpendingAccount;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class TestBank {
	private Bank bank;
	private HashMap<Person, ArrayList<Account>> content;

	public TestBank() {
		bank = Bank.getInstance();
		content = bank.getBankContent();
	}

	@Test
	public void testAddPerson() {
		Person person = new Person("Alexander", "Riback", "AAlex", "root", 20, 0);
		if (!content.containsKey(person)) {
			int nr = content.size();
			content.put(person, null);
			assertEquals(nr + 1, content.size(), 0.0);
		}
	}

	@Test
	public void testRemovePerson() {
		Iterator<Person> it = content.keySet().iterator();
		Person person = it.next();
		if (content.containsKey(person)) {
			int nr = content.size();
			content.remove(person);
			assertEquals(nr - 1, content.size(), 0.0);
		}
	}

	@Test
	public void testAddHolderAssociatedAccount() {
		Iterator<Person> it = content.keySet().iterator();
		Person person = it.next();
		Account account = new SpendingAccount(3000);
		if (content.containsKey(person)) {
			int nrAccounts;
			if (content.get(person) == null) {
				nrAccounts = 0;
				ArrayList<Account> accounts = new ArrayList<>();
				accounts.add(account);
				content.remove(person);
				person.setNrOfAccounts(person.getNrOfAccounts() + 1);
				content.put(person, accounts);
			} else {
				ArrayList<Account> accounts = new ArrayList<>();
				accounts.addAll(content.get(person));
				nrAccounts = accounts.size();
				accounts.add(account);
				content.remove(person);
				person.setNrOfAccounts(person.getNrOfAccounts() + 1);
				content.put(person, accounts);
			}
			assertEquals(nrAccounts + 1, person.getNrOfAccounts(), 0.0);
		}
	}

	@Test
	public void testRemoveHolderAssociatedAccount() {
		Iterator<Person> it = content.keySet().iterator();
		Person person = it.next();
		Account account = content.get(person).get(0);
		if (content.containsKey(person)) {
			if (person.getNrOfAccounts() > 0) {
				int nrAccounts;
				ArrayList<Account> accounts = content.get(person);
				nrAccounts = accounts.size();
				if (accounts.contains(account)) {
					content.remove(person);
					person.setNrOfAccounts(person.getNrOfAccounts() - 1);
					accounts.remove(account);
					content.put(person, accounts);
					assertEquals(nrAccounts - 1, accounts.size(), 0.0);
				}
			}
		}
	}

	@Test
	public void testGetAllPersons() {
		Set<Person> persons = content.keySet();
		ArrayList<Object> pers = new ArrayList<>();
		for (Person person : persons) {
			pers.add(person);
		}
		assertEquals(pers.size(), content.size(), 0.0);
	}

}
