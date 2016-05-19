package models;

public class SavingAccount extends Account {
	/**
		 * 
		 */
	private static final long serialVersionUID = -4445265741026183528L;
	private boolean insufficientStartSum = false;

	public SavingAccount(double money) {
		super(money);
		if (money < Bank.getMinStartSum())
			insufficientStartSum = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void depositMoney(double money) {
		setAccountSum(getAccountSum() + money);
		setChanged();
		notifyObservers(this);
		// TODO Auto-generated method stub

	}

	@Override
	public void withdrawMoney(double money1) {
		if (getAccountSum() - money1 - money1 * Bank.getComission() > Bank.getMinStartSum()) {
			setAccountSum(getAccountSum() - money1 - money1 * Bank.getComission());
			setChanged();
			notifyObservers(this);
		} else
			insufficientStartSum = true;
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Saving";
	}

	@Override
	public boolean sumIsInsufficient() {
		// TODO Auto-generated method stub
		return insufficientStartSum;
	}

	@Override
	public void setSumIsInsufficient(boolean notSufficient) {
		insufficientStartSum = notSufficient;

	}

}
