package models;

public interface BancProc {
	/**
	 * Method for adding a new person
	 * 
	 * @invariant isWellFormed()
	 * @pre p != null && p.getName()!=null
	 * @post getNumberOfPersons() == getNumberOfPersons()@pre + 1
	 * @post get(p) != null
	 * @invariant isWellFormed()
	 * @param p
	 *            Person to be added
	 */
	public void addPerson(Person p);

	/**
	 * Method for deleting a person
	 * 
	 * @invariant isWellFormed()
	 * @pre getNumberOfPersons()!=0
	 * @pre p != null && p.getId() != null;
	 * @pre containsKey(p)
	 * @post !containsKey(p)
	 * @post getNumberOfPersons() == getNumberOfPersons()@pre - 1
	 * @invariant isWellFormed()
	 * @param p
	 *            The person to be deleted
	 */

	public void deletePerson(Person p);

	/**
	 * Method for adding a new account
	 * 
	 * @invariant isWellFormed()
	 * @pre a != null && p!= null
	 * @post (getNumberOfAccounts() != 0) && (getNumberOfAccounts() ==
	 *       getNumberOfAccounts()@pre + 1)
	 * @invariant isWellFormed()
	 * @param a
	 *            The account to be added
	 * @param p
	 *            The person whose account is added
	 */

	public void addAccountToHolder(Account a, Person p);

	/**
	 * Method for deleting an account
	 * 
	 * @invariant isWellFormed()
	 * @pre getNumberOfPersonsHavingAccounts() != 0 && getNumberOfAccounts() !=
	 *      0
	 * @pre a != null && a.getP != null;
	 * @post getNumberOfAccounts() == getNumberOfAccounts()@pre - 1
	 * @invariant isWellFormed()
	 * @param a
	 *            Account to be deleted
	 * @param p
	 *            The person whose account is deleted
	 */

	public void deleteAccountToHolder(Account a, Person p);

	public void generateReports();
}
