package models;

import java.util.Random;

public class SavingAccount extends Account {

	private double comision = 0.3;
	private int accountID;
	
	public SavingAccount(double money){
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
		if((funds-money-money*comision)<0){
			return -1;
		}else{
			setMoney(funds-money-money*comision);
			return funds-money-money*comision;
		}
	}
	
	public int getAccountID() {
		return accountID;
	}
	
}
