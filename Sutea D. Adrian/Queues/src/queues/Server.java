package queues;

import java.util.concurrent.*;

import gui.Gui;

public class Server implements Runnable {
	private Client client;
	private BlockingQueue<Client> queue = new ArrayBlockingQueue<Client>(5);
	private int index;
	private long endTime, crtTime;
	private Thread thread = new Thread();
	private boolean finished = false;
	private long initTime, avgWaitTime, avgServiceTime;
	private int servedClients;
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	public Server(int x) {
		crtTime = System.currentTimeMillis();
		this.endTime = crtTime + Gui.getSimulationTime();
		this.index = x;
	}

	public void run() {
		initTime = System.currentTimeMillis();
		servedClients = 0;
		while (crtTime <= endTime) {
			if (queue.isEmpty() == false) {
				try {
					client = queue.take();
					// executor.execute(client);
					thread = new Thread(client);
					Gui.showMessage("Client " + client.getID() + " is being served by server " + index);
					initTime = initTime + client.getArrivalTime();
					servedClients++;
					thread.start();
					thread.join();
					avgServiceTime += client.getServiceTime();
					avgWaitTime = avgWaitTime + (System.currentTimeMillis() - initTime);
					Gui.showMessage("Client " + client.getID() + " has been served by server " + index);
					Gui.removeFromServer(index);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			crtTime = System.currentTimeMillis();
		}
		finished = true;
		avgWaitTime = avgWaitTime / servedClients;
		avgServiceTime = avgServiceTime / servedClients;
	}

	public long getAvgWaitTime() {
		return avgWaitTime;
	}

	public long getAvgServiceTime() {
		return avgServiceTime;
	}

	public void addClient(Client c) {
		try {
			Thread.sleep(c.getArrivalTime());
			queue.put(c);
			Gui.addToServer(index, c.getID());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getNoOfClients() {
		return queue.size();
	}

	public boolean hasFinished() {
		return finished;
	}

	public int capacity() {
		return queue.remainingCapacity();
	}

}
