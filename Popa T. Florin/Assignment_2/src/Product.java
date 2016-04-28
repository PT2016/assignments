
public class Product {

	private double price;
	private int quantity;
	private String name;

	public Product(String name, double price,int quantity) {
		this.price = price;
		this.quantity=quantity;
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

}
