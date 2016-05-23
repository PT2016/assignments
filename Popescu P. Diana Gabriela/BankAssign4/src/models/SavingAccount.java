package models;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final double MAX_WITHDRAW = 100;
	private static final double BANK_INTEREST = 0.1 / 100;

	public SavingAccount(String ID, double money) {
		super(ID, money, "SAVING");
	}

	@Override
	public void deposit(double money) {
		if (money < 500)
			money -= money * BANK_INTEREST;
		this.money += money;
		this.setChanged();
		this.notifyObservers(money);
	}

	@Override
	public boolean withdraw(double money) {
		if (this.money < money || money > MAX_WITHDRAW)
			return false;
		if (money < 500)
			money += money * BANK_INTEREST * 10; //1%
		this.money -= money;
		this.setChanged();
		this.notifyObservers(money);
		return true;
	}

}
