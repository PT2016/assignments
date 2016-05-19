package model;

public class Person implements java.io.Serializable {
	
	private String FirstName;
	private String LastName;
	private String CNP;
	
	public Person(String FirstName, String LastName, String CNP){
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.CNP=CNP;
		
	}
	
	public String getFristName(){
		return FirstName;
	}
	
	public String getLastName(){
		return LastName;
	}
	
	public String getCNP(){
		return CNP;
	}
	
	public boolean correctParam(){
		if(FirstName== null || LastName == null || CNP.length()!=13)
			return false;
		else 
			return true;
		
	}
	

	
}
