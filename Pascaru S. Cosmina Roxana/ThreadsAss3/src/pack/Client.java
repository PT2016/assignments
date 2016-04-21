package pack;

public class Client {
	private int arrivalTime;
	private int processingTime;
	private int waitingTime;
	
	public Client(int arrivalTime, int processingTime){
		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
	}
	
	public int getProcessingTime(){
		return processingTime;
	}
	
	public void setWaitingTime(int time){
		waitingTime = time;
	}
	
	public int getWaitingTime(){
		return waitingTime;
	}
	
	public int getArrivalTime(){
		return arrivalTime;
	}
	
	public String toString(){
		return String.format("a:%d / p:%d / w:%d", arrivalTime,processingTime,waitingTime);
	}
}
