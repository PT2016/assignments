package Entities;

public class Task {

	// known as input
	private int arrivalTime;
	private int serviceTime;

	// have to be computed
	private int finishTime;
	private int waitingTime;

	public Task(int arrivalTime, int serviceTime) {
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
	}

	public void setFinishTime(int time) {
		finishTime = time;
	}

	public void setWaitingTime() {
		waitingTime = (finishTime - arrivalTime);
	}

	public void setArrivalTime(int time) {
		arrivalTime = time;
	}

	public void setServiceTime(int time) {
		serviceTime = time;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public String toString() {
		return ("<html>Task<br>" + arrivalTime + " - " + serviceTime + "</html>");

	}
}
