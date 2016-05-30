package models;

import java.util.ArrayList;
import java.util.Random;

import views.MainFrame;
import views.SimulationPanel;

public class TaskScheduler implements Runnable {
	private int serverNumber = 0;
	private int simulationTime = 0;
	private static int taskCount;
	private int currentCycle = 0;
	private static int waitingTime;
	private int averageServiceTime[];
	private int emptyTime[];
	private int maxTasks = 0;
	private int maxRushHour = 0;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private Random rand = new Random();
	private SimulationPanel panel;
	private Server[] servers;
	private MainFrame frame;
	volatile String textLog = "";

	public TaskScheduler(TaskGenerator generator, int queueCount, int simulationTime, MainFrame frame) {
		this.serverNumber = queueCount;
		this.simulationTime = simulationTime;
		servers = new Server[this.serverNumber];
		this.tasks = generator.createTasks();
		initializeServers(queueCount);
		this.frame = frame;
	}

	public void initializeServers(int size) {
		for (int i = 0; i < size; i++) {
			servers[i] = new Server(i, this, 15);
		}
	}

	public Server[] getServers() {
		return servers;
	}

	public String getLogText() {
		synchronized (textLog) {
			return textLog;
		}
	}

	public int getCurrentCycle() {
		return currentCycle;
	}

	public static void addWaitingTime(int x) {
		waitingTime += x;
	}

	public int getNrOfTasks() {
		int sum = 0;
		for (Server curr : servers) {
			sum = sum + curr.queueSize();
		}

		return sum;
	}

	public void updateRushHour() {
		int tasks = 0;
		for (int i = 0; i < servers.length; i++) {
			tasks += servers[i].queueSize();
		}
		if (tasks > maxTasks) {
			maxTasks = tasks;
			maxRushHour = currentCycle;
		}
	}

	public void updateEmptyTime() {
		emptyTime = new int[servers.length];
		for (int j = 0; j < servers.length; j++) {
			emptyTime[j] = 0;
		}
		for (int i = 0; i < servers.length; i++) {
			emptyTime[i] += servers[i].getTotalWaitingTime();
		}
	}

	public void run() {
		frame.setGraphPanel(panel = new SimulationPanel(this));
		startServers();
		simulate();
		stopServers();
		updateEmptyTime();
		Utilities.showResultsPopUp(this, frame);
	}

	public void simulate() {
		averageServiceTime = new int[servers.length];
		for (currentCycle = 0; currentCycle < simulationTime; currentCycle++) {
			frame.setCycleCountField(Integer.toString(currentCycle));
			if (currentCycle == 15 && getNrOfTasks() < 20) {
				int random = rand.nextInt((serverNumber - 1)) + 1;
				Utilities.appendToLog(this, "-->Server " + random + " is closing\n");
				Task[] t = servers[random].getTasks();
				servers[random].stopExecution();
				for (int i = 0; i < t.length; i++) {
					Server server = Utilities.getOptimalServer(this);
					server.addTasks(t[i]);
					panel.repaint();
					servers[random].deleteTasks();
					Utilities.appendToLog(this, "<-->Task " + t[i].getTaskID() + " from server " + random
							+ " was moved to the server " + server.getID() + "\n");
				}
			}
			for (Task task : tasks) {
				if (task.getArrivalTime() == currentCycle) {
					Server optimalServer = Utilities.getOptimalServer(this);
					optimalServer.addTasks(task);
					averageServiceTime[optimalServer.getID()] = averageServiceTime[optimalServer.getID()]
							+ task.getServingTime();
					Utilities.appendToLog(this,
							"-->Task " + task + " added at server #" + optimalServer.getID() + "\n");
				}
			}
			updateRushHour();
			try {
				frame.setLog(this.getLogText());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panel.repaint();
		}
	}

	public void stopServers() {
		for (Server sever : servers) {
			sever.stopExecution();
		}
	}

	public void startServers() {
		for (Server sever : servers) {
			Thread th = new Thread(sever);
			th.start();
		}
	}

	public int getTaskCount() {
		return taskCount;
	}

	public static float getWaitingTime() {
		return (float) waitingTime;
	}

	public int[] getAverageServiceTime() {
		return averageServiceTime;
	}

	public int[] getEmptyTime() {
		return emptyTime;
	}

	public int getMaxRushHour() {
		return maxRushHour;
	}

	public int getSimulationTime() {
		return simulationTime;
	}

	public MainFrame getFrame() {
		return frame;
	}

}
