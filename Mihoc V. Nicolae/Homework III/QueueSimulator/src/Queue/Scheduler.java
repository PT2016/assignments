package Queue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import View.GUI;

public class Scheduler {
	private Checkout Checkout1 = new Checkout(1);
	private Checkout Checkout2 = new Checkout(2);
	private Checkout Checkout3 = new Checkout(3);
	private CustomerGenerator generator;
	int index;
	private Customer client;
	ScheduledExecutorService exec = Executors.newScheduledThreadPool(3);

	public Scheduler() {
		boolean done = false;
		long startTime = System.currentTimeMillis();
		long endTime = startTime + GUI.getSimulationTime();
		generator = new CustomerGenerator();
		Checkout1 = new Checkout(0);
		Checkout2 = new Checkout(1);
		Checkout3 = new Checkout(2);
		exec.execute(Checkout1);
		exec.execute(Checkout2);
		exec.execute(Checkout3);
		Checkout1.addCustomer(generator.getIndex(index));
		index++;
		Checkout2.addCustomer(generator.getIndex(index));
		index++;
		Checkout3.addCustomer(generator.getIndex(index));
		index++;

		while (done == false) {
			client = generator.getIndex(index);
			int min = calculateMin(Checkout1.getCustomers(), Checkout2.getCustomers(), Checkout3.getCustomers());
			if (Checkout1.getCustomers() == min) {

				if (Checkout1.getCapacity() != 0) {
					System.out.println("Client " + client.getID());
					Checkout1.addCustomer(client);
					index++;
				}
			} else {
				if (Checkout2.getCustomers() == min) {
					if (Checkout2.getCapacity() != 0) {
						System.out.println("Client " + client.getID());
						Checkout2.addCustomer(client);
						index++;
					}
				} else {
					if (Checkout3.getCustomers() == min) {

						if (Checkout3.getCapacity() != 0) {
							System.out.println("Client " + client.getID());
							Checkout3.addCustomer(client);
							index++;
						}
					}
				}
			}
			if (Checkout1.isDone() == true && Checkout2.isDone() == true && Checkout3.isDone() == true) {
				exec.shutdown();
			}
			if (exec.isTerminated() == true || startTime > endTime)
				done = true;
		}
		GUI.displayText("Shop Closed!");
		GUI.displayText("Checkout: " + 1 + " " + "has average waiting time:" + " " + Checkout1.getAverageWaitTime());
		GUI.displayText("Checkout: " + 1 + " " + "has average service time:" + " " + Checkout1.getAverageServiceTime());
		GUI.displayText("Checkout: " + 2 + " " + "has average waiting time:" + " " + Checkout2.getAverageWaitTime());
		GUI.displayText("Checkout: " + 2 + " " + "has average service time:" + " " + Checkout2.getAverageServiceTime());
		GUI.displayText("Checkout: " + 3 + " " + "has average waiting time:" + " " + Checkout3.getAverageWaitTime());
		GUI.displayText("Checkout: " + 3 + " " + "has average service time:" + " " + Checkout3.getAverageServiceTime());

	}

	public int calculateMin(int x, int y, int z) {
		int min = Integer.MAX_VALUE;
		if (x <= y)
			min = x;
		else
			min = y;
		if (z < min)
			min = z;
		return min;
	}
	/*
	 * @Override public void run() { long startTime =
	 * System.currentTimeMillis(); long endTime = startTime +
	 * GUI.getSimulationTime(); boolean done = false; while (done == false) {
	 * client = generator.getIndex(index); int min =
	 * calculateMin(Checkout1.getCustomers(), Checkout2.getCustomers(),
	 * Checkout3.getCustomers()); if (Checkout1.getCustomers() == min) { if
	 * (Checkout1.getCapacity() != 0) { System.out.println("Client " +
	 * client.getID()); Checkout1.addCustomer(client); index++;
	 * System.out.println("Client" + client.getID()); } } else { if
	 * (Checkout2.getCustomers() == min) { if (Checkout2.getCapacity() != 0) {
	 * Checkout2.addCustomer(client); index++; } } else { if
	 * (Checkout3.getCustomers() == min) { if (Checkout3.getCapacity() != 0) {
	 * Checkout3.addCustomer(client); index++; } } } } } if (Checkout1.isDone()
	 * == true && Checkout2.isDone() == true && Checkout3.isDone() == true) {
	 * exec.shutdown(); } if (exec.isTerminated() == true || startTime >
	 * endTime) done = true; }
	 */

}