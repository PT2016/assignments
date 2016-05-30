
public class Order {
	
	Product product;
	Customer customer;
	int quantity;

	public Order(Product p, Customer c, int quantity){
		this.product=p;
		this.customer=c;
		this.quantity=quantity;
		
	}
	
	public Product getProduct() {
		return product;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
}
