package test;

import java.util.Random;
import models.Account;
import models.Bank;
import models.Person;
import models.SavingAccount;

public class Test {

	private Bank bank;
	private Person person;
	private Account account;
	
	public Test() throws Exception{
		bank = new Bank();
		person = new Person(new Random().toString(), new Random().toString());
		account = new SavingAccount(new Random().toString(), 100.0);
		
		this.deposit();
		this.withdraw();
		this.addAccount();
		this.removeAccount();
		this.addHolder();
		this.removeHolder();
	}
	
	/**
	 * @Test
	 * @throws Exception
	 */
	public void deposit() throws Exception{
		bank.addHolder(person);
		bank.addAccount(person, account);
		bank.deposit(100.0, account.ID, person);
		
		bank.removeAccount(person, account);
		bank.removeHolder(person);
	}
	
	/**
	 * @Test
	 * @throws Exception
	 */
	public void withdraw() throws Exception{
		bank.addHolder(person);
		bank.addAccount(person, account);
		bank.withdraw(50.0, account.ID, person);
		
		bank.removeAccount(person, account);
		bank.removeHolder(person);
	}
	
	/**
	 * @Test
	 * @throws Exception
	 */
	public void addAccount() throws Exception{
		bank.addHolder(person);
		bank.addAccount(person, account);
		
		bank.removeAccount(person, account);
		bank.removeHolder(person);
	}
	
	/**
	 * @Test
	 * @throws Exception
	 */
	public void removeAccount() throws Exception{
		bank.addHolder(person);
		bank.addAccount(person, account);
		bank.removeAccount(person, account);
		
		bank.removeHolder(person);
	}
	
	/**
	 * @Test
	 * @throws Exception
	 */
	public void addHolder() throws Exception{
		bank.addHolder(person);
		
		bank.removeHolder(person);
	}
	
	/**
	 * @Test
	 * @throws Exception
	 */
	public void removeHolder() throws Exception{
		bank.addHolder(person);
		bank.removeHolder(person);
	}
}
