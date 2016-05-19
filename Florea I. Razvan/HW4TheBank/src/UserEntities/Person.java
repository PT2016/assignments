package UserEntities;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import AccountEntities.Account;

public class Person implements Serializable, Observer{


	private static final long serialVersionUID = -3402417217945594620L;
	protected String name;
	protected String password;

	public Person(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public boolean isAdmin() {
		return false;
	}

	public boolean isUser() {
		return false;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Account) {
			Account account = (Account) arg0;
			System.out.println(account + " :: " + arg1);
		}
	}
}
