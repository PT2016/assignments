package order.management.models;

import java.io.Serializable;
import java.util.Random;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String ID;
	public Product product;
	public int nrPieces;
	public Customer customer;

	public Order(Product p, int nrPieces, Customer c) {
		this.ID = new Random().toString();
		this.product = p;
		this.nrPieces = nrPieces;
		this.customer = c;
	}

	public String toString() {
		return "product: " + product.name + " price: " + product.price + " pieces: " + nrPieces + "customer:"
				+ customer.name;
	}
}
