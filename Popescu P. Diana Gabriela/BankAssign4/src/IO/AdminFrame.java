package IO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.Account;
import models.Bank;
import models.Person;
import models.SavingAccount;
import models.SpendingAccount;

public class AdminFrame extends GenericFrame {

	public static int NR_ELEM_IN_TABLE;

	private JPanel logOutOpPanel;

	public JTextField jTextField;
	public JButton searchFilterButton;
	private JPanel searchPanelButton;

	private JScrollPane jPanelContent;
	public JTable tableAdmin;
	private DefaultTableModel tableModel;

	public JButton addAccountButton;
	public JButton addHolderButton;
	public JButton removeHolderButton;

	public AdminFrame(Bank bank) {
		super();
		logInOutButton = new JButton("LOGOUT");
		addAccountButton = new JButton("ADD ACCOUNT");
		addHolderButton = new JButton("ADD HOLDER");
		removeHolderButton = new JButton("REMOVE HOLDER");

		logOutOpPanel = new JPanel(new GridLayout(1, 4));
		logOutOpPanel.add(addAccountButton);
		logOutOpPanel.add(addHolderButton);
		logOutOpPanel.add(removeHolderButton);
		logOutOpPanel.add(logInOutButton);

		jTextField = new JTextField(30);
		jTextField.setBounds(10, 30, 300, 300);
		jTextField.setBackground(Color.white);
		jTextField.setForeground(Color.black);
		searchFilterButton = new JButton("Search");
		searchPanelButton = new JPanel(new BorderLayout());
		JLabel labelType = new JLabel("Filter(Person ID):");

		searchPanelButton.add(labelType, BorderLayout.WEST);
		searchPanelButton.add(jTextField, BorderLayout.CENTER);
		searchPanelButton.add(searchFilterButton, BorderLayout.EAST);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("HolderID");
		tableModel.addColumn("Holder name");
		tableModel.addColumn("AccountID");
		tableModel.addColumn("Account type");
		tableModel.addColumn("Available MONEY");
		tableModel.addColumn("OP"); // removeAccount
		// tableModel.addColumn("OP2");
		tableAdmin = new JTable(tableModel);

		this.updateTable(bank);
		tableAdmin.setFillsViewportHeight(true);
		jPanelContent = new JScrollPane(tableAdmin);

		frame.setLayout(new BorderLayout());
		frame.add(searchPanelButton, BorderLayout.NORTH);
		frame.add(jPanelContent, BorderLayout.CENTER);
		frame.add(logOutOpPanel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void updateTable(Bank bank) {
		this.removeAllRows(); // preserve the order as in warehouse
		try {
			NR_ELEM_IN_TABLE = bank.bankMap.size();
		} catch (NullPointerException ex) {
			NR_ELEM_IN_TABLE = 0;
		}
		// if (NR_ELEM_IN_TABLE == 0) {
		// JLabel noElemLabel = new JLabel("no product to be displayed");
		// jPanelContent.add(noElemLabel);
		// }
		if (NR_ELEM_IN_TABLE > 0) {
			Set<Person> holders = bank.getHolders();
			Iterator<Person> iterator = holders.iterator();
			while (iterator.hasNext()) {
				Person auxP = iterator.next();
				Set<Account> accounts = bank.getAccounts(auxP);
				
				if(accounts.size() == 0){
					tableModel.addRow(
							new Object[] { auxP.ID, auxP.name, "-", "-", "-", "-" });
				}
				else{
					Iterator<Account> iterator2 = accounts.iterator();
					while (iterator2.hasNext()) {
						Account auxA = iterator2.next();
						tableModel.addRow(
								new Object[] { auxP.ID, auxP.name, auxA.ID, auxA.type, auxA.money, "REMOVE Account" });
					}
				}
				
			}
		}
	}

	public void updateTable(Bank bank, String searchForID) {
		if (!(searchForID.length() == 0)) { // not empty string
			this.removeAllRows();
			
			Set<Person> holders = bank.getHolders();
			Iterator<Person> iterator = holders.iterator();
			while (iterator.hasNext()) {
				Person auxP = iterator.next();
				
				if(auxP.ID.equals(searchForID)){
					Set<Account> accounts = bank.getAccounts(auxP);
					
					if(accounts.size() == 0){
						tableModel.addRow(
								new Object[] { auxP.ID, auxP.name, "-", "-", "-", "-" });
						NR_ELEM_IN_TABLE ++;
					}
					else{
						Iterator<Account> iterator2 = accounts.iterator();
						while (iterator2.hasNext()) {
							Account auxA = iterator2.next();
							tableModel.addRow(
									new Object[] { auxP.ID, auxP.name, auxA.ID, auxA.type, auxA.money, "REMOVE Account" });
							NR_ELEM_IN_TABLE ++;
						}
					}
				}//end-if (auxP.ID.equals(searchForID))
			}
		} // end-if there is a string
		else{ //empty string
			this.updateTable(bank);
		}
	}

	private void removeAllRows() {
		int n = tableModel.getRowCount();
		for (int i = n - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		NR_ELEM_IN_TABLE = 0;
	}

	// *** 1 ***// ADD holder
	public Person queryAddCustomer(Bank bank) { // ID, name
		JTextField xField = new JTextField(10); // id
		JTextField yField = new JTextField(15); // name

		JPanel myPanel = new JPanel(new GridLayout(2, 1));
		myPanel.add(new JLabel("ID:"));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("NAME:"));
		myPanel.add(yField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Customer details", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String id, name;
			try {
				id = xField.getText();
			} catch (NumberFormatException ex) {
				id = new Random().toString(); // invalid customer
			}
			try {
				name = yField.getText();
			} catch (NumberFormatException ex) {
				name = new Random().toString(); // invalid customer
			}

			Set<Person> holders = bank.getHolders();
			Iterator<Person> iterator = holders.iterator();
			while (iterator.hasNext()) {
				Person auxP = iterator.next();
				if(auxP.ID.equals(id) && auxP.name.equals(name)){
					System.out.println("already existent");
					return null;
				}
			}
			return new Person(id, name);
		}
		return null;
	}

	// *** 2 ***// ADD account
	public boolean queryAddAccount(Bank bank) { // ID, name
		JTextField xField = new JTextField(10); // P id
		JTextField yField = new JTextField(15); // P name
		JTextField zField = new JTextField(10); // A id
		JTextField wField = new JTextField(15); // A type

		JPanel myPanel = new JPanel(new GridLayout(2, 1));
		myPanel.add(new JLabel("Holder ID:"));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Holder NAME:"));
		myPanel.add(yField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("New Account ID:"));
		myPanel.add(zField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("New Account TYPE:"));
		myPanel.add(wField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "New ACCOUNT info", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String pid, pname, aid, atype;
			try {
				pid = xField.getText();
			} catch (NumberFormatException ex) {
				pid = new Random().toString(); // invalid customer
			}
			try {
				pname = yField.getText();
			} catch (NumberFormatException ex) {
				pname = new Random().toString(); // invalid customer
			}
			try {
				aid = zField.getText();
			} catch (NumberFormatException ex) {
				aid = new Random().toString(); // invalid customer
			}
			try {
				atype = wField.getText();
			} catch (NumberFormatException ex) {
				atype = new Random().toString(); // invalid customer
			}
			
			Set<Person> holders = bank.getHolders();
			Iterator<Person> iterator = holders.iterator();
			while (iterator.hasNext()) {
				Person auxP = iterator.next();
				if(auxP.ID.equals(pid) && auxP.name.equals(pname)){
					Set<Account> accounts = bank.getAccounts(auxP);
					if(accounts.size() == 0){
						if (atype.equals("SAVING")) {
							bank.addAccount(auxP, new SavingAccount(aid, 0));
							return true;
						}
						if (atype.equals("SPENDING")) {
							bank.addAccount(auxP, new SpendingAccount(aid, 0));
							return true;
						}
					}
					else{
						Iterator<Account> iterator2 = accounts.iterator();
						while (iterator2.hasNext()) {
							Account auxA = iterator2.next();
							if(auxA.ID.equals(aid) && auxA.type.equals(atype))
							{
								System.out.println("already exists");
								return false;
							}
						}
						if (atype.equals("SAVING")) {
							bank.addAccount(auxP, new SavingAccount(aid, 0));
							return true;
						}
						if (atype.equals("SPENDING")) {
							bank.addAccount(auxP, new SpendingAccount(aid, 0));
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	// *** 3 ***// REMOVE holder
	public boolean queryRemoveCustomer(Bank bank) { // ID, name
			JTextField xField = new JTextField(10); // id
			JTextField yField = new JTextField(15); // name

			JPanel myPanel = new JPanel(new GridLayout(2, 1));
			myPanel.add(new JLabel("ID:"));
			myPanel.add(xField);
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("NAME:"));
			myPanel.add(yField);

			int result = JOptionPane.showConfirmDialog(null, myPanel, "Customer details", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				String id, name;
				try {
					id = xField.getText();
				} catch (NumberFormatException ex) {
					id = new Random().toString(); // invalid customer
				}
				try {
					name = yField.getText();
				} catch (NumberFormatException ex) {
					name = new Random().toString(); // invalid customer
				}

				Set<Person> holders = bank.getHolders();
				Iterator<Person> iterator = holders.iterator();
				while (iterator.hasNext()) {
					Person auxP = iterator.next();
					if(auxP.ID.equals(id) && auxP.name.equals(name)){
						bank.removeHolder(auxP);
						return true;
					}
				}
				System.out.println("not existing");
				return false;
			}
			return false;
		}
}
