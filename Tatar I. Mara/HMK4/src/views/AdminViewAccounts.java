package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import controller.Serializer;
import models.Bank;
import models.Person;
import models.SavingAccount;
import models.SpendingAccount;
import models.Account;

public class AdminViewAccounts {

	private Serializer io;
	private JTable table;
	private DefaultTableModel model;
	private Bank bank;
	
	private String amount;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * constructor
	 */
	public AdminViewAccounts() {

		JFrame frame = new JFrame();
		io = new Serializer();
		table = new JTable();
		bank = io.deserializeBank();

		// create a table model and set a Column Identifiers to this model
		Object[] columns = { "Name", "Type", "Money", "Open Date", "Expiration Date" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		Font font = new Font("", 1, 18);
		table.setModel(model);
		table.setBackground(new Color(255, 255, 230));
		table.setForeground(new Color(81, 52, 21));
		table.setGridColor(new Color(81, 52, 21));

		table.setFont(font);
		table.setRowHeight(30);

		for (Entry<Person, ArrayList<Account>> entry : bank.getBankData().entrySet()) {
			Person key = entry.getKey();
			Object value = entry.getValue();
			for (Account a : entry.getValue()) {
				Object[] row = { entry.getKey().getName(), a.getType(), a.getAccountSum(),
						sdf.format(a.getcreationDate()), sdf.format(a.getValidUntilDate()) };
				model.addRow(row);
			}
		}

		JTextField textName = new JTextField();
		JTextField textType = new JTextField();
		JTextField textMoney = new JTextField();

		// create JButtons
	
		JButton btnAdd = new JButton("Add Account");
		btnAdd.setBackground(new Color(255, 255, 230));
		JButton btnRemove = new JButton("Remove Account");
		btnRemove.setBackground(new Color(255, 255, 230));
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBackground(new Color(255, 255, 230));
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBackground(new Color(255, 255, 230));
		JButton btnViewClients = new JButton("View Clients");
		btnViewClients.setBackground(new Color(255, 255, 230));
		// textId.setBounds(20, 220, 100, 25);
		textName.setBounds(20, 210, 150, 25);
		textName.setBackground(new Color(255, 255, 230));
		textType.setBounds(20, 240, 150, 25);
		textType.setBackground(new Color(255, 255, 230));
		textMoney.setBounds(20, 270, 150, 25);
		textMoney.setBackground(new Color(255, 255, 230));
	

		btnAdd.setBounds(180, 210, 150, 25);
		btnDeposit.setBounds(180, 240, 150, 25);
		btnRemove.setBounds(180, 270, 150, 25);
		btnWithdraw.setBounds(180, 330, 150, 25);
		btnViewClients.setBounds(180, 300, 150, 25);

		// create JScrollPane

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 200);
		pane.getViewport().setBackground(new Color(232, 189, 128));
		pane.setBackground(new Color(232, 189, 128));
		frame.setLayout(null);
		frame.add(pane);

		// add JTextFields to the jframe
		// frame.add(textId);
		frame.add(textName);
		frame.add(textType);
		frame.add(textMoney);

		// add JButtons to the jframe
	
		frame.add(btnAdd);
		frame.add(btnRemove);
		frame.add(btnDeposit);
		frame.add(btnWithdraw);
		frame.add(btnViewClients);
		// create an array of objects to set the row data

		Object[] row = new Object[5];

		// button add row

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row[0] = textName.getText();
				row[1] = textType.getText();
				row[2] = textMoney.getText();

				boolean validOperation = false;
				boolean personFound = false;
				for (Person p : bank.getBankData().keySet()) {
					if (p.getName().compareTo(row[0].toString()) == 0) {
						personFound = true;
						Account account;
						if (row[1].toString().compareTo("Spending") == 0) {
							account = new SpendingAccount(Double.parseDouble(row[2].toString()));
							validOperation = true;
						} else if (row[1].toString().compareTo("Saving") == 0) {
							account = new SavingAccount(Double.parseDouble(row[2].toString()));
							validOperation = true;
						} else {
							JOptionPane.showMessageDialog(null, "Please register a valid type");
							account = null;
						}

						if ((account != null) && (validOperation) && (account.sumIsInsufficient() == false)) {
							bank.addHolderAccount(p, account);
							row[3] = sdf.format(account.getcreationDate());
							row[4] = sdf.format(account.getValidUntilDate());
							model.addRow(row);
						} else if (account.sumIsInsufficient())
							JOptionPane.showMessageDialog(null, "Client need at least 10$ to open a saving account");
						else
							JOptionPane.showMessageDialog(null, "Some data might not be valid");
					}
				}
				if (personFound == false)
					JOptionPane.showMessageDialog(null, "Please register the client first");

				System.out.println(bank.getReport());
				io.SerializeBank(bank);

			}
		});

		btnViewClients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminViewClients();
				frame.dispose();
			}

		});
		btnWithdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			//	amount = af.getAmount();
				int i = table.getSelectedRow();
			
				if (i >= 0) {
					java.util.Iterator<Entry<Person, ArrayList<Account>>> it = bank.getBankData().entrySet().iterator();
					Entry<Person, ArrayList<Account>> entry;
					while (it.hasNext()) {
						entry = it.next();
						if (entry.getKey().getName().compareTo(textName.getText().toString()) == 0) {
							for (java.util.Iterator<Account> itAcc = entry.getValue().iterator(); itAcc.hasNext();) {
								Account account = (Account) itAcc.next();
								if (account.getType().compareTo(textType.getText()) == 0) 
									if ((Double) account.getAccountSum() == (Double.parseDouble(textMoney.getText()))){
										amount=JOptionPane.showInputDialog(null,"Please insert the amount of money");
										account.withdrawMoney(Double.parseDouble(amount));
									
									if (account.sumIsInsufficient()) {
										JOptionPane.showMessageDialog(null,
												"Cannot perform this operation.Insufficient funds");
												account.setSumIsInsufficient(false);
									} else {
										
										io.SerializeBank(bank);
										JOptionPane.showMessageDialog(null, "Successful operation");
										model.setValueAt(account.getAccountSum(), i, 2);
										
									}
								}
							}

						}

					}
				} else {
					System.out.println("Withdrawal Error");
				}
			}

		});
		// button remove row
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				boolean validOperation = false;
				int index=-1;
				int count=-1;
				if (i >= 0) {
					java.util.Iterator<Entry<Person, ArrayList<Account>>> it = bank.getBankData().entrySet().iterator();
					Entry<Person, ArrayList<Account>> entry;
					while (it.hasNext()) {
						entry = it.next();
						if (entry.getKey().getName().compareTo(textName.getText().toString()) == 0) {
							for (java.util.Iterator<Account> itAcc = entry.getValue().iterator(); itAcc.hasNext();) {
								Account account = (Account) itAcc.next();
								count++;
								if (account.getType().compareTo(textType.getText()) == 0) {
									if ((Double) account.getAccountSum() == (Double.parseDouble(textMoney.getText())))
										index=count;
									validOperation = true;
								}
							}
							if (validOperation){
								bank.removeHolderAccount(entry.getKey(), bank.getBankData().get(entry.getKey()).get(index));
								io.SerializeBank(bank);
								System.out.println(bank.getReport());
								model.removeRow(i);}
							else
								JOptionPane.showMessageDialog(null, "There was an error in the system");
						}

					}
				
				} else {
					System.out.println("Delete Error");
				}
			}
		});
		// get selected row data From table to textfields
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// i = the index of the selected row
				int i = table.getSelectedRow();
				textName.setText(model.getValueAt(i, 0).toString());
				textType.setText(model.getValueAt(i, 1).toString());
				textMoney.setText(model.getValueAt(i, 2).toString());
			}
		});
		// button update row
		btnDeposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();

				if (i >= 0) {
					java.util.Iterator<Entry<Person, ArrayList<Account>>> it = bank.getBankData().entrySet().iterator();
					Entry<Person, ArrayList<Account>> entry;
					while (it.hasNext()) {
						entry = it.next();
						if (entry.getKey().getName().compareTo(textName.getText().toString()) == 0) {
							for (java.util.Iterator<Account> itAcc = entry.getValue().iterator(); itAcc.hasNext();) {
								Account account = (Account) itAcc.next();
								if (account.getType().compareTo(textType.getText()) == 0) 
									if ((Double) account.getAccountSum() == (Double.parseDouble(textMoney.getText()))){
										amount=JOptionPane.showInputDialog(null,"Please insert the amount of money");
                                        account.depositMoney(Double.parseDouble(amount));
										io.SerializeBank(bank);
										JOptionPane.showMessageDialog(null, "Successful operation");
										model.setValueAt(account.getAccountSum(), i, 2);

								}
							}

						}

					}
				} else {
					System.out.println("Deposit Error");
				}
			}
		});
		frame.setSize(900, 410);
		frame.getContentPane().setBackground(new Color(162, 104, 42));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
