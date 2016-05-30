package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

import javax.sql.rowset.CachedRowSet;

import utilities.Constants;
import view.QueuePanel;
import view.SimulationFrame;

public class QueuesManager {

	private SimulationFrame simulationFrame;

	private CashRegister[] cashRegisters;
	private int nrOfQueues;
	private Thread[] tCashRegisters;
	private Date d, startTime, endTime, currentTime;

	private int nrOfClients = 0;

	private long[] input = new long[15];

	public QueuesManager(int nrOfQueues) {

		this.input = readFromFile(Constants.FILE_INPUT);

		this.setNrOfQueues(nrOfQueues);

		startTime = new Date(System.currentTimeMillis());
		startTime.setSeconds((int) (startTime.getSeconds() - input[7]));
		startTime.setMinutes((int) (startTime.getMinutes() - input[6]));
		startTime.setHours((int) (startTime.getHours() - input[5]));
		endTime = new Date(System.currentTimeMillis());
		endTime.setSeconds((int) (startTime.getSeconds() + input[10]));
		endTime.setMinutes((int) (startTime.getMinutes() + input[9]));
		endTime.setHours((int) (startTime.getHours() + input[8]));

		// create a generator for clients
		this.setCurrentTime(new Date());

		this.cashRegisters = new CashRegister[nrOfQueues];
		this.tCashRegisters = new Thread[nrOfQueues];

		for (int i = 0; i < cashRegisters.length; i++) {

			cashRegisters[i] = new CashRegister(i, this);
			// start threads
			tCashRegisters[i] = new Thread(cashRegisters[i], "Thread" + i);
			tCashRegisters[i].start();

		}

		this.simulationFrame = new SimulationFrame(cashRegisters);
		this.startSimulation();

	}

	public void startSimulation() {
		
		ClientGenerator cg = new ClientGenerator(input[0], input[1], input[2], input[3], input[4], this);
	

	}

	public CashRegister getCashRegisterWithId(int id) {

		return cashRegisters[id];
	}

	/**
	 * Chooses the minimal queue for the client to wait
	 * 
	 * @param client
	 * @throws InterruptedException
	 */
	public void placeClientAtAQueue(Client client) throws InterruptedException {

		d = new Date();
		client.setArrivingTime(d);
		long min = Long.MAX_VALUE;
		int minIndex = 0;

		for (int i = 0; i < cashRegisters.length; i++) {

			if (cashRegisters[i].getProcessingTime() < min && cashRegisters[i].getActive()) {
				min = cashRegisters[i].getProcessingTime();
				minIndex = i;
			}
		}

		simulationFrame.getLoggerPanel().getTaLogger().append(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds()
				+ " PLACE CLIENT: " + client.toString() + " AT QUEUE " + (minIndex + 1) + "\n");
		cashRegisters[minIndex].addClient(client);

	}

	public void endSimulation() {

		for (int i = 0; i < cashRegisters.length; i++) {

			cashRegisters[i].setActive(false);
		}
	}

	public boolean isOpen() {

		Date d = new Date(System.currentTimeMillis());
		return (startTime.before(d) && endTime.after(d));

	}

	public int getNrOfQueues() {
		return nrOfQueues;
	}

	public void setNrOfQueues(int nrOfQueues) {
		this.nrOfQueues = nrOfQueues;
	}

	public SimulationFrame getSimulationFrame() {
		return this.simulationFrame;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long[] readFromFile(String filename) {
		Scanner file = null;
		int i = 0;
		long[] input = new long[15];
		try {
			file = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (file.hasNext()) {
			if (file.hasNextLong()) {
				input[i++] = file.nextLong();
			} else
				file.next();
		}

		return input;
	}

	public void blockQueue1() throws InterruptedException {

		cashRegisters[1].closeCashRegister();
		Client client;
		BlockingQueue<Client> freeClients = cashRegisters[1].getClientsQueue();
	

		Iterator<Client> itr = freeClients.iterator();
		while (itr.hasNext()) {

			client = itr.next();
				
			if (client != null) {

				placeClientAtAQueue(client);
				
				cashRegisters[1].getClientsQueue().take();
				
			}
				
		}
	}

	

}
