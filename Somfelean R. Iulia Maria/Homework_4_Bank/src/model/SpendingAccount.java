package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Observer;

import javax.swing.JOptionPane;

import utilities.AccountType;
import utilities.Constants;

public class SpendingAccount extends Account implements Serializable{

	public SpendingAccount( double balance) {

		super( balance);
		this.setAccountType(AccountType.SPENDING);
	}

	public void withdraw(double amount){
		double balance;
		if(!isExpired()){
			if(isActive()){
				balance = super.getBalance() - amount;
				super.setBalance( balance);
			}
			else{
				balance = super.getBalance() - amount - amount*Constants.PROCESSING_FEES_PERCENT_SAVING;
				super.setBalance(balance);
				this.getAccountStatement().append("\n" + new Date() + " : " + balance +" were deposited.\n");
			}
		}
		notifyObservers(this);
	}
	
	public boolean isActive() {

		if (super.getBalance() >= Constants.ACTIVE_ACCOUNT_BALANCE_LIMIT_SAVING) {

			return true;

		} else {
			return false;
		}
	}
	
	public boolean isExpired() {

		Calendar currentTime = Calendar.getInstance();
		currentTime.setTimeInMillis(System.currentTimeMillis());

		if (getAccountCreationDate().get(Calendar.YEAR) - currentTime.get(Calendar.YEAR) > Constants.ACCOUNT_TIME_PERIOD_SPENDING) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Acc.Nr"+this.getAccountNr()+ " Balance:" + this.getBalance();
	}

	@Override
	public void deposit(double amount) {
		double balance;
		if(!isExpired()){
			
				balance = super.getBalance() + amount;
				super.setBalance( balance);
				this.getAccountStatement().append("\n" + new Date() + " : " + balance +" were deposited.\n");
		}
		notifyObservers(this);
	}


}
