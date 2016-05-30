package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.UUID;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import utilities.AccountType;
import utilities.InsufficientMoney;

public abstract class Account extends Observable implements Serializable{

	private String accountNr;
	private AccountType accountType;
	private double balance;
	private double interestRate;

	private StringBuffer accountStatement;
	private int nrDeposits;
	private int nrWithdrawals;

	private Calendar accountCreationDate = Calendar.getInstance();

	public Account(double balance) {

		this.accountNr = UUID.randomUUID().toString();

		this.setBalance(balance);

		this.accountCreationDate.setTimeInMillis(System.currentTimeMillis());
		this.accountStatement = new StringBuffer();
		
		Date date = accountCreationDate.getTime();
		SimpleDateFormat ft = new SimpleDateFormat("MM-dd-YYYY");
		accountStatement.append("\nAccount was created on: " + ft.format(date) + "\n");
	}

	public String getAccountNr() {
		return accountNr;
	}

	public void setAccountNr(String accountNr) {
		this.accountNr = accountNr;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getNrDeposits() {
		return nrDeposits;
	}

	public void setNrDeposits(int nrDeposits) {
		this.nrDeposits = nrDeposits;
	}

	public int getNrWithdrawals() {
		return nrWithdrawals;
	}

	public void setNrWithdrawals(int nrWithdrawals) {
		this.nrWithdrawals = nrWithdrawals;
	}

	public Calendar getAccountCreationDate() {
		return accountCreationDate;
	}

	public abstract String toString();

	public abstract void withdraw(double amount) throws InsufficientMoney;

	public abstract void deposit(double amount);

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public StringBuffer getAccountStatement() {
		return accountStatement;
	}

	public void setAccountStatement(StringBuffer accountStatement) {
		this.accountStatement = accountStatement;
	}

	public void generateAccountStatement() {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AccountStatement.pdf"));

			document.open();
			
			document.add(new Paragraph("ACCOUNT STATEMENT"));
			document.add(new Paragraph(new Date().toString()));
			document.add(new Paragraph(accountStatement.toString()));
			document.close();

			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
