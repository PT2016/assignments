package pt.ObserverBank.basicObjects;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * has a balance and methods to add and withdraw and inquire
 * 
 * @author Chiti
 *
 */
public abstract class Account implements Serializable{
	private static final long serialVersionUID = 2555754820532741874L;
	protected double balance;
	protected int id;
	public Account() {
		balance = 0;
		id=0;
	}

	public Account(int id,double sum) {
		this.balance = sum;
		this.id=id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [balance=" + balance + ", id=" + id + "]";
	}
/**
 * cannot add more then 10000
 * @param sum
 * @pre sum>0 && sum<10000
 */
	public void add(double sum) {
		assert (sum > 0.0 && sum < 10000.0) :"" + sum+ "The sum you ar trying to add is not legal! ";
        this.balance += sum;
	}

	public abstract double withdraw(double sum);
    public double inquire(){
    	return balance;
    }
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * used to tell the user what happend
	 * 
	 * @param infoMessage
	 * @param titleBar
	 */
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
}
