package order.management.models;

import java.io.Serializable;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int ID;
	public String name;

	public Customer(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}
}
