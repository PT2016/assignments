package Utilities;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import AccountEntities.Account;
import UserEntities.Person;

public class Helper {

	private Random random;
	
	public Helper() {
		random = new Random();
	}
	
	public String generateAccountID (String clientName) {
		
		String id = String.valueOf(random.nextInt(899) + 100);
		id += clientName;
		id += String.valueOf(random.nextInt(899) + 100);
		
		return id;
	}
	
	public void updateSerFile(Map<Person, List<Account>> dataSet, String fileName) {
		try {

			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			
				oos.writeObject(dataSet);

			oos.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error: creating the file" + fileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: writing in the file" + fileName);
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readFromSerFile(Map<Person, List<Account>> dataSet, String fileName) {
		try {

			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);

			try {
				while (true) {
					dataSet.putAll((Map<Person, List<Account>>) ois.readObject());
				}
			} catch (EOFException e) {
				System.out.println("\n********************************\nAll data read from file " + fileName
						+ "\n********************************\n");
			} finally {
				ois.close();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: creating the file " + fileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: writing in the file " + fileName);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error: class not found for file " + fileName);
			e.printStackTrace();
		}
	}
}
