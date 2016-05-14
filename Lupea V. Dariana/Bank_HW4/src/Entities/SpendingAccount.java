package Entities;

import java.io.Serializable;

public class SpendingAccount extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public SpendingAccount(int accountID, double money) {
		super(accountID, money);
	}

	public void addMoney(double sum) {
		this.money += sum;
		setChanged();
		notifyObservers("The sum " + sum + " was added to your spending account");
	}

	public void withdrawMoney(double sum) {
		if (sum < 1000 && this.money > -1000) {
			this.money -= sum;
			setChanged();
			notifyObservers("The sum " + sum + " was withdrawn from your spending account");
		} else {
			System.out.println("Sorry, this sum cannot be withdrawn");
			notifyObservers("Sorry, this sum cannot be withdrawn");
		}
	}
}
