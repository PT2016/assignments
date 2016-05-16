package models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.UUID;

public abstract class Account extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5815152608580337260L;
	private String accountId;
	private double money;
	private Date creationDate;
	private Date validUntilDate;
	
	public Account(double money){
		accountId=UUID.randomUUID().toString();
		this.money=money;
		creationDate=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(creationDate);
		calendar.add(Calendar.DATE,3*365);
		validUntilDate=calendar.getTime();
		
	}
	
	public String getAccountID(){
		return accountId;
	}
	public void setAccountSum(double money){
		this.money=money;
	}
	public double getAccountSum(){
		return money;
	}
	public Date getcreationDate(){
		return creationDate;
	}
	public Date getValidUntilDate(){
		return validUntilDate;
	}
	public abstract String getType();
	public abstract boolean sumIsInsufficient();
	public abstract void setSumIsInsufficient(boolean notSufficient);
	public abstract void depositMoney(double money);
	public abstract void withdrawMoney(double money);
	public String toString(){
		return this.accountId+" "+this.money+" "+String.format(" %tdMonth dd, CCYY ",this.creationDate )+" "+String.format(" %tdMonth dd, CCYY ", this.validUntilDate);
	}
	
}
