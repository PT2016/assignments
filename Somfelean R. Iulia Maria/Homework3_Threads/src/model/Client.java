package model;

import java.util.Date;

/**
 * @author iulia
 *
 *         The client represents the task to bo processed by the server, in our
 *         case by the cash register to which it was dispatched.
 */
public class Client {

	private int id;
	private String firstName;
	private String lastName;
	private long arrivalTime;
	private long finishTime;
	private long serviceTime;
	private long waitingTime;
	private Date arrivingTime;

	public Client(int id, String firstName, String lastName, long arrivalTime, long serviceTime) {

		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setArrivalTime(arrivalTime);
		setServiceTime(serviceTime);

	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}

	public long getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public long getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		return String.format(id + " " + lastName + " " + firstName + " Arrival time: " + arrivalTime + " "
				+ " Service Time: " + serviceTime).toString();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getArrivingTime() {
		return arrivingTime;
	}

	public void setArrivingTime(Date arrivingTime) {
		this.arrivingTime = arrivingTime;
	}
}
