package Management;
import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id, clientID;
	private int balance;
	private Person person;
	
	public Account(int id, int clientID, int balance, String name, String address)
	{
		this.setId(id);
		this.setClientID(clientID);
		this.setBalance(balance);
		setPerson(new Person(clientID, name, address));
	}


	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	//method to check if 2 accounts are equal in terms of client id and account id
	public boolean equals(Object a){
		Account acc= (Account) a;
		if(a==null)
			return false;
		else
			if(this.id==acc.getId() && this.clientID==acc.getClientID())
				return true;
		return false;
		
	}
	
	//hash code
	public int hashFunction(){
		return id % 9973;
	}
}
