package Main;
/**
 * 
 * @author Lorand
 *
 */
public class Client {
	protected int arrivalTime;
	protected int serviceTime;
	/**
	 * Constructor
	 * @param arrivalTime
	 * @param serviceTime
	 */
	public Client(int arrivalTime, int serviceTime)
	{
		this.arrivalTime=arrivalTime;
		this.serviceTime=serviceTime;
	}
	/**
	 * Metoda care returneaza timpul de sosire al clientului
	 * @return
	 */
	protected int getArrivalTime()
	{
		return arrivalTime;
	}
	/**
	 * Metoda care seteaza timpul de sosire al clientului
	 * @return
	 */
	/*protected void setArrivalTime(int aTime)
	{
		arrivalTime=aTime;
		return arrivalTime;
		this.arrivalTime=aTime;
	}*/
	
	/**
	 * Metoda care returneaza timpul de serivire al clientului
	 * @return
	 */
	protected int getServiceTime()
	{
		return serviceTime;
	}
	
	/**
	 * Metoda care reteaza timpul de servire pt un client
	 * @param sTime
	 */
	protected void setServiceTime(int sTime)
	{
		
		this.serviceTime=sTime;
		
	}
	
	/**
	 * Metoda de afisare a clientului, respectiv timpul de asteptare
	 * @param time
	 * @param nrClient
	 * @return
	 */
	public String toString(int time, int nrClient)
	{
		String str="";
		str+= time;
		str="Client("+ (nrClient+1)+") Time:" + str + "  ";
		return str;
	}

	
}
