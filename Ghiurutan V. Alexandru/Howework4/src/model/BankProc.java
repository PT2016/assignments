package model;

public interface BankProc {
	/**
	 * @pre !bank.containsKey(person)&&person!=null&&isWellFormed()
	 * @post (bank.size()==bank.size()@pre+1)&& isWellFormed
	 * @param person
	 */
	public void addPerson(Person person);

	/**
	 * @pre bank.containsKey(person)&&isWellFormed()
	 * @post (bank.size()==@pre bank.size()-1)&& isWellFormed()
	 * @param person
	 */
	public void removePerson(Person person);

	/**
	 * @pre bank.containsKey(person)&&isWellFormed()&&account.getMoney()>=0
	 * @post (bank.get(person).size()==@pre bank.get(person).size()+1)&&
	 *       isWellFormed()
	 * @param person
	 */
	public void addHolderAssociatedAccount(Person person, Account account);

	/**
	 * @pre bank.containsKey(person)&&isWellFormed()&&bank.get(person).contains(
	 *      account)
	 * @post (bank.get(person).size()==@pre bank.get(person).size()-1)&&
	 *       isWellFormed()
	 * @param person
	 */
	public void removeHolderAssociatedAccount(Person person, Account account);

	public void generateReport(Person person, String message);

	public boolean isWellFormed();
}
