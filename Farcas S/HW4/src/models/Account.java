package models;

import java.util.UUID;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable {

	protected String id;
	protected Date dateOpened;
	protected double money;
	protected AccountType type;
	protected Date expireDate;
	protected String userReport;

	public Account(int money, AccountType type){
		id = UUID.randomUUID().toString();
		dateOpened = new Date();
		this.money = money;
		this.type = type;
		Calendar c = Calendar.getInstance();
		c.setTime(dateOpened);
		c.add(Calendar.DATE, 30);
		expireDate = c.getTime();
		userReport = "Account with ID " + id;
	}

	public abstract void deposit(double amount);

	public abstract void withdraw(double amount) throws NotEnoughFundsException;
	
	
	public void generateReportHolder(){
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("UserReport.pdf"));

			document.open();
			document.add(new Paragraph(userReport));
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public double getMoney() {
		return money;
	}

	public AccountType getType() {
		return type;
	}
	
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String toString() {
		return this.id + ", " + String.format("%tD", this.dateOpened) + ", " + this.money + ", " + this.type + ", "
				+ String.format("%tD", this.expireDate);
	}
}
