package models;

public interface BankProc {
	
	public void addPerson(Person person);

	public void removePerson(Person person);

	public void addHolderAccount(Person holder,Account account);
	
	public void removeHolderAccount(Person holder,Account account);

}
