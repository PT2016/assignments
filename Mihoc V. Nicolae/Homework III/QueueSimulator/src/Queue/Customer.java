package Queue;

public class Customer {
	private long arrivalTime;
	private int serviceTime;
	private int id;

	public Customer(int x, long arrivalTime, int serviceTime) {
		this.id = x;
		this.serviceTime = serviceTime;
		this.arrivalTime = arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(long arrTime) {
		this.arrivalTime = arrTime;
	}

	public int getID() {
		return id;
	}

	public void setServiceTime(int service) {
		this.serviceTime=service;
		
	}
}