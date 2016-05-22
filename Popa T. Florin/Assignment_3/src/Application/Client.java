package Application;

public class Client {

	private int noId;
	private int arrivalTime;
	private int processingTime;
	
	public Client(int noId, int arrivalTime, int processingTime){
		this.noId=noId;
		this.arrivalTime=arrivalTime;
		this.processingTime=processingTime;
	}
	
	public int getNoId() {
		return noId;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public int getProcessingTime() {
		return processingTime;
	}
	
}
