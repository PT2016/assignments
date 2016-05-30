package models;

import java.util.Set;

public interface BankProc {
	/**
	 * @param sum 
	 * @param accountID
	 * @param person
     * @pre sum > 0
     * @pre accountID != null
     * @pre person != null
     * @post money = money@pre + sum
     * @invariant isWellFormed
     */
	public void deposit(double sum, String accountID, Person person);
	
	/**
	 * @param sum 
	 * @param accountID
	 * @param person
     * @pre sum > 0
     * @pre accountID != null
     * @pre person != null
     * @post money = money@pre - sum
     * @invariant isWellFormed
     */
	public void withdraw(double sum, String accountID, Person person);
	
	/**
	 * @param person
	 * @param account
     * @pre person != null
     * @pre account != null
     * @post bankmap.get(person).size() = bankmap.get(person).size()@pre + 1
     * @invariant isWellFormed
     */
	public void addAccount(Person person, Account account);
	
	/**
	 * @param person
	 * @param account
     * @pre person != null
     * @pre account != null
     * @post bankmap.get(person).size() = bankmap.get(person).size()@pre - 1
     * @invariant isWellFormed
     */
	public void removeAccount(Person person, Account account);
	
	/**
	 * @param person
     * @pre person != null
     * @post bankmap = bankmap@pre
     * @invariant isWellFormed
     */
	public Set<Account> getAccounts(Person person);
	
	/**
     * @post bankmap = bankmap@pre
     * @invariant isWellFormed
     */
	public Set<Person> getHolders();
	
	/**
	 * @param person
     * @pre person != null
     * @post bankmap.size() = bankmap.size()@pre + 1
     * @invariant isWellFormed
     */
	public void addHolder(Person p);
	
	/**
	 * @param person
     * @pre person != null
     * @post bankmap.size() = bankmap.size()@pre - 1
     * @invariant isWellFormed
     */
	public void removeHolder(Person p);
	
	//invariant:
	public boolean isWellFormed();
	public String toString();
}
