package models;

import java.util.Date;

public class SavingAccount extends Account {
	private static final long serialVersionUID = 1L;

	public SavingAccount(double sum, Person p, String date, String type) {
		super(sum, p, date, type);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void depositMoney(double s) {
		this.sum = this.sum + s + s * (0.01 * (new Date().getMonth()));
		setChanged();
		notifyObservers(s);

	}

	@Override
	public void withdrawMoney(double s) {
		if (sum >= s) {
			this.sum = this.sum - s;
			setChanged();
			notifyObservers(s);
		} else {
			System.out.println("Not enugh founds");
		}

	}

}
