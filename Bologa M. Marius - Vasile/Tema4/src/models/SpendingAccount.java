package models;

import java.util.Date;

public class SpendingAccount extends Account {
	private static final long serialVersionUID = 1L;

	public SpendingAccount(double sum, Person p, String date, String type) {
		super(sum, p, date, type);
	}

	@Override
	public void depositMoney(double s) {
		this.sum = this.sum + s;
		setChanged();
		notifyObservers(s);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void withdrawMoney(double s) {
		if (sum >= s) {
			this.sum = this.sum - s - s * (0.01 * (new Date().getMonth()));
			setChanged();
			notifyObservers(s);
		}
	}

}
