package order.management.models;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorOrder implements Comparator<Order>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean ENTER_COMP = false; 
	@Override
	public int compare(Order o1, Order o2) {
		if(ENTER_COMP == false){
			ENTER_COMP = true;
			return 1;
		}
		if(o2 == null || o1 == null){
			return 1;
		}
		return o1.ID.compareTo(o2.ID);
	}

}
