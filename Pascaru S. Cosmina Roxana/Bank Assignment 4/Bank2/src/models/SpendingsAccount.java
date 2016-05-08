package models;

public class SpendingsAccount extends Account {

	public SpendingsAccount(int ID, String ownerFirstName, String ownerLastName, String pin, int total) {
		super(ID, ownerFirstName, ownerLastName, pin, total);
	}

	@Override
	public void deposit(int sum) {
		total += sum;

	}

	@Override
	public void withdraw(int sum) {
		total -= sum;
	}

	public boolean cannotWithdraw(int sum) {
		if (total - sum < 0) {
			return false;
		} else {
			return true;
		}
	}

}
