package queues;

public class SimulationInput {
	private int nrClientsField;
	private int nrQueuesField;
	private int arrivalMinField;
	private int arrivalMaxField;
	private int serviceMinField;
	private int serviceMaxField;
	private int hoursFieldMin;
	private int hoursFieldMax;

	/**
	 * @param nrClientsField
	 * @param nrQueuesField
	 * @param arrivalMinField
	 * @param arrivalMaxField
	 * @param serviceMinField
	 * @param serviceMaxField
	 * @param hoursFieldMin
	 * @param hoursFieldMax
	 */
	public SimulationInput(int nrClientsField, int nrQueuesField, int arrivalMinField, int arrivalMaxField,
			int serviceMinField, int serviceMaxField, int hoursFieldMin, int hoursFieldMax) {
		this.nrClientsField = nrClientsField;
		this.nrQueuesField = nrQueuesField;
		this.arrivalMinField = arrivalMinField;
		this.arrivalMaxField = arrivalMaxField;
		this.serviceMinField = serviceMinField;
		this.serviceMaxField = serviceMaxField;
		this.hoursFieldMin = hoursFieldMin;
		this.hoursFieldMax = hoursFieldMax;
	}

	public int getNrClientsField() {
		return nrClientsField;
	}

	public void setNrClientsField(int nrClientsField) {
		this.nrClientsField = nrClientsField;
	}

	public int getNrQueuesField() {
		return nrQueuesField;
	}

	public void setNrQueuesField(int nrQueuesField) {
		this.nrQueuesField = nrQueuesField;
	}

	public int getArrivalMinField() {
		return arrivalMinField;
	}

	public void setArrivalMinField(int arrivalMinField) {
		this.arrivalMinField = arrivalMinField;
	}

	public int getArrivalMaxField() {
		return arrivalMaxField;
	}

	public void setArrivalMaxField(int arrivalMaxField) {
		this.arrivalMaxField = arrivalMaxField;
	}

	public int getServiceMinField() {
		return serviceMinField;
	}

	public void setServiceMinField(int serviceMinField) {
		this.serviceMinField = serviceMinField;
	}

	public int getServiceMaxField() {
		return serviceMaxField;
	}

	public void setServiceMaxField(int serviceMaxField) {
		this.serviceMaxField = serviceMaxField;
	}

	public int getHoursFieldMin() {
		return hoursFieldMin;
	}

	public void setHoursFieldMin(int hoursFieldMin) {
		this.hoursFieldMin = hoursFieldMin;
	}

	public int getHoursFieldMax() {
		return hoursFieldMax;
	}

	public void setHoursFieldMax(int hoursFieldMax) {
		this.hoursFieldMax = hoursFieldMax;
	}

}
