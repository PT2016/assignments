package account;

import java.io.Serializable;
import java.util.ArrayList;

import user.Person;

public abstract class Account implements Serializable {
	private double money;
	private int months;
	private ArrayList<Person> pers;
	private int ID = 0;
	private Person mainHolder;

	public Account(int ID, Person p, int months, int money) {
		pers = new ArrayList<Person>();
		this.ID = ID;
		this.money = months;
		this.money = money;
		pers.add(p);
		setMainHolder(p);
	}

	
	public void setMainHolder(Person p) {
		mainHolder = p;
	}

	public Person getMainHolder() {
		return mainHolder;
	}

	public void addPers(Person p) {
		pers.add(p);
	}

	public void deletePers(Person p) {
		pers.remove(p);
		setMainHolder(pers.get(0));
	}

	public ArrayList<Person> getPerson() {
		return pers;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getID() {
		return ID;
	}

	
	public void setMoney(double d) {
		this.money = d;
	}

	public double getMoney() {
		return money;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getMonths() {
		return months;
	}

	public abstract void addMoney(double s);

	public abstract int withdrawMoney(double s);

	public abstract double getTotalSum();
	
	public abstract String type();
}
