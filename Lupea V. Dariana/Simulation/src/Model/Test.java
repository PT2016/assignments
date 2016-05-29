package Model;

import GUI.SimulationFrame;

public class Test {

	public static void main(String args[]) throws InterruptedException{
		
		/*Task t1 = new Task(3,5);
		Task t2 = new Task(4,6);
		Task t3 = new Task(6,5);
		
		
		BlockingQueue<Task> tasksQueue = new LinkedBlockingQueue<Task>();
		try {
			tasksQueue.put(t1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tasksQueue.put(t2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			tasksQueue.put(t3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Server s = new Server(15);
		s.setTasksQueue(tasksQueue);
		Thread tre = new Thread(s);
		tre.start();
	}*/
	//	TaskGenerator taskGen = new TaskGenerator(1, 3, 1, 3);
	//TaskScheduler scheduler = new TaskScheduler(3, taskGen, 10);
		//scheduler.activate();
///	Thread th = new Thread(scheduler);
///		th.start();
		//scheduler.showResults();
	    SimulationFrame frame = new SimulationFrame();
	}
		
		
		
	
}
