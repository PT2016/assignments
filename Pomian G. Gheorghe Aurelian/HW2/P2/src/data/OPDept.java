package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import Helpers.BST;
import Helpers.FileHelp;
import Item.Order;
import Item.Product;

public class OPDept {

	private BST<Integer, Order> tree = new BST<Integer, Order>();
	private int count = 0;
	private String path = "C:/a/Polipoly/P2/Orders.txt";
	private String history = "C:/a/Polipoly/P2/History.txt";
	private final int MAX = 50;

	public void getOrders() {
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
			products = new Product[MAX];
			tree.put(count, order);
			count++;
			order = new Order();
			order.setName(FileHelp.readFile(path));
		}
		FileHelp.closeFile();
	}
	
	public void confirmOrder(int index) {
		confirm(tree.get(index));
		tree.delete(index);
		count--;
	}
	
	public void decrease() {
		count--;
	}
	
	public int lenght() {
		return tree.size();
	}
	
	
	private void confirm(Order order) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(history, true)))) {
			out.println(order);
		} catch (IOException e) {

		}
	}

	
	public String[] getStringOrders() {
		String[] S = new String[MAX];
		int i = 0;
		for (i = 0; i < count; i++) {
			S[i] = "" + tree.get(i).display();
		}
		return S;
	}
	



	public void updateOrders() {
		int i;
		boolean first = true;
		if(tree.isEmpty())
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
				out.print("");
			} catch (IOException e) {

			}
		for (i = 0; i < count; i++) {
			if (first) {
				try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
					out.println(tree.get(i));
				} catch (IOException e) {

				}
				first = false;
			} else
				try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
					out.println(tree.get(i));
				} catch (IOException e) {

				}
		}
	}

	
	public void add(Order order) {
		tree.put(count, order);
		count++;
	}
	
	public void remove(int index) {
		tree.delete(index);
		count--;
	}
	
	public Order getOrder(int index) {
		return tree.get(index);
	}

}
