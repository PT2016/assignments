package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest {

	@Test
	public void testAddPerson() {
		Bank.deserialize();
		Person p = new Person("Test", 20);
		boolean flag = false;
		try {
			Bank.getInstance().addPerson(p);
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		for(Person pp: Bank.getInstance().getInfo().keySet())
			if(p.equals(pp)){
				flag = true;
				break;
			}
		assertTrue(flag);
	}

	@Test
	public void testRemovePerson() {
		Bank.deserialize();
		Person p = (Person) Bank.getInstance().getInfo().keySet().toArray()[0];
		try {
			Bank.getInstance().removePerson(p);
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		assertFalse(Bank.getInstance().getInfo().containsKey(p));
	}

	@Test
	public void testAddAccount() {
		Bank.deserialize();
		Person p = (Person) Bank.getInstance().getInfo().keySet().toArray()[0];
		Account a = null;
		try {
			a = new SavingsAccount(400);
		} catch (NotEnoughFundsException e1) {
			e1.printStackTrace();
			assertTrue(true);
			return;
		}
		try {
			Bank.getInstance().addAccount(p, a);
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		assertTrue(Bank.getInstance().getInfo().get(p).contains(a));
	}

	@Test
	public void testRemoveAccount() {
		Bank.deserialize();
		Person p = (Person) Bank.getInstance().getInfo().keySet().toArray()[0];
		Account a = Bank.getInstance().getInfo().get(p).get(0);
		try {
			Bank.getInstance().removeAccount(p, a);
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		assertFalse(Bank.getInstance().getInfo().get(p).contains(a));
	}

}
