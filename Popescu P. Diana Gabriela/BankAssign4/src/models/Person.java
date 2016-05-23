package models;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Person implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String ID;
	public String name;

	public Person(String ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID.hashCode() + name.hashCode();
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Person))
			return false;
		Person auxP = (Person) obj;
		if (this.ID.equals(auxP.ID) && this.name.equals(auxP.name))
			return true;
		return false;
	}

	public String toString() {
		return "PERSON ID=" + this.ID + ", NAME=" + this.name;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Account account = (Account) arg0;
		Double sum = (Double) arg1;
		System.out.println(this.toString() + account.toString() + " has changed with sum " + sum);
	}
}
