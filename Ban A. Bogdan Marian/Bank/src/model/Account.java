package model;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

import observers.Observable;

@SuppressWarnings("serial")
public class Account extends Observable implements Serializable {
	protected long sum = 0;
	protected long accountId;

	public Account() {
		this(0);
	}

	public Account(long sum) {
		this.sum = sum;
		this.accountId = Math.abs(ThreadLocalRandom.current().nextLong(100000));
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getSum() {
		return sum;
	}

	public void setSum(long sum, String name) {
		this.sum = sum;
		setChanged();
		notifyObservers(name);
	}

}
