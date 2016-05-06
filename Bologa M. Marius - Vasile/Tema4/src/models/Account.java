package models;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

import models.utilities.Utilities;

public abstract class Account extends Observable implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int id;
	protected double sum;
	protected Person p;
	protected String date;
	protected String closeDate;
	protected String type;
	private Random rand = new Random();

	public Account(double sum, Person p, String date, String type) {
		this.id = 100000 + rand.nextInt(900000);
		this.sum = sum;
		this.p = p;
		this.date = date;
		this.type = type;
		this.closeDate = Utilities.getDateRandom(3650);

	}

	public double getSum() {
		return sum;
	}

	public Person getP() {
		return p;
	}

	public String getDate() {
		return date;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public abstract void depositMoney(double parseDouble);

	public abstract void withdrawMoney(double parseDouble);

}
