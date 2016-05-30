package model;

import utilities.IllegalProcedureException;

public interface BankProc {

	/**
	 * @pre person != null
	 * @post size() == size()@pre + 1
	 * @post get(person) != null
	 * 
	 */
	public void addPerson(Person person) throws IllegalProcedureException;

	
	/**
	 * @pre person != null
	 * @pre containsKey(person)
	 * @post size() == size()@pre -1
	 * @post !containsKey(person)
	 * 
	 */
	public void removePerson(Person person) throws IllegalProcedureException;

	
	/**
	 * @pre person != null
	 * @pre account != null
	 * @pre get(person) != null
	 * @post get(person).size() == get(person).size()@pre + 1;
	 * @post !get(person).isEmpty()
	 * 
	 */
	public void addAccount(Person person, Account account) throws IllegalProcedureException;

	
	/**
	 * @pre person != null
	 * @pre aaccount != null
	 * @pre get(person) != null
	 * @post get(person).size() == get(person).size()@pre - 1;
	 * 
	 */
	public void removeAccount(Person person, Account account) throws IllegalProcedureException;

	public void readAccountsData();

	public Bank writeAccountsData();

	public void generateStatement();
}
