package TestUnit;

import org.junit.Test;

import Bank.Bank;
import Management.Account;
import Management.HashTable;

import static org.junit.Assert.*;


public class JUnitTest {
    Bank bank=new Bank();
    HashTable hash=new HashTable(9973);
    
    Account acc=new Account(15,5,500,"Mihoc Nic","Str Observatorului 34");
    

   @Test
    public void testHashFunction() {
        int i = 15;
        assertEquals(i, acc.hashFunction());
    }

    @Test
    public void testAdd() {
        boolean ok = true;
        assertEquals(bank.addAccount(acc), ok);
    }
    
    @Test
    public void testRemove(){
    	boolean ok=false;
    	assertEquals(bank.removeAccount(acc),ok);
    }

    @Test
    public void testIsWellFormed() {
        boolean ok = true;
        assertEquals(hash.isWellFormed(), ok);
    }
    
    @Test
    public void testfindAccount(){
    	Object ok=null;;
    	assertEquals(bank.findAccount(acc),ok);
    }
}

