package queues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import model.Client;

public class SimulationQueue implements Runnable {
	private int id;
	private List<Client> clients = new ArrayList<>();
	private List<Client> finishedClients = new ArrayList<>();
	private JTextArea textArea;

	/**
	 * @param textArea
	 * 
	 */
	public SimulationQueue(int id, JTextArea textArea) {
		this.id = id;
		this.textArea = textArea;
	}

	public void addClient(Client client) {
		clients.add(client);
	}

	@Override
	public void run() {
		while (!clients.isEmpty()) {
			try {
				Iterator<Client> it = clients.iterator();
				while (it.hasNext()) {
					printSim();
					Client client = it.next();
					client.setWaitingTime(getWatingTime(client) + client.getServiceTime());
					client.setTerminationTime(client.getArrivalTime() + client.getWaitingTime() * 60 * 1000);
					printClient(client);
					long sleep = client.getServiceTime() * 1000;
					finishedClients.add(client) ;
					it.remove();
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		textArea.append("Queue " + id + " is now empty \n");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		summary();
		
	}
	
	private void summary(){
		textArea.append("\n Here is the summary for queue " + id + ":\nAverage waiting time: " + getAverageWaitingTime()
		+ "\n Average service time:" + getAverageServiceTime() + "\n Peak hour: " + getPeakHour() + "\n");
	}

	private StringBuilder printSim() {
		StringBuilder result = new StringBuilder("@ queue " + id + " we have " + clients.size() + " clients : \n");
		for (Client client : clients) {
			result.append(client.getName() + "\n");
		}
		textArea.append(result.toString());
		return result;
	}

	public long getWatingTime(Client currentClient) {
		long result = 0;
		for (Client client : finishedClients) {
			if (!(client.equals(currentClient))) {
				if (client.getTerminationTime() != 0 && client.getTerminationTime() > currentClient.getArrivalTime()) {
					result += (client.getTerminationTime() - currentClient.getArrivalTime())/60000;
				}

			}
		}
		return result;
	}

	public float getAverageWaitingTime() {
		if (finishedClients.size() == 0) {
			return -1;
		}
		float average = 1;
		for (Client client : finishedClients) {
			average += client.getWaitingTime();
		}
		return average / finishedClients.size();
	}

	public float getAverageServiceTime() {
		if (finishedClients.size() == 0) {
			return -1;
		}
		float service = 1;
		for (Client client : finishedClients) {
			service += client.getServiceTime();
		}
		return service / finishedClients.size();
	}

	public int getPeakHour() {
		Map<Integer, Integer> map = new HashMap<>();
		for (Client client : finishedClients) {
			int h = Util.getHourFromDate(client.getArrivalTime());
			if (map.containsKey(h)) {
				int newValue = map.get(h) + 1;
				map.put(h, newValue);
			} else {
				map.put(h, 1);
			}
		}
		int max = 0;
		int hour = -1;
		for (Integer value : map.keySet()) {
			if (map.get(value) > max) {
				max = map.get(value);
				hour = value;
			}
		}
		return hour;
	}

	public long getWatingTime() {
		long result = 0;
		for (Client client : clients) {
			result += client.getServiceTime();
		}
		return result;
	}

	public int getId() {
		return id;
	}

	public void printClient(Client client) {
		System.out.println(client.getName() + " entered @ " + Util.convertToString(client.getArrivalTime())
				+ " has been at @ queue " + this.id + " waiting in total for " + client.getWaitingTime() +" minute(s) "
				+ " being serviced for " + client.getServiceTime() + " minute(s) , leaving at "
				+ Util.convertToString(client.getTerminationTime()) + "\n");
	}
}
