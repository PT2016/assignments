package TestDriver;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

import java.util.Set;

import Entities.Account;
import Entities.Bank;
import Entities.Person;
import Entities.SavingAccount;
import Entities.SpendingAccount;

public class TestUnit {

	private Bank b = new Bank();

	@Test
	public void testAddAccForPerson() {

		boolean ok = false;
		Person p = new Person("Dari", "dari.lu@yahoo.com", "4456");
		Account a = new SavingAccount(1, 200);
		b.addAccForPerson(p, a);

		Iterator<Entry<Person, Set<Account>>> it = b.getList().entrySet().iterator();
		while (it.hasNext()) {
			Entry<Person, Set<Account>> entry = it.next();
			if ((entry.getKey()).equals(p)) {
				ok = true;
				break;
			}
		}
		assertTrue(ok);
	}

	@Test
	public void testDepositMoney() {
		boolean ok = false;
		Person p = new Person("Dari", "dari.lu@yahoo.com", "4456");
		int accountId = 1;
		double sum = 200;

		double initialSum = 0;
		double actual = 0;
		double expected = 0;

		if (b.getList().containsKey(p)) {
			Set<Account> account = b.getList().get(p);
			for (Account acc : account) {
				if (acc.getAccId() == accountId) {
					initialSum = acc.getMoney();
					expected = initialSum + sum;
					b.depositMoney(sum, accountId, p);
					actual = acc.getMoney();
				}
			}
		}
		if (assertEquals(expected, actual)) {
			ok = true;
		}

		assertTrue(ok);
	}

	@Test
	public void testWithDrawMoney() {
		boolean ok = false;
		Person p = new Person("Dari", "dari.lu@yahoo.com", "4456");
		int accountId = 1;
		double sum = 50.3;

		double initialSum = 0;
		double actual = 0;
		double expected = 0;

		if (b.getList().containsKey(p)) {
			Set<Account> account = b.getList().get(p);
			for (Account acc : account) {
				if (acc.getAccId() == accountId) {
					initialSum = acc.getMoney();
					expected = initialSum - sum;
					b.withdrawMoney(sum, accountId, p);
					actual = acc.getMoney();
				}
			}
		}
		if (assertEquals(expected, actual)) {
			ok = true;
		}

		assertTrue(ok);
	}

	@Test
	public void testDeletePerson() {

		Person p = new Person("Dari", "dari.lu@yahoo.com", "4456");
		int prevSize = b.getList().size();
		int actual = 0;
		int expected = prevSize - 1;

		if (b.getList().containsKey(p)) {
			b.deletePerson(p);
			actual = b.getList().size();
		}

		assertEquals(expected, actual);
	}

	@Test
	public void testDeleteAccount() {
		Person p = new Person("Nicu", "nicu.r@yahoo.com", "4245");
		Account a = new SpendingAccount(2, 200);
		b.addAccForPerson(p, a);
		int accountId = 2;

		int initialSize = 0;
		int actual = 0;
		int expected = 0;

		if (b.getList().containsKey(p)) {

			Set<Account> account = b.getList().get(p);
			initialSize = account.size();
			expected = initialSize - 1;
			b.deleteAccount(accountId, p);
			actual = account.size();
		}

		assertEquals(expected, actual);

	}

	private boolean assertEquals(double expected, double actual) {
		return (expected == actual);
	}
}
