package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class TaskScheduler {
	private int nrOfQueues;
	private int nrOfTasksPerQueue, queueClose, timeClose, nrOfTasks, peakHour;
	private List<Server> servers;
	private ArrayBlockingQueue<Task> waitingQueue;
	private Iterator<Server> iterator;

	public TaskScheduler(int nrOfQueues, int nrOfTasksPerQueue, int queueClose, int timeClose) {
		this.nrOfQueues = nrOfQueues;
		this.nrOfTasksPerQueue = nrOfTasksPerQueue;
		this.queueClose = queueClose;
		this.timeClose = timeClose;
		servers = new ArrayList<Server>(nrOfQueues);
		waitingQueue = new ArrayBlockingQueue<Task>(100);
	}

	private boolean areServersFull() {
		for (Server s : servers) {

			if (s.getNrOfTasks() < nrOfTasksPerQueue) {
				return false;
			}
		}
		return true;
	}

	public int getPeakHour() {
		return peakHour;
	}

	private void displayServers(List<Server> servers) {
		iterator = servers.iterator();
		int nrTasks = 0;
		while (iterator.hasNext()) {
			Server server = iterator.next();
			nrTasks += server.getNrOfTasks();
			System.out.println(server.getName());
			Task[] t = server.getTasks();
			System.out.println(server.getNrOfTasks());
			for (Task task : t) {
				System.out.println(task);
			}
		}
		if (nrTasks > nrOfTasks) {
			nrOfTasks = nrTasks;
			peakHour = TaskGenerator.getCurrentTime();
		}
	}

	private void verifyTimeToClose() {
		if (TaskGenerator.getCurrentTime() == timeClose) {
			Server serv = new Server(1);
			Server s=null;
			serv.setName("Server " + queueClose);
			if (servers.contains(serv)) {
				iterator = servers.iterator();
				while (iterator.hasNext()) {
					s = iterator.next();
					if (s.getName().equals(serv.getName())) {
						break;
					}
				}				
				Task[] tasks=s.getTasks();
				for (Task t:tasks)
					waitingQueue.add(t);
				System.out.println(serv.getName() + " was closed.");
				System.out.println("Waiting:" + waitingQueue.size());
				servers.remove(serv);

			}
		}
	}

	private void distributeServers(Task task) {
		verifyTimeToClose();
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
				try {
					servers.get(0).addTask(waitingQueue.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
