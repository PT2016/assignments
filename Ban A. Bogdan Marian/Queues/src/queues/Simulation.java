package queues;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JTextArea;

import model.Client;

public class Simulation {
	private List<SimulationQueue> queues = new ArrayList<>();
	private SimulationInput simulationInput;

	public Simulation(SimulationInput simulationInput, JTextArea textArea) {
		this.simulationInput = simulationInput;
		List<Client> clients = new ArrayList<>();
		for (int i = 0; i < simulationInput.getNrClientsField(); i++) {
			Client current = new Client("Client" + i);
			current.setServiceTime(Math.round(ThreadLocalRandom.current().nextInt(simulationInput.getServiceMinField(),
					simulationInput.getServiceMaxField() + 1) / 1000));
			current.setArrivalTime(generateHour() + Math.round(ThreadLocalRandom.current()
					.nextInt(simulationInput.getArrivalMinField(), simulationInput.getArrivalMaxField() + 1)) * 60);
			clients.add(current);
		}
		for (int i = 0; i < simulationInput.getNrQueuesField(); i++) {
			queues.add(new SimulationQueue(i, textArea));
		}
		Collections.sort(clients, new Comparator<Client>() {
			@Override
			public int compare(Client o1, Client o2) {
				return Long.compare(o1.getArrivalTime(), o2.getArrivalTime());
			}
		});

		for (Client client : clients) {
			// here we should filter hours
			getMinQueue().addClient(client);
		}

		for (SimulationQueue sq : queues) {
			new Thread(sq).start();
		}
	}

	private long generateHour() {
		int hour = ThreadLocalRandom.current().nextInt(simulationInput.getHoursFieldMin(),
				simulationInput.getHoursFieldMax() + 1);
		int minute = ThreadLocalRandom.current().nextInt(0, 61);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date d = null;
		try {
			d = sdf.parse("15/04/2016 " + hour + ":" + minute + ":00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d.getTime();
	}

	private SimulationQueue getMinQueue() {
		Collections.sort(queues, new Comparator<SimulationQueue>() {
			@Override
			public int compare(SimulationQueue sq1, SimulationQueue sq2) {
				return Long.compare(sq1.getWatingTime(), sq2.getWatingTime());
			}
		});
		return queues.get(0);
	}
}
