package Bank;
import java.io.File;

import Management.Account;

public interface BankProc {
	/**
	 * adds new account to the hash table
	 * @pre x!=null
	 * @param x
	 * @post totalElements == totalElements @pre+1
	 * @return true for success, false for failure
	 */
	public boolean addAccount(Account x);
	
	/**
	 * remove account
	 * @pre x!=null
	 * @post totalElements == totalElements @pre-1
	 * @param x
	 * @return true upon success, false for failure
	 */
	public boolean removeAccount(Account x);
	
	/**
	 * write accounts to a given file
	 * @pre file!=null
	 * @param f
	 */
	public void writeAccount(File f);
	
	/**
	 * read existing accounts from a given file
	 * @pre file!=null
	 * @post @nochange
	 * @param f
	 */
	public void readAccount(File f);
	
	/**
	 * return info about all the accounts stored in the hash table
	 * @pre true
	 * @post @nochange
	 * @param x
	 * @return
	 */
	public Account findAccount(Account x);

}
