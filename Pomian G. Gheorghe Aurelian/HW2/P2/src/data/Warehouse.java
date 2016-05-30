package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.swing.JOptionPane;

import Helpers.BST;
import Helpers.FileHelp;
import Item.Product;

public class Warehouse {

	private BST<Integer, Product> tree = new BST<Integer, Product>();
	private int count = 0;
	private String path = "C:/a/Polipoly/P2/Stock.txt";
	private final int MAX = 50;

	public void getProducts() {
		FileHelp.openFile(path);
		String item, amount;
		float price = 0;
		
		count = 0;
		item = FileHelp.readFile(path);
		if(!Objects.equals(item, "end of file"))
			price = Float.parseFloat(item);
		item = FileHelp.readFile(path);
		amount = FileHelp.readFile(path);
		while (!Objects.equals(item, "end of file")) {
			Product product = new Product(price);
			product.setName(item);
			product.setAmount(Integer.parseInt(amount));
			tree.put(count, product);
			count++;
			item = FileHelp.readFile(path);
			if(!Objects.equals(item, "end of file"))
				price = Float.parseFloat(item);
			item = FileHelp.readFile(path);
			amount = FileHelp.readFile(path);
			if(count > MAX)
				break;
		}
		FileHelp.closeFile();
	
	}

	public String[] getStringProducts() {
		String[] S = new String[MAX];
		int i = 0;
		for (i = 0; i < count; i++) {
			S[i] = "" + tree.get(i);
		}
		return S;
	}
	
	public String[] getDisplayProducts() {
		String[] S = new String[MAX];
		int i = 0;
		for (i = 0; i < count; i++) {
			S[i] = "" + tree.get(i).display();
		}
		return S;
	}
	
	public Product getProduct(int index) {
		return tree.get(index);
	}
	
	public int getProductIndex(Product product) {
		int i;
		for (i = 0; i < count; i++) {
			if(Objects.equals(product.getName(), tree.get(i).getName()))
				return i;
		}
		
		return -1;
	}

	public void changeAmount(int index, int amount) {
		Product item = new Product(0);
		item = tree.get(index);
		item.setAmount(item.getAmount() + amount);
		if (item.getAmount() < 0)
			item.setAmount(0);
		if (item.getAmount() > 0)
			tree.get(index).setAmount(item.getAmount());
	}

	public void updateProduct(Product item) {
		boolean exists = false;
		if (item.getAmount() < 1)
			infoBox("Product amount must be positive!", "Error");
		else {
			for (int i = 0; i < count; i++) {
				if (tree.get(i).getName().equals(item.getName())) {
					tree.get(i).setAmount(item.getAmount());
					exists = true;
				}
			}
			if (!exists){
				tree.put(count, item);
				count++;				
			}
		}
	}

	public void updateProducts() {
		boolean first = true;
		if(tree.isEmpty())
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
				out.print("");
			} catch (IOException e) {

			}
		for (int i = 0; i < count; i++) {
			if (first) {
				try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
					out.println(tree.get(i));
				} catch (IOException e) {

				}
				first = false;
			} else
				addProduct(tree.get(i));
		}
	}

	private void addProduct(Product item) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
			out.println(item);
		} catch (IOException e) {

		}
	}
	
	public void removeProduct(int index) {
		tree.delete(index);
		count--;
	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

}
