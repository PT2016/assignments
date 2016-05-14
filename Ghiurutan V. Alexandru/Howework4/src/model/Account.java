package model;

import java.io.Serializable;
import java.util.Observable;

public class Account extends Observable implements Serializable {
	private static final long serialVersionUID = 6169922767471535655L;
	public static int index = 0;
	private String name;
	private double money;

	public Account(double money, String name) {
		this.money = money;
		this.name = name + index++;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getMoney() {
		return money;
	}

	public String getName() {
		return name;
	}

	@Override
	public void notifyObservers() {

	}
}
