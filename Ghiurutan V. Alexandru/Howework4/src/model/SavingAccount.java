package model;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class SavingAccount extends Account {
	private String name;
	private double money;
	private double gain;
	private LocalDate gainDate, currentDate;
	private boolean isRunning = true;
	private static int MINIMUM_SUM_TO_WITHDRAW = 1000;

	public SavingAccount(double money, double gain, int year, int month, int day) {
		super(money, "Account: ");
		this.money = super.getMoney();
		this.name = super.getName();
		this.gain = gain;
		gainDate = LocalDate.of(year, month, day);
		currentDate = LocalDate.now();
		addingGain();
	}

	@Override
	public double getMoney() {
		return this.money;
	}

	public void addingGain() {
		new Thread(new Runnable() {
			public void run() {
				while (isRunning) {
					currentDate = LocalDate.now();
					if (currentDate.getYear() > gainDate.getYear()
							|| currentDate.getYear() == gainDate.getYear()
									&& currentDate.getMonthValue() > gainDate.getMonthValue()
							|| currentDate.getYear() == gainDate.getYear()
									&& currentDate.getMonthValue() == gainDate.getMonthValue()
									&& currentDate.getDayOfYear() > gainDate.getDayOfYear()) {
						isRunning = false;
					}
					if (currentDate.equals(gainDate)) {
						synchronized (this) {
							double gainSum = money * gain / 100;
							money += gainSum;
							setChanged();
							notifyObservers();
							isRunning = false;
						}
					}
					try {
						Thread.sleep(86400000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void setMoney(double money) {
		this.money = money;
		setChanged();
		notifyObservers();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(money);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SavingAccount other = (SavingAccount) obj;
		if (Double.doubleToLongBits(money) != Double.doubleToLongBits(other.money))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static int getMinimumSumToWithdraw() {
		return MINIMUM_SUM_TO_WITHDRAW;
	}

	@Override
	public String toString() {
		return String.format(name + " " + money + " " + gain + " " + gainDate);
	}
}
