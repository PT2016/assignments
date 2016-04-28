package shop;

import main.ThreadsGui;
import scheduler.Client;
import scheduler.Scheduler;

public class Shop implements Runnable {

	int finishTime;
	int maxProcessingTime;
	int minProcessingTime;
	int openCounters;
	private Scheduler scheduler;
	private ThreadsGui gui;

	public Shop(int finishTime, int maxProcessingTime, int minProcessingTime, int openCounters) {
		this.finishTime = finishTime;
		this.maxProcessingTime = maxProcessingTime;
		this.minProcessingTime = minProcessingTime;
		this.openCounters = openCounters;

		scheduler = new Scheduler();

		for (int i = 0; i < openCounters; i++) {
			Counter counter = new Counter(i);
			scheduler.addCounter(counter);
		}

		gui = new ThreadsGui(openCounters, scheduler, this);

	}

	@Override
	public void run() {
		int currentTime = 0;

		while (currentTime < finishTime) {
			currentTime++;
			int processingTime = (int) (Math.random() * (maxProcessingTime - minProcessingTime) * 2);
			int arrivalTime = (int) (currentTime + Math.random() * 3);
			Client client = new Client(arrivalTime, processingTime);
			scheduler.sendClientToCounter(client, arrivalTime);
			scheduler.serveClients();
			gui.displayData(scheduler.getCounters());

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Stopped");
		scheduler.stopCounters();
		gui.displayConclusions();

	}

	public float[] getAverageWaitingTimes() {
		float[] averageTimes = new float[openCounters];
		int i = 0;
		for (Counter c : scheduler.getCounters()) {
			averageTimes[i] = c.getAverageWaitingTime();
			i++;
		}
		return averageTimes;
	}

	public float[] getAverageServingTimes() {
		float[] averageTimes = new float[openCounters];
		int i = 0;
		for (Counter c : scheduler.getCounters()) {
			averageTimes[i] = c.getAverageServingTime();
			i++;
		}
		return averageTimes;
	}

}
