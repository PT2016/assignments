package model;

import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import view.Gui;

public class TaskGenerator implements Runnable {
	private static int currentTime = 0;
	private int minimumArrival, maximumArrival, minimumServiceTime, maximumServiceTime;
	private int simulationTime;
	private int numberOfQueues, nrOfTasksPerQueue;
	private double waitingAverage, serviceAverage, emptyQueueTimeAverage;
	private TaskScheduler taskScheduler;
	private Gui gui;
	private Server[] servers;
	private BlockingQueue<Task> tasks;
	private DecimalFormat decimalFormat;
	private volatile boolean running;

	public TaskGenerator(Gui gui, int minimumArrival, int maximumArrival, int minimumServiceTime,
			int maximumServiceTime, int simulationTime, int numberOfQueues, int nrOfTasksPerQueue, int queueClose,
			int time) {
		this.gui = gui;
		this.minimumArrival = minimumArrival;
		this.maximumArrival = maximumArrival;
		this.minimumServiceTime = minimumServiceTime;
		this.maximumServiceTime = maximumServiceTime;
		this.simulationTime = simulationTime;
		this.setNumberOfQueues(numberOfQueues);
		this.setNrOfTasksPerQueue(nrOfTasksPerQueue);
		decimalFormat = new DecimalFormat();
		taskScheduler = new TaskScheduler(numberOfQueues, nrOfTasksPerQueue, queueClose, time);
	}

	private int getRandomWithinRange(int min, int max) {
		int range = (max - min + 1);
		return ((int) (Math.random() * range) + min);
	}

	private void displayResults() {
		servers = taskScheduler.getAllServers();
		for (Server serv : servers) {
			waitingAverage += serv.getAverageWaitingTime();
			serviceAverage += serv.getAverageServiceTime();
			emptyQueueTimeAverage += serv.getEmptyQueueTime();
		}
		waitingAverage /= servers.length;
		serviceAverage /= servers.length;
		emptyQueueTimeAverage /= servers.length;
		JOptionPane.showMessageDialog(gui,
				"Results: waiting time average: " + decimalFormat.format(waitingAverage) + ", service time average: "
						+ decimalFormat.format(serviceAverage) + ", empty queue time average: "
						+ decimalFormat.format(emptyQueueTimeAverage) + ", peak hour: " + taskScheduler.getPeakHour(),
				"End of simulation", JOptionPane.INFORMATION_MESSAGE);
	}

	private void setAllTasks() {
		int nrOfTasks = simulationTime;
		tasks = new ArrayBlockingQueue<Task>(nrOfTasks);
		while (nrOfTasks > 0) {
			int arrivalTime = getRandomWithinRange(minimumArrival, maximumArrival);
			int serviceTime = getRandomWithinRange(minimumServiceTime, maximumServiceTime);
			tasks.add(new Task(arrivalTime, serviceTime));
			nrOfTasks--;
		}
	}

	private void scheduleTasksPerTime() {
		for (Task task : tasks) {
			if (task.getArrivalTime() == currentTime) {
				taskScheduler.addTask(task);
				tasks.remove(task);
			}
		}
	}

	@Override
	public void run() {
		running = true;
		setAllTasks();
		while (running) {
			scheduleTasksPerTime();
			gui.displayAction(taskScheduler.getAllServers());
			currentTime++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (currentTime == simulationTime) {
				running = false;
				System.out.println("END");
				displayResults();
			}
		}
	}

	public int getNrOfTasksPerQueue() {
		return nrOfTasksPerQueue;
	}

	public static int getCurrentTime() {
		return currentTime;
	}

	public void setNrOfTasksPerQueue(int nrOfTasksPerQueue) {
		this.nrOfTasksPerQueue = nrOfTasksPerQueue;
	}

	public int getNumberOfQueues() {
		return numberOfQueues;
	}

	public void setNumberOfQueues(int numberOfQueues) {
		this.numberOfQueues = numberOfQueues;
	}
}
