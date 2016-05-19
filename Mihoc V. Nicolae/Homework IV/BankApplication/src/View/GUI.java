package View;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import Bank.Bank;
import Management.Account;
import Management.SavingAccount;
import Management.SpendingAccount;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	private FilteredTableModel model;
	private TableRowSorter<FilteredTableModel> sorter;
	private JScrollPane pane;

	private JTextField searchbox, idbox, namebox, accIDbox, addressbox, balancebox, moneybox;

	private JLabel search, id, name, accID, address, balance, accType, money;

	private JButton addAccount, removeAccount, plus, minus;

	private JComboBox<Object> acctypebox;

	private Bank bank;

	public GUI() {
		super("Bank Application");
		setLayout(null);
		setSize(800, 600);

		bank = new Bank();

		model = new FilteredTableModel();
		sorter = new TableRowSorter<FilteredTableModel>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pane = new JScrollPane(table);

		addAccount = new JButton("Add Account");
		removeAccount = new JButton("Remove Account");
		plus = new JButton("Deposit");
		minus = new JButton("Withdraw");

		idbox = new JTextField(4);
		accIDbox = new JTextField(4);
		namebox = new JTextField(100);
		addressbox = new JTextField(100);
		balancebox = new JTextField(10);
		searchbox = new JTextField();
		moneybox = new JTextField(10);

		search = new JLabel("Search: ");
		id = new JLabel("Client ID: ");
		name = new JLabel("Client Name: ");
		accID = new JLabel("Acc ID: ");
		address = new JLabel("Address: ");
		balance = new JLabel("Balance: ");
		accType = new JLabel("Acc Type: ");
		money = new JLabel("Money: ");

		acctypebox = new JComboBox<Object>(new String[] { "Spending Account", "Saving Account" });

		search.setBounds(30, 30, 50, 30);
		add(search);
		accID.setBounds(30, 110, 70, 30);
		add(accID);
		accType.setBounds(30, 140, 70, 30);
		add(accType);
		id.setBounds(30, 170, 70, 30);
		add(id);
		name.setBounds(30, 200, 90, 30);
		add(name);
		address.setBounds(30, 230, 90, 30);
		add(address);
		balance.setBounds(30, 260, 90, 30);
		add(balance);
		money.setBounds(30, 290, 70, 30);
		add(money);

		searchbox.setBounds(90, 30, 100, 30);
		add(searchbox);
		accIDbox.setBounds(110, 115, 40, 20);
		add(accIDbox);
		acctypebox.setBounds(110, 145, 150, 20);
		acctypebox.setSelectedIndex(0);
		add(acctypebox);
		idbox.setBounds(110, 175, 40, 20);
		add(idbox);
		namebox.setBounds(110, 205, 150, 20);
		add(namebox);
		addressbox.setBounds(110, 235, 150, 20);
		add(addressbox);
		balancebox.setBounds(110, 265, 60, 20);
		add(balancebox);
		moneybox.setBounds(110, 295, 60, 20);
		add(moneybox);
		pane.setBounds(300, 60, 470, 450);
		add(pane);

		bank.readAccount(new File("data.dat"));
		model.setData(bank.reportAccount());

		addAccount.setBounds(50, 360, 140, 30);
		addAccount.addActionListener(new ActionListener() {
			int accid = 0;
			int cid = 0;
			int acType;
			int balances;
			String names, addresss;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						accid = Integer.parseInt(accIDbox.getText());
					} catch (NumberFormatException exc) {
						throw new Exception("Account ID must be an integer!");
					}
					try {
						cid = Integer.parseInt(idbox.getText());
					} catch (NumberFormatException exc) {
						throw new Exception("Client ID must be an integer!");
					}
					names = namebox.getText();
					if (names.equals(""))
						throw new Exception("Please enter a name!");
					addresss = addressbox.getText();
					if (addresss.equals(""))
						throw new Exception("Please enter an address!");
					balances = Integer.parseInt(balancebox.getText());
					int ok = JOptionPane.OK_OPTION;
					if (bank.findAccount(new Account(accid, cid, balances, names, addresss)) != null) {
						ok = JOptionPane.showConfirmDialog(null,
								"An account with the same ID already exists!\n"
										+ "Procedding will replace the existing account information with the current ones.\n"
										+ "Are you sure?",
								"Updating Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if (ok == JOptionPane.OK_OPTION) {
							bank.removeAccount(new Account(accid, cid, balances, names, addresss));
						}
					}
					acType = acctypebox.getSelectedIndex();
					if (acType == 0 && ok == JOptionPane.OK_OPTION) {
						bank.addAccount(new SpendingAccount(accid, cid, balances, names, addresss));
					} else if (ok == JOptionPane.OK_OPTION) {
						bank.addAccount(new SavingAccount(accid, cid, balances, names, addresss));
					}
					model.setData(bank.reportAccount());
					repaint();
					bank.writeAccount(new File("data.dat"));
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE,
							null);
				}

			}

		});
		add(addAccount);
		removeAccount.setBounds(50, 400, 140, 30);
		add(removeAccount);
		removeAccount.addActionListener(new ActionListener() {
			int ok;
			int accid, cid;

			@Override
			public void actionPerformed(ActionEvent e) {
				ok = JOptionPane.showConfirmDialog(null, "This will delete " + "the selected account.\nProceed?",
						"Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (ok == JOptionPane.OK_OPTION) {
					try {
						try {
							accid = Integer.parseInt(accIDbox.getText());
						} catch (NumberFormatException exc) {
							throw new Exception("Account ID must me a valid integer!");
						}
						try {
							cid = Integer.parseInt(idbox.getText());
						} catch (NumberFormatException exc) {
							throw new Exception("Client ID must be a valid integer!");
						}
						bank.removeAccount(new Account(accid, cid, 0, "", ""));
						model.setData(bank.reportAccount());
						bank.writeAccount(new File("data.dat"));
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(null, exc.getMessage(), "Invalid Input!",
								JOptionPane.ERROR_MESSAGE, null);
					}

				}

			}

		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {

				if (!event.getValueIsAdjusting() && (table.getSelectedRowCount() != 0)) {
					int selectedRow = table.getSelectedRow();
					Object[] row = model.getRowAt(selectedRow);
					accIDbox.setText(row[0].toString());
					if (row[1].toString().equals("Saving Account"))
						acctypebox.setSelectedIndex(1);
					else
						acctypebox.setSelectedIndex(0);
					idbox.setText(row[2].toString());
					namebox.setText(row[3].toString());
					addressbox.setText(row[4].toString());
					balancebox.setText(row[5].toString());

				}
			}
		});

		searchbox.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {
				newFilter();
			}

			public void insertUpdate(DocumentEvent e) {
				newFilter();
			}

			public void removeUpdate(DocumentEvent e) {
				newFilter();
			}
		});

		plus.setBounds(50, 440, 140, 30);
		add(plus);
		plus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int accid = 0, addmoney = 0, balances = 0, cid = 0;
				int acType;
				String names;
				String addresss = null;
				int money;
				try {
					try {
						accid = Integer.parseInt(accIDbox.getText());
					} catch (NumberFormatException exc) {
						throw new Exception("Account ID must be an integer!");
					}
					try {
						cid = Integer.parseInt(idbox.getText());
					} catch (NumberFormatException exc) {
						throw new Exception("Client ID must be an integer!");
					}
					names = namebox.getText();
					if (names.equals(""))
						throw new Exception("Please enter a name!");
					addresss = addressbox.getText();
					if (addresss.equals(""))
						throw new Exception("Please enter the address");
					balances = Integer.parseInt(balancebox.getText());
					acType = acctypebox.getSelectedIndex();
					if (acType == 0) {
						addmoney = Integer.parseInt(moneybox.getText());
						money = (int) model.getValueAt((accid - 2), 5);
						if (bank.findAccount(new Account(accid - 1, cid, money, names, addresss)) != null) {
							if (addmoney < money) {
								if (bank.findAccount(new Account(accid, cid, balances, names, addresss)) != null) {
									bank.removeAccount(new Account(accid, cid, balances, names, addresss));

									bank.addAccount(new SpendingAccount(accid, cid, balances + addmoney, names, addresss));
									bank.removeAccount(new Account(accid - 1, cid, money, names, addresss));
									bank.addAccount(new SavingAccount(accid-1, cid, money - addmoney, names, addresss));
								}
							} else {
								JOptionPane.showMessageDialog(null, "Not enough money in the SavingAccount");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Not a Spending Account selected!");
					}
					model.setData(bank.reportAccount());
					bank.writeAccount(new File("data.dat"));
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE,
							null);
				}
			}

		});

		minus.setBounds(50, 480, 140, 30);
		add(minus);

		minus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int accid = 0, minusmoney = 0, balances = 0, cid = 0;
				int acType;
				String names;
				String addresss = null;

				try {
					try {
						accid = Integer.parseInt(accIDbox.getText());
					} catch (NumberFormatException exc) {
						throw new Exception("Account ID must be an integer!");
					}
					try {
						cid = Integer.parseInt(idbox.getText());
					} catch (NumberFormatException exc) {
						throw new Exception("Client ID must be an integer!");
					}
					names = namebox.getText();
					if (names.equals(""))
						throw new Exception("Please enter a name!");
					addresss = addressbox.getText();
					if (addresss.equals(""))
						throw new Exception("Please enter the address");
					balances = Integer.parseInt(balancebox.getText());
					acType = acctypebox.getSelectedIndex();
					if (acType == 0) {
						minusmoney = Integer.parseInt(moneybox.getText());
						if (minusmoney > balances) {
							JOptionPane.showMessageDialog(null, "Not enough money!");
						} else {
							if (bank.findAccount(new Account(accid, cid, balances, names, addresss)) != null) {
								bank.removeAccount(new Account(accid, cid, balances, names, addresss));
							}
							bank.addAccount(new SpendingAccount(accid, cid, balances - minusmoney, names, addresss));
						}
					}
					model.setData(bank.reportAccount());
					bank.writeAccount(new File("data.dat"));
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE,
							null);
				}
			}
		});
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void newFilter() {
		RowFilter<FilteredTableModel, Object> filter = null;
		try {
			filter = RowFilter.regexFilter(searchbox.getText());
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(filter);
	}
}
