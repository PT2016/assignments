package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Bank implements BankProc, Serializable, Observer {

	public static Bank instance = null;
	public static double interest = 0.07;
	public static double comission = 0.01;
	public static double minAmount = 300;
	public static String admin = "vlad";
	public String adminReport = "Admin Report";

	private HashMap<Person, ArrayList<Account>> info = new HashMap<>();

	private Bank() {

	}

	public static Bank getInstance() {
		if (instance == null)
			instance = new Bank();
		return instance;
	}

	@Override
	public void addPerson(Person p) throws IllegalOperationException {
		assert isWellFormed();
		assert p != null;
		int size = info.size();
		for (Person pp : info.keySet())
			if (p.equals(pp))
				throw new IllegalOperationException("Person already there");
		info.put(p, new ArrayList<Account>());
		serialize();
		adminReport += "\nPerson was added: " + p;
		assert info.size() == size + 1;
		assert info.get(p) != null;
		assert isWellFormed();
	}

	@Override
	public void removePerson(Person p) throws IllegalOperationException {
		assert isWellFormed();
		assert p != null;
		assert info.containsKey(p);
		int size = info.size();
		boolean notHere = true;
		for (Person pp : info.keySet())
			if (p.equals(pp)) {
				notHere = false;
				break;
			}
		if (notHere == true)
			throw new IllegalOperationException("Person not there");
		info.remove(p);
		serialize();
		adminReport += "\nPerson was removed: " + p;
		assert !(info.containsKey(p));
		assert info.size() == size - 1;
		assert isWellFormed();
	}

	@Override
	public void addAccount(Person p, Account account) throws IllegalOperationException {
		assert isWellFormed();
		assert p != null;
		assert account != null;
		assert info.get(p) != null;
		int size = info.get(p).size();
		if (info.containsKey(p))
			info.get(p).add(account);
		else
			throw new IllegalOperationException("No such person");
		serialize();
		adminReport += "\nAccount : " + account + " was added for person: " + p;
		assert info.get(p).size() == size + 1;
		assert !(info.get(p).isEmpty());
		assert isWellFormed();
	}

	@Override
	public void removeAccount(Person p, Account account) throws IllegalOperationException {
		assert isWellFormed();
		assert p != null;
		assert account != null;
		assert info.get(p) != null;
		int size = info.get(p).size();
		if (info.containsKey(p))
			info.get(p).remove(account);
		else
			throw new IllegalOperationException("No such person");
		serialize();
		adminReport += "\nAccount : " + account + " was removed for person: " + p;
		assert info.get(p).size() == size - 1;
		assert isWellFormed();
	}

	@Override
	public void generateReportAdmin() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("AdminReport.pdf"));

			document.open();
			document.add(new Paragraph(adminReport));
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		adminReport += "\n" + "An operation has been done on account: " + ((Account) o).getId()
				+ ", now the balance is: " + ((Account) o).getMoney();
		serialize();
	}

	public void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("bank.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static Bank deserialize() {
		Bank b = null;
		try {
			FileInputStream fileIn = new FileInputStream("bank.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			b = (Bank) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		for (Person p : b.getInfo().keySet())
			for (Account a : b.getInfo().get(p))
				a.addObserver(b);
		instance = b;
		return b;
	}

	@Override
	public String toString() {
		String s = "";
		for (Person p : info.keySet()) {
			s += "person " + p + " with accounts: " + info.get(p) + "\n";
		}
		return s;
	}

	/**
	 * @invariant isWellFormed()
	 */
	public boolean isWellFormed() {
		for (Person p : info.keySet()) {
			if (info.get(p).isEmpty())
				return false;
			for (Account a : info.get(p)) {
				if (a.getDateOpened().after(a.getExpireDate()))
					return false;
				if (a.getType() == AccountType.SAVINGSACCOUNT && a.getMoney() < 300)
					return false;
				if (a.getMoney() < 0)
					return false;
				for (Person pp : info.keySet()) {
					for (Account aa : info.get(pp))
						if (aa.getId().equals(a.getId()) && !(aa.equals(a)))// check
																			// for
																			// uniqueness
							return false;
				}
			}
		}
		return true;
	}

	public HashMap<Person, ArrayList<Account>> getInfo() {
		return info;
	}

}
