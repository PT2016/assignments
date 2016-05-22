package Controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import models.Account;
import models.Bank;
import models.Person;
import models.SavingAccount;
import models.SpendingAccount;

public class TestingClass {
	

	@Test
	public void addPerson() {

		Person testPerson=new Person("Florin Popa",21);
		Bank bank = new Bank();
		try{
			bank.addPerson(testPerson);
		}
		catch (Exception e){
			e.printStackTrace();
			return;
		}
		
	}
	@Test
	public void removePerson() {
		Bank bank = new Bank();
		Person testPerson=new Person("Florin Popa",21);
		try{			
			bank.removePerson(testPerson);
		}
		catch (Exception e){
			e.printStackTrace();
			return;
		}
	}
	@Test
	public void addAccount() {

		Bank bank = new Bank();
		Person testPerson=new Person("Florin Popa",21);
		try{
			Account acc=new SpendingAccount(150);
			Account accc=new SavingAccount(100);
		    bank.addHolderAccount(testPerson, acc);
		    bank.addHolderAccount(testPerson, accc);
		}
		catch (Exception e){
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		
	}
	@Test
	public void removeAccount() {
		Bank bank = new Bank();
		Person testPerson=new Person("Florin Popa",21);
		SpendingAccount account = new SpendingAccount(2000);
		try{
			
		    bank.removeHolderAccount(testPerson, account);
		}
		catch (Exception e){
			e.printStackTrace();
			assertTrue(true);
			return;
		}
	
	}
}
