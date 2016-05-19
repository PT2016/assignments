package order.management.models;

import java.io.Serializable;
import java.util.TreeSet;

//use a BinarySearchTree for storing orders.
public class Warehouse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TreeSet<Product> products;
	
	public Warehouse(){
		products = new TreeSet<Product>(new ComparatorProduct());
	}
	public void addExistentProduct(Product p){
		products.add(p);
	}
	public void updateStock(Product p, int newStock){
		if(products.contains(p)){
			products.remove(p);
			products.add(new Product(p.name, p.description, newStock));
		}
	}
	public void removeProduct(Product p){
		if(products.contains(p)){
			products.remove(p);
		}
	}
}
