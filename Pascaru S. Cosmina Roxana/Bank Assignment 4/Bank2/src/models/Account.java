package models;

import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable {

	private int ID;
	private String ownerFirstName;
	private String ownerLastName;
	private String pin;
	protected int total;

	public Account(int ID, String ownerFirstName, String ownerLastName, String pin, int total) {
		this.ID = ID;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.pin = pin;
		this.total = total;
	}

	public int getID() {
		return ID;
	}

	public String getPin() {
		return pin;
	}

	public int getTotal() {
		return total;
	}

	public void changeTotal(int total) {
		this.total = total;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public abstract void deposit(int sum);

	public abstract void withdraw(int sum);

	public String toString() {
		return String.format("Account ID: %d Account total: %d Account PIN: %s\n", ID, total, pin);
	}

	public abstract boolean cannotWithdraw(int sum);

}
