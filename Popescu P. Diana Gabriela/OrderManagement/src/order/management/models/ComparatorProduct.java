package order.management.models;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorProduct implements Comparator<Product>, Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override
		public int compare(Product p1, Product p2) {
			return p1.name.compareTo(p2.name);
		}
}
