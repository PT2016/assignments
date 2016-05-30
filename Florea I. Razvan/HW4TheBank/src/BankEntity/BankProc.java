package BankEntity;

public interface BankProc {

	/**
	 * @pre (accountType != null) && (name != null) 
	 * @post new Account(accountID)
	 * @invariant isWellFormed() 
	 */
	public void addAccount(String name, String accountType);
	
	/**
	 * @pre (accountID != null) && (Account(accountID) != null) && (name != null) && (Person(name) != null)
	 * @post Account(accountID) == null
	 * @invariant isWellFormed()
	 */
	public void removeAccount(String name, String accountID);
	
	/**
	 * @pre (name != null) && (password != null)
	 * @post new Person(name, password)
	 * @invariant isWellFormed()
	 */
	public void addClient(String name, String password, String accountType);
	
	/**
	 * @pre (name != null) && (Person(name) != null)
	 * @post Person(name) == null
	 * @invariant isWellFormed()
	 */
	public void removeClient(String name);
	
	/**
	 * @pre (clientName != null) && (accountID != null) && (sum != null) && (sum > 0)
	 * @post account(accountID).getMoney() == (@pre account(accountID).getMoney() + sum)
	 * @invariant isWellFormed() 
	 */
	public void addCash(String clientName, String accountID, double sum);
	
	/**
	 * @pre (clientName != null) && (accountID != null) && (sum != null) && 
	 * 			(sum > 0) && (sum <= account.getMoney())
	 * @post account.getMoney() == (@pre account.getMoney() - sum)
	 * @invariant isWellFormed()
	 */
	public void withdrawCash(String clientName, String accountID, double sum);
	
	/**
	 * @pre client != null
	 * @post new SpecificClientTable(client)
	 * @invariant isWellFormed()
	 */
	public void generateReport(String client);
}
