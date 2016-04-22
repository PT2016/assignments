package models;

public class Task {
	private int servingTime = 0;
	private int arrivalTime = 0;
	private final int taskID;

	public Task(int taskID, int servingTime, int arrivalTime) {
		this.servingTime = servingTime;
		this.arrivalTime = arrivalTime;
		this.taskID = taskID;

	}

	public int getServingTime() {
		return servingTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	@Override
	public String toString() {
		return "[ID=" + getTaskID() + " AT=" + arrivalTime + " ST=" + servingTime + "]";
	}

	public int getTaskID() {
		return taskID;
	}

}
