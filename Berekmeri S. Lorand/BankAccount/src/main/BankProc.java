
package main;

import model.Account;

/**
 * @author Lorand
 *
 */
public interface BankProc {

	/**
	 * 
	  *@pre 	bank != null
	  *@post    
	  *@param
	  *@return
	 */
	public void readAccounts();
	
	/**
	 * 
	  *@pre 	
	  *@post    bank!=null
	  *@param
	  *@return
	 */
	public void writeAccounts();
	
	/**
	 * 
	  *@pre 	acc!=null
	  *@post    !isEmpty() && size= size-ul precedent +1
	  *@param   acc-contul pe care-l adaugam
	  *@return
	 */
	public void addAccount(Account acc);
	
	/**
	 * 
	  *@pre 	acc!=null
	  *@post    !isEmpty() && size= size-ul precedent -1
	  *@param   acc-contul pe care-l stergem
	  *@return
	 */
	public void deleteAccount(Account acc);
	
	/**
	 * 
	 * 
	  @pre accountId >0 || money>0
	  @post
	  @param accountId, money
	  @return
	 */
	
	public Account getAccount(int acc);
	public  double depositMoneyIntoAccount( int accountId, double money);
	public  double extractMoneyIntoAccount( int accountId, double money);
	
	
	
}
