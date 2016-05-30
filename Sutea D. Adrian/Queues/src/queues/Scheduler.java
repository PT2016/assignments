package queues;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import gui.Gui;

public class Scheduler {
	private Server server0 = new Server(0);
	private Server server1 = new Server(1);
	private Server server2 = new Server(2);
	private Generator generator;
	private Client client;
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

	public Scheduler() {
		int index = 0;
		boolean finished = false;
		long startTime = System.currentTimeMillis();
		long endTime = startTime + Gui.getSimulationTime();
		generator = new Generator();
		server0 = new Server(0);
		server1 = new Server(1);
		server2 = new Server(2);
		executor.execute(server0);
		executor.execute(server1);
		executor.execute(server2);
		server0.addClient(generator.getClient(index));
		index++;
		server1.addClient(generator.getClient(index));
		index++;
		server2.addClient(generator.getClient(index));
		index++;
		while (finished == false) {
			int min = getMinimum(server0.getNoOfClients(), server1.getNoOfClients(), server2.getNoOfClients());
			if (server0.getNoOfClients() == min) {
				if (server0.capacity() != 0) {
					client = generator.getClient(index);
					server0.addClient(client);
					index++;
				}
				;
			} else {
				if (server1.getNoOfClients() == min) {
					if (server1.capacity() != 0) {
						client = generator.getClient(index);
						server1.addClient(client);
						index++;
					}
				} else {
					if (server2.getNoOfClients() == min) {
						if (server2.capacity() != 0) {
							client = generator.getClient(index);
							server2.addClient(client);
							index++;
						}
					}
				}
			}
			if (server0.hasFinished() == true && server1.hasFinished() == true && server2.hasFinished() == true) {
				executor.shutdown();
			}
			if (executor.isTerminated() == true || startTime > endTime)
				finished = true;
		}
		Gui.showMessage("Simulation finished");
		Gui.showMessage("Server " + 0 + " average waiting time " + server0.getAvgWaitTime() + " average service time "
				+ server0.getAvgServiceTime());
		Gui.showMessage("Server " + 1 + " average waiting time " + server1.getAvgWaitTime() + " average service time "
				+ server1.getAvgServiceTime());
		Gui.showMessage("Server " + 2 + " average waiting time " + server2.getAvgWaitTime() + " average service time "
				+ server2.getAvgServiceTime());
	}

	public int getMinimum(int a, int b, int c) {
		int min;
		if (a <= b)
			min = a;
		else
			min = b;
		if (c < min)
			min = c;
		return min;
	}

}
