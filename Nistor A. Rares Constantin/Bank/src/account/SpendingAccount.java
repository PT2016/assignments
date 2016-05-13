package account;

import java.io.Serializable;

import user.Person;

public class SpendingAccount extends Account implements Serializable{

	private double commission = 0.02;

	public SpendingAccount(int ID,Person pers, int months, int money) {
		super(ID,pers, months, money);
	}

	public void addMoney(double s) {
		setMoney(getTotalSum() + s);
	}

	public int withdrawMoney(double s) {
		setMoney( getTotalSum() );
		if( getMoney() >= s ){
			setMoney( getTotalSum() - s);
			return 1;
		}
		else 
			return 2;
	}

	public double getTotalSum() {
		return (getMoney() - commission * getMoney());
	}

	@Override
	public String type() {
		return "Spending Account";
	}

}
