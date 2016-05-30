package view;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ThreadFactory;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.pdf.RadioCheckField;

import model.Account;
import model.Person;



public class BankView extends JFrame {

	private JScrollPane scrollPersons;
	private JTable tablePersons;
	public DefaultTableModel modelPersons;
	
	private JScrollPane scrollAccounts;
	private JTable tableAccounts;
	private DefaultTableModel modelAccounts;
	
	private JPanel panelPersons;
	private JPanel panelAccounts;
	private JPanel panelPersonsOps;
	private JPanel panelAccountsOps;
	private JPanel panelPerson;
	private JPanel panelAccount;
	
	private JTextField tfSsid;
	private JTextField tfLastName;
	private JTextField tfFirstName;
	private JTextField tfPhoneNo;
	private JTextField tfBalance;
	
	
	private JButton btnAddPerson;
	private JButton btnRemovePerson;
	private JButton btnAddAccount;
	private JButton btnRemoveAccount;
	private JButton btnWithdraw;
	private JButton btnDeposit;
	private JButton btnStatement;
	
	private JRadioButton rbSpending;
	private JRadioButton rbSaving;
	
	private JTextField tfAmount;
	
	GridBagConstraints gbc;
	public BankView(){
		
		super("Bank");
		this.setSize(900,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		
		//create the panel containing the data about persons
		panelPersons = new JPanel();
		createPanelPersons(panelPersons);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(panelPersons, gbc);
		
		panelAccounts = new JPanel();
		createPanelAccounts(panelAccounts);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(panelAccounts, gbc);
		
		
		panelPersonsOps = new JPanel();
		createPanelPersonsOps(panelPersonsOps);
		gbc.insets = new Insets(0,0,10,10);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(panelPersonsOps, gbc);
		
		panelAccountsOps = new JPanel();
		createPanelAccountsOps(panelAccountsOps);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,0,10);
		this.add(panelAccountsOps, gbc);
		
		this.setVisible(true);
	}
	
	public void createPanelPersons(JPanel panelPersons){
		
		Object[] rowCount = new Object[]{"SSID", "Last name", "First name", "PhoneNo"};
		modelPersons = new DefaultTableModel(null, rowCount);
		tablePersons = new JTable(modelPersons);
		scrollPersons = new JScrollPane(tablePersons);
		scrollPersons.setPreferredSize(new Dimension(400, 300));
		panelPersons.add(scrollPersons);
	}
	
	public void createPanelAccounts(JPanel panelAccounts){
		
		Object[] rowCount1 = new Object[]{"Acc.Nr.", "Owner", "Type", "Balance"};
		modelAccounts = new DefaultTableModel(null, rowCount1);
		tableAccounts = new JTable(modelAccounts);
		scrollAccounts = new JScrollPane(tableAccounts);
		scrollAccounts.setPreferredSize(new Dimension(400, 300));
		panelAccounts.add(scrollAccounts);
	}
	
	public void createPanelPersonsOps(JPanel panelPersonsOps){
		
		GridBagLayout layout = new GridBagLayout();
		panelPersonsOps.setLayout(layout);
		GridBagConstraints gbcp = new GridBagConstraints();
		
		btnAddPerson=new JButton("Add person");
		btnRemovePerson = new JButton("Remove person");
		btnAddAccount = new JButton("Add account");
		btnRemoveAccount = new JButton("Remove account");
		
		panelPerson = new JPanel(new GridLayout(4, 4));
		JLabel lblSsid = new JLabel("SSID: ");
		JLabel lblLastName = new JLabel("Last Name: ");
		JLabel lblFirstName = new JLabel("First Name: ");
		JLabel lblPhoneNo = new JLabel("Phone No: ");
		
		tfSsid = new JTextField();
		tfLastName = new JTextField();
		tfFirstName = new JTextField();
		tfPhoneNo = new JTextField();
		
		panelPerson.add(lblSsid);
		panelPerson.add(tfSsid);
		panelPerson.add(lblLastName);
		panelPerson.add(tfLastName);
		panelPerson.add(lblFirstName);
		panelPerson.add(tfFirstName);
		panelPerson.add(lblPhoneNo);
		panelPerson.add(tfPhoneNo);
		
		gbcp.fill = GridBagConstraints.HORIZONTAL;
		
		gbcp.gridx = 0;
		gbcp.gridy = 0;
		panelPersonsOps.add(panelPerson, gbcp);
		
		gbcp.gridx = 0;
		gbcp.gridy = 1;
		panelPersonsOps.add(btnAddPerson, gbcp);
		
		gbcp.gridx = 0;
		gbcp.gridy = 2;
		panelPersonsOps.add(btnRemovePerson, gbcp);
		
		panelAccount = new JPanel(new GridLayout(2, 2));
		rbSaving = new JRadioButton("Saving account", true);
		rbSpending = new JRadioButton("Spending account");
		ButtonGroup radio = new ButtonGroup();
		radio.add(rbSaving);
		radio.add(rbSpending);
		
		panelAccount.add(rbSaving);
		panelAccount.add(rbSpending);
		
		tfBalance = new JTextField();
		JLabel lblBalance = new JLabel("Initial balance:");
		
		panelAccount.add(lblBalance);
		panelAccount.add(tfBalance);
		
		gbcp.gridx = 0;
		gbcp.gridy = 3;
		panelPersonsOps.add(panelAccount, gbcp);
		
		gbcp.gridx = 0;
		gbcp.gridy = 4;
		panelPersonsOps.add(btnAddAccount, gbcp);
		
		gbcp.gridx = 0;
		gbcp.gridy = 5;
		panelPersonsOps.add(btnRemoveAccount, gbcp);
	}

	public void createPanelAccountsOps(JPanel panelAccountsOps){
		
		GridLayout layout = new GridLayout(3,2);
		layout.setVgap(10);
		layout.setHgap(5);
		panelAccountsOps.setLayout(layout);
		
		btnWithdraw=new JButton("Withdraw");
		btnDeposit = new JButton("Deposit");
		btnStatement = new JButton("Generate Bank Statement");
		
		
		JLabel lblAmount = new JLabel("Amount:");
		tfAmount = new JTextField();
		
		panelAccountsOps.add(lblAmount);
		
		panelAccountsOps.add(btnWithdraw);
		
		panelAccountsOps.add(tfAmount);
		
		panelAccountsOps.add(btnDeposit);
		
		panelAccountsOps.add(btnStatement);
	
	}
	public JPanel getPanelAccounts() {
		return panelAccounts;
	}

	public void setPanelAccounts(JPanel panelAccounts) {
		this.panelAccounts = panelAccounts;
	}

	public JPanel getPanelPersonsOps() {
		return panelPersonsOps;
	}

	public void setPanelPersonsOps(JPanel panelPersonsOps) {
		this.panelPersonsOps = panelPersonsOps;
	}

	public JPanel getPanelAccountsOps() {
		return panelAccountsOps;
	}

	public void setPanelAccountsOps(JPanel panelAccountsOps) {
		this.panelAccountsOps = panelAccountsOps;
	}
	
	public void addAddPersonButtonListener(ActionListener l){
		btnAddPerson.addActionListener(l);
	}
	
	public void addRemovePersonButtonListener(ActionListener l){
		btnRemovePerson.addActionListener(l);
	}
	public void addAddAccountButtonListener(ActionListener l){
		btnAddAccount.addActionListener(l);
	}
	
	public void addRemoveAccountButtonListener(ActionListener l){
		btnRemoveAccount.addActionListener(l);
	}

	public void addWithdrawButtonListener(ActionListener l){
		btnWithdraw.addActionListener(l);
	}
	
	public void addDepositButtonListener(ActionListener l){
		btnDeposit.addActionListener(l);
	}
	
	public void addGenerateStatementButtonListener(ActionListener l){
		btnStatement.addActionListener(l);
	}
	
	public void addTablePersonsActionListener(TableModelListener tme) {
		modelPersons.addTableModelListener(tme);
	}
	public void addTableAccountsActionListener(TableModelListener tme) {
		modelAccounts.addTableModelListener(tme);
	}
	
	public DefaultTableModel getModelPersons() {
		return this.modelPersons;
	}
	
	public void setModelPersons(DefaultTableModel modelPersons){
		this.modelPersons = modelPersons;
	}
	public DefaultTableModel getModelAccounts() {
		return this.modelAccounts;
	}
	
	public void setModelAccounts(DefaultTableModel modelAccounts){
		this.modelAccounts = modelAccounts;
	}
	
	public void fillTablePersons(Hashtable<Person, ArrayList<Account>> bankData){
		
		
		this.modelPersons.setRowCount(0);
		
		for (Person person : bankData.keySet()) {
			this.modelPersons.addRow(new Object[]{person.getSsid(), person.getLastName(), person.getFirstName(), person.getPhoneNo()});
		}
		
	}
	
public void fillTableAccounts(Hashtable<Person, ArrayList<Account>> bankData){
		
		
		this.modelAccounts.setRowCount(0);
		
		for (Person person : bankData.keySet()) {
			for (Account account : bankData.get(person))
				this.modelAccounts.addRow(new Object[]{account.getAccountNr(), person.getLastName()+" "+person.getFirstName(), account.getAccountType(), account.getBalance()});
		}
		
	}
	public void setBalance(){
		this.tfBalance.setText("");
	}
	public double getBalance(){
		
		return Double.parseDouble(this.tfBalance.getText());
	}
	public String getSsid(){
		
		return this.tfSsid.getText();
	}
	public String getLastName(){
		return this.tfLastName.getText();
	}
	public String getFirstName(){
		return this.tfFirstName.getText();
	}
	public String getPhoneNo(){
		return this.tfPhoneNo.getText();
	}
	
	public void setSsid(){
		this.tfSsid.setText("");
	}
	public void setLastName(){
		this.tfLastName.setText("");
	}
	public void setFirstName(){
		this.tfFirstName.setText("");
	}
	public void setPhoneNo(){
		this.tfPhoneNo.setText("");
	}
	public JTable getPersonsTable(){
		return this.tablePersons;
	}
	public JTable getAccountsTable(){
		return this.tableAccounts;
	}
	
	public JRadioButton getSavingRadioButton(){
		return this.rbSaving;
	}
	public JRadioButton getSpendingRadioButton(){
		return this.rbSpending;
	}
	
	public double getAmount(){
		return Double.parseDouble(tfAmount.getText());
	}
}
