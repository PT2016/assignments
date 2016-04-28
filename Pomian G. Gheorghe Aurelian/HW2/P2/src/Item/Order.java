package Item;

import java.util.Objects;

public class Order {

	private String name, date;
	
	private Product[] products = new Product[15];
	private int nr;

	public Order() {
		nr = 0;
	}
	
	public void removeProduct(int index) {
		int i;
		for(i = index; i < nr - 1; i++) {
			products[i] = products[i+1];
		}
		products[i+1] = null;
		nr--;
	}

	public void addProduct(Product product, int amount) {
		Product aux = new Product(0);
		aux.setName(product.getName());
		int i;
		boolean exist = false;
		
		for (i = 0; i < nr; i++)
			if (Objects.equals(aux.getName(), products[i].getName())) {
				products[i].setAmount(products[i].getAmount() + amount);
				exist = true;
			}
		
		if(!exist) {
			aux.setAmount(amount);
			aux.setPrice(product.getPrice());
			products[nr] = aux;
			nr++;
		}
	}
	
	public String history() {
		String S = "";
		S += display() + " | " + info();
		return S;
	}
	
	public String info() {
		String S = "";
		S += "products: ";
		int i;
		S += products[0].info();
		for (i = 1; i < nr; i++) {
			S += " / " + products[i].info();
		}
		S += " | total - " + getTotal();
		return S;
	}

	public String toString() {
		String S = "";
		S += name + " " + date + " " + nr;
		int i;
		for (i = 0; i < nr; i++) {
			S += " " + products[i];
		}
		return S;
	}


	public String[] getDisplayProducts() {
		String[] S = new String[50];
		int i = 0;
		for (i = 0; i < nr; i++) 
			S[i] = products[i].display();

		return S;
	}

	public String display() {
		String S = "";
		S += date + " - " + name;
		return S;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public float  getTotal() {
		int i;
		float total = 0;
		for (i = 0; i < nr; i++) 
			total += products[i].getPrice() * products[i].getAmount();
		
		return total;
	}

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

}
