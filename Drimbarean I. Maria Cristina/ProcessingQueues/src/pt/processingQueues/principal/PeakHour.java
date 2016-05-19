package pt.processingQueues.principal;
/**
 * Class that takes the interval of 10 seconds and remembers the muximum number of clients
 * that was in a certain interval of time
 * @author Chiti
 *
 */
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeakHour implements Runnable{
  private ClientGenerator generator;
  private boolean run;
  private int intervalNumber;//the time from witch we started measuring
  private int maxNumber;
 
  public PeakHour(ClientGenerator generator) {
      this.generator = generator;
      this.run=true;
      this.maxNumber=0;
      this.intervalNumber=0;
      
  }

  @Override
  public void run() {
      int aux;
      int i=0;
      while (true) {
          while (run) {
              try {
                  Thread.sleep(10000);
                  System.out.println("PeakHour READY!");
                  aux=generator.getClientsGeneratedSince();
                  if(maxNumber<aux){
                  maxNumber=aux;
                  intervalNumber=i;}
                  i++;
              } catch (InterruptedException ex) {
                  Logger.getLogger(PeakHour.class.getName()).log(Level.SEVERE, null, ex);
              }

          }
      }
  }


  public String getPeackHour() {
      int max = intervalNumber*10;
      int max2=max+10;
      return "Peak Hour between second  " + max 
              + " and second " + max2;
  }

  public void setRun(boolean run) {
      this.run = run;
  }
}
