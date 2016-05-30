package model;

@SuppressWarnings("serial")
public class SpendingAccount extends Account {
	// can't extract if no money;
	public long extractMoney(long extract){
		return sum-extract;
	}
}
