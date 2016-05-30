package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import model.Account;
import model.Bank;
import model.Person;

public class InputOutput {
	private FileOutputStream fileOut;
	private ObjectOutputStream out;
	private FileInputStream fileIn;
	private ObjectInputStream in;
	private Bank bank;
	private HashMap<Person, ArrayList<Account>> bankSet;

	public InputOutput() {
	}

	public void serializeBank() {
		try {
			bank = Bank.getInstance();
			fileOut = new FileOutputStream("D:\\Java workspace\\Howework4\\Bank.ser");
			out = new ObjectOutputStream(fileOut);
			bankSet = bank.getBankContent();
			out.writeObject(bankSet);
			fileOut.close();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			e.getMessage();
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap<Person, ArrayList<Account>> deserializeBank() {
		HashMap<Person, ArrayList<Account>> bankRecover = new HashMap<Person, ArrayList<Account>>();
		try {
			fileIn = new FileInputStream("D:\\Java workspace\\Howework4\\Bank.ser");
			in = new ObjectInputStream(fileIn);
			try {
				bankRecover = (HashMap<Person, ArrayList<Account>>) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return bankRecover;
	}
}
