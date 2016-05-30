package panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.Account;
import models.Bank;
import models.Person;
import tables.ClientsTable;
import tables.ClientsTableModel;
import tables.InfoTable;
import tables.InfoTableModel;

public class AdminPanel extends JPanel implements ActionListener {

	private Bank bank;

	private JButton viewClients = new JButton("View Clients");
	private JButton checkClientAccounts = new JButton("Check Client Accounts");
	private JButton removeClient = new JButton("Remove Client");
	private JButton removeClientAccount = new JButton("Remove Client Account");
	private JButton addClient = new JButton("Add Client");
	private JButton addClientAccount = new JButton("Add Client Account");

	private JPanel layout1 = new JPanel();
	private JPanel layout2 = new JPanel();

	ClientsTableModel clientsTableModel = new ClientsTableModel();
	ClientsTable table;
	private JPanel clientsTablePanel = new JPanel();

	InfoTableModel infoTableModel = new InfoTableModel();
	InfoTable infoTable;
	private JPanel infoTablePanel = new JPanel();

	public AdminPanel(Bank bank) {
		this.bank = bank;
		setLayout(new BorderLayout());

		viewClients.setActionCommand("viewClients");
		viewClients.addActionListener(this);
		checkClientAccounts.setActionCommand("checkAccounts");
		checkClientAccounts.addActionListener(this);
		addClient.setActionCommand("addClient");
		addClient.addActionListener(this);
		removeClient.setActionCommand("removeClient");
		removeClient.addActionListener(this);
		removeClientAccount.setActionCommand("removeAccount");
		removeClientAccount.addActionListener(this);
		addClientAccount.setActionCommand("addAccount");
		addClientAccount.addActionListener(this);

		layout1.setLayout(new GridLayout(3, 1));
		layout1.add(viewClients);
		layout1.add(checkClientAccounts);
		layout1.add(addClient);

		layout2.setLayout(new GridLayout(3, 1));
		layout2.add(removeClient);
		layout2.add(removeClientAccount);
		layout2.add(addClientAccount);

		add(layout1, BorderLayout.WEST);
		add(layout2, BorderLayout.EAST);
		clientsTablePanel.setVisible(false);
		infoTablePanel.setVisible(false);
		add(clientsTablePanel, BorderLayout.CENTER);

		add(infoTablePanel, BorderLayout.CENTER);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("viewClients")) {
			updateClients();
		} else if (e.getActionCommand().equals("checkAccounts")) {
			boolean found = false;
			String firstName = JOptionPane.showInputDialog("Enter first name");
			String lastName = JOptionPane.showInputDialog("Enter last name");
			for (Person client : bank.getClients()) {
				if (client.getFirstName().equals(firstName) && client.getLastName().equals(lastName)) {
					for (Account a : bank.getAccounts()) {
						if (a.getOwnerFirstName().equals(client.getFirstName())
								&& a.getOwnerLastName().equals(client.getLastName())) {
							found = true;
							updateInfo(client);
						}
					}
				}
			}
			if (!found) {
				JOptionPane.showMessageDialog(null, "No account found");
			}
		} else if (e.getActionCommand().equals("addClient")) {
			Random randomGenerator = new Random();
			String firstName = JOptionPane.showInputDialog("Enter first name");
			String lastName = JOptionPane.showInputDialog("Enter last name");
			Person newClient = new Person(firstName, lastName, randomGenerator.nextInt(100));
			bank.addPerson(newClient);
			updateClients();
			bank.serialize();
		} else if (e.getActionCommand().equals("removeClient")) {
			boolean found = false;
			Person foundPerson = null;
			String firstName = JOptionPane.showInputDialog("Enter first name");
			String lastName = JOptionPane.showInputDialog("Enter last name");
			for (Person client : bank.getClients()) {
				if (client.getFirstName().equals(firstName) && client.getLastName().equals(lastName)) {
					found = true;
					foundPerson = client;
				}
			}
			if (found) {
				bank.removePerson(foundPerson);
				updateClients();
			}

			bank.serialize();
		} else if (e.getActionCommand().equals("removeAccount")) {
			Person person = null;
			Account account = null;
			String firstName = JOptionPane.showInputDialog("Enter first name");
			String lastName = JOptionPane.showInputDialog("Enter last name");
			String accountId = JOptionPane.showInputDialog("Enter account ID");

			for (Person client : bank.getClients()) {
				if (client.getFirstName().equals(firstName) && client.getLastName().equals(lastName)) {
					for (Account a : bank.getAccounts()) {
						if (a.getOwnerFirstName().equals(client.getFirstName())
								&& a.getOwnerLastName().equals(client.getLastName())) {
							account = a;
							person = client;
						}
					}
				}
			}
			bank.removeHolderAccounts(person, account);
			updateInfo(person);
			bank.serialize();

		} else if (e.getActionCommand().equals("addAccount")) {
			Person foundPerson = null;
			Random randomGenerator = new Random();
			boolean found = false;
			String firstName = JOptionPane.showInputDialog("Enter first name");
			String lastName = JOptionPane.showInputDialog("Enter last name");
			for (Person p : bank.getClients()) {
				if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
					found = true;
					foundPerson = p;
				}
			}

			if (!found) {
				Person newClient = new Person(firstName, lastName, randomGenerator.nextInt(100));
				bank.addPerson(newClient);
				String type = JOptionPane.showInputDialog("Enter account type");
				String pin = JOptionPane.showInputDialog("Enter pin");

				bank.addHolderAccounts(newClient, type, pin);
				updateInfo(newClient);
			} else {
				String type = JOptionPane.showInputDialog("Enter account type");
				String pin = JOptionPane.showInputDialog("Enter pin");

				bank.addHolderAccounts(foundPerson, type, pin);
				updateInfo(foundPerson);
			}
			bank.serialize();
		}

	}

	public void updateClients() {
		for (int j = clientsTableModel.getRowCount() - 1; j >= 0; j--) {
			clientsTableModel.removeClient(j);
		}
		ArrayList<Person> clients;
		clients = bank.getClients();
		for (Person p : clients) {
			clientsTableModel.addCustomer(p);
		}
		table = new ClientsTable(clientsTableModel);
		clientsTablePanel.add(table);
		clientsTablePanel.setVisible(true);
		infoTablePanel.setVisible(false);
		clientsTablePanel.setSize(850, 400);
		setVisible(true);
		add(clientsTablePanel, BorderLayout.CENTER);
	}

	public void updateInfo(Person client) {
		for (int j = infoTableModel.getRowCount() - 1; j >= 0; j--) {
			infoTableModel.removeAccount(j);
		}
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = bank.getAccounts();
		if (bank.getHashtable().get(client) != null)
			for (Iterator<Account> iterator = bank.getHashtable().get(client).iterator(); iterator.hasNext();) {
				Account value = iterator.next();
				if (value != null && value.getOwnerFirstName().equals(client.getFirstName())
						&& value.getOwnerLastName().equals(client.getLastName())) {
					infoTableModel.addAccount(value);
				}

			}
		infoTable = new InfoTable(infoTableModel);
		infoTablePanel.add(infoTable);
		infoTablePanel.setVisible(true);
		clientsTablePanel.setVisible(false);
		infoTablePanel.setSize(850, 450);
		setSize(850, 450);
		setVisible(true);
		add(infoTablePanel, BorderLayout.CENTER);
	}

}
