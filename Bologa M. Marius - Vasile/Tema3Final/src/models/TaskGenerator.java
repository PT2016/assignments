package models;

import java.util.ArrayList;
import java.util.Random;

public class TaskGenerator {
	private int minServingTime;
	private int maxServingTime;
	private int minArrivalTime;
	private int maxArrivalTime;
	private int simulationTime;
	private Random rand = new Random();
	private int taskCount = 0;

	public TaskGenerator(int minServingTime, int maxServingT, int minArT, int maxArT, int simT) {
		this.simulationTime = simT;
		this.maxArrivalTime = maxArT;
		this.maxServingTime = maxServingT;
		this.minServingTime = minServingTime;
		this.minArrivalTime = minArT;

	}

	public ArrayList<Task> createTasks() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		for (int currentCycle = 0; currentCycle < simulationTime; currentCycle++) {
			int arrivingTime = minArrivalTime
					+ ((maxArrivalTime == minArrivalTime) ? 0 : rand.nextInt(maxArrivalTime - minArrivalTime));
			int servintTime = getMinServingTime() + ((getMaxServingTime() == getMinServingTime()) ? 0
					: rand.nextInt(getMaxServingTime() - getMinServingTime()));
			tasks.add(new Task(taskCount, servintTime, arrivingTime));
			taskCount++;
		}
		return tasks;
	}

	public int getMinServingTime() {
		return minServingTime;
	}

	public void setMinServingTime(int minServingTime) {
		this.minServingTime = minServingTime;
	}

	public int getMaxServingTime() {
		return maxServingTime;
	}

	public void setMaxServingTime(int maxServingTime) {
		this.maxServingTime = maxServingTime;
	}

	public int getMinArrivalTime() {
		return minArrivalTime;
	}

	public void setMinArrivalTime(int minArrivalTime) {
		this.minArrivalTime = minArrivalTime;
	}

	public int getMaxArrivalTime() {
		return maxArrivalTime;
	}

	public void setMaxArrivalTime(int maxArrivalTime) {
		this.maxArrivalTime = maxArrivalTime;
	}

}
