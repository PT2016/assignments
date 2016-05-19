package models;

public class SavingsAccount extends Account {

	public SavingsAccount(int ID, String ownerFirstName, String ownerLastName, String pin, int total) {
		super(ID, ownerFirstName, ownerLastName, pin, total);
		super.changeTotal(30000);
	}

	@Override
	public void deposit(int sum) {
		total += sum;
		notifyObservers("Changed sum");
	}

	@Override
	public void withdraw(int sum) {
		total -= sum;
		notifyObservers("Changed sum");
	}

	public boolean cannotWithdraw(int sum) {
		if (total - sum < 30000 || total - sum < 0) {
			return false;
		} else {
			return true;
		}
	}

}
