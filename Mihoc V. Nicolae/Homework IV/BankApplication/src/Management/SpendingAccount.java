package Management;

public class SpendingAccount extends Account{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpendingAccount(int id, int clientID, int balance, String name, String address) {
		super(id, clientID, balance, name, address);
	}
	
	public String toString()
	{
		return "Saving Account";
	}

}