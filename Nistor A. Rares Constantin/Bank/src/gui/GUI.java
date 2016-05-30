package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bank.Bank;
import user.Person;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import account.Account;
import account.SavingAccount;
import account.SpendingAccount;

public class GUI implements ActionListener {
	// BANK

	Bank bank = null;

	// LOGIN FRAME
	private JFrame loginFrame;
	private JTextField CNPtextField;
	private JTextField IDtextField;
	private JTextField errorTextField;
	private JButton btnL;

	// HOLDER FRAME
	private JFrame holderFrame;
	private JTextField moneyTextField;
	private JTextField totMoneyTextField;
	private JButton btnWithdraw;
	private JButton btnAdd;
	private JButton btnBack;
	private JLabel type;
	private JTextPane notifications;
	private JTextField errorTextFiledHolder;

	// ADMIN FRAME
	private JFrame adminFrame;
	private JTable bankData;
	private String[] column = { "ID", "NAME", "CNP", "TYPE" };
	private Object[][] data;
	private JScrollPane pane;
	private JTextPane info;
	private JButton infoBtn;
	private JButton btnBack2;
	private JTextField accID;
	private JTextField holdeCNP;
	private JButton btnAddAcc;
	private JLabel lblHolderName;
	private JTextField holderName;
	private JLabel lblAccType;
	private JTextField accType;
	private JTextField error;
	private JButton btnRemoveAcc;
	private JButton btnAddHolder;
	private JButton btnRemoveHolder;
	private JButton btnEnd;

	private void populateBank() {
		try {
			FileInputStream fileIn = new FileInputStream("bank.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			bank = (Bank) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Bank class not found");
			c.printStackTrace();
			return;
		}

	}

	public GUI() {
		populateBank();
		bank.print();
		// LOGIN FRAME
		loginFrame = new JFrame();
		loginFrame.setVisible(true);
		loginFrame.setBounds(100, 100, 533, 448);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);

		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(139, 203, 62, 20);
		loginFrame.getContentPane().add(lblCnp);

		JLabel lblIdAccount = new JLabel("ID Account");
		lblIdAccount.setBounds(139, 228, 81, 30);
		loginFrame.getContentPane().add(lblIdAccount);

		CNPtextField = new JTextField();
		CNPtextField.setBounds(211, 203, 141, 20);
		loginFrame.getContentPane().add(CNPtextField);
		CNPtextField.setColumns(10);

		IDtextField = new JTextField();
		IDtextField.setBounds(211, 233, 141, 20);
		loginFrame.getContentPane().add(IDtextField);
		IDtextField.setColumns(10);

		errorTextField = new JTextField();
		errorTextField.setEditable(false);
		errorTextField.setBounds(139, 318, 213, 20);
		loginFrame.getContentPane().add(errorTextField);
		errorTextField.setColumns(10);

		btnL = new JButton("Login");
		btnL.setBounds(207, 269, 89, 23);
		loginFrame.getContentPane().add(btnL);

		btnL.addActionListener(this);

		// HOLDER FRAME
		holderFrame = new JFrame();
		holderFrame.setBounds(100, 100, 450, 300);
		holderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		holderFrame.getContentPane().setLayout(null);
		holderFrame.setVisible(false);

		JLabel lblMoney = new JLabel("Money:");
		lblMoney.setBounds(22, 36, 46, 14);
		holderFrame.getContentPane().add(lblMoney);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(109, 69, 89, 23);
		holderFrame.getContentPane().add(btnAdd);

		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(10, 69, 89, 23);
		holderFrame.getContentPane().add(btnWithdraw);

		JLabel lblNewLabel = new JLabel("Moeny in bank");
		lblNewLabel.setBounds(22, 181, 77, 14);
		holderFrame.getContentPane().add(lblNewLabel);

		moneyTextField = new JTextField();
		moneyTextField.setBounds(78, 33, 120, 20);
		holderFrame.getContentPane().add(moneyTextField);
		moneyTextField.setColumns(10);

		totMoneyTextField = new JTextField();
		totMoneyTextField.setBounds(112, 178, 86, 20);
		holderFrame.getContentPane().add(totMoneyTextField);
		totMoneyTextField.setColumns(10);
		totMoneyTextField.setEditable(false);

		btnBack = new JButton("Back");
		btnBack.setBounds(37, 227, 89, 23);
		holderFrame.getContentPane().add(btnBack);

		type = new JLabel("asd");
		type.setBounds(22, 11, 127, 14);
		holderFrame.getContentPane().add(type);

		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setBounds(275, 73, 132, 14);
		holderFrame.getContentPane().add(lblNotifications);

		notifications = new JTextPane();
		notifications.setBounds(252, 100, 172, 150);
		holderFrame.getContentPane().add(notifications);
		notifications.setEditable(false);

		errorTextFiledHolder = new JTextField();
		errorTextFiledHolder.setBounds(10, 130, 188, 20);
		holderFrame.getContentPane().add(errorTextFiledHolder);
		errorTextFiledHolder.setColumns(10);
		errorTextFiledHolder.setEditable(false);

		btnBack.addActionListener(this);
		btnAdd.addActionListener(this);
		btnWithdraw.addActionListener(this);

		// ADMIN FRAME

		adminFrame = new JFrame();
		adminFrame.setBounds(100, 100, 533, 448);
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.getContentPane().setLayout(null);
		adminFrame.setVisible(false);

		bankData = new JTable(new DefaultTableModel(createModel(), column));
		bankData.setPreferredScrollableViewportSize(new Dimension(300, 100));
		bankData.setFillsViewportHeight(true);
		pane = new JScrollPane(bankData);
		pane.setBounds(10, 11, 300, 100);
		adminFrame.getContentPane().add(pane);

		info = new JTextPane();
		info.setBounds(10, 203, 300, 118);
		info.setEditable(false);
		adminFrame.getContentPane().add(info);

		JLabel accountID = new JLabel("account ID");
		accountID.setBounds(370, 18, 80, 14);
		adminFrame.getContentPane().add(accountID);

		JLabel accountCNP = new JLabel("holder CNP");
		accountCNP.setBounds(370, 73, 80, 14);
		adminFrame.getContentPane().add(accountCNP);

		infoBtn = new JButton("info");
		infoBtn.setBounds(10, 359, 89, 23);
		adminFrame.getContentPane().add(infoBtn);

		btnBack2 = new JButton("back");
		btnBack2.setBounds(418, 359, 89, 23);
		adminFrame.getContentPane().add(btnBack2);

		accID = new JTextField();
		accID.setBounds(364, 42, 86, 20);
		adminFrame.getContentPane().add(accID);
		accID.setColumns(10);

		holdeCNP = new JTextField();
		holdeCNP.setBounds(364, 98, 86, 20);
		adminFrame.getContentPane().add(holdeCNP);
		holdeCNP.setColumns(10);

		btnAddAcc = new JButton("add acc");
		btnAddAcc.setBounds(20, 122, 115, 23);
		adminFrame.getContentPane().add(btnAddAcc);

		lblHolderName = new JLabel("holder Name");
		lblHolderName.setBounds(364, 129, 65, 14);
		adminFrame.getContentPane().add(lblHolderName);

		holderName = new JTextField();
		holderName.setBounds(364, 154, 86, 20);
		adminFrame.getContentPane().add(holderName);
		holderName.setColumns(10);

		lblAccType = new JLabel("acc type");
		lblAccType.setBounds(374, 185, 46, 14);
		adminFrame.getContentPane().add(lblAccType);

		accType = new JTextField();
		accType.setBounds(364, 206, 86, 20);
		adminFrame.getContentPane().add(accType);
		accType.setColumns(10);

		error = new JTextField();
		error.setBounds(123, 360, 241, 20);
		adminFrame.getContentPane().add(error);
		error.setColumns(10);
		error.setEditable(false);
		
		btnRemoveAcc = new JButton("remove acc");
		btnRemoveAcc.setBounds(20, 169, 115, 23);
		adminFrame.getContentPane().add(btnRemoveAcc);
		
		btnAddHolder = new JButton("add holder");
		btnAddHolder.setBounds(165, 122, 115, 23);
		adminFrame.getContentPane().add(btnAddHolder);
		
		btnRemoveHolder = new JButton("remove holder");
		btnRemoveHolder.setBounds(165, 169, 115, 23);
		adminFrame.getContentPane().add(btnRemoveHolder);
		
		btnEnd = new JButton("end accounts");
		btnEnd.setBounds(340, 254, 133, 23);
		adminFrame.getContentPane().add(btnEnd);

		btnBack2.addActionListener(this);
		infoBtn.addActionListener(this);
		btnAddAcc.addActionListener(this);
		btnRemoveAcc.addActionListener(this);
		btnAddHolder.addActionListener(this);
		btnRemoveHolder.addActionListener(this);
		btnEnd.addActionListener(this);
	}

	public Object[][] createModel() {

		Iterator<LinkedList<Account>> iterator = bank.getAccounts().iterator();
		LinkedList<Account> list;
		data = new Object[bank.getNrAccounts()][4];
		int i = 0, j;
		while (iterator.hasNext()) {

			list = iterator.next();
			for (j = 0; j < list.size(); j++) {
				data[i][0] = list.get(j).getID();

				data[i][1] = list.get(j).getMainHolder().getName();
				data[i][2] = list.get(j).getMainHolder().getCNP();
				data[i][3] = list.get(j).type();
				i++;
			}
		}

		return data;
	}

	public void actionPerformed(ActionEvent e) {
		// LOGIN
		if (e.getSource() == btnL) {
			int ID;
			if (CNPtextField.getText().equals("admin")) {
				adminFrame.setVisible(true);
				loginFrame.setVisible(false);
			} else {
				try {
					ID = Integer.parseInt(IDtextField.getText());
				} catch (NumberFormatException q) {
					errorTextField.setText("invalid CNP or ID");
					ID = 0;
				}
				if (bank.search(CNPtextField.getText(), ID) == true) {
					holderFrame.setVisible(true);
					loginFrame.setVisible(false);
					totMoneyTextField.setText("" + new DecimalFormat("##.##").format(bank.getAc().getTotalSum()));
					moneyTextField.setText("");
					type.setText(bank.getAc().type());

				} else {
					errorTextField.setText("invalid CNP or ID");
				}
			}
		}

		// HOLDER
		else if (e.getSource() == btnBack) {
			holderFrame.setVisible(false);
			loginFrame.setVisible(true);
		} else if (e.getSource() == btnAdd) {
			int nr;
			try {
				nr = Integer.parseInt(moneyTextField.getText());
			} catch (NumberFormatException q) {
				errorTextFiledHolder.setText("Invalid data");
				nr = 0;
			}
			if (nr > 0)
				bank.getAc().addMoney(nr);
			else
				errorTextFiledHolder.setText("Invalid data");
			totMoneyTextField.setText("" + new DecimalFormat("##.##").format(bank.getAc().getTotalSum()));
		}

		else if (e.getSource() == btnWithdraw) {
			int nr;
			try {
				nr = Integer.parseInt(moneyTextField.getText());
			} catch (NumberFormatException q) {
				errorTextFiledHolder.setText("Invalid data");
				nr = 0;
			}
			if (nr < bank.getAc().getTotalSum() && nr > 0)
				bank.getAc().withdrawMoney(nr);
			else
				errorTextFiledHolder.setText("Invalid data");
			totMoneyTextField.setText("" + new DecimalFormat("##.##").format(bank.getAc().getTotalSum()));
		}
		// ADMIN
		else if (e.getSource() == btnBack2) {
			adminFrame.setVisible(false);
			loginFrame.setVisible(true);
		} else if (e.getSource() == infoBtn) {
			int nr;
			try {
				nr = Integer.parseInt(accID.getText());
			} catch (NumberFormatException q) {
				error.setText("Invalid data");
				nr = 999;
			}
			if (bank.getInfo(nr).equals(""))
				error.setText("Invalid data");
			info.setText(bank.getInfo(nr));
			error.setText("");
		} else if (e.getSource() == btnAddAcc) {
			Person p = new Person(holderName.getText(), holdeCNP.getText());
			Account a = null;
			if (accType.getText().equals("saving"))
				a = new SavingAccount(0, p, 1, 100);
			else if (accType.getText().equals("spending"))
				a = new SpendingAccount(0, p, 1, 100);
			if (a == null || holderName.getText() == null || holdeCNP.getText() == null)
				error.setText("Invalid data");
			else {
				bank.addAccount(a);
				error.setText("");
			}
			bankData.setModel(new DefaultTableModel(createModel(), column));
		} else if(e.getSource()==btnRemoveAcc){
			int nr;
			try {
				nr = Integer.parseInt(accID.getText());
			} catch (NumberFormatException q) {
				error.setText("Invalid data");
				nr = 999;
			}
			Person p = new Person("x", holdeCNP.getText());
			Account a = new SavingAccount(nr, p, 1, 1);
			if(holdeCNP.getText()==null || nr==999)
				error.setText("Invalid data");
			else{
				if(!bank.removeAccount(a))
					error.setText("Invalid data");
				else{
					error.setText("");
					bankData.setModel(new DefaultTableModel(createModel(), column));
				}
			}
			
		}else if(e.getSource()==btnAddHolder){
			Person p = new Person(holderName.getText(), holdeCNP.getText());
			Person p1;
			int nr;
			try {
				nr = Integer.parseInt(accID.getText());
			} catch (NumberFormatException q) {
				error.setText("Invalid data");
				nr = 999;
			}
			p1=bank.getMainHolder(nr);
			Account a = new SavingAccount(nr, p1, 1, 2);
			if(nr>bank.getNrAccounts() || holderName.getText()==null || holdeCNP.getText()==null){
				error.setText("Invalid data");
			}else{
				error.setText("");
				bank.addPerson(p, a);
			}
		}else if(e.getSource()==btnRemoveHolder){
			Person p = new Person(holderName.getText(), holdeCNP.getText());
			Person p1;
			int nr;
			try {
				nr = Integer.parseInt(accID.getText());
			} catch (NumberFormatException q) {
				error.setText("Invalid data");
				nr = 999;
			}
			p1=bank.getMainHolder(nr);
			Account a = new SavingAccount(nr, p1, 1, 2);
			if(nr>bank.getNrAccounts() || holderName.getText()==null || holdeCNP.getText()==null ){
				error.setText("Invalid data");
			}else{
				error.setText("");
				bank.removePerson(p, a);
				bankData.setModel(new DefaultTableModel(createModel(), column));
			}
		}else if(e.getSource()==btnEnd){
			Person p = new Person(holderName.getText(), holdeCNP.getText());
			if(holdeCNP.getText()==null){
				error.setText("Invalid data");
			}else{
				bank.removeHolder(p);
				bankData.setModel(new DefaultTableModel(createModel(), column));
				error.setText("");
			}
		}
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
