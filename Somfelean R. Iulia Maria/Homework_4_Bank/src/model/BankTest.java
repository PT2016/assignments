package model;

import static org.junit.Assert.*;

import org.junit.Test;

import utilities.IllegalProcedureException;
import utilities.InsufficientMoney;

public class BankTest {

	
	Bank bank = new Bank();

	@Test
	public void testAddPerson() {
		
		bank = bank.writeAccountsData();
		Person p1 = new Person("Claudia", "Danciu", "29019943474", "0765345122");
		boolean flag = false;
		
		try {
			bank.addPerson(p1);
		} catch (IllegalProcedureException e) {
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		
		for(Person person: bank.getBankData().keySet()){
			if(p1.equals(person)){
				flag = true;
				break;
			}
		}
		assertTrue(flag);
	}
	
	@Test 
	public void testRemovePerson(){
	
		bank = bank.writeAccountsData();
		Person p = (Person) bank.getBankData().keySet().toArray()[0];
		try {
			bank.removePerson(p);
		} catch (IllegalProcedureException e) {
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		assertFalse(bank.getBankData().containsKey(p));
	}
	
	@Test
	public void testAddAccount() {
		
		bank = bank.writeAccountsData();
		Person p1 = (Person) bank.getBankData().keySet().toArray()[0];
		Account a = null;
		a = new SavingAccount(10000);
		try {
			bank.addAccount(p1, a);
		} catch (IllegalProcedureException e) {
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		assertFalse(bank.getBankData().get(p1).contains(a));
	}

	@Test
	public void testRemoveAccount() {
		bank = bank.writeAccountsData();
		Person p1 = (Person) bank.getBankData().keySet().toArray()[0];
		Account a = bank.getBankData().get(p1).get(0);
		try {
			bank.removeAccount(p1, a);
		} catch (IllegalProcedureException e) {
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		assertFalse(bank.getBankData().get(p1).contains(a));
	}
}
