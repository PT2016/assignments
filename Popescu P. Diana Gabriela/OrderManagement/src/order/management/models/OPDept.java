package order.management.models;

import java.io.Serializable;
import java.util.TreeSet;

//use a BinarySearchTree for storing orders.
public class OPDept implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TreeSet<Order> orders;

	public OPDept() { 
		orders = new TreeSet<Order>(new ComparatorOrder());
	}

	public void addExistentOrder(Order o) {
		orders.add(o);
	}
	
	public void addNewOrder(Product p, int nrPieces, Customer c) {
		orders.add(new Order(p, nrPieces, c));
	}

	public void cancelOrder(Order o) {
		if (orders.contains(o)) {
			orders.remove(o);
		}
	}
}
