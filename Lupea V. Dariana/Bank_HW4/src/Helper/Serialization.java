package Helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Entities.Bank;

public class Serialization {

	public void SerializeBank(Bank b) {
		try {
			FileOutputStream fileOut = new FileOutputStream("Resources/bank.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(b);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in <<<bankInfo>>>");
		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("Bank cannot be serialized");
		}
	}

	public Bank DeserializeBank() throws ClassNotFoundException {
		Bank b = new Bank();
		try {
			FileInputStream fileIn = new FileInputStream("Resources/bank.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			b = (Bank) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("Bank cannot be deserialized");
		}
		return b;
	}

}
