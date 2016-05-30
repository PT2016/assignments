package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import utilities.AccountType;
import utilities.Constants;
import utilities.IllegalProcedureException;

public class Bank implements BankProc, Serializable, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Hashtable<Person, ArrayList<Account>> bankData;
	private StringBuffer bankStatement;

	public Bank() {
		setBankData(new Hashtable<Person, ArrayList<Account>>());
		bankStatement = new StringBuffer("Bank Report");
	}

	public Hashtable<Person, ArrayList<Account>> getBankData() {
		return bankData;
	}

	public void setBankData(Hashtable<Person, ArrayList<Account>> hashtable) {
		this.bankData = hashtable;
	}

	public void addPerson(Person person) throws IllegalProcedureException {
		
		assert isWellFormed();
		assert person!=null;
		assert !bankData.containsKey(person);
		int size = bankData.size();
		
		for (Person p : bankData.keySet())
			if (p.equals(person))
				throw new IllegalProcedureException("The person was already added!");
	

		bankData.put(person, new ArrayList<Account>());

		this.readAccountsData();

		assert bankData.size() == size+1;
		assert bankData.get(person) != null;
		assert isWellFormed();
	}

	public void removePerson(Person person) throws IllegalProcedureException {
		
		assert isWellFormed();
		assert person!= null;
		assert bankData.containsKey(person);
		
		int size = bankData.size();
		boolean found = false;

		for (Person p : bankData.keySet())
			if (p.equals(person)) {
				found = true;
				break;
			}

		if (!found)
			throw new IllegalProcedureException("The person was not found!");
		else {
			bankData.remove(person);
			this.readAccountsData();
		}

		assert bankData.size() == size -1;
		assert !bankData.contains(person);
		assert isWellFormed();
	}

	public void addAccount(Person person, Account account) throws IllegalProcedureException {

		assert isWellFormed();
		assert person != null;
		assert account != null;
		assert bankData.get(person) != null;

		int size = bankData.get(person).size();

		if (bankData.containsKey(person))
			bankData.get(person).add(account);
		else
			throw new IllegalProcedureException("Person not found!");

		this.readAccountsData();
		this.bankStatement.append("\nAccount : " + account + " was added with the owner: " + person + "\n");

		assert bankData.get(person).size() == size + 1;
		assert !(bankData.get(person).isEmpty());
		assert isWellFormed();

	}

	public void removeAccount(Person person, Account account) throws IllegalProcedureException {

		assert isWellFormed();
		assert person != null;
		assert account != null;
		assert bankData.get(person) != null;

		int size = bankData.get(person).size();
		
		if (bankData.containsKey(person))
			bankData.get(person).remove(account);
		else
			throw new IllegalProcedureException("Person not found!");

		this.readAccountsData();
		this.bankStatement.append("\nAccount : " + account + " of the person " + person + " was removed\n");
	

		assert bankData.get(person).size() == size - 1;
		assert isWellFormed();

	}

	public void readAccountsData() {
		try {
			FileOutputStream fileOut = new FileOutputStream("bank.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("\nSerialized data is saved in bank.ser\n");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Bank writeAccountsData() {
		Bank bank = null;
		try {
			FileInputStream fileIn = new FileInputStream("bank.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			bank = (Bank) in.readObject();
			in.close();
			fileIn.close();
			System.out.printf("\nDesearialize\n");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Bank class not found!");
			System.out.println(e.getMessage());

		}

		// for (Person person : bank.getBankData().keySet())
		// for (Account account : bank.getBankData().get(person))
		// account.addObserver(bank);
		;
		return bank;
	}

	public void update(Observable arg0, Object arg1) {

		bankStatement.append("NOTIFIER: an operation has been on account: " + ((Account) arg0).getAccountNr()
				+ ". The current balance is:" + ((Account) arg0).getBalance());
		this.readAccountsData();
	}

	public StringBuffer getBankStatement() {
		return bankStatement;
	}

	public void setBankStatement(StringBuffer bankStatement) {
		this.bankStatement = bankStatement;
	}

	public String toString() {
		String s = "";
		for (Person p : bankData.keySet()) {
			s += "person " + p + " with accounts: " + bankData.get(p) + "\n";
		}
		return s;
	}

	@Override
	public void generateStatement() {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("BankStatement.pdf"));

			document.open();

			document.add(new Paragraph("BANK REPORT"));
			document.add(new Paragraph(new Date().toString()));
			document.add(new Paragraph(bankStatement.toString()));
			document.close();

			writer.close();

			JOptionPane.showMessageDialog(null, "Bank statement generated with succes!");

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Person getPersonBySsid(String ssid) {

		for (Person p : bankData.keySet()) {
			if (p.getSsid().equals(ssid)) {
				return p;
			}
		}
		return null;
	}

	public Account getAccountByNr(String accNo) {

		System.out.println("ACCNO" + accNo);
		for (Person person : bankData.keySet()) {
			for (Account account : bankData.get(person)) {

				if (account.getAccountNr().equals(accNo)) {
					System.out.println("ACC" + account.toString());
					return account;
				}

			}
		}
		return null;
	}
	
	public Person getPersonByAccount(String accNo) {

		for (Person person : bankData.keySet()) {
			for (Account account : bankData.get(person)) {

				if (account.getAccountNr().equals(accNo)) {
					return person;
				}

			}
		}
		return null;
	}
	
	/**
	 * @invariant isWellFormed()
	 */
	public boolean isWellFormed() {
		for (Person person : bankData.keySet()) {
			for (Account account : bankData.get(person)) {
				if (account.getBalance() < 0)
					return false;
				if(account.getAccountType().equals(AccountType.SAVING) && account.getBalance() < Constants.ACTIVE_ACCOUNT_BALANCE_LIMIT_SAVING)
					return false;
				if(account.getAccountType().equals(AccountType.SPENDING) && account.getBalance() < Constants.ACTIVE_ACCOUNT_BALANCE_LIMIT_SPENDING)
					return false;

			}
		}
		return true;
	}

}
