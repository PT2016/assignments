package Model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import Comparators.OrderComparator;

/**
 * 
 * @author Dariana Lupea 
 * Order Processing Department - where the orders are processed
 *
 */
public class OPD implements Serializable {

	private static final long serialVersionUID = 1L;
	private TreeSet<Order> orders;

	public OPD() {
		orders = new TreeSet<Order>(new OrderComparator());
	}

	public void setOrder(TreeSet<Order> orders){
		this.orders = orders;
	}
	public void addOrder(Order ord) {
		orders.add(ord);
	}

	public void removeOrder(Order ord) {
		orders.remove(ord);
	}

	public boolean existsOrder(Order ord) {
		return orders.contains(ord);
	}

	public Iterator<Order> checkOrders() {
		return orders.iterator();
	}

	public int getNoOfOrders() {
		return orders.size();
	}
}
