package panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.Account;
import models.Bank;

public class CustomerPanel extends JPanel implements ActionListener {

	private Account account;
	private Bank bank;

	private JButton withdraw = new JButton("Withdraw");
	private JButton deposit = new JButton("Deposit");
	private JButton checkSold = new JButton("Check Sold");

	private JPanel buttonLayout = new JPanel();

	public CustomerPanel(Bank bank, Account account) {
		this.bank = bank;
		this.account = account;

		setLayout(new BorderLayout());

		withdraw.addActionListener(this);
		withdraw.setActionCommand("withdraw");
		deposit.addActionListener(this);
		deposit.setActionCommand("deposit");
		checkSold.addActionListener(this);
		checkSold.setActionCommand("check");

		buttonLayout.setLayout(new GridLayout(3, 1));
		buttonLayout.add(withdraw);
		buttonLayout.add(deposit);
		buttonLayout.add(checkSold);

		add(buttonLayout, BorderLayout.WEST);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("withdraw")) {
			String sum = JOptionPane.showInputDialog("Enter the sum to withdraw");
			if (!account.cannotWithdraw(Integer.parseInt(sum))) {
				JOptionPane.showMessageDialog(null, "The total of the account is too small for this operation");
			} else {
				account.withdraw(Integer.parseInt(sum));
			}
			bank.update(account, bank);
			System.out.println(bank.getObserverReport());
			bank.serialize();
		} else if (e.getActionCommand().equals("deposit")) {
			String sum = JOptionPane.showInputDialog("Enter the sum to deposit");
			account.deposit(Integer.parseInt(sum));
			bank.update(account, bank);
			System.out.println(bank.getObserverReport());
			bank.serialize();
		} else if (e.getActionCommand().equals("check")) {
			String total = "" + account.getTotal();
			JOptionPane.showMessageDialog(null, total);

		}
	}

}
