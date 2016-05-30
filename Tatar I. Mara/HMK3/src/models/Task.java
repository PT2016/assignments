package models;

public class Task {
	int clientNo;
	int arrivalTime;
	int serviceTime;
	int waitingTime;
	int finishTime;

	public Task(int arrivalTime, int serviceTime) {
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;

	}

	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}

	public int getClientNo() {
		return clientNo;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int processTime) {
		this.serviceTime = processTime;
	}

	public String toString() {
		return "Client " + String.valueOf(clientNo) + " arrives at " + String.valueOf(arrivalTime) + " ST "
				+ String.valueOf(serviceTime) + " leaves at " + String.valueOf(finishTime);
	}

}
