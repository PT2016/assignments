package Main;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Lorand
 *
 */
public class Algorithm implements Runnable {

	protected ArrayList<Client> clients;
	public ArrayList<Queue> queues;
	protected ArrayList<Thread> threads;
	private int nrClients;
	private int nrQueues;
	static public int contor = 0;

	/**
	 * Constructor
	 * 
	 * @param nrClients
	 * @param nrQueues
	 */
	public Algorithm(int nrClients, int nrQueues) {
		this.nrClients = nrClients;
		this.nrQueues = nrQueues;
		clients = new ArrayList<Client>();
		queues = new ArrayList<Queue>();
		threads = new ArrayList<Thread>();
		int i;

		for (i = 0; i <= nrClients - 1; i++) {
			Client cl = new Client(randomSpace(GUI.minSosire, GUI.maxSosire),
					randomSpace(GUI.minServire, GUI.maxServire));
			System.out.println("Service time=" + cl.serviceTime + "  Arrival time=" + cl.arrivalTime);
			clients.add(cl);
		}

		for (i = 0; i <= nrQueues - 1; i++) {
			Queue q = new Queue();
			queues.add(q);
		}

		for (i = 0; i <= queues.size() - 1; i++) {
			Thread t = new Thread(queues.get(i), "" + i);
			// Thread t= new Thread(queues.get(i),"Threadul "+i);
			threads.add(t);
		}
	}

	/**
	 * Metoda care verifica valoarea lui rdy, daca e false contorul nu se va mai
	 * incrementa si nu va mai sta o secunda
	 * 
	 * @return
	 */
	protected boolean readyQueue() {
		int i;
		for (i = 0; i <= queues.size() - 1; i++) {
			if (queues.get(i).rdy == false)
				return false;
		}

		return true;
	}

	/**
	 * Metoda care verifica daca toate cozile s-au golit
	 * 
	 * @param q
	 * @return
	 */
	protected boolean allQueuesEmpty(ArrayList<Queue> q) {
		boolean empty = false; // pp goale
		int i;
		for (i = 0; i <= q.size() - 1; i++) {
			if (!q.get(i).clients.isEmpty())
				empty = true; // daca se gaseste plina
		}
		return empty;

	}

	/**
	 * Metoda care gaseste coada de lungime minima
	 * 
	 * @param q
	 * @return
	 */
	protected int minQueue(ArrayList<Queue> q) {
		int index = 0, i;
		int minLength = q.get(0).getWaitingTime();
		for (i = 0; i <= q.size() - 1; i++)
			if (q.get(i).getWaitingTime() < minLength) {
				minLength = q.get(i).getWaitingTime();
				index = i;

			}

		return index;

	}

	/**
	 * Metoda care genereaza un nr random din intervalul (x, y)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private int randomSpace(int x, int y) {
		Random randomm = new Random();
		int a;
		do {
			a = randomm.nextInt(1000000);
		} while ((a < x) || (a > y));

		return a;
	}

	/**
	 * Metoda care insereaza in coada clientul care are timpul de sosire egal cu
	 * timpul scurs este apelata de thread-ul principal, care se repeta o data
	 * la o secunda
	 */
	@Override
	public void run() {
		float average = 0;
		int k = clients.size();
		int i;

		for (i = 0; i < threads.size(); i++)
			threads.get(i).start();

		while ((!(clients.isEmpty()) || allQueuesEmpty(queues) == true)) {
			if (this.readyQueue()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				contor++;

				ArrayList<Client> removee = new ArrayList<Client>();
				for (i = 0; i <= clients.size() - 1; i++) {
					if (contor == clients.get(i).arrivalTime) {
						int posMin = minQueue(queues);
						queues.get(posMin).introduceClient(clients.get(i));
						removee.add(clients.get(i));

					}
				}

				for (i = 0; i < removee.size(); i++) {
					for (int j = 0; j < clients.size(); j++) {
						if (removee.get(i).arrivalTime == clients.get(j).arrivalTime)
							clients.remove(j);

					}
				}
			}

			GUI.incrementTime.setText("" + contor); // in gui
		}
		for (i = 0; i < queues.size(); i++)
			queues.get(i).setFlag(false);
	}

}
