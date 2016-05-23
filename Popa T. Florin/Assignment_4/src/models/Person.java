package models;

public class Person {

	private String name;
	private int age;
	private int nrOfAccounts=0;
	
	
	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	public void setNrOfAccounts(int nrOfAccounts) {
		this.nrOfAccounts = nrOfAccounts;
	}
	
	public int getNrOfAccounts() {
		return nrOfAccounts;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
	
}
