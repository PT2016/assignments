package pack;

import java.util.ArrayList;


public class Scheduler {

	private ArrayList<Counter> counters;
	private int totalWaitingTime=0;
	private int totalServingTime =0;
	private int totalNrOfClients =0;
	int []countClients =new int[100];
	private int peakHour;

	public Scheduler() {
		counters = new ArrayList<Counter>();
		for (Counter c : counters) {
			Thread counterThread = new Thread(c);
			counterThread.start();
		}
	}

	public void sendClientToCounter(Client c,int arrivalTime) {
		countClients[arrivalTime]++;
		int minNrOfClients = 9999;
		Counter pickedCounter = null;
		for (int i = 0; i < counters.size(); i++) {
			if (!counters.get(i).isClosed() && counters.get(i).getQueueSize() < minNrOfClients) {
				minNrOfClients = counters.get(i).getQueueSize();
				pickedCounter = counters.get(i);
			}
		}
		pickedCounter.addClient(c);
	}

	public void addCounter(Counter c) {
		counters.add(c);
	}

	public void serveClients() {
		for (Counter c : counters) {
			Thread counterThread = new Thread(c);
			counterThread.start();
		}
	}
	
	public void stopCounters(){
		for (Counter c : counters) {
			c.setRunning(false);
		}
	}

	public ArrayList<Counter> getCounters() {
		return counters;
	}
	
	public float getAverageWaitingTime(){
		for(Counter c: counters){
			totalWaitingTime +=c.getTotalWaitingTime();
			totalNrOfClients +=c.getTotalNumberOfClients();
		}
		
		return (float)totalWaitingTime/totalNrOfClients;
	}
	
	public float getAverageServingTime(){
		for(Counter c: counters){
			totalServingTime +=c.getTotalServingTime();
		}
		
		return (float)totalServingTime/totalNrOfClients;
	}
	
	public int getPeakHour(){
		int maxClients = -1234;
		
		for(int i=0;i<countClients.length;i++){
			if(countClients[i]>maxClients){
				maxClients = countClients[i];
				peakHour = i;
			}
		}
		return peakHour;
	}
}
