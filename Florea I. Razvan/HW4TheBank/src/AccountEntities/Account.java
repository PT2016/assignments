package AccountEntities;

import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable{


	private static final long serialVersionUID = -7577959386600255509L;
	protected double money;
	protected String ID;
	protected String ownerName;
	protected String type;
	
	public Account(String ID, String ownerName) {
		this.ID = ID;
		money = 0.0;
		this.ownerName = ownerName;
	}
	
	public String getType() {
		return type;
	}
	
	public String getID() {
		return ID;
	}
	
	public double getMoney() {
		return money;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public abstract void addMoney(double sum);
	public abstract void withdrawMoney(double sum);
	
	public abstract void setType();
	
	public abstract String toString();
}
