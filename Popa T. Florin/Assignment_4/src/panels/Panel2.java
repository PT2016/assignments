package panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel2 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton userLogout;
	public JButton accounts;
	public JButton btnAddClient;
	public JButton btnRemoveClient;
	public JButton btnRefresh;
	
	public JTextField nameField;
	public JTextField ageField;
	public JTextField accField;
	
	public JLabel name;
	public JLabel age;

	public Panel2(){
		
		setLayout(null);
		
		userLogout = new JButton("Logout");
		userLogout.setBounds(481, 14, 117, 29);
		
		accounts = new JButton("Accounts");
		accounts.setBounds(352, 14, 117, 29);
		
		name = new JLabel("Name:");
		name.setBounds(50, 308, 130, 26);
		nameField = new JTextField();
		nameField.setBounds(100, 308, 130, 26);
		
		age = new JLabel("Age:");
		age.setBounds(50, 338, 130, 26);
		ageField = new JTextField();
		ageField.setBounds(100, 338, 130, 26);
		
		btnAddClient = new JButton("Add Client");
		btnAddClient.setBounds(71, 382, 117, 29);
		
		btnRemoveClient = new JButton("Remove Client");
		btnRemoveClient.setBounds(71, 412, 117, 29);
		
		btnRefresh = new JButton("REFRESH");
		btnRefresh.setBounds(471, 382, 117, 29);
		
		add(btnRemoveClient);
		add(age);
		add(name);
		add(nameField);
		add(ageField);
		add(userLogout);
		add(accounts);
		add(btnAddClient);
		add(btnRefresh);
	}
	
}
