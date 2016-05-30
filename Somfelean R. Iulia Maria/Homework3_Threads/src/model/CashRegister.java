package model;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import utilities.Constants;

/**
 * @author iulia
 *
 *         This represents the cash register, the server that processes clients,
 *         so it's a thread.
 */

public class CashRegister implements Runnable {

	private int id;
	private boolean active;
	private QueuesManager queuesManager;
	private long processingTime = 0; // estimated time to perform the tasks of
										// the clients that are waiting at the
										// cash register
	private long processingTimeFinal = 0;
	private long arrivingTimeFinal = 0;
	private long waitingTime = 0;
	private long peakMinutes = 0;
	private int nrClientsPeakHour = 0;
	private int maxClientsInPeakHour;
	private BlockingQueue<Client> clientsQueue;
	private int nrClients = 0;
	private Date peakTime = new Date();

	public CashRegister(int id, QueuesManager queuesManager) {

		this.id = id;
		this.queuesManager = queuesManager;

		this.processingTime = 0;
		this.clientsQueue = new ArrayBlockingQueue<Client>(1024);

		this.setActive(true);
	}

	/**
	 * Adds a new client to the queue
	 * 
	 * @param client
	 * @throws InterruptedException
	 */
	public void addClient(Client client) throws InterruptedException {

		clientsQueue.put(client);
		processingTime += client.getServiceTime();
		++nrClients;

		queuesManager.getSimulationFrame().draw(id, this);

	}

	public Client getNextServedClient() throws InterruptedException {

		return clientsQueue.take();

	}

	public BlockingQueue<Client> getClientsQueue() {

		return this.clientsQueue;
	}

	public void run() {

		Client client;
		peakTime = new Date();

		while (queuesManager.isOpen()) {

			if (!queuesManager.getCashRegisterWithId(id).getClientsQueue().isEmpty()) {
				try {
					client = queuesManager.getCashRegisterWithId(id).getClientsQueue().peek();

					processingTimeFinal += client.getServiceTime();
					arrivingTimeFinal += client.getArrivalTime();

					if (peakMinutes + client.getArrivalTime() < Constants.PEAK_CONSTANT) {

						peakMinutes += client.getArrivalTime();
						nrClientsPeakHour++;

					} else {
						if (nrClientsPeakHour > maxClientsInPeakHour) {
							maxClientsInPeakHour = nrClientsPeakHour;
							peakTime.setTime(System.currentTimeMillis());
						}

						nrClientsPeakHour = 0;
						peakMinutes = 0;

					}

					nrClients++;

					queuesManager.getSimulationFrame().getLoggerPanel().getTaLogger()
							.append(peakTime.getHours() + ":" + peakTime.getMinutes() + ":" + peakTime.getSeconds()
									+ " Started processing: " + client.toString() + "\n");

					Thread.sleep(client.getServiceTime() * 100);

					client.setWaitingTime(client.getArrivingTime().getTime() - client.getServiceTime());
					this.setWaitingTime(this.getWaitingTime() + client.getWaitingTime());

					queuesManager.getCashRegisterWithId(id).getClientsQueue().take();

					long processingTimeOld = queuesManager.getCashRegisterWithId(id).getProcessingTime();
					queuesManager.getCashRegisterWithId(id)
							.setProcessingTime(processingTimeOld - client.getServiceTime());

					queuesManager.getSimulationFrame().getLoggerPanel().getTaLogger()
							.append(peakTime.getHours() + ":" + peakTime.getMinutes() + ":" + peakTime.getSeconds()
									+ " Finished processing: " + client.toString() + "\n");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				queuesManager.getSimulationFrame().draw(id, this);
			}
		}

		if (queuesManager.isOpen() == false) {

			if (nrClients > 0) {

				queuesManager.getSimulationFrame().getLoggerPanel().getTaStatistics().append("QUEUE" + (id + 1) + "\n");
				queuesManager.getSimulationFrame().getLoggerPanel().getTaStatistics()
						.append("Average service time is " + (double) this.processingTimeFinal / nrClients + "\n");
				queuesManager.getSimulationFrame().getLoggerPanel().getTaStatistics()
						.append("Average of waiting time is " + this.waitingTime / nrClients + " ms.\n");
				queuesManager.getSimulationFrame().getLoggerPanel().getTaStatistics()
						.append("Number of clients at peak time is " + this.maxClientsInPeakHour + "\n");
				queuesManager.getSimulationFrame().getLoggerPanel().getTaStatistics().append("Peak time "
						+ peakTime.getHours() + ":" + peakTime.getMinutes() + ":" + peakTime.getSeconds() + "\n");
				queuesManager.getSimulationFrame().getLoggerPanel().getTaStatistics()
						.append("----------------------------------------------------------\n");
				//queuesManager.getSimulationFrame().getLoggerPanel().getTaStatistics().revalidate();
			}
		}
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getActive() {
		return this.active;
	}

	public long getProcessingTime() {

		return this.processingTime;
	}

	public void setProcessingTime(long processingTime) {

		this.processingTime = processingTime;
	}

	public long getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}

	public String millisToDate(long millis) {

		return DateFormat.getDateInstance(DateFormat.DEFAULT).format(millis);
		// You can use DateFormat.LONG instead of SHORT

	}

	public void closeCashRegister() {

		this.setActive(false);
	}
}
