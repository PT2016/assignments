package controller;

import model.Task;

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
	private Iterator<Task> iterator;

	public TaskScheduler(int nrOfQueues, int nrOfTasksPerQueue) {
		this.nrOfQueues = nrOfQueues;
		this.nrOfTasksPerQueue = nrOfTasksPerQueue;
		servers = new ArrayList<Server>(nrOfQueues);
	}

	private void distributeServers(Task task) {
		if (servers.isEmpty()) {
			servers.add(new Server(nrOfTasksPerQueue));
		} else {
			Collections.sort(servers, new Comparator<Server>() {

				@Override
				public int compare(Server o1, Server o2) {
					if (o1.getNumberOfActiveTasks() < o2.getNumberOfActiveTasks()) {
						return -1;
					} else if (o1.getNumberOfActiveTasks() > o2.getNumberOfActiveTasks()) {
						return 1;
					}
					return 0;
				}
			});
			servers.get(0).addTask(task);
		}
	}

	public void addTask(Task task) {
		distributeServers(task);

	}
}
