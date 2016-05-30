package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Person implements Serializable {
	private String firstName, lastName, userName, password;
	private int age;
	private int nrOfAccounts;

	public Person(String firstName, String lastName, String userName, String password, int age, int nrOfAccounts) {
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(userName);
		setPassword(password);
		setAge(age);
		setNrOfAccounts(nrOfAccounts);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * @pre age>0 && age<110
	 * @post age instanceOf Integer
	 * @param age
	 */
	public void setAge(int age) {
		assert age > 0 && age < 110 && isWellFormed() : "Age entered " + age + " is invalid.";
		this.age = age;
	}

	private boolean isWellFormed() {
		return (firstName != null && lastName != null);
	}

	public int getNrOfAccounts() {
		return nrOfAccounts;
	}

	public void setNrOfAccounts(int nrOfAccounts) {
		this.nrOfAccounts = nrOfAccounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + nrOfAccounts;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (nrOfAccounts != other.nrOfAccounts)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "First name: " + firstName + ". last name: " + lastName + ", age: " + age + ", nr of accounts: "
				+ nrOfAccounts;
	}
}
