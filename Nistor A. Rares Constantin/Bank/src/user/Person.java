package user;

import java.io.Serializable;

public class Person implements Serializable{
	private String name;
	private String CNP;
	
	public Person(String name,String CNP){
		this.name=name;
		this.CNP=CNP;
	}
	
	public String getCNP() {
		return CNP;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
