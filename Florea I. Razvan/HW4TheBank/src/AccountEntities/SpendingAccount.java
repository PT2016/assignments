package AccountEntities;

public class SpendingAccount extends Account {

	private static final long serialVersionUID = 7518877457423816922L;
	private static final double MIN_WITHDRAW = 500.0;
	private static final double MIN_ADD = 1000.0;
	
	public SpendingAccount(String ID, String ownerName) {
		super(ID, ownerName);
		setType();
	}

	@Override
	public void addMoney(double sum) {
		assert(sum >= MIN_ADD):"Add more for Spending Account";
	
		double previousMoney = money;
		money += sum;
		
		setChanged();
		notifyObservers("You added " + sum + " in " + ID);
		
		assert(money == (previousMoney + sum)):"Wrong sum after addition";
	}

	@Override
	public void withdrawMoney(double sum) {
		assert((sum >= MIN_WITHDRAW)&&(sum <= money)):"Withdraw more money for Spending Account";
		
		double previousMoney = money;
		money -= sum;
		
		setChanged();
		notifyObservers("You withdrawn " + sum + " from " + ID);
		
		assert(money == (previousMoney - sum)):"Wrong sum after subtraction";
	}

	@Override
	public void setType() {
		type = "spending";
	}
	
	@Override
	public String toString() {
		return "type: " + type + "\nowner: " + ownerName + "\nmoney: " + money +"\n----------------";
	}

}
