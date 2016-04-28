package data;

import java.util.Objects;

import Helpers.BST;
import Helpers.FileHelp;
import Item.Order;
import Item.Product;

public class Library {
	private BST<String, Order> tree = new BST<String, Order>();
	private String path = "C:/a/Polipoly/P2/History.txt";
	private String name;
	private int MAX = 50;
	
	
	public void getHistory() {
		FileHelp.openFile(path);
		Order order = new Order();
		Product[] products = new Product[MAX];

		int i;
		
		order.setName(FileHelp.readFile(path));

		while (!Objects.equals(order.getName(), "end of file")) {
				
			order.setDate(FileHelp.readFile(path));
			order.setNr(Integer.parseInt(FileHelp.readFile(path)));
			for (i = 0; i < order.getNr(); i++) {
				Product product = new Product(0);
				product.setPrice(Float.parseFloat(FileHelp.readFile(path)));
				product.setName(FileHelp.readFile(path));
				product.setAmount(Integer.parseInt(FileHelp.readFile(path)));
				products[i] = product;
			}
			order.setProducts(products);
			tree.put(order.getName(), order);
			products = new Product[MAX];
			order = new Order();
			order.setName(FileHelp.readFile(path));
		}
		FileHelp.closeFile();
	}
	
	public String[] getDisplayHistory() {
		String[] S = new String[100];
		int i = 0;
		System.out.println(name);
		while(tree.get(name) != null) {
			S[i] = "";
			S[i] += tree.get(name).history();
			i++;
			tree.delete(name);
		}
		return S;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}
