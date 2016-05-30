package Entities;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import Control.DisplayControl;
import Helper.AtomicOperations;

public class Server extends Thread {

	private AtomicOperations operations;
	private int sleepingTime;

	private BlockingQueue<Task> taskQueue;
	private int simulationTime;
	private int finishTime;
	private int serverID;

	private DisplayControl controler;

	public Server(int serverID, int simulationTime, int finishTime, DisplayControl controler, int sleep) {
		taskQueue = new ArrayBlockingQueue<Task>(100);
		this.serverID = serverID;
		this.simulationTime = simulationTime;
		this.finishTime = finishTime;
		this.sleepingTime = sleep;
		this.controler = controler;
		this.controler.displayServers(serverID);
		operations = new AtomicOperations();
	}

	public void receiveTask(Task task, int receivingTime) {
		try {
			
			task.setServiceTime(task.getServiceTime() + receivingTime);
			System.out.println("task finishes at " + task.getServiceTime());
			taskQueue.put(task);
			operations.numberOfTasks.getAndIncrement();
			int i = 0;
			for (Task currentTask : taskQueue) {
				System.out.println(currentTask + " in server " + serverID);
				controler.displayTask(currentTask.toString(), serverID * 15 + i);
				i++;
			}
		} catch (InterruptedException e) {
			System.out.println("Cannot put task " + task);
		}
	}

	public BlockingQueue<Task> getTaskQueue() {
		return taskQueue;
	}

	@Override
	public void run() {

		while (simulationTime <= finishTime) {

			if (simulationTime == finishTime) {
				System.out.println("\ntotal tasks in server" + serverID + ": " + operations.numberOfTasks.get());
				System.out.println(
						"total waiting time for server " + serverID + ": " + operations.totalWaitingTime.get());
				break;
			}

			int i = 0;
			for (Task task : taskQueue) {
				controler.hideTask(serverID * 15 + i);
				if (task.getServiceTime() == simulationTime) {
					task.setFinishTime(simulationTime);
					task.setWaitingTime();
					operations.totalWaitingTime.getAndAdd(task.getWaitingTime());
					try {
						taskQueue.take();
					} catch (InterruptedException e) {
						System.out.println("Cannot take task from server " + serverID);
					}
				}
				i++;
			}

			i = 0;
			for (Task currentTask : taskQueue) {
				System.out.println(currentTask + " in server " + serverID);
				controler.displayTask(currentTask.toString(), serverID * 15 + i);
				i++;
			}

			try {
				Thread.sleep(sleepingTime);
			} catch (InterruptedException e) {
				System.out.println("Error sleeping in server thread");
			}
			simulationTime++;
		}

	}

}
