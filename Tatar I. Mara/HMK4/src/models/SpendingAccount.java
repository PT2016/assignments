package models;

public class SpendingAccount extends Account{
/**
	 * 
	 */
	private static final long serialVersionUID = 2669450146740551745L;
boolean insufficientFunds=false;
	public SpendingAccount(double money) {
		super(money);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void depositMoney(double money) {
		setAccountSum(getAccountSum()+money);
		setChanged();
		notifyObservers(this);
	}

	@Override
	public void withdrawMoney(double money) {
		if (money>getAccountSum()) {
			insufficientFunds=true;
		}
		else{
			setAccountSum(getAccountSum()-money);
			setChanged();
			notifyObservers(this);
		}
	}
	public void increaseAmount(long months){
		setAccountSum(getAccountSum()+months*Bank.getIncreaseSum());
		setChanged();
		notifyObservers(this);
	}
	public boolean hasInsufficientFunds(){
		return insufficientFunds;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Spending";
	}

	@Override
	public boolean sumIsInsufficient() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setSumIsInsufficient(boolean notSufficient) {
		insufficientFunds=notSufficient;
		
	}

}
