package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * Finds the server with the minimum number of tasks and adds there the new
 * generated task
 * 
 * @author Dariana Lupea
 *
 */
public class TaskScheduler implements Runnable {
	private List<Server> servers;
	private TaskGenerator taskGen;
	private final static Logger LOGGER = Logger.getLogger(TaskScheduler.class.getName());
	private int simulationTime;
	private int currentTime;
	private int noOfServers;

	private double waitingAvgTime, serviceAvgTime;
	private int maxNumberOfTasks, peakHour;

	public TaskScheduler(int noOfServers, TaskGenerator taskGen, int simulationTime) {
		this.noOfServers = noOfServers;
		this.servers = new ArrayList<Server>(noOfServers);
		this.taskGen = taskGen;
		this.simulationTime = simulationTime;
		this.currentTime = 0;
		for (int i = 0; i < noOfServers; i++) {
			servers.add(new Server(this.simulationTime, i));

		}
		maxNumberOfTasks = 0;
	}

	public Server getAvailableServer() {
		int minTasks = 10000;
		int currTasksNo;
		int availableServer = 0;

		for (int i = 0; i < servers.size(); i++) {
			currTasksNo = (servers.get(i)).getNumberOfTasks();
			if (currTasksNo < minTasks) {
				minTasks = currTasksNo;
				availableServer = i;
			}
		}
		LOGGER.info("Found available server");
		return servers.get(availableServer);
	}

	public void activateServers() {
		for (int i = 0; i < noOfServers; i++) {
			Thread th = new Thread(servers.get(i));
			th.start();
		}

	}

	/*
	 * Select the time at which the maximum number of tasks are at a given server
	 */
	public int getPeakHour(int time) {
		for (Server s : servers) {
			if (s.getNumberOfTasks() > maxNumberOfTasks) {
				maxNumberOfTasks = s.getNumberOfTasks();
				peakHour = time;
			}

		}
		return peakHour;
	}

	@Override
	public void run() {

		activateServers();

		Task currentTask = taskGen.generateTask();
		currentTask.setArrivalTime(currentTask.getArrivalTime() + currentTime);

		while (currentTime <= simulationTime) {
			System.out.println("Current time: " + currentTime);

			if (currentTask.getArrivalTime() == currentTime) {
				Server s = getAvailableServer();
				s.addTaskToServer(currentTask, currentTime);
				getPeakHour(currentTime);
				currentTask = taskGen.generateTask();
				currentTask.setArrivalTime(currentTask.getArrivalTime() + currentTime);
			}
			LOGGER.info(currentTask + "Task ready to go to server...");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				LOGGER.info("Thread cannot be sent to sleep...");
			}
			currentTime++;
		}
		if (currentTime > simulationTime) {
			showResults();
		}
		
	}

	public void showResults() {
		for (Server s : servers) {
			waitingAvgTime += s.getAverageWaitingTime();
			serviceAvgTime += s.getAverageServiceTime();
		}
		waitingAvgTime /= noOfServers;
		serviceAvgTime /= noOfServers;
		System.out.println("The average waiting time is: " + waitingAvgTime + "\nThe average service time is: "
				+ serviceAvgTime + "\nThe peak hour is: " + peakHour);
	}

}
