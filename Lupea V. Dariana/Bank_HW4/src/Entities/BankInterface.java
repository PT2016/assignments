package Entities;

import java.util.Set;

//import java.util.Set;

public interface BankInterface {

	/**
	 * Method used for creating an account for a person
	 *
	 * @param p
	 *            Person -> account holder
	 * @param assocAcc
	 *            Account
	 * 
	 * @invariant isWellFormed
	 * @pre p!=NULL, assocAcc!=NULL
	 * 
	 * @post size = size@pre + 1
	 * @invariant isWellFormed
	 */
	public void addAccForPerson(Person p, Account assocAcc);
	
	

	/**
	 * Method used in order to deposit a sum to an account
	 * 
	 * @param p Person -> account holder
	 * 
	 * @param assocAcc
	 *                 Account
	 * @param sum
	 *            Sum to be deposit to account
	 * 
	 * @invariant isWellFormed
	 * @pre p!=NULL, accId != 0, sum > 0
	 * 
	 * @post initialMoney < afterDepositMoney
	 * @invariant isWellFormed
	 */
	public void depositMoney(double sum, int accId, Person p);
	
	

	/**
	 * Method used in order to withdraw a sum from an account
	 * 
	 * * @param p Person -> account holder
	 * 
	 * @param assocAcc
	 *            Account
	 * @param sum
	 *            Sum to be withdrawn from account
	 * 
	 * @invariant isWellFormed
	 * @pre p!=NULL, accId != 0, sum > 0
	 * 
	 * @post initialMoney > afterWithdrawalMoney
	 * @invariant isWellFormed
	 */
	public void withdrawMoney(double sum, int accId, Person p);

	
	
	/**
	 * Method used in order to delete a person and all the corresponding
	 * accounts
	 * 
	 * * @param p
	 *            Person -> account holder
	 *            
	 * @invariant isWellFormed         
	 * @pre p!=NULL
	 * 
	 * @post initialSize > afterDeleteSize
	 * @invariant isWellFormed
	 */
	
	public void deletePerson(Person p);

	/**
	 * Method used in order to delete an account of a given person
	 * 
	 * @param p
	 *            Person -> account holder
	 * @param assocAcc
	 *            Account 
	 *                     
	 * @invariant isWellFormed         
	 * @pre p!=NULL,  accId != 0
	 * 
	 * @post initialSize > afterDeleteSize
	 * @invariant isWellFormed
	 */
	public void deleteAccount(int accId, Person p);

	
	
	public Set<Person> findAllPersons();

	public Set<Account> findAllAccounts();

}
