package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entities.Account;
import Entities.Bank;
import Entities.Person;
import Entities.SavingAccount;
import Entities.SpendingAccount;
import Helper.Serialization;
import Helper.UserInputChecker;


public class Frame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JFrame f = new JFrame();

	private JTable table;
	private JRadioButton userButton, managerButton;
	private ButtonGroup group;
	boolean user, manager;
	private JButton btnAddAccount, btnDeleteAccount, btnDeletePerson, findAllPersons;
	private JButton btnDeposit, btnWithdraw, btnSold, saveBank,  findAllAccounts;
	private JTextField textName, textEmail, textPhone, textId, textMoney;
	private JLabel lblName, lblEmail, lblPhone, lblId, lblMoney, bankLabel;
	private DefaultTableModel model;
	UserInputChecker checkInput;
	Bank bank;
	Serialization ser;
	JScrollPane scrollPane;

	public Frame() {
		super("Bank");

		ser = new Serialization();
		try {
			bank = ser.DeserializeBank();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		checkInput = new UserInputChecker();
		table = new JTable();

		Object[] columns = { "Name", "Email", "Phone", "Id", "Money" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setBackground(Color.lightGray);
		table.setForeground(Color.GRAY);
		table.setRowHeight(30);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 200);

		btnAddAccount = new JButton("Add account");
		btnDeleteAccount = new JButton("Delete account");
		btnDeletePerson = new JButton("Delete person");

		btnAddAccount.addActionListener(this);
		btnDeletePerson.addActionListener(this);
		btnDeleteAccount.addActionListener(this);

		btnAddAccount.setBounds(250, 220, 120, 25);
		btnDeletePerson.setBounds(250, 260, 120, 25);
		btnDeleteAccount.setBounds(250, 300, 120, 25);

		textName = new JTextField();
		textEmail = new JTextField();
		textPhone = new JTextField();
		textId = new JTextField();
		textMoney = new JTextField();

		textName.setBounds(50, 220, 100, 25);
		textEmail.setBounds(50, 250, 100, 25);
		textPhone.setBounds(50, 280, 100, 25);
		textId.setBounds(50, 310, 100, 25);
		textMoney.setBounds(50, 340, 100, 25);

		findAllPersons = new JButton("Find all persons");
		findAllPersons.addActionListener(this);
		findAllPersons.setBounds(250, 340, 125, 25);

		findAllAccounts = new JButton("Find all accounts");
		findAllAccounts.addActionListener(this);
		findAllAccounts.setBounds(250, 380, 130, 25);

		saveBank = new JButton("Save bank");
		saveBank.addActionListener(this);
		saveBank.setBounds(450, 380, 100, 25);

		btnDeposit = new JButton("Deposit Money");
		btnWithdraw = new JButton("Withdraw Money");
		btnSold = new JButton("Sold balance");

		btnDeposit.addActionListener(this);
		btnWithdraw.addActionListener(this);
		btnSold.addActionListener(this);

		btnDeposit.setBounds(620, 300, 200, 25);
		btnWithdraw.setBounds(620, 340, 200, 25);
		btnSold.setBounds(620, 380, 200, 25);

		lblName = new JLabel("Name ");
		lblName.setBounds(10, 220, 100, 25);

		lblEmail = new JLabel("Email ");
		lblEmail.setBounds(10, 250, 100, 25);

		lblPhone = new JLabel("Phone ");
		lblPhone.setBounds(10, 280, 100, 25);

		lblId = new JLabel("PIN ");
		lblId.setBounds(10, 310, 100, 25);

		lblMoney = new JLabel("Money");
		lblMoney.setBounds(10, 340, 100, 25);

		ImageIcon iconBank = new ImageIcon("Resources/bank.jpg");
		Image takeImage = iconBank.getImage();
		Image resizedImg = takeImage.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(resizedImg);
		bankLabel = new JLabel();
		bankLabel.setBounds(432, 200, 350, 200);
		bankLabel.setIcon(newImage);

		setLayout(null);

		add(bankLabel);
		add(lblName);
		add(lblEmail);
		add(lblPhone);
		add(lblId);
		add(lblMoney);
		add(btnSold);
		add(btnDeposit);
		add(btnWithdraw);
		add(pane);
		add(textName);
		add(textEmail);
		add(textPhone);
		add(textId);
		add(textMoney);
		add(btnAddAccount);
		add(btnDeletePerson);
		add(btnDeleteAccount);
		add(findAllPersons);
		add(findAllAccounts);
		add(saveBank);

		userButton = new JRadioButton("Account Holder");
		userButton.addActionListener(this);
		userButton.setBounds(650, 200, 120, 30);
		getContentPane().add(userButton);

		managerButton = new JRadioButton("Bank Manager");
		managerButton.addActionListener(this);
		managerButton.setBounds(650, 230, 120, 30);
		getContentPane().add(managerButton);

		group = new ButtonGroup();
		user = false;
		manager = false;
		group.add(userButton);
		group.add(managerButton);

		setVisible(true);
		setSize(900, 470);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent event) {
				int i = table.getSelectedRow();
				textName.setText(model.getValueAt(i, 0).toString());
				textEmail.setText(model.getValueAt(i, 1).toString());
				textPhone.setText(model.getValueAt(i, 2).toString());
				textId.setText(model.getValueAt(i, 3).toString());
				textMoney.setText(model.getValueAt(i, 4).toString());

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object c = (Object) event.getSource();

		if (c == userButton) {
			user = true;
			manager = false;
		}

		if (c == managerButton) {
			user = false;
			manager = true;
		}
		// for account holder
		if (user == true) {
			btnAddAccount.setEnabled(false);
			btnDeleteAccount.setEnabled(false);
			btnDeletePerson.setEnabled(false);
			findAllPersons.setEnabled(false);
			findAllAccounts.setEnabled(false);
			saveBank.setEnabled(false);

			btnDeposit.setEnabled(true);
			btnWithdraw.setEnabled(true);
			btnSold.setEnabled(true);
			// for bank manager
		} else if (manager == true) {
			btnDeposit.setEnabled(false);
			btnWithdraw.setEnabled(false);
			btnSold.setEnabled(false);

			btnAddAccount.setEnabled(true);
			btnDeleteAccount.setEnabled(true);
			btnDeletePerson.setEnabled(true);
			findAllPersons.setEnabled(true);
			findAllAccounts.setEnabled(true);
			saveBank.setEnabled(true);
		}

		/**
		 * Deposit money to SPENDING or SAVING account
		 */
		if (event.getSource() == btnDeposit) {

			String name = checkInput.checkString(textName);
			String email = checkInput.checkString(textEmail);
			String phone = checkInput.checkString(textPhone);
			int pin = checkInput.checkNumber(textId);
			int sum = checkInput.checkNumber(textMoney);

			if (name != null && email != null && phone != null && pin != 0 && sum != 0) {
				Person p = new Person(name, email, phone);
				if (sum > 0){
					bank.depositMoney(sum, pin, p);
					JOptionPane.showMessageDialog(this, "The sum " + sum + " was successfully added to your account!");
				}
				else
					JOptionPane.showMessageDialog(this, "Sum to be deposit must be greater than 0!");
			} else
				JOptionPane.showMessageDialog(this, "Please enter valid data!");
			textMoney.setText(" ");
		}

		/**
		 * Withdraw money from a certain account
		 */
		else if (event.getSource() == btnWithdraw) {

			String name = checkInput.checkString(textName);
			String email = checkInput.checkString(textEmail);
			String phone = checkInput.checkString(textPhone);
			int pin = checkInput.checkNumber(textId);
			int sum = checkInput.checkNumber(textMoney);

			if (name != null && email != null && phone != null && pin != 0 && sum != 0) {
				Person p = new Person(name, email, phone);
				if (sum > 0){
					bank.withdrawMoney(sum, pin, p);
				  // JOptionPane.showMessageDialog(this, "The sum " + sum + " was successfully withdrawn from your account!");
				}
				else
					JOptionPane.showMessageDialog(this, "Sum to be withdrawn must be greater than 0!");

			} else
				JOptionPane.showMessageDialog(this, "Please enter valid data!");
			textMoney.setText(" ");
		}

		/**
		 * Sold balance
		 */
		else if (event.getSource() == btnSold) {
			String name = checkInput.checkString(textName);
			String email = checkInput.checkString(textEmail);
			String phone = checkInput.checkString(textPhone);
			int pin = checkInput.checkNumber(textId);

			if (name != null && email != null && phone != null && pin != 0) {
				Person p = new Person(name, email, phone);
				double sold = bank.findAccount(pin, p);
				JOptionPane.showMessageDialog(this, "Your sold is: " + sold);
			}

		}

		/**
		 * Add a new account for a person
		 */
		else if (event.getSource() == btnAddAccount) {
			
			String name = checkInput.checkString(textName);
			String email = checkInput.checkString(textEmail);
			String phone = checkInput.checkString(textPhone);
			int pin = checkInput.checkNumber(textId);
			int sum = checkInput.checkNumber(textMoney);

			if (name != null && email != null && phone != null && pin != 0 && sum != 0) {
				String choice = JOptionPane.showInputDialog(this,
						"Please enter: 1 for spending account or 2 for saving account ");
				Person p = new Person(name, email, phone);
				if (choice.equals("1")) {
					Account acc = new SpendingAccount(pin, sum);
					bank.addAccForPerson(p, acc);
					JOptionPane.showMessageDialog(this, "New spending account added for: " + p.toString());
				} else if (choice.equals("2")) {
					Account acc = new SavingAccount(pin, sum);
					bank.addAccForPerson(p, acc);
					JOptionPane.showMessageDialog(this, "New saving account added for: " + p.toString());
				} else {
					JOptionPane.showMessageDialog(this, "Please enter valid data!");
				}
				refresh();
				Object[] row1 = { name, email, phone, pin, sum };
				model.addRow(row1);
			} else
				JOptionPane.showMessageDialog(this, "Please enter valid data!");

		}

		/**
		 * Delete a person from bank + all corresponding accounts
		 */
		else if (event.getSource() == btnDeletePerson) {
			String name = checkInput.checkString(textName);
			String email = checkInput.checkString(textEmail);
			String phone = checkInput.checkString(textPhone);

			if (name != null && email != null && phone != null) {
				Person p = new Person(name, email, phone);
				bank.deletePerson(p);
			} else {
				JOptionPane.showMessageDialog(this, "Please enter valid data!");
			}
			refresh();
			int i = table.getSelectedRow();
			if (i >= 0) {
				model.removeRow(i);
				refresh();
			} else {
				System.out.println("Delete error");
			}
		}

		/**
		 * Delete an account
		 */
		else if (event.getSource() == btnDeleteAccount) {

			String name = checkInput.checkString(textName);
			String email = checkInput.checkString(textEmail);
			String phone = checkInput.checkString(textPhone);
			int pin = checkInput.checkNumber(textId);

			if (name != null && email != null && phone != null && pin != 0) {
				Person p = new Person(name, email, phone);
				bank.deleteAccount(pin, p);
			}
			refresh();
		}

		/**
		 * Find all account in the bank
		 */
		else if (event.getSource() == findAllAccounts) {
			Bank newBank = new Bank();
			try {
				newBank = ser.DeserializeBank();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Set<Account> allAccounts = newBank.findAllAccounts();
			for (Account acc : allAccounts) {
				System.out.println(acc.toString());
				String id = Integer.toString(acc.getAccId());
				String money = Double.toString(acc.getMoney());
				Object[] row = { "", "", "", id, money };
				model.addRow(row);
			}
			Object[] row1 = { "", "", "", "", "" };
			model.addRow(row1);
		}

		/**
		 * Find all persons which have accounts + their accounts
		 */
		else if (event.getSource() == findAllPersons) {
			Bank newBank = new Bank();
			try {
				newBank = ser.DeserializeBank();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Iterator<Entry<Person, Set<Account>>> it = newBank.getList().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Person, Set<Account>> pair = it.next();
				Person p = pair.getKey();
				String name = p.getName();
				String email = p.getEmail();
				String phone = p.getPhone();

				Set<Account> accForOnePerson = pair.getValue();
				for (Account acc : accForOnePerson) {
					String pin = Integer.toString(acc.getAccId());
					String money = Double.toString(acc.getMoney());
					Object[] row = { name, email, phone, pin, money };
					model.addRow(row);
				}
			}
			Object[] emptyRow = { "", "", "", "", "" };
			model.addRow(emptyRow);

		}

		/**
		 * Save the current state of the bank in a file using serialization
		 * 
		 */
		else if (event.getSource() == saveBank) {
			ser.SerializeBank(bank);
		}
	}

	public void changeRows() {

		Object[] row = new Object[5];
		row[0] = textName.getText();
		row[1] = textEmail.getText();
		row[2] = textPhone.getText();
		row[3] = textId.getText();
		row[4] = textMoney.getText();
		model.addRow(row);
	}

	public void refresh() {
		textName.setText(" ");
		textEmail.setText(" ");
		textPhone.setText(" ");
		textId.setText(" ");
		textMoney.setText(" ");

	}
}
