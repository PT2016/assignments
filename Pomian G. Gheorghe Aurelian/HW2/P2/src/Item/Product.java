package Item;

public class Product {
	private String name;
	private int amount;
	private float price;

	public Product(float price) {
		name = "_";
		amount = 1;
		this.setPrice(price);
	}
	
	public String info() {
		String S = "";
		S += name + " - " + amount; 
		return S;
	}

	public String toString() {
		String S = "";
		S += price + " " + name + " " + amount;
		return S;
	}
	
	public String display() {
		String S = "";
		S += name + "\t / " + price + "\t / " + amount;
		return S;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
