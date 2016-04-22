import java.util.ArrayList;
import java.util.Iterator;

public class OPDept {

	private ArrayList<Order> orders;
	
	public OPDept(){
		
		orders = new ArrayList<Order>();
	}
	
	public ArrayList<Order> getOrder(){
		
		return orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	public int getSize() {
		return orders.size();
	}

	public Iterator<Order> orderIterator() {
		return orders.iterator();
	}
	
}
