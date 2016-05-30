package models;

public abstract class Account {
	
	private double money;
	
	public Account(double money){
		this.money=money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public double getMoney() {
		return money;
	}
	
	public abstract int getAccountID();
	public abstract double depositMoney(double money);
	public abstract double withdrawMoney(double money);

}
