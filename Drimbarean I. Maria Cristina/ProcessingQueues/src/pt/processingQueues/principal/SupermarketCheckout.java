package pt.processingQueues.principal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupermarketCheckout implements Runnable{
private static final Logger LOGGER = Logger.getLogger(SupermarketCheckout.class.getName());
  private int id;
  private BlockingQueue<Client> clients=new ArrayBlockingQueue<Client>(100);
  private int serviceTime=0;
  private int waitingTime=0;
  private int clientsServed=0;
  private boolean isRunning;
  public SupermarketCheckout(){
	  this.isRunning=true;
  }
  public SupermarketCheckout(int id){
	  this.id=id;
	  this.isRunning=true;
  }
@Override
public void run() {
	LOGGER.info("Logger Name: "+LOGGER.getName()+id);
    Client client=new Client();
    int delay;
    while (true) {
        while (isRunning) {
            if (clients.size() > 0) {
            	System.out.println(this.listToString());
                try {
					client = clients.take();
				} catch (InterruptedException ex) {
					System.out.println("EXCEPTION " + ex);
				}
                clientsServed++;
                delay = client.getServiceTime();
                try {
                    Thread.sleep(delay * 1000);
                    waitingTime += serviceTime;
                    serviceTime+= delay;
                    System.out.println(this.toString()+" : "+client.toString()+" checked-out!");
                } catch (InterruptedException ex) {
                	LOGGER.log(Level.SEVERE, "Cannot take a client from queue", ex);
                }

            }
            else {
            	//System.out.println(this.toString()+" is empty!\n");
            	//this.setRunning(false);
            }
        }
    }

}
/**
 * adds a client from the scheduler
 * @param client
 */
public void addClient(Client client){
	System.out.println(client.toString()+" was added in: "+this.toString());
	try {
		this.clients.put(client);
	} catch (InterruptedException e) {
		LOGGER.log(Level.SEVERE, "Cannot put a client in the queue", e);
	}
}
/**
 * @return  isRunning (the thread)
 */
public boolean isRunning() {
	return isRunning;
}
/**
 * @param isRunning thread is running or not
 */
public void setRunning(boolean isRunning) {
	this.isRunning = isRunning;
}
/**
 * @return averageServiceTime
 */
public int avgServiceTime(){
	if (clientsServed!=0)
	  return serviceTime/clientsServed;
	return 0;
}
/**
 * @return averageWaitingTime
 */
public int avgWaitingTime(){
	if (clientsServed!=0)
	  return waitingTime/clientsServed;
	return 0;
}
/**
 * @return the clients
 */
public BlockingQueue<Client> getClients() {
	return clients;
}
/**
 * @return the clientsServed
 */
public int getClientsServed() {
	return clientsServed;
}
/**
 * prints the queue
 * @return
 */
public String listToString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.toString() + "------");
    for (Client client : clients) {
        sb.append(client.toString());
    }
    return sb.toString()+"\n";
}

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "[SupermarketCheckout" + id + "]";
}
}
