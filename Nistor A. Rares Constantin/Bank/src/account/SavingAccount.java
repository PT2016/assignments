package account;

import java.io.Serializable;

import user.Person;

public class SavingAccount extends Account implements Serializable{
	
	private double intRate=0.04;
	public SavingAccount(int ID,Person pers, int months, int money){
		super(ID,pers,months,money);
	}
	
	public void addMoney(double s) {
		for(int i = 0; i < getMonths(); i++)
			setMoney( getTotalSum() );
		setMoney( getTotalSum() + s );
	}

	public int withdrawMoney(double s) {
			for(int i = 0; i < getMonths(); i++)
				setMoney( getTotalSum());
			if( getMoney() >= s ){
				setMoney( getMoney() - s);
				return 1;
			}
			else 
				return 2;
		}
	public double getTotalSum() {
		return getMoney()+(getMoney()*intRate);
	}

	public String type() {	
		return "Saving Account";
	}
	

}
