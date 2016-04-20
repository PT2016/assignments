package pack;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter implements Runnable {

	private BlockingQueue<Client> clients;
	private AtomicInteger waitingTime = new AtomicInteger(0);
	private boolean closed = false;
	private boolean running = true;
	private AtomicInteger totalWaitingTime = new AtomicInteger(0);
	private AtomicInteger totalNrOfClients = new AtomicInteger();
	private AtomicInteger totalServingTime = new AtomicInteger();

	public Counter() {
		clients = new LinkedBlockingQueue<Client>();
	}

	@Override
	public void run() {
		while (running) {

			try {
				while (clients.size() > 0) {
					for (Client client : clients) {
						Thread.sleep(client.getProcessingTime() * 100);
						clients.remove(client);
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setRunning(boolean b) {
		running = b;
	}

	public void addClient(Client c) {
		c.setWaitingTime(waitingTime.getAndAdd(c.getProcessingTime()));
		System.out.println(c.getArrivalTime() + " " + c.getWaitingTime() + " " + c.getProcessingTime());
		totalWaitingTime.addAndGet(c.getWaitingTime());
		totalServingTime.addAndGet(c.getProcessingTime());
		totalNrOfClients.getAndIncrement();
		clients.add(c);
	}

	public Client[] getClientsArray() {
		Client[] clientsArray = new Client[clients.size()];
		clients.toArray(clientsArray);

		return clientsArray;
	}

	public BlockingQueue<Client> getClientsList() {
		return clients;
	}

	public int getQueueSize() {
		return clients.size();
	}

	public boolean isEmpty() {
		return clients.isEmpty();
	}

	public void setClosed(boolean b) {
		closed = b;
	}

	public boolean isClosed() {
		return closed;
	}

	public int getTotalWaitingTime() {
		return totalWaitingTime.get();
	}

	public int getTotalServingTime() {
		return totalServingTime.get();
	}

	public int getTotalNumberOfClients() {
		return totalNrOfClients.get();
	}

}
