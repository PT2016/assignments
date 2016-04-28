package Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import View.GUI;

public class Checkout implements Runnable {
	private BlockingQueue<Customer> customerlist = new ArrayBlockingQueue<Customer>(7);
	private Thread T= new Thread();
	private Customer customer;
	private boolean done = false;
	private int id = 1;
	private long finishTime, currentTime, initialTime, average_WaitTime, average_ServiceTime;
	private int nrclients;
	ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

	public Checkout(int a) {
		this.id = a;
		currentTime = System.currentTimeMillis();
		this.finishTime = currentTime + GUI.getSimulationTime();
	}
	
	public void addCustomer(Customer client) {
		try {
			customerlist.put(client);
			Thread.sleep(client.getArrivalTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Runnable holdTime= new Runnable (){
		public void run(){
			try {
				Thread.sleep(customer.getServiceTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	public void run() {
		nrclients = 0;
		initialTime = System.currentTimeMillis();
		while (currentTime <= finishTime) {
			if (customerlist.isEmpty() == false) {
				try {
					customer = customerlist.take();
					T= new Thread(holdTime);
					GUI.displayText("Customer " + customer.getID() + " is at check-out " + id);
					initialTime = initialTime + customer.getArrivalTime();
					nrclients++;
					T.start();
					T.join();
					average_ServiceTime =average_ServiceTime + customer.getServiceTime();
					average_WaitTime = average_WaitTime + (System.currentTimeMillis() - initialTime);
					GUI.displayText("Customer " + customer.getID() + " has been served at check-out " + id);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTime = System.currentTimeMillis();
		}
		done = true;
		average_WaitTime = average_WaitTime / nrclients;
		average_ServiceTime = average_ServiceTime / nrclients;
	}

	public long getAverageWaitTime() {
		return average_WaitTime;
	}

	public long getAverageServiceTime() {
		return average_ServiceTime;
	}

	public int getCustomers() {
		return customerlist.size();
	}

	public boolean isDone() {
		return done;
	}

	public int getCapacity() {
		return customerlist.remainingCapacity();
	}

}
