package model;

public abstract class Account implements java.io.Serializable  {
	

	protected Person person;
	protected int accountId;
	protected double moneyAmount;
	protected String accountName;
	
	public Person getPerson(){
		return person;
	}
	
	public int getAccountId(){
		return accountId;
	}
	
	public double getMoneyAmount(){
		return moneyAmount;
	}

	public String getAcountName(){
		return accountName;
	}
	
	
	public abstract double depositMoney(double Amount);
	public abstract double extractMoney(double Amount);
	public abstract boolean correctParam();
		
	
	
}
