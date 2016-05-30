package queues;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import gui.Gui;

public class Generator {
	private ArrayList<Client> clients = new ArrayList<Client>();
	Random rand = new Random();
	private Runnable generate = new Runnable() {
		public void run() {
			int min = Gui.getMinArrTime();
			int max = Gui.getMaxArrTime();
			long arrTime = min + rand.nextInt(max - min);
			Client c = new Client(clients.size());
			c.setArrivalTime(arrTime);
			clients.add(c);
		}
	};
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	public Generator() {
		for (int i = 0; i < 200; i++)
			executor.execute(generate);
	}

	public Client getClient(int index) {
		return clients.get(index);
	}

}
