package main;

import models.Account;
import models.Bank;
import models.Person;

public class MainClass {

	public static void main(String[] args) {
		Bank bank = new Bank();

		bank.serialize();

		Bank newBank = new Bank();
		newBank = bank.deserialize();
		System.out.println(newBank);
		LoginFrame frame = new LoginFrame(newBank);

	}

}
