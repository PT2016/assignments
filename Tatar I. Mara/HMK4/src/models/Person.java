package models;

import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2756680936198027574L;
	private String name;
	private String occupation;
	private long phoneNo;
	private int age;
	private int pin;
	private int noAccounts;
	
	public Person(String name,String occupation,int age,int pin){
		this.name=name;
		this.occupation=occupation;
		this.age=age;
		this.pin=pin;
		noAccounts=0;
		
	}
	private void setPin(int pin){
		this.pin=pin;
	}
	private int getPin(){
		return pin;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setOccupation(String occupation){
		this.occupation=occupation;
	}
	public String getOccupation(){
		return occupation;
	}
	public void setAge(int age){
		this.age=age;
	}
	public int getAge(){
		return age;
	}
	public void setPhoneNo(long phoneNo){
		this.phoneNo=phoneNo;
	}
	public long getPhoneNo(){
		return phoneNo;
	}
	public String toString(){
		return this.name+" "+this.occupation+" "+this.age;
		
	}
	public void setNumberOfAccounts(int noAccounts){
		this.noAccounts=noAccounts;
	}
	public int getNumberOfAccounts() {
		// TODO Auto-generated method stub
		return noAccounts;
	}
}
