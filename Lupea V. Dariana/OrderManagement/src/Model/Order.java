package Model;

import java.io.Serializable;
import java.util.Set;

/**
 * 
 * @author Dariana Lupea
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private int orderId;
	private String orderDate;
	private String orderStatus;
	
	private Set<Product> orderedProducts;
	private Customer customer;

	public Order(int orderId, int noOfProductsContained,String orderDate, String orderStatus, Customer customer) {
		this.setNoOfProductsContained(noOfProductsContained);
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderId = orderId;
		//this.orderedProducts = new HashSet<Product>();
		this.setCustomer(customer);
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Set<Product> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(Set<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getNoOfProductsContained() {
		return orderedProducts.size();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setNoOfProductsContained(int noOfProductsContained) {
	}
}
