package models;

import java.util.ArrayList;

public interface BankProc {
	
	/**
	 * 
	 * @pre p != null
	 * @post size() == size()@pre + 1
	 * @post get(p) != null
	 */
	public void addPerson(Person p) throws IllegalOperationException;
	
	/**
	 * @pre p != null
	 * @pre containsKey(p)
	 * @post !containsKey(p)
	 * @post size() == size()@pre - 1;
	 *  
	 */
	public void removePerson(Person p) throws IllegalOperationException;
	
	/**
	 * @pre p != null
	 * @pre a != null
	 * @pre get(p) != null
	 * @post get(p).size() == get(p).size()@pre + 1;
	 * @post !get(p).isEmpty()
	 * 
	 */
	public void addAccount (Person p, Account account) throws IllegalOperationException;
	
	/**
	 * @pre p != null
	 * @pre account != null
	 * @pre get(p) != null
	 * @post get(p).size() == get(p).size()@pre - 1
	 */
	public void removeAccount(Person p, Account account) throws IllegalOperationException;
	
	public void generateReportAdmin();
}
