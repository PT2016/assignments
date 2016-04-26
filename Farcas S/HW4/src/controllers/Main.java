package controllers;

import models.*;
import views.*;

public class Main {
	public static void main(String[] args) {
//		Bank b = Bank.deserialize();
//		Person p = new Person("Vlad", 21);
//		Account a = null;
//		try {
//			a = new SavingsAccount(400);
//		} catch (NotEnoughFundsException e1) {
//			e1.printStackTrace();
//		}
//		a.addObserver(b);
//		try {
//			b.addPerson(p);
//			b.addAccount(p, a);
//		} catch (IllegalOperationException e) {
//			e.printStackTrace();
//		}
//		a.deposit(200);
//		b.serialize();
//		System.out.println(b);
//		System.out.println(b.readAccountInfo(a));
		Bank.deserialize();
		new LogInController(new LogIn("Bank"), false);
	}
}
