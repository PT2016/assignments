package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import models.Account;
import models.Bank;
import models.Person;
import models.SpendingsAccount;

public class TestBank1 {
	Bank bank = new Bank();

	@Test
	public void testAddPerson() {
		bank = bank.deserialize();
		Person p = new Person("a", "b", 10);
		boolean ok = false;
		bank.getClients().add(p);
		for (Person client : bank.getClients()) {
			if (client.equals(p)) {
				ok = true;

			}
		}

		assertTrue(ok);

	}

	@Test
	public void testRemovePerson() {
		boolean ok = true;
		bank = bank.deserialize();
		Person p = bank.getClients().get(0);
		bank.getClients().remove(p);
		bank.getHashtable().remove(p, bank.getHashtable().get(p));
		if (bank.getHashtable().get(p) != null) {
			ok = false;
		}
		assertTrue(ok);

	}

	@Test
	public void testaddHolderAccounts() {
		boolean ok = false;
		bank = bank.deserialize();
		Person p = bank.getClients().get(0);
		Account a = new SpendingsAccount(100, p.getFirstName(), p.getLastName(), "1111", 300);
		bank.getAccounts().add(a);
		bank.getHashtable().get(p).add(a);
		for (Account account : bank.getHashtable().get(p)) {
			if (account.getID() == a.getID()) {
				ok = true;
			}
		}
		assertTrue(ok);
	}

}
