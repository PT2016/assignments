package models;

import java.io.Serializable;

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
