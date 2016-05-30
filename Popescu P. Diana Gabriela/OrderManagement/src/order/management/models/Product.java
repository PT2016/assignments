package order.management.models;

import java.io.Serializable;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String description;
	public int stock;
	public float price;

	public Product(String name) {
		this.name = name;
		setDescription("");
		stock = 0;
	}

	public Product(String name, float price, int stock, String desc) {
		this.name = name;
		setDescription(desc);
		this.stock = stock;
		this.price = price;
	}

	public Product(String name, String desc, int stock) {
		this.name = name;
		setDescription(desc);
		this.stock = stock;
	}

	public Product(String name, float price, String desc) {
		this.name = name;
		setDescription(desc);
		this.price = price;
	}

	public void setDescription(String desc) {
		description = desc;
	}
}
