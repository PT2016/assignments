package Model;

/**
 * POJO class for Task
 */
public class Task {
	private int arrivalTime;
	private int serviceTime;
	private int finishTime;
	private int waitingTime;

	/* Each task is generated with an arrival time and a service time */
	public Task(int arrivalTime, int serviceTime) {
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrTime) {
		arrivalTime = arrTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int arrTime) {
		this.serviceTime = arrTime;
	}
	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime() {
		this.waitingTime = this.finishTime - this.arrivalTime - this.serviceTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public String toString() {
		return ("Task with arrival time: " + arrivalTime + " " + " and service time: " + serviceTime+ "---> ");
	}
	
	public String prettyPrint(){
		return ("Task with A:"+ arrivalTime + " S:"+ serviceTime);
	}

}
