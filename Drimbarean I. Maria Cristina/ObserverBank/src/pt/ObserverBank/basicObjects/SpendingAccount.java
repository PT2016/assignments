package pt.ObserverBank.basicObjects;

import java.io.Serializable;

public class SpendingAccount extends Account implements Serializable{
	private static final long serialVersionUID = 8823009297222561808L;

	public SpendingAccount(int id, double sum) {
		super.setBalance(sum);
		super.setId(id);
	}

	@Override
	public double withdraw(double amount) {
		assert (amount > 0) : "" + amount + " invalid withdraw sum in SpendingAccount ";
		assert (this.balance - amount >= 0) : "Error at withdraw " + (this.balance - amount);
		if (this.balance >= amount) {
			this.balance -= amount;
		}
		return balance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SpendingAccount [balance=" + balance + ", id=" + id + "]";
	}
}
