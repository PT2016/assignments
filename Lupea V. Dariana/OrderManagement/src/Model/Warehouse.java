package Model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import Comparators.ProductComparator;

public class Warehouse implements Serializable {

	private static final long serialVersionUID = 1L;
	private TreeSet<Product> products;

	public Warehouse() {
		products = new TreeSet<Product>(new ProductComparator());
	}

	public void addProduct(Product p) {
		products.add(p);
	}

	public void removeProduct(Product p) {
		products.remove(p);
	}
	
	public boolean containsProduct(Product p) {
		return products.contains(p);
	}
	
	public Iterator<Product> checkProducts(){
		return products.iterator();
	}

	void printProducts(Set<Product> product) {
		for (Product prod : product) {
			System.out.println(prod);
		}
		System.out.println();
	}
}
