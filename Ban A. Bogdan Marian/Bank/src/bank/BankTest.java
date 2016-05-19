package bank;

import model.Account;
import model.AccountType;
import model.Person;

public class BankTest {
	public static void main(String[] args) {
		Bank bank = new Bank();
		Person person1 = bank.addPerson("Mihai",AccountType.SPENDING);
		Person person2 = bank.addPerson("George",AccountType.SAVING);
		Person person3 = bank.addPerson("Theon",AccountType.SPENDING);
		
		Account account1 = bank.addHolderAccount(person1.getId(),AccountType.SAVING);
		Account account2 = bank.addHolderAccount(person2.getId(),AccountType.SPENDING);
		
		System.out.println("All:" + bank.getHashtable());
		
		bank.removeHolderAccount(person1.getId(),account1.getAccountId());
		
		bank.removePerson(person3.getId());
		
		System.out.println("Remove" + bank.getHashtable());
		
		bank.writeAccountData(person2.getId(),account2.getAccountId(), 1000);
		
		bank.readAccountData(person2.getId(),account2.getAccountId());

		bank.reportGenerator();
	}
}
