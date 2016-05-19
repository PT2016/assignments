package model;

@SuppressWarnings("serial")
public class SpendingAccount extends Account {
	private String name;
	private double money;
	private static int MAXIMUM_SUM_TO_WITHDRAW = 1000;

	public SpendingAccount(double money) {
		super(money, "Account: ");
		this.name = super.getName();
		this.money = super.getMoney();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setMoney(double money) {
		this.money = money;
		setChanged();
		notifyObservers();
	}

	@Override
	public double getMoney() {
		return this.money;
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
		SpendingAccount other = (SpendingAccount) obj;
		if (Double.doubleToLongBits(money) != Double.doubleToLongBits(other.money))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static int getMaximumSumToWithdraw() {
		return MAXIMUM_SUM_TO_WITHDRAW;
	}

	@Override
	public String toString() {
		return String.format(name + " " + money);
	}
}
