package Main;

import java.util.*;
import java.util.ArrayList;

/**
 * 
 * @author Lorand
 *
 */
public class Queue implements Runnable {

	public ArrayList<Client> clients;
	protected boolean flag;
	protected boolean rdy;

	/**
	 * Constructor
	 */
	public Queue() {
		this.clients = new ArrayList<Client>();
		flag = true;
	}

	/**
	 * Metoda prin care adaugam in lista de clienti, un client la sf cozii
	 * 
	 * @param c
	 */
	protected void introduceClient(Client c) {
		clients.add(0, c);
	}

	/**
	 * Metoda care sterge clientul din coada
	 * 
	 * @return
	 */
	protected Client deleteClient() {
		Client cl = clients.get(0);
		clients.remove(0);
		return cl;
	}

	/**
	 * Metoda care returneaza clientul care ajunge la casa(primul din coada)
	 * 
	 * @return
	 */
	protected Client outClient() {
		Client cl = clients.get(clients.size() - 1);
		return cl;
	}

	/**
	 * Metoda de afisare a cozii la fiecare casa
	 */
	public String toString() {
		String queue = "";
		int time = 0, i;
		for (i = clients.size() - 1; i >= 0; i--) {
			time = time + clients.get(i).serviceTime;
			queue = queue + clients.get(i).toString(time, i) + "  ";

		}

		return queue;
	}

	/**
	 * 
	 * @return
	 */
	protected boolean idFlag() {
		return flag;

	}

	/**
	 * 
	 * @param flag
	 */
	protected void setFlag(boolean flag) {
		this.flag = flag;

	}

	/**
	 * Metoda care returneaza timpul de asteptare de la o coada
	 * 
	 * @return
	 */
	protected int getWaitingTime() {
		int time, i;
		time = 0;
		/*
		 * if(clients.size()==0) return 0; else
		 */
		for (i = 0; i <= clients.size() - 1; i++)
			time = time + clients.get(i).getServiceTime();
		return time;

	}

	/**
	 * Metoda care va afisa la fiecare secunda coada, decrementand timpul de
	 * asteptare si scotand cate un client. Se aplica pt toate thread-urile 
	 * care vor exista pe obiectele de tip Queue
	 */
	@Override
	public void run() {
		int a = 0, ok = 0, timpp = 0, contor = 0;
		while (flag) {
			if (a == Algorithm.contor)
				rdy = true;
			else if (clients.isEmpty()) {
				a++;
				rdy = false;
				GUI.queues[Integer.parseInt(Thread.currentThread().getName())].setText(" "); // cozile
																								// din
																								// gui

			} else {
				Client clnt = this.outClient();
				int time = clnt.serviceTime;
				if (ok == 0)
					timpp = timpp + time;
				ok = 1;
				GUI.queues[Integer.parseInt(Thread.currentThread().getName())].setText(this.toString()); // cozile
																											// din
																											// gui
				time--;
				clients.get(clients.size() - 1).setServiceTime(time);
				if (time == 0) {
					ok = 0;
					clients.remove(clients.size() - 1);
					contor++;
				}

				a++;
				rdy = false;
			}
			System.out.print("");
		}

		GUI.queues[Integer.parseInt(Thread.currentThread().getName())].setText(" "); // cozile
																						// din
																						// gui
		GUI.afTimpMediu.setText("" + (timpp / contor));
		// System.out.println("timpuuuuul " +timpp/contor);

	}

}
