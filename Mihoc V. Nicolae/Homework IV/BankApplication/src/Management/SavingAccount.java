package Management;



public class SavingAccount extends Account{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SavingAccount(int id, int clientID, int balance, String name, String address) {
		super(id, clientID, balance, name, address);
	}
	
	public String toString()
	{
		return "Saving Account";
	}

}
