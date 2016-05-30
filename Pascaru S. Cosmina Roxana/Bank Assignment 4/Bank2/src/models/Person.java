package models;

import java.io.Serializable;

public class Person implements Serializable {

	private String firstName;
	private String lastName;
	private int ID;

	public Person(String firstName, String lastName, int ID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String toString() {
		return String.format("ID:%d FirstName: %s LastName: %s\n", ID, firstName, lastName);
	}

}
