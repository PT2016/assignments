package models;

import java.util.Date;

public class SpendingsAccount extends Account {
	
	public SpendingsAccount(int money){
		super(money, AccountType.SPENDINGSACCOUNT);
	}
	
	public void deposit(double amount){
		money = money + amount;
		setChanged();
		notifyObservers(this);
		userReport += "\n" + "Money deposited: " + amount + ", on: " + new Date();
	}
	
	public void withdraw(double amount) throws NotEnoughFundsException{
		double a = money - amount;
		if (a >= 0)
			money = money - amount;
		else
			throw new NotEnoughFundsException("Not enough funds");
		setChanged();
		notifyObservers(this);
		userReport += "\n" + "Money withdrawn: " + amount + ", on: "
				+ new Date();
	}
	
}
