package website;

import java.util.ArrayList;
import java.util.TreeSet;

public class Warehouse {

	ArrayList<String> productList = new ArrayList<>();
	TreeSet<Product> tree = new TreeSet<>();

	public Warehouse() {
		Product shirt = new Product("T-Shirt", 10.00, "red", 40);
		Product pants = new Product("Pants", 20.00, "blue", 10);
		Product jacket = new Product("Jacket", 40.00, "black", 25);
		Product hat = new Product("Hat", 80.00, "black", 25);
		Product scarf = new Product("Scarf", 60.00, "yellow", 15);
		Product shoes = new Product("Shoes", 20.00, "black", 60);

		Product gloves = new Product("Gloves", 40.00, "magenta", 60);
		Product boots = new Product("Boots", 50.00, "red", 60);
		Product socks = new Product("Socks", 80.00, "white", 15);
		Product television = new Product("Television", 1400.00, "black", 5);
		Product laptop = new Product("Laptop", 1800.00, "grey", 9);
		Product monitor = new Product("Monitors", 700.00, "black", 10);
		Product keyboard = new Product("Keyboard", 200.00, "white", 15);
		Product mouse = new Product("mouse", 99.50, "blue", 25);
		productList.add(shirt.getName());
		productList.add(pants.getName());
		productList.add(jacket.getName());
		productList.add(hat.getName());
		tree.add(shirt);
		tree.add(pants);
		tree.add(jacket);
		tree.add(hat);
		tree.add(scarf);
		tree.add(shoes);
		tree.add(gloves);
		tree.add(boots);
		tree.add(socks);
		tree.add(television);
		tree.add(laptop);
		tree.add(monitor);
		tree.add(television);
		tree.add(keyboard);
		tree.add(mouse);
	}

	public TreeSet<Product> getTree() {
		return tree;
	}

	public ArrayList<String> getProductList() {
		return productList;
	}
}
