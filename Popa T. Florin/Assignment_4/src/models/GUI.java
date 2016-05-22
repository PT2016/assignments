package models;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import panels.Panel1;
import panels.Panel2;
import panels.Panel3;

public class GUI {

	public JFrame frame;
	JPanel panel;
	CardLayout card;

	JTable table;
	JTable table1;
	
	Bank bank = new Bank();

	public GUI() {

		initialize();

	}

	public void initialize() {

		frame = new JFrame();
		frame.setSize(640, 480);

		card = new CardLayout();
		panel = new JPanel();
		panel.setLayout(card);

		Panel1 p1 = new Panel1();
		Panel2 p2 = new Panel2();
		Panel3 p3 = new Panel3();

		panel.add(p1, "Login");
		panel.add(p2, "Admin");
		panel.add(p3, "Accounts");


		// THE CLIENTS TABLE
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.setBounds(165, 87, 100, 100);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(40, 55, 558, 241);
		model.addColumn("Name");
		model.addColumn("Age");
		model.addColumn("Accounts");
		p2.add(scrollPane);

		// THE ACCOUNTS TABLE
		DefaultTableModel model1 = new DefaultTableModel();
		table1 = new JTable(model1);
		table1.setBounds(165, 87, 100, 100);
		JScrollPane scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(40, 55, 558, 211);
		model1.addColumn("Name");
		model1.addColumn("Type");
		model1.addColumn("Funds");
		model1.addColumn("ID");
		p3.add(scrollPane1);

		frame.getContentPane().add(panel);
		
		//BUTTON EVENTS
		p1.login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				String usr = p1.userField.getText();
				String pass = new String(p1.passField.getPassword());
				Properties config = new Properties();
				InputStream file;

				try {
					file = new FileInputStream("res/data.properties");
					config.load(file);

					if (usr.toString().equals("admin")) {

						String adminPassword = config.getProperty("username." + usr.toString());
						if (adminPassword.equals(pass.toString())) {
							card.show(panel, "Admin");
							p1.error.setVisible(false);
							p1.passField.setText(null);
							p1.userField.setText(null);
						} else {
							p1.error.setVisible(true);
							p1.passField.setText(null);
							p1.userField.setText(null);
						}
					}

					file.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		p2.userLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				card.show(panel, "Login");
			}
		});
		
		p2.btnAddClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String name = p2.nameField.getText();
				String age = p2.ageField.getText();
				Person p = new Person(name,Integer.parseInt(age));
				bank.addPerson(p);
				model.addRow(new Object[] { p.getName(), p.getAge(), p.getNrOfAccounts() });
				p2.nameField.setText(null);
				p2.ageField.setText(null);
			}
		});
		
		p2.btnRemoveClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = table.getSelectedRow();
				String name = table.getValueAt(index, 0).toString();
				model.removeRow(index);
				Iterator<Map.Entry<Person, ArrayList<Account>>> iterator = bank.data.entrySet().iterator();
				while(iterator.hasNext()){
					Map.Entry<Person, ArrayList<Account>> aux = iterator.next();
					if(aux.getKey().getName().equals(name)){
						bank.removePerson(aux.getKey());
						break;
					}
				}
				}
				
		});
		
		p2.btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int rowCount = model.getRowCount();
				for(int i=rowCount-1;i>=0;i--){
					model.removeRow(i);
				}
				
				Iterator<Map.Entry<Person, ArrayList<Account>>> iterator = bank.data.entrySet().iterator();
				
				while(iterator.hasNext()){
					Map.Entry<Person, ArrayList<Account>> aux = iterator.next();
					model.addRow(new Object[] { aux.getKey().getName(), aux.getKey().getAge(), aux.getKey().getNrOfAccounts() });
				}
			}
		});
		
		p2.accounts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				card.show(panel, "Accounts");
			}
		});
		
		p3.userLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				card.show(panel, "Login");
			}
		});
		
		p3.clients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				card.show(panel, "Admin");
			}
		});
		
		p3.btnRemoveAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				int index = table1.getSelectedRow();
				String id = table1.getValueAt(index, 3).toString();
				model1.removeRow(index);
				Iterator<Map.Entry<Person, ArrayList<Account>>> iterator = bank.data.entrySet().iterator();
				
				while(iterator.hasNext()){
					Map.Entry<Person, ArrayList<Account>> aux = iterator.next();
					Iterator<Account> itAccount = aux.getValue().iterator();
					while(itAccount.hasNext()){
						Account a = itAccount.next();
					if(a.getAccountID()==Integer.parseInt(id)){
					  bank.removeHolderAccount(aux.getKey(), a);
					}
					}
				}
				
			}
		});
		
		p3.btnAddAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String name = p3.nameField.getText();
				String type = p3.typeField.getText();
				String funds = p3.fundsField.getText();
				
				Iterator<Map.Entry<Person, ArrayList<Account>>> iterator = bank.data.entrySet().iterator();
				
				while(iterator.hasNext()){
					Map.Entry<Person, ArrayList<Account>> aux = iterator.next();
					if(aux.getKey().getName().equals(name)){
						if(type.equals("Savings")){
							SavingAccount account = new SavingAccount(Double.parseDouble(funds));
							bank.addHolderAccount(aux.getKey(), account);
							Integer id1 = account.getAccountID();
							model1.addRow(new Object[] { aux.getKey().getName(), type, funds,id1.toString() });
							p3.nameField.setText(null);
							p3.typeField.setText(null);
							p3.fundsField.setText(null);
						}
						if(type.equals("Spending")){
							SpendingAccount account = new SpendingAccount(Double.parseDouble(funds));
							bank.addHolderAccount(aux.getKey(), account);
							Integer id = account.getAccountID();
							model1.addRow(new Object[] { aux.getKey().getName(), type, funds,id.toString()});
							p3.nameField.setText(null);
							p3.typeField.setText(null);
							p3.fundsField.setText(null);
							}
					}
				}
				
			}
		});
		
		p3.btnWithdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				int index = table1.getSelectedRow();
				String id = table1.getValueAt(index, 3).toString();
				String withdrawAmount = p3.withdrawField.getText();
				
				Iterator<Map.Entry<Person, ArrayList<Account>>> iterator = bank.data.entrySet().iterator();
				
				while(iterator.hasNext()){
					Map.Entry<Person, ArrayList<Account>> aux = iterator.next();
					Iterator<Account> itAccount = aux.getValue().iterator();
					while(itAccount.hasNext()){
						Account a = itAccount.next();
					if(a.getAccountID()==Integer.parseInt(id)){
					  double amount=a.withdrawMoney(Double.parseDouble(withdrawAmount));
					  table1.setValueAt(amount, index, 2);
					}
					}
				}
				
			}
		});
		
		p3.btnDeposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				int index = table1.getSelectedRow();
				String id = table1.getValueAt(index, 3).toString();
				String depositAmount = p3.depositField.getText();
				
				Iterator<Map.Entry<Person, ArrayList<Account>>> iterator = bank.data.entrySet().iterator();
				
				while(iterator.hasNext()){
					Map.Entry<Person, ArrayList<Account>> aux = iterator.next();
					Iterator<Account> itAccount = aux.getValue().iterator();
					while(itAccount.hasNext()){
						Account a = itAccount.next();
					if(a.getAccountID()==Integer.parseInt(id)){
					  double amount=a.depositMoney(Double.parseDouble(depositAmount));
					  table1.setValueAt(amount, index, 2);
					}
					}
				}
				
			}
		});
	}
}

