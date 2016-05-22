package models;

import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String SAVING = "0";
	public static final String SPENDING = "1";

	public String ID;
	public double money;
	public String type;

	public Account(String ID, double money, String type) {
		this.ID = ID;
		this.money = money;
		this.type = type;
	}

	public abstract void deposit(double money);
	public abstract boolean withdraw(double money);

	public int hashCode() {
        final int prime = 31;
		int result = 1;
		result = prime * result + ID.hashCode();
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Account))
			return false;
		Account auxP = (Account) obj;
		if (this.ID.equals(auxP.ID) && this.money == auxP.money)
			return true;
		return false;
	}
	
	public String toString(){
		return "ACCOUNT ID=" + this.ID + ", TYPE=" + this.type + ", MONEY=" + this.money;
	}

}
