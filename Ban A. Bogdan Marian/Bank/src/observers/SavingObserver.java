package observers;

import model.Account;

@SuppressWarnings("serial")
public class SavingObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		Account account = (Account) o;
		System.out.println(String.format("Dear %s, Saving account %s has now %d lei ", arg, account.getAccountId(),
				account.getSum()));
	}

}
