package Entities;

import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable {

	private static final long serialVersionUID = 1L;
	private int accId;
	public double money;

	public Account(int accId, double money) {
		this.accId = accId;
		this.money = money;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Account with accId=" + accId + ", money=" + money + ".";
	}

	public abstract void addMoney(double sum);

	public abstract void withdrawMoney(double sum);
}
