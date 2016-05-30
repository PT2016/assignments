package models;

public interface BankProc {

	/*
	 * @preconditions param person!=null
	 * @precondition age>18 
	 * @postcondition size()=size@pre+1
	 * */
	public void addPerson(Person person);
	/*
	 * @preconditions param person!=null
	 * @precondition containsKey(person)
	 * @post size()=size()-1
	 * */
	public void removePerson(Person person);
	/*
	 * @preconditions param holder!=null
	 * @precondition account!=null
	 * @post get(person).size()=get(person).size()+1
	 * */
	public void addHolderAccount(Person holder,Account account);
	/*
	 * @preconditions param holder!=null
	 * @precondition account!=null
	 * @post holder.getNumberOfAccounts=holder.getNumberOfAccounts@pre-1
	 * */
	public void removeHolderAccount(Person holder,Account account);

	public void generateReport();
	
}
