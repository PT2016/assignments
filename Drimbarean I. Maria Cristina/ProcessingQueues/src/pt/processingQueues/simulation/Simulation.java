package pt.processingQueues.simulation;

import pt.processingQueues.principal.*;

public class Simulation {
  private int simulationTime=2000;
  private int queuesNumber=3;
  private int minS=1,maxS=3,minA=1,maxA=3;
  protected ClientGenerator clientGenerator;
  protected Scheduler scheduler;
  protected SupermarketCheckout[] queues=new SupermarketCheckout[30];
  protected Thread[] threads=new Thread[30];
  protected Thread schedulerThread;
  protected Thread generatorThread;
  protected PeakHour peakHour;
  protected Thread peakHourThread;
  public Simulation(int minS,int maxS,int minA,int maxA,int queuesNr,int simTime){
	  this.minS=minS;this.maxS=maxS;
	  this.minA=minA;this.maxA=maxA;
	  this.queuesNumber=queuesNr;this.simulationTime=simTime;
  }
  public void initSimulation(){
	  scheduler=new Scheduler();
	  schedulerThread=new Thread(scheduler);
	  for (int i=0;i<queuesNumber;i++)
	  {
		  queues[i]=new SupermarketCheckout(i+1);
		  threads[i]=new Thread(queues[i]);
		  threads[i].start();
		  scheduler.addSupermarketCheckout(queues[i]);
	  }
	  clientGenerator=new ClientGenerator(maxA,minA,maxS,minS,scheduler);
	  peakHour = new PeakHour(clientGenerator);
      generatorThread = new Thread(clientGenerator);
      peakHourThread = new Thread(peakHour);
      schedulerThread.start();
      generatorThread.start();
      peakHourThread.start();
  }
  /**pause all threads*/
  public void pause() {
      System.out.println("Simulation is in pause!");
      for (int i =0; i < queuesNumber; i++) {
          queues[i].setRunning(false);}
      clientGenerator.setRunning(false);
      scheduler.setRunning(false);
      peakHour.setRun(false);
  }

  public void pauseGenerator() {
      clientGenerator.setRunning(false);
  }

  /** starts all threads*/
  public void start() {

      for (int i = 0; i < queuesNumber; i++) {
         queues[i].setRunning(true);
      }
      clientGenerator.setRunning(true);
      scheduler.setRunning(true);
      peakHour.setRun(true);
  }

/** stops threads*/
  public void stop() {
      for (int i = 0; i < queuesNumber; i++) {
          threads[i].stop();
      }
      generatorThread.stop();
     schedulerThread.stop();
      peakHourThread.stop();
  }
 
  /**get peak hour*/
  public String getPeakHour() {
      return peakHour.getPeackHour();
  }

  /**how many clients per checkout were served*/
  public double getAvgAllProcersorsActiveTime() {
      double total = 0;
      for (int i = 0; i < queuesNumber; i++) {
          total=total+queues[i].getClientsServed();
      }
      return total / queuesNumber;
  }
/**average service time for all queues*/
  public double getAvgServiceTime() {
	  double total = 0;
      for (int i = 0; i < queuesNumber; i++) {
          total=total+queues[i].avgServiceTime();
      }
      return total / queuesNumber;
  }
  
  /**average service time for all queues*/
  public double getAvgWaitingTime() {
	  double total = 0;
      for (int i = 0; i < queuesNumber; i++) {
          total=total+queues[i].avgWaitingTime();
      }
      return total / queuesNumber;
  }
  
}
