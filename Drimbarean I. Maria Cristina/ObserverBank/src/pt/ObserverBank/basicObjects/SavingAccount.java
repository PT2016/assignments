package pt.ObserverBank.basicObjects;

import java.io.Serializable;

/**
 * You can not withdraw more then 1000, a certain interest is applied by the
 * bank
 * 
 * @author Chiti
 *
 */
public class SavingAccount extends Account implements Serializable{
	private static final long serialVersionUID = -1514517527266947360L;

	public SavingAccount(int id, double sum) {
		super.setBalance(sum);
		super.setId(id);
	}

	@Override
	public double withdraw(double amount) {
        assert (amount > 0 && amount < 1000) : "" + amount+ "invalid withdraw sum from saving account ";
        assert (this.balance - amount >= 0) : "Error at withdraw: " + (this.balance - amount);
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
		return "SavingAccount [balance=" + balance + ", id=" + id + "]";
	}

}
