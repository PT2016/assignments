package models;

public interface BankProc {

	/**
	 * @pre p!=null
	 * @post persons.size()=persons.size()@pre+1
	 * @post get(p)!= null
	 */
	public void addPerson(Person p);

	/**
	 * @pre p!=null
	 * @pre containsKey(p)
	 * @post persons.size() = persons.size@pre-1
	 * @post !containsKey(p)
	 */
	public void removePerson(Person p);

	/**
	 * @pre p!=null
	 * @pre type!=null
	 * @prea pin!=null
	 * @post accounts.size()=accounts.size()@pre+1
	 */
	public Account addHolderAccounts(Person p, String type, String pin);

	/**
	 * @pre p!=null
	 * @pre a!=null
	 * @post accounts.size()=accounts.size()@pre-1
	 */
	public void removeHolderAccounts(Person p, Account a);

	/**
	 * @pre p!=null
	 * @pre a!=null
	 * 
	 */
	public void readAccountsData(Person p, Account a);

	/**
	 * @pre p!=null
	 * @pre a!=null
	 * @pre data!=0
	 * @post a.getTotal()!=a.getTotal()@pre
	 */
	public void writeAccountsData(Person p, Account a, int data);

	public void generateReport();

}
