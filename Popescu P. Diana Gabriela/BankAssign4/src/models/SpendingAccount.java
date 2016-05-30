package models;

import java.io.Serializable;

public class SpendingAccount extends Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double BANK_INTEREST = 0.5 / 100;

	public SpendingAccount(String ID, double money) {
	super(ID, money, "SPENDING");
}
	@Override
	public void deposit(double money) {
		this.money += money;
		this.setChanged();
		this.notifyObservers(money);
	}

	@Override
	public boolean withdraw(double money) {
		if (this.money < money)
			return false;
		this.money -= money;
		this.money += money * BANK_INTEREST;
		this.setChanged();
		this.notifyObservers(money);
		return true;
	}

}
