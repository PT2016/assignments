package tes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import account.Account;

import account.SavingAccount;
import account.SpendingAccount;
import bank.Bank;
import user.Person;

public class Test {
	public static void main(String[] args) {
		Person p1 = new Person("Corneliu","120");
		Person p2 = new Person("Teodor","121");
		Person p3 = new Person("Vasile","122");
		Person q=null;
		Account a = new SavingAccount(0,p1,2, 100);
		Account b = new SavingAccount(1,p2,2, 200);
		Account c = new SpendingAccount(1,p3,0, 300);
		Account d = new SpendingAccount(1,p1,0, 400);
		a.addPers(p3);
		b.addPers(p3);
		c.addPers(p2);
		Account z=null;
		Bank ban = new Bank();
		ban.addAccount(a);
		ban.addAccount(b);
		ban.addAccount(c);
		ban.addAccount(d);
//		ban.removePerson(p1, d);
//		ban.removeHolder(p1);
//		ban.removePerson(q, z);
//		ban.addPerson(q, a);
//		ban.removeAccount(a);
//		ban.addPerson(q, a);
//		ban.removeAccount(z);
//		ban.addAccount(z);
		ban.print();
//		ban.ceva();

		 try
	      {
	         FileOutputStream fileOut =new FileOutputStream("bank.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(ban);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in bank.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
}
