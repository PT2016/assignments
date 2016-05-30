package model;

import java.io.Serializable;

public class Person implements Serializable{

	private String firstName;
	private String lastName;
	private String phoneNo;
	private String ssid;

	public Person(String firstName, String lastName, String ssid, String phoneNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssid = ssid;
		this.phoneNo = phoneNo;
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

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		// object must be Person at this point
		Person person = (Person) obj;
	
		return person.getSsid().equals(this.getSsid());
	}
	
	public String toString(){
		return this.ssid+ " "+ this.firstName + " " + this.lastName + " " + this.phoneNo;
	}
}
