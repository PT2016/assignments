package Entities;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public SavingAccount(int accountID, double money) {
		super(accountID, money);
	}

	public void addMoney(double sum) {
		this.money += sum;
		setChanged();
		notifyObservers("The sum " + sum + " was added to your saving account");
	}

	public void withdrawMoney(double sum) {
		if (sum < 200 && this.money > 0) {
			this.money -= sum;
			setChanged();
			notifyObservers("The sum " + sum + " was withdrawn from your spending account");
		} else {
			setChanged();
			notifyObservers("Sorry, this sum cannot be withdrawn");
			System.out.println("Sorry, this sum cannot be withdrawn");
		}

	}
}
