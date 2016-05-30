package models;

import java.util.Random;

public class SpendingAccount extends Account {
	
	private int accountID;
	
	public SpendingAccount(double money){
		super(money);
		Random r = new Random();
		accountID=r.nextInt(3000)+100;
	}
	
	public double depositMoney(double money){
		double amount=getMoney()+money;
		setMoney(getMoney()+money);
		return amount;
	}
	
	public double withdrawMoney(double money){
		double funds=getMoney();
		if((funds-money)<0){
			return -1;
		}else{
			setMoney(funds-money);
			return funds-money;
		}
	}
	
	public int getAccountID() {
		return accountID;
	}

}
