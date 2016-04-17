package website;

public class Order {
	private Customer customer;
	private Product product;
	private int numberToOrder;

	public Order(Customer customer, Product product, int numberToOrder) {
		this.customer = customer;
		this.product = product;
		this.numberToOrder = numberToOrder;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getNumberToOrder() {
		return numberToOrder;
	}

	public void setNumberToOrder(int numberToOrder) {
		this.numberToOrder = numberToOrder;
	}
}
