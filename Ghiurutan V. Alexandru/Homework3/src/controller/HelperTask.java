package controller;

public class HelperTask {
	public int getWaitingTime(int arrivalTime, int finishTime, int serviceTime) {
		return (finishTime - arrivalTime - serviceTime);
	}
}
