package main;

import model.Account;
import model.Person;

public class SavingAccount extends Account {
	private double commission=2.75;

	public SavingAccount(Person person, int accountId, double moneyAmount){
		
		super.accountId=accountId;
		super.person=person;
		super.accountName="Saving Account";
		
		super.moneyAmount=moneyAmount;
		}
	
	
	@Override
	public double depositMoney(double Amount) {
		// TODO Auto-generated method stub
		moneyAmount+=Amount;
		
		return moneyAmount;
	}

	@Override
	public double extractMoney(double Amount) {
		// TODO Auto-generated method stub
		if(moneyAmount< Amount)
			return -1;
		else
			moneyAmount=moneyAmount - (Amount+ commission);
			return moneyAmount;
		
	}

	@Override
	public boolean correctParam() {
		
		if(person.correctParam() || accountId !=0 || accountName!= "Saving Account")
			return true;
		else
			return false;
	}
	
	
	

}
