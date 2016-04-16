package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {
	private BlockingQueue<Task> queue;
	private static int number;
	private String name;
	private int serviceTimeSum;
	private int totalNrOfTasksEnd;
	private int waitingTimeSum;
	private int emptyQueueTime;
	private Task currentTask;

	public Server(int nrOfTasks) {
		queue = new ArrayBlockingQueue<Task>(nrOfTasks);
		this.name = "Server " + number++;
		/*
		 * emptyQueueTime = new AtomicInteger(0); waitingTimeSum = new
		 * AtomicInteger(0); serviceTimeSum = new AtomicInteger(0);
		 */
	}

	public double getAverageServiceTime() {
		return serviceTimeSum / totalNrOfTasksEnd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAverageWaitingTime() {
		return waitingTimeSum / totalNrOfTasksEnd;
	}

	public double getEmptyQueueTime() {
		return emptyQueueTime;
	}

	public void addTask(Task task) {
		queue.add(task);
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (queue.isEmpty()) {
					emptyQueueTime++;
				}
				currentTask = queue.take();
				Thread.sleep(currentTask.getServiceTime() * 1000);
				currentTask.setFinishTime(TaskGenerator.getCurrentTime());
				currentTask.computeWaitingTime();
				waitingTimeSum += currentTask.getWaitingTime();
				totalNrOfTasksEnd++;
				serviceTimeSum += currentTask.getServiceTime();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Server other = (Server) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Task[] getTasks() {
		Task[] tasks = queue.toArray(new Task[queue.size()]);
		return tasks;
	}

	public int getNrOfTasks() {
		return queue.size();
	}

	public String getName() {
		return this.name;
	}
}
