package bank;

import account.Account;
import user.Person;

public interface BankProc {
	
	/**
	 * @param a
	 * @pre (a != null) && (a.getMainHolder().getCNP() != null)
	 * @invariant isWellFormed()
	 * @post nr + 1 == getNrAccounts()
	 */
	public void addAccount(Account a);
	
	/**
	 * 
	 * @param a
	 * @pre (a != null) && (a.getMainHolder().getCNP() != null)
	 * @invariant isWellFormed()
	 * @post size@pre - 1 == getNrAccounts()
	 */
	public boolean removeAccount(Account a);
	
	/**
	 * 
	 * @param p
	 * @param a
	 * @pre (a != null) && (a.getMainHolder().getCNP() != null)
	 * @pre (p != null) && (p.getCNP() != null)
	 * @invariant  isWellFormed()
	 * @post size@pre + 1 == size@post
	 */
	public void addPerson(Person p, Account a);
	
	/**
	 * 
	 * @param p
	 * @param a
	 * @pre (a != null) && (a.getMainHolder().getCNP() != null)
	 * @pre (p != null) && (p.getCNP() != null)
	 * @invariant  isWellFormed()
	 * @post size@pre - 1 == size@post
	 */
	public boolean removePerson(Person p, Account a);
	
	/**
	 * 
	 * @param p
	 * @pre (p != null) && (p.getCNP() != null)
	 * @invariant isWellFormed()
	 * @post nr1 < nr2
	 */
	public boolean removeHolder(Person p);

}
