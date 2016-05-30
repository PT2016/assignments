package pt.ObserverBank.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.ObserverBank.basicObjects.*;

public class TestBank {

	/**
     * Test of addPersont method, of class Bank.
     */
    @Test
    public void testAddPerson() {
        Bank bank = new Bank();
        bank.addPerson(-1, "Nume");
        assertEquals(bank.getDatabase().size(), 1);
    }

    @Test
    public void testAddPersonNoPersons() {
        Bank bank = new Bank();
        assertEquals(bank.getDatabase().size(), 0);
    }

    /**
     * Test of deletePerson method, of class Bank.
     */
    @Test
    public void testDeletePerson() {
        Bank bank = new Bank();
        bank.addPerson(-10, "Name");
        bank.removePerson(-10, "Name");
        assertEquals(bank.getDatabase().size(),0);
    }
    /**
     * Test add Account
     */
    @Test
    public void testAddAccount(){}
    /**
     * Test remove Account
     */
    @Test
    public void testRemoveAccount(){}
    /**
     * Test of withdraw method, of class Bank.
     */
    @Test
    public void testWithdraw() {
        Bank bank = new Bank();
        bank.addPerson(0, "name");
        Person p=new Person(0,"name");
        bank.addAccount(p, 300.0, AccountType.SAVINGS);
        bank.withdraw(p, 0,100.0);
        Account a=bank.getAccount(p,0);
        assertEquals(new Double(a.inquire()),new Double(200.0));
    }

   /**
    * Test deposit method
    */
    @Test
    public void testDeposit(){
    	Bank bank = new Bank();
        bank.addPerson(0, "name");
        Person p=new Person(0,"name");
        bank.addAccount(p, 300.0, AccountType.SAVINGS);
        bank.deposit(p, 0,100.0);
        Account a=bank.getAccount(p,0);
        assertEquals(new Double(a.inquire()),new Double(400.0));
    }
    @Test
    public void testGetAccountNoAccount() {
        Bank bank = new Bank();
        assertNull(bank.getDatabase().get(new Person(12,"name")));
    }

    /*@Test
    public void testApplyInterestWithSavingAcc() {

    }

    @Test
    public void testApplyInterestWithSpendingAcc() {
   
    }*/
}
