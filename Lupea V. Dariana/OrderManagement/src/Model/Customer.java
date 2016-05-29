package Model;

import java.io.Serializable;

public class Customer extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	

	public Customer() {

	}

	public Customer(String name, String password) {
		super(name, password);
	}
}
