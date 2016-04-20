package pack;

public class Shop implements Runnable {

	int finishTime = 50;
	int maxProcessingTime = 5;
	int minProcessingTime = 1;
	int openCounters = 3;
	private Scheduler scheduler;
	private Gui gui;

	public Shop() {
		scheduler = new Scheduler();
		for (int i = 0; i < openCounters; i++) {
			Counter counter = new Counter();
			scheduler.addCounter(counter);
		}
		gui = new Gui(openCounters, scheduler);

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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Stopped");
		scheduler.stopCounters();
		displayAverageWaitingTime();
		displayAverageServingTime();
		displayPeakHour();
		gui.displayConclusions();

	}

	public void displayAverageWaitingTime() {
		System.out.printf("\nAverage waiting time = %f\n", scheduler.getAverageWaitingTime());
	}

	public void displayAverageServingTime() {
		System.out.printf("\nAverage serving time = %f\n", scheduler.getAverageServingTime());
	}

	public void displayPeakHour() {
		System.out.printf("\nPeak hour = %d\n", scheduler.getPeakHour());
	}

}
