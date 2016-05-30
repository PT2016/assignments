package model;

import controller.HelperTask;

public class Task {
	private String name;
	private static int number = 0;
	private int arrivalTime;
	private int serviceTime;
	private int finishTime;
	private int waitingTime;
	private HelperTask helper;

	public Task(int arrivalTime, int serviceTime) {
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.name = "Task " + number++;
		helper = new HelperTask();
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public void computeWaitingTime() {
		this.waitingTime = helper.getWaitingTime(arrivalTime, finishTime, serviceTime);
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	@Override
	public String toString() {
		return String.format("%s arrival time: %d, service time: %d.\n", name, arrivalTime, serviceTime);
	}
}
