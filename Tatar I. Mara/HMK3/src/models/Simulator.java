package models;

import java.util.concurrent.atomic.AtomicInteger;

import Controller.Controller;
import parameters.Parameters;
import views.SimulatorFrame;

public class Simulator implements Runnable {
	
	private AtomicInteger clientNo=new AtomicInteger();
	private Scheduler scheduler;
	private SimulatorFrame frame;
    private int[] averageWaitingTime=new int[10];
    private int[] averageServiceTime=new int[10];
    private int[] peakHour=new int[10];
    private int[] clientsPerServer=new int[10];
	public Simulator() {
		scheduler = new Scheduler();
		frame = new SimulatorFrame();

	  
	}

	@Override
	public void run() {
        int peak=0;
		int currTime = 0;
		while (currTime <Parameters.finishTime) {
			
			int serviceTime = (int) (Math.random() * (Parameters.maxServiceTime - Parameters.minServiceTime) + Parameters.minServiceTime);
            int arrivalInterval=(int) (Math.random() * (Parameters.maxArrivalInterval - Parameters.minArrivalInterval) + Parameters.minArrivalInterval);
            currTime=currTime+arrivalInterval;
            Task t = new Task(currTime, serviceTime);
          
            clientNo.set(clientNo.get()+1);
		    t.setClientNo(clientNo.get());
			scheduler.dispatchTaskOnServer(t);
			t.toString();
			
			frame.displayData(scheduler.getCurrentTasks());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		if (currTime>=Parameters.finishTime){
			int i=0;
		
			for (Task[] t:scheduler.getAllTasks()){
				 averageWaitingTime[i]=0;
				 averageServiceTime[i]=0;
				 clientsPerServer[i]=0;
				System.out.println("Logger of server no"+i);
				for (Task task:t){
					System.out.println(task.toString());
					clientFrequency(task);
					int aux=task.getFinishTime()-task.getArrivalTime();
					averageWaitingTime[i]+=aux;
					averageServiceTime[i]+=task.getServiceTime();
					clientsPerServer[i]++;
				}
				averageWaitingTime[i]=averageWaitingTime[i]/clientsPerServer[i];
				averageServiceTime[i]=averageServiceTime[i]/clientsPerServer[i];
				System.out.println("Average waiting time: "+Integer.toString(averageWaitingTime[i]));
				System.out.println("Average service time: "+Integer.toString(averageServiceTime[i]));
				System.out.println("No clients per server: "+Integer.toString(clientsPerServer[i]));
		
			i++;
			
			}
			System.out.println("Peak hour: "+Integer.toString(peak(peakHour)));
		    peak=peak(peakHour);
		}
		while (scheduler.isEmpty()==false){
			frame.displayData(scheduler.getCurrentTasks());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		frame.displayStatistics(averageWaitingTime,averageServiceTime,peak);
		

	}
	public int peak(int[] peakHour){
		int max=Integer.MIN_VALUE;
		for (int i=0;i<peakHour.length;i++){
			if (peakHour[i]>max) max=i;
		}
		return max;
	}
	public void clientFrequency(Task t){
		int hours=3;
		for (int i=1;i<hours;i++){
			if ((i*60>t.getArrivalTime())&&(t.getArrivalTime()>(i-1)*60)){
				peakHour[i]+=1;
			}
		}
	}
	public SimulatorFrame getSimulatorFrame(){
		return frame;
	}
	public static void main(String[] args){
		new Controller();
		
		
	}

}
