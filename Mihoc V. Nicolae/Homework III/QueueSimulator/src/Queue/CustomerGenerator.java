package Queue;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import View.GUI;

public class CustomerGenerator {
	private ArrayList<Customer> clients = new ArrayList<Customer>();
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	Random r = new Random();
	private Runnable create = new Runnable() {
		public void run() {
			long arrival = GUI.getMinArrivalTime() + r.nextInt(GUI.getMaxArrivalTime() - GUI.getMinArrivalTime());
			int service = GUI.getMinServiceTime() + r.nextInt(GUI.getMaxServiceTime() - GUI.getMinServiceTime());
			Customer c = new Customer(clients.size(), arrival, service);
			c.setArrivalTime(arrival);
			c.setServiceTime(service);
			clients.add(c);
		}
	};
	
	public CustomerGenerator() {
		for (int i = 0; i < 100; i++)
			executor.execute(create);
	}

	public Customer getIndex(int index) {
		return clients.get(index);
	}

}
