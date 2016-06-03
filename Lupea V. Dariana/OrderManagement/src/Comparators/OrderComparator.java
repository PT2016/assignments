package Comparators;

import java.io.Serializable;
import java.util.Comparator;

import Model.Order;

public class OrderComparator implements Comparator<Order>, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getOrderId().compareTo(o2.getOrderId());
	}

}
