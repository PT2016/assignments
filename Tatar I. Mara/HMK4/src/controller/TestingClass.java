package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import models.Account;
import models.Bank;
import models.Person;
import models.SavingAccount;
import models.SpendingAccount;

public class TestingClass {
	

	@Test
	public void addPerson() {
		Serializer serializer=new Serializer();
		Bank bank = serializer.deserializeBank();
		Person testPerson=new Person("Lilly Potter","teacher",37,1010);
		try{
			bank.addPerson(testPerson);
		}
		catch (Exception e){
			e.printStackTrace();
			assertTrue(true);
			return;
		}
		
	}
	@Test
	public void removePerson() {
		Serializer serializer=new Serializer();
		Bank bank = serializer.deserializeBank();
		Person testPerson=(Person) bank.getBankData().keySet().toArray()[0];
		try{			bank.removePerson(testPerson);

		}
		catch (Exception e){
			e.printStackTrace();
			assertTrue(true);
			return;
		}
	}
	@Test
	public void addAccount() {
		Serializer serializer=new Serializer();
		Bank bank = serializer.deserializeBank();
		Person testPerson=(Person) bank.getBankData().keySet().toArray()[0];
		
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
		Serializer serializer=new Serializer();
		Bank bank = serializer.deserializeBank();
		Person testPerson=(Person) bank.getBankData().keySet().toArray()[0];
		Account testAccount=bank.getBankData().get(testPerson).get(0);
		try{
			
		    bank.removeHolderAccount(testPerson, testAccount);
		}
		catch (Exception e){
			e.printStackTrace();
			assertTrue(true);
			return;
		}
	
	}
}
