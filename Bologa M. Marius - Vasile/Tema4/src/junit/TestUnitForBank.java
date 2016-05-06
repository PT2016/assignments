package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

import controllers.SerializableManager;
import models.Account;
import models.Bank;
import models.Person;
import models.SpendingAccount;
import models.utilities.Utilities;

public class TestUnitForBank {
	private SerializableManager manager = new SerializableManager();
	private Bank bank = new Bank();

	@Test
	public void testAddPerson() {
		Person p = new Person(7, "Seven");
		bank.addPerson(p);
		boolean flag = true;
		Iterator<Entry<Person, ArrayList<Account>>> iterator = bank.getContent().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, ArrayList<Account>> entry = iterator.next();
			if ((entry.getKey()).equals(p)) {
				flag = true;
				break;
			}
		}
		assertTrue(flag);
	}

	@Test
	public void testDeletePerson() {
		bank = manager.deserializeBank();
		Person p = (Person) bank.getContent().keySet().toArray()[0];
		bank.deletePerson(p);
		assertFalse(bank.getContent().containsKey(p));
	}

	@Test
	public void testAddAccountToHolder() {
		bank = manager.deserializeBank();
		Person p = (Person) bank.getContent().keySet().toArray()[0];
		Account a = new SpendingAccount(0.0, p, Utilities.getDateRandom(356), "Spending Account");
		bank.addAccountToHolder(a, p);
		boolean flag = false;
		Iterator<Entry<Person, ArrayList<Account>>> iterator = bank.getContent().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, ArrayList<Account>> entry = iterator.next();
			if ((entry.getKey().getId() == p.getId()) && ((entry.getKey().getName()).equals(p.getName()))) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (a.getId() == entry.getValue().get(i).getId()) {
						flag = true;
						break;
					}
				}

			}
		}
		assertTrue(flag);
	}

	@Test
	public void testDeleteAccountToHolder() {
		bank = manager.deserializeBank();
		Person p = (Person) bank.getContent().keySet().toArray()[0];
		Account a = new SpendingAccount(0.0, p, Utilities.getDateRandom(356), "Spending Account");
		bank.addAccountToHolder(a, p);
		bank.deleteAccountToHolder(a, p);
		assertFalse(bank.getContent().containsValue(a));
	}
}
