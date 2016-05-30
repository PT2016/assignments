package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entities.Server;
import Entities.Task;
import Helper.CheckerClass;

public class TaskScheduler implements Runnable {

	private int sleepingTime;

	private int maxArrivalTime;
	private int minArrivalTime;
	private int maxServiceTime;
	private int minServiceTime;
	private int startingTime;
	private int finishTime;
	private int simulationTime;
	private int numberOfServers;
	private boolean goodValues;

	private CheckerClass checker;
	private TaskGenerator generator;

	private List<Server> serverList;

	public TaskScheduler(String startingTime, String finishTime, String maxArrival, String minArrival,
			String maxService, String minService, String nrnumberOfServers, DisplayControl controler,
			String sleepingTime) {

		goodValues = true;

		try {
			this.sleepingTime = Integer.parseInt(sleepingTime);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Error at sleeping time", "error", JOptionPane.ERROR_MESSAGE);
		}

		// check arrival and service intervals
		checkSeconds(maxArrival, "Maximum Arrival");
		checkSeconds(minArrival, "Minimum Arrival");
		checkSeconds(maxService, "Maximum Service");
		checkSeconds(minService, "Minimum Service");

		// check number of numberOfServers
		numberOfServers = 0;
		try {
			numberOfServers = Integer.parseInt(nrnumberOfServers);
		} catch (NumberFormatException e) {
			goodValues = false;
			JOptionPane.showMessageDialog(null, "Invalid format for number servers", "ERROR numeric format",
					JOptionPane.ERROR_MESSAGE);
		}

		// check the starting time
		if (checker.checkTimeFormat(startingTime)) {
			this.startingTime = (Integer.parseInt(startingTime.substring(0, 2))) * 3600
					+ (Integer.parseInt(startingTime.substring(3, 5))) * 60
					+ (Integer.parseInt(startingTime.substring(6)));
		} else {
			goodValues = false;
			JOptionPane.showMessageDialog(null, "Invalid format for starting time !", "ERROR time format",
					JOptionPane.ERROR_MESSAGE);
		}

		// check the finish time
		if (checker.checkTimeFormat(finishTime)) {
			this.finishTime = (Integer.parseInt(finishTime.substring(0, 2))) * 3600
					+ (Integer.parseInt(finishTime.substring(3, 5))) * 60 + (Integer.parseInt(finishTime.substring(6)));
		} else {
			goodValues = false;
			JOptionPane.showMessageDialog(null, "Invalid format for finish time !", "ERROR time format",
					JOptionPane.ERROR_MESSAGE);
		}

		if (goodValues) {
			simulationTime = this.startingTime;
			generator = new TaskGenerator();
			serverList = new ArrayList<Server>();

			for (int i = 0; i < numberOfServers; i++) {
				serverList.add(new Server(i, this.simulationTime, this.finishTime, controler, this.sleepingTime));
			}
		}
	}

	private void checkSeconds(String seconds, String message) {

		checker = new CheckerClass();
		if (checker.checkSecondsFormat(seconds)) {
			switch (message) {
			case "Maximum Arrival":
				maxArrivalTime = Integer.parseInt(seconds);
				break;
			case "Minimum Arrival":
				minArrivalTime = Integer.parseInt(seconds);
				break;
			case "Maximum Service":
				maxServiceTime = Integer.parseInt(seconds);
				break;
			case "Minimum Service":
				minServiceTime = Integer.parseInt(seconds);
				break;
			}

		} else {
			goodValues = false;
			JOptionPane.showMessageDialog(null, "Invalid format for " + message + " time !", "ERROR seconds format",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void sendTaskToServer(Task task, int sendingTime) {

		// best server is the one with the smallest amount of tasks to serve
		Server bestServer = serverList.get(0);
		int minSize = bestServer.getTaskQueue().size();
		for (Server server : serverList) {
			if (server.getTaskQueue().size() < minSize)
				bestServer = server;
		}
		bestServer.receiveTask(task, sendingTime);
	}

	@Override
	public void run() {
		if (goodValues) {

			for (Server server : serverList) {
				server.start();
			}

			Task currentTask = generator.generateTask(minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
			currentTask.setArrivalTime(currentTask.getArrivalTime() + simulationTime);
			System.out.println(currentTask + " is preparing");

			while (simulationTime <= finishTime) {
				System.out.println(simulationTime);

				if (currentTask.getArrivalTime() == simulationTime) {
					sendTaskToServer(currentTask, simulationTime);

					currentTask = generator.generateTask(minArrivalTime, maxArrivalTime, minServiceTime,
							maxServiceTime);
					currentTask.setArrivalTime(currentTask.getArrivalTime() + simulationTime);
					System.out.println(currentTask + " is preparing");
				}

				try {
					Thread.sleep(sleepingTime);
				} catch (InterruptedException e) {
					System.out.println("Sleeping error in TaskScheduler");
				}

				simulationTime++;
			}
		}
	}
}
