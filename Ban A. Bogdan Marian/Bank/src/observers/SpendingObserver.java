package observers;

import model.Account;

@SuppressWarnings("serial")
public class SpendingObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		Account account = (Account) o;
		System.out.println(String.format("Dear %s, Spending account %s has now %d lei ", arg, account.getAccountId(),
				account.getSum()));
	}

}
