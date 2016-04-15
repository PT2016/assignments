package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TaskScheduler {
	private int nrOfQueues;
	private int nrOfTasksPerQueue;
	private List<Server> servers;
	private List<Task> waitingQueue;
	private Iterator<Server> iterator;

	public TaskScheduler(int nrOfQueues, int nrOfTasksPerQueue) {
		this.nrOfQueues = nrOfQueues;
		this.nrOfTasksPerQueue = nrOfTasksPerQueue;
		servers = new ArrayList<Server>(nrOfQueues);
		waitingQueue = new ArrayList<Task>();
	}

	private boolean areServersFull() {
		for (Server s : servers) {
			if (s.getNrOfTasks() < nrOfTasksPerQueue) {
				return false;
			}
		}
		return true;
	}

	private void displayServers(List<Server> servers) {
		iterator = servers.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			System.out.println("Server " + (i++));
			Task[] t = iterator.next().getTasks();
			for (Task task : t) {
				System.out.println(task);
			}
		}
	}

	private void distributeServers(Task task) {
		boolean fullServers = areServersFull();
		if (servers.isEmpty() || ((servers.size() < nrOfQueues) && (fullServers))) {
			Server server = new Server(nrOfTasksPerQueue);
			servers.add(server);
			server.addTask(task);
			new Thread(server).start();
		} else if (fullServers) {
			waitingQueue.add(task);
		} else {
			Collections.sort(servers, new Comparator<Server>() {

				@Override
				public int compare(Server o1, Server o2) {
					if (o1.getNrOfTasks() < o2.getNrOfTasks()) {
						return -1;
					} else if (o1.getNrOfTasks() > o2.getNrOfTasks()) {
						return 1;
					}
					return 0;
				}
			});
			if (waitingQueue.isEmpty()) {
				servers.get(0).addTask(task);
			} else {
				servers.get(0).addTask(waitingQueue.get(0));
			}
		}
		displayServers(servers);

	}

	public void addTask(Task task) {
		distributeServers(task);
	}

	public Server[] getAllServers() {
		Server[] serv = servers.toArray(new Server[servers.size()]);
		return serv;
	}
}
