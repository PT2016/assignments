package model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import view.Gui;

public class TaskGenerator {
	private static int currentTime = 0;
	private int minimumArrival, maximumArrival, minimumServiceTime, maximumServiceTime;
	private int simulationTime;
	private int numberOfQueues, nrOfTasksPerQueue;
	private Timer timer;
	private TaskScheduler taskScheduler;
	private Gui gui;

	public TaskGenerator(Gui gui, int minimumArrival, int maximumArrival, int minimumServiceTime,
			int maximumServiceTime, int simulationTime, int numberOfQueues, int nrOfTasksPerQueue) {
		this.gui = gui;
		this.minimumArrival = minimumArrival;
		this.maximumArrival = maximumArrival;
		this.minimumServiceTime = minimumServiceTime;
		this.maximumServiceTime = maximumServiceTime;
		this.simulationTime = simulationTime;
		this.numberOfQueues = numberOfQueues;
		this.setNrOfTasksPerQueue(nrOfTasksPerQueue);
		taskScheduler = new TaskScheduler(numberOfQueues, nrOfTasksPerQueue);
		timer = new Timer();
	}

	private int getRandomWithinRange(int min, int max) {
		int range = (max - min + 1);
		return ((int) (Math.random() * range) + min);
	}

	public void start() {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (currentTime == simulationTime) {
					timer.cancel();
				}
				int arrivalTime = getRandomWithinRange(minimumArrival, maximumArrival);
				int serviceTime = getRandomWithinRange(minimumServiceTime, maximumServiceTime);
				taskScheduler.addTask(new Task(arrivalTime, serviceTime));
				gui.displayAction(taskScheduler.getAllServers());
				currentTime++;
			}
		}, 1000, 1000);
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
}
