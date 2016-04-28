package models;

import java.util.Date;

public class SavingsAccount extends Account {

	private Date lastDayWhenInterestWasCalculated = new Date();
	
	public SavingsAccount(int money) throws NotEnoughFundsException {
		super(money, AccountType.SAVINGSACCOUNT);
		if (money < Bank.minAmount)
			throw new NotEnoughFundsException("Need more money to begin with");
	}

	public void calculateInterest(long days) {
		money = money * Math.pow((1 + Bank.interest), days);
		lastDayWhenInterestWasCalculated = new Date();
		setChanged();
		notifyObservers(this);
		userReport += "\nInterest calculated. Now, the balance is: " + money + ", on" + new Date();
	}

	public void withdraw(double amount) throws NotEnoughFundsException {
		double a = money - amount * (1 + Bank.comission);
		if (a >= Bank.minAmount)
			money = a;
		else
			throw new NotEnoughFundsException("Not enough funds");
		setChanged();
		notifyObservers(this);
		userReport += "\nMoney withdrawn: " + amount + ", with comission: " + Bank.comission * amount + ", on: "
				+ new Date();
	}

	public void deposit(double amount) {
		money = money + amount;
		setChanged();
		notifyObservers(this);
		userReport += "\nMoney deposited: " + amount + ", on: " + new Date();
	}

	public Date getLastDayWhenInterestWasCalculated() {
		return lastDayWhenInterestWasCalculated;
	}
	
}
