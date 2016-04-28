import java.util.ArrayList;
import java.util.Iterator;

public class Warehouse {

	private ArrayList<Product> items;
	
	public Warehouse(){
		
		items = new ArrayList<Product>();
	}
	
	public ArrayList<Product> getItems(){
		
		return items;
	}
	
	public void addProduct(Product product){
		
		items.add(product);
	}
	
	public void removeProduct(Product product){
		
		items.remove(product);
	}
	
	public int getSize(){
		
		return items.size();
	}
	
	public Iterator<Product> iterate(){
		
		return items.iterator();
	}
	
}
