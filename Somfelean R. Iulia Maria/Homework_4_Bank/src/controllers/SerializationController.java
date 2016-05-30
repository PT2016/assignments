package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import model.Bank;



public class SerializationController {

	public static void serializeBankData(Bank bank) {
		try {
			FileOutputStream fileOut = new FileOutputStream("bank.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(bank);
			out.close();
			fileOut.close();
			System.out.printf("\nSerialized data is saved in bank.ser\n");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static Bank deserializeBankData() {
		Bank bank = null;
		try {
			FileInputStream fileIn = new FileInputStream("bank.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			bank = (Bank) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Bank class not found!");
			System.out.println(e.getMessage());

		}
		
		return bank;

	}


}
