package Model;

import java.io.Serializable;

/**
 * 
 * @author Dariana Lupea
 *
 */
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int quantity;
	private int price; 
	
	public Product(String name, int id, int quantity, int price){
		this.name = name;
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
