package bank;
//real scenarios for updating account - update account money, update account holder information

import java.util.List;

import model.Account;

//each method should get as input a unique identifier for the account

import model.AccountType;
import model.Person;

public interface BankProc {

	/**
	 * @pre name != null
	 * @post Person != null
	 */
	Person addPerson(String name,AccountType type);

	/**
	 * @pre id>0
	 * @post Person = null
	 */

	void removePerson(long id);

	/**
	 * @pre Person != null
	 * @post Account != null
	 */

	Account addHolderAccount(long id, AccountType type);

	/**
	 * @pre id>0 && Account != null
	 * @post Account = null
	 */

	void removeHolderAccount(long id, long accountId);

	/**
	 * @pre Person != null && Account != null
	 * @inv sum >= 0
	 */

	long readAccountData(long id, long accountId);

	/**
	 * @pre Person != null && Account != null && sum >= 0
	 * @inv sum >= 0
	 */

	void writeAccountData(long id, long accountId, long sum);

	/**
	 * @pre
	 * @post
	 */

	List<Long> reportGenerator();

}
