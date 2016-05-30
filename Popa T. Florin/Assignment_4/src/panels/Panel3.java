package panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel3 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton userLogout;
	public JButton clients;
	public JButton btnAddAccount;
	public JButton btnRemoveAccount;
	public JButton btnWithdraw;
	public JButton btnDeposit;
	
	public JTextField nameField;
	public JTextField typeField;
	public JTextField fundsField;
	public JTextField withdrawField;
	public JTextField depositField;
	
	public JLabel name;
	public JLabel type;
	public JLabel funds;
	public JLabel withdraw;
	public JLabel deposit;

	public Panel3(){
		
		setLayout(null);
		
		userLogout = new JButton("Logout");
		userLogout.setBounds(481, 14, 117, 29);
		
		clients = new JButton("Clients");
		clients.setBounds(352, 14, 117, 29);
		
		name = new JLabel("Name:");
		name.setBounds(50, 278, 130, 26);
		nameField = new JTextField();
		nameField.setBounds(100, 278, 130, 26);
		
		withdraw = new JLabel("Withdraw:");
		withdraw.setBounds(320, 278, 130, 26);
		withdrawField = new JTextField();
		withdrawField.setBounds(400, 278, 130, 26);
		
		deposit = new JLabel("Deposit:");
		deposit.setBounds(320, 308, 130, 26);
		depositField = new JTextField();
		depositField.setBounds(400, 308, 130, 26);
		
		type = new JLabel("Type:");
		type.setBounds(50, 308, 130, 26);
		typeField = new JTextField();
		typeField.setBounds(100, 308, 130, 26);
		
		funds = new JLabel("Funds:");
		funds.setBounds(50, 338, 130, 26);
		fundsField = new JTextField();
		fundsField.setBounds(100, 338, 130, 26);
		
		btnAddAccount = new JButton("Add Account");
		btnAddAccount.setBounds(71, 382, 200, 29);
		
		btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.setBounds(71, 412, 200, 29);
		
		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(371, 382, 200, 29);
		
		btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(371, 412, 200, 29);
		
		add(btnWithdraw);
		add(btnDeposit);
		add(btnRemoveAccount);
		add(btnRemoveAccount);
		add(deposit);
		add(depositField);
		add(withdrawField);
		add(type);
		add(name);
		add(nameField);
		add(typeField);
		add(funds);
		add(fundsField);
		add(userLogout);
		add(clients);
		add(btnAddAccount);
		add(withdraw);
	}
	
}
