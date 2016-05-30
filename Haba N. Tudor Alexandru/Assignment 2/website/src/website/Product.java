package website;

public class Product implements Comparable<Product> {
	private String name;
	Double price;
	String colour;
	int stock;
	double compareVar = 0;

	public Product(String nume, Double pret, String colour, int stock) {
		this.name = nume;
		this.price = pret;
		this.colour = colour;
		this.stock = stock;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Price: " + price + ", name:" + name + ", colour:" + colour+ ", stock:" + stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public int compareTo(Product product) {
		if (compareVar == product.getPrice()) {
			compareVar = product.getPrice();
			return 0;
		} else if (compareVar < product.getPrice()) {
			compareVar = product.getPrice();
			return -1;
		} else {
			compareVar = product.getPrice();
			return 1;
		}
	}
}
