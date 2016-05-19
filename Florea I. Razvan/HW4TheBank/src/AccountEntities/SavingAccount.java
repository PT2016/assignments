package AccountEntities;

public class SavingAccount extends Account {

	private static final long serialVersionUID = -1197249545293894998L;
	private static final double MIN_ADD = 3000.0;
	private static final double MAX_WITHDRAW = 2000.0;
	
	public SavingAccount(String ID, String ownerName) {
		super(ID, ownerName);
		setType();
	}

	@Override
	public void addMoney(double sum) {
		assert(sum >= MIN_ADD):"Add more money for Saving Account";
		
		double previousMoney = money;
		money += sum;
		
		setChanged();
		notifyObservers("You added " + sum + " in " + ID);
		
		assert(money == (previousMoney + sum)):"Wrong sum after addition";
	}

	@Override
	public void withdrawMoney(double sum) {
		assert((sum <= MAX_WITHDRAW)&&(sum > 0.0)):"Withdraw more money from Saving Account";
		
		double previousMoney = money;
		money -= sum;
		
		setChanged();
		notifyObservers("You withdrawn " + sum + " from " + ID);
		
		assert(money == (previousMoney - sum)):"Wrong sum after subtraction";
	}

	@Override
	public void setType() {
		type = "saving";
		
	}
	
	@Override
	public String toString() {
		return "type: "+ type + "\nowner: " + ownerName + "\nmoney: " + money +"\n----------------";
	}

	

}
