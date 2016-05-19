package pt.ObserverBank.gui;
import pt.ObserverBank.basicObjects.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class SerializeBank {
  private Bank bank;
  
	public void writeBank(Bank bank) {
		try (FileOutputStream fs = new FileOutputStream("bank.bin")) {
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(bank);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readBank() {
		try (FileInputStream fs = new FileInputStream("bank.bin")) {
			ObjectInputStream os = new ObjectInputStream(fs);
	        bank=(Bank)os.readObject();
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * @return the bank
 */
public Bank getBank() {
	return bank;
}
}
