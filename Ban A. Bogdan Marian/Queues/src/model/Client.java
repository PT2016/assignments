package model;

public class Client {
	private String name;
	private long arrivalTime;
	private long waitingTime;
	private long serviceTime;
	private long terminationTime;

	public Client(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public long getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}

	public long getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public long getTerminationTime() {
		return terminationTime;
	}

	public void setTerminationTime(long terminationTime) {
		this.terminationTime = terminationTime;
	}

}
