package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Observer;

import javax.swing.JOptionPane;

import utilities.AccountType;
import utilities.Constants;
import utilities.InsufficientMoney;

public class SavingAccount extends Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SavingAccount( double balance) {

		super( balance);
		this.setAccountType(AccountType.SAVING);

	}

	public void withdraw(double amount) throws InsufficientMoney {
		double balance;
		if (isActive()) {
			if (isMature()) {
				balance = super.getBalance() - amount;
				if (balance > Constants.ACTIVE_ACCOUNT_BALANCE_LIMIT_SAVING) {
					super.setBalance(balance);
					this.getAccountStatement().append("\n" + new Date() + " : " + amount +" were withdrawed. The current balance is:"+balance+"\n");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Insufficent money!");
					throw new InsufficientMoney("Insufficent money!");
					

				}
			} else {
				JOptionPane.showMessageDialog(null, "Your saving account is not mature yet and withdrawing money will take a higher comission!");
				balance = super.getBalance() - amount - amount * Constants.PROCESSING_FEES_PERCENT_SAVING;
				if (balance > Constants.ACTIVE_ACCOUNT_BALANCE_LIMIT_SAVING) {
					super.setBalance(balance);
					this.getAccountStatement().append("\n" + new Date() + " : " + (amount + amount * Constants.PROCESSING_FEES_PERCENT_SAVING) +" were withdrawed, processing fees were applied. The current balance is:"+balance+"\n");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Your saving account is inactive");
		}
		
		notifyObservers(this);
	}

	public boolean isMature() {

		Calendar currentTime = Calendar.getInstance();
		currentTime.setTimeInMillis(System.currentTimeMillis());

		if (getAccountCreationDate().get(Calendar.YEAR)
				- currentTime.get(Calendar.YEAR) > Constants.ACTIVE_ACCOUNT_BALANCE_LIMIT_SAVING) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isActive() {

		if (super.getBalance() >= Constants.ACTIVE_ACCOUNT_BALANCE_LIMIT_SAVING) {

			return true;

		} else {
			return false;
		}
	}

	@Override
	public String toString() {

		return "Acc.Nr: "+this.getAccountNr()+ " Balance:" + this.getBalance();
	}

	@Override
	public void deposit(double amount) {
		double balance;
		
			
				balance = super.getBalance() + amount;
				if (balance > Constants.ACTIVE_ACCOUNT_BALANCE_LIMIT_SAVING) {
					super.setBalance(balance);
					this.getAccountStatement().append("\n" + new Date() + " : " + amount +" were deposited. The current balance is:"+balance+"\n");
				}
				else{
				
					JOptionPane.showMessageDialog(null, "You need to deposit more money to make your account active again!");
					
				}
		
		notifyObservers(this);
	}
	
	public void computeInterest(){
		/*
		if(this.isActive()){
			double newBalance = this.getBalance() + this.getBalance()*Constants.INTEREST_RATE_PERCENT_SAVING/100;
			Calendar currentDate = Calendar.getInstance();
			currentDate.setTimeInMillis(System.currentTimeMillis());
			if(this.getAccountCreationDate().YEAR - currentDate.YEAR > 0)
				
		}*/
	}
	



}
