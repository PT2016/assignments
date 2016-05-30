package main;

import model.Account;
import model.Person;

public class SpendingAccount extends Account {
	private double commission= 1.5;

	public SpendingAccount(Person person, int accountId, double moneyAmount){
		
		super.accountId=accountId;
		super.person=person;
		super.accountName="Spending Account";
		super.moneyAmount=moneyAmount;
		}
	@Override
	public double depositMoney(double Amount) {
		// TODO Auto-generated method stub
		System.out.println(moneyAmount);
		moneyAmount=moneyAmount + Amount- commission;
		Amount=0;
		
		System.out.println(moneyAmount);
		
		return moneyAmount;
		
	}

	@Override
	public double extractMoney(double Amount) {
		// TODO Auto-generated method stub
		
		if(moneyAmount< Amount)
			return -1;
		else 
			moneyAmount= moneyAmount-Amount;
			return moneyAmount;
	}

	@Override
	public boolean correctParam() {
		if(person.correctParam() || accountId !=0 || accountName!= "Spending Account")
			return true;
		else
			return false;
	}
	

}
