
package ui;


import javax.swing.*;

import java.util.*;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import main.Bank;
import main.SavingAccount;
import main.SpendingAccount;
import model.Account;
import model.Person;
/**
 * @author Lorand
 *
 */
public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JFrame Frm;

	private JMenuBar meniu;
	
	
	private JMenu file;
	private JMenu Accounts; 
	private JMenu Operations;
	private JMenu search;
	
	private JMenuItem eMenuItem;
	private JMenuItem showAccounts;
	private JMenuItem insertAccount;
	private JMenuItem deleteAccount;
	private JMenuItem DepositMoney;
	private JMenuItem extractMoney;
	private JMenuItem searchId;
	private JMenuItem searchCNP;


	
	private JButton insertButton;
	private JButton deleteButton;
	private JButton enter;
	private JButton extractButton;
	private JButton depositButton;
	private JButton searchButton;
	
	private JTextField formNumeText;
	private JTextField formIdText;
	private JTextField formPrenumeText;
	private JTextField formCNPText;
	private JTextField formMoneyAmountText;

	private JLabel formNume;
	private JLabel formId;
	private JLabel formPrenume;
	private JLabel formCNP;
	private JLabel formMoneyAmount;
	
	private JScrollPane scrollPane;
	private JTable tableAccounts;
	private JTable tableAcc;
	private  JTable tableAc;
	protected Bank bank;
	private int iddAccounts;
	
	private JRadioButton savingRadio;
	private JRadioButton spendingRadio;


	
	public GUI(){
	
	Frm= new JFrame("Homework 4");
	Frm.setBounds(200, 30, 900, 660);
	Frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Frm.getContentPane().setLayout(null);
	Frm.setContentPane(new JLabel(new ImageIcon("C:/Users/Lorand/workspace/BankAccount/bank.jpg")));
	
	meniu = new JMenuBar();
	Frm.setJMenuBar(meniu);
	
	bank = new Bank();
	bank.readAccounts();
	iddAccounts = bank.nextIdAccount();
	iddAccounts++;
	
	//tabel
	tableAccounts= new JTable();
	tableAcc= new JTable();
	tableAc= new JTable();
	scrollPane= new JScrollPane();
	
	//lista date client
	formId= new JLabel("ID");
	formId.setVisible(false);
	formId.setBounds(110, 110, 100, 20);
	Frm.getContentPane().add(formId);
	
	formNume= new JLabel("First Name");
	formNume.setVisible(false);
	formNume.setBounds(40, 110, 100, 20);
	Frm.getContentPane().add(formNume);
	
	formPrenume= new JLabel("Last Name");
	formPrenume.setVisible(false);
	formPrenume.setBounds(40, 160, 100, 20);
	Frm.getContentPane().add(formPrenume);
	
	formCNP= new JLabel("CNP");
	formCNP.setVisible(false);
	formCNP.setBounds(40, 210, 100, 20);
	Frm.getContentPane().add(formCNP);
	
	formIdText= new JTextField();
	formIdText.setVisible(false);
	formIdText.setBounds(200, 110, 50, 30);
	Frm.getContentPane().add(formIdText);
	
	formNumeText= new JTextField();
	formNumeText.setVisible(false);
	formNumeText.setBounds(250, 110, 200, 30);
	Frm.getContentPane().add(formNumeText);
	
	formPrenumeText= new JTextField();
	formPrenumeText.setVisible(false);
	formPrenumeText.setBounds(250, 160, 200, 30);
	Frm.getContentPane().add(formPrenumeText);

	formCNPText= new JTextField();
	formCNPText.setVisible(false);
	formCNPText.setBounds(250, 210, 200, 30);
	Frm.getContentPane().add(formCNPText);
	
	formMoneyAmount= new JLabel("Money Amount");
	formMoneyAmount.setVisible(false);
	formMoneyAmount.setBounds(40, 160, 100, 20);
	Frm.getContentPane().add(formMoneyAmount);

	formMoneyAmountText= new JTextField();
	formMoneyAmountText.setVisible(false);
	formMoneyAmountText.setBounds(250, 160, 200, 30);
	Frm.getContentPane().add(formMoneyAmountText);

	
	insertButton= new JButton("Insert");
	insertButton.setBounds(600,200, 90, 30);
	insertButton.setVisible(false);
	Frm.getContentPane().add(insertButton);
	
	savingRadio = new JRadioButton("Saving Account");
	savingRadio.setBounds(33, 300, 156, 20);
	savingRadio.setVisible(false);
	Frm.getContentPane().add(savingRadio);
	
	spendingRadio = new JRadioButton("Spending Account");
	spendingRadio.setBounds(261, 300, 174, 20);
	spendingRadio.setVisible(false);
	Frm.getContentPane().add(spendingRadio);
	
	enter= new JButton("Delete");
	enter.setBounds(600,300, 90, 20);
	enter.setVisible(false);
	Frm.getContentPane().add(enter);
	
	extractButton= new JButton("Extract Money");
	extractButton.setBounds(600,300, 90, 20);
	extractButton.setVisible(false);
	Frm.getContentPane().add(extractButton);

	depositButton= new JButton("Deposit Money");
	depositButton.setBounds(600,300, 90, 20);
	depositButton.setVisible(false);
	Frm.getContentPane().add(depositButton);

	searchButton= new JButton("Search");
	searchButton.setBounds(300,300, 90, 20);
	searchButton.setVisible(false);
	Frm.getContentPane().add(searchButton);
	
	//Meniu Accounts
	
	Accounts= new JMenu ("Accounts");
	Accounts.setMnemonic(KeyEvent.VK_F);
	meniu.add(Accounts);

	//Show Accounts
	showAccounts= new JMenuItem("Show Accounts");
	showAccounts.setMnemonic(KeyEvent.VK_E);
	showAccounts.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event) {
			
			
			if(event.getSource()==showAccounts){
				clearScreen();
				
				tableAccounts = showAccounts();
				
				scrollPane = new JScrollPane(tableAccounts);
				scrollPane.setBounds(20, 40, 800, 200);
				scrollPane.setVisible(true);
				Frm.getContentPane().add(scrollPane);
				
				
				
				
		}
	}});
	Accounts.add(showAccounts);
	
	//Insert Accounts
	insertAccount= new JMenuItem("Insert Account");
	insertAccount.setMnemonic(KeyEvent.VK_E);
	insertAccount.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==insertAccount){
				clearScreen();
				formNume.setVisible(true);
				formPrenume.setVisible(true);
				formCNP.setVisible(true);
				formNumeText.setVisible(true);
				formPrenumeText.setVisible(true);
				formCNPText.setVisible(true);
				insertButton.setVisible(true);
				savingRadio.setVisible(true);
				spendingRadio.setVisible(true);
				
				insertButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
				if (event.getSource() == insertButton)
				{
					
					String name = formNumeText.getText();
					String pre = formPrenumeText.getText();
					String cnp = formCNPText.getText();
					
					
				
					if (savingRadio.isSelected())
					{
						SavingAccount sa = new SavingAccount(new Person(name,pre,cnp),iddAccounts++,0);
						bank.addAccount(sa);
						
					}
					if(spendingRadio.isSelected())
					{
						SpendingAccount sa = new SpendingAccount(new Person(name,pre,cnp),iddAccounts++,0);
						bank.addAccount(sa);
					}
					bank.writeAccounts();
				}
				
					}});
						
				
				
				
			}

		}
	});
	Accounts.add(insertAccount);
	
	//Delete Accounts
	deleteAccount= new JMenuItem("Delete Account");
	deleteAccount.setMnemonic(KeyEvent.VK_E);
	deleteAccount.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==deleteAccount){
				clearScreen();
				enter.setVisible(true);
				formId.setVisible(true);
				formIdText.setVisible(true);
				
				enter.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
				if (event.getSource() == enter)
				{
					int id = Integer.parseInt(formIdText.getText());
					Account a = bank.getAccount(id);
					if (a == null)
						JOptionPane.showMessageDialog(null, "Account does not exist!");
					else
					{
					assert a !=null : "not exists";
						bank.deleteAccount(a);
						bank.writeAccounts();
					}
					
				}
					}});
				
				
			}
		}
	});
	Accounts.add(deleteAccount);
	
	//meniu operations
			Operations= new JMenu ("Operations");
			Operations.setMnemonic(KeyEvent.VK_F);
			meniu.add(Operations);
			
			
			DepositMoney= new JMenuItem("Deposit Money");
			DepositMoney.setMnemonic(KeyEvent.VK_E);
			DepositMoney.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){

					if(event.getSource()==DepositMoney){
						clearScreen();
						depositButton.setVisible(true);
						formId.setVisible(true);
						formIdText.setVisible(true);
						formMoneyAmountText.setVisible(true);
						formMoneyAmount.setVisible(true);
						
						depositButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event){
						if (event.getSource() == depositButton)
						{
							
							int id = Integer.parseInt(formIdText.getText());
							int money = Integer.parseInt(formMoneyAmountText.getText());
							
							double rez = bank.depositMoneyIntoAccount(id, money);
							
							
							if (rez == -1.0)
								JOptionPane.showMessageDialog(null, "Account does not exist!");
							
							bank.writeAccounts();

						}
						clearScreen();
						tableAccounts = showAccounts();
						
						scrollPane = new JScrollPane(tableAccounts);
						scrollPane.setBounds(20, 40, 800, 200);
						scrollPane.setVisible(true);
						Frm.getContentPane().add(scrollPane);
						
							}});
						
	}
						
				}
			});
			Operations.add(DepositMoney);

			extractMoney= new JMenuItem("Extract Money");
			extractMoney.setMnemonic(KeyEvent.VK_E);
			extractMoney.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					if(event.getSource()==extractMoney){
						
						clearScreen();
						extractButton.setVisible(true);
						formId.setVisible(true);
						formIdText.setVisible(true);
						formMoneyAmountText.setVisible(true);
						formMoneyAmount.setVisible(true);
						
						extractButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event){
						if (event.getSource() == extractButton)
						{
							
							int id = Integer.parseInt(formIdText.getText());
							int money = Integer.parseInt(formMoneyAmountText.getText());
							
							double rez = bank.extractMoneyIntoAccount(id, money);
							
							
							if (rez == -1.0)
								JOptionPane.showMessageDialog(null, "Account does not exist!");
							
							bank.writeAccounts();

						}
						clearScreen();
						tableAccounts = showAccounts();
						
						scrollPane = new JScrollPane(tableAccounts);
						scrollPane.setBounds(20, 40, 800, 200);
						scrollPane.setVisible(true);
						Frm.getContentPane().add(scrollPane);
						
							}});
						
					}

				}
			});
			Operations.add(extractMoney);

			//meniu search
			search= new JMenu ("Search");
			search.setMnemonic(KeyEvent.VK_F);
			meniu.add(search);

	searchId= new JMenuItem("ID");
			searchId.setMnemonic(KeyEvent.VK_E);
			searchId.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					
					if(event.getSource()==searchId){
					clearScreen();
					formId.setVisible(true);
					formIdText.setVisible(true);
					searchButton.setVisible(true);
					
					searchButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent event){
					if (event.getSource() == searchButton)
					{
						tableAc = searchAccounts(formIdText.getText(), "ID");
					
					
						
					scrollPane = new JScrollPane(tableAc);
					scrollPane.setBounds(20, 40, 800, 200);
					scrollPane.setVisible(true);
					Frm.getContentPane().add(scrollPane);
					}
						
						}});
	}
					
					
					
				}
			});
			search.add(searchId);

			searchCNP = new JMenuItem("CNP ");
			searchCNP.setMnemonic(KeyEvent.VK_E);
			searchCNP.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					if(event.getSource()==searchCNP){
						clearScreen();
						formCNP.setVisible(true);
						formCNPText.setVisible(true);
						searchButton.setVisible(true);
						
						searchButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event){
						if (event.getSource() == searchButton)
						{
							tableAcc = searchAccounts(formCNPText.getText(), "CNP");
						
							
							
							
						scrollPane = new JScrollPane(tableAcc);
						scrollPane.setBounds(20, 40, 800, 200);
						scrollPane.setVisible(true);
						Frm.getContentPane().add(scrollPane);}	
						
							
							}});
						
						}
						
						
						
					}
				});
			search.add(searchCNP);




	
	
	//exit
	file= new JMenu("Exit");
	file.setMnemonic(KeyEvent.VK_F);
	meniu.add(file);
	eMenuItem = new JMenuItem("Exit Program");
	eMenuItem.setMnemonic(KeyEvent.VK_E);
	eMenuItem.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource()== eMenuItem){
				System.exit(0);}
		}});

	file.add(eMenuItem);
	

	Frm.setVisible(true);

	
	}
	
	private void clearScreen(){
		
		formNume.setVisible(false);
		formPrenume.setVisible(false);
		formCNP.setVisible(false);
		formNumeText.setVisible(false);
		formPrenumeText.setVisible(false);
		formCNPText.setVisible(false);
		insertButton.setVisible(false);
		savingRadio.setVisible(false);
		spendingRadio.setVisible(false);
		enter.setVisible(false);
		tableAccounts.setVisible(false);
		scrollPane.setVisible(false);
		formId.setVisible(false);
		formIdText.setVisible(false);
		depositButton.setVisible(false);
		extractButton.setVisible(false);
		formMoneyAmountText.setVisible(false);
		formMoneyAmount.setVisible(false);
		searchButton.setVisible(false);
		searchButton.setVisible(false);
		tableAccounts.setVisible(false);
		tableAcc.setVisible(false);
		tableAc.setVisible(false);
		

		

	}

	
	protected DefaultTableModel buildTableModel()
	{
	 
	    Vector<String> columnNames = new Vector<String>();
	    columnNames.add("First Name");
	    columnNames.add("Last Name");
	    columnNames.add("CNP");
	    columnNames.add("Account Id");
	    columnNames.add("Money Amount");
	    columnNames.add("Account Type");
	    
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    
	    Set<Map.Entry<String, ArrayList<Account>>> entrySet= bank.bank.entrySet();
		Iterator<Map.Entry<String,ArrayList<Account>>> i= entrySet.iterator();
		
		while(i.hasNext()){
			Map.Entry<String, ArrayList<Account>> w= i.next();
	    
			ArrayList<Account> list = (ArrayList<Account>) w.getValue();
			for (int j = 0;j<list.size();j++)
			{
				Person p = list.get(j).getPerson();
				Vector<Object> vector = new Vector<Object>();
				vector.add(p.getLastName());
				vector.add(p.getFristName());
				
				vector.add(p.getCNP());
				
				vector.add(list.get(j).getAccountId());
				
				vector.add(list.get(j).getMoneyAmount());
				vector.add(list.get(j).getAcountName());
				data.add(vector);
			}
		}
	    return new DefaultTableModel(data, columnNames);
	}
	
	protected JTable searchAccount(String id, String mesaj){
		JTable table;
		Vector<String> columnNames = new Vector<String>();
	    columnNames.add("First Name");
	    columnNames.add("Last Name");
	    columnNames.add("CNP");
	    columnNames.add("Account Id");
	    
	    columnNames.add("Money Amount");
	    columnNames.add("Account Type");
	    
	    Vector<Vector<Object>> data= bank.searchAccountA(id, mesaj);
	    table = new JTable(new DefaultTableModel(data, columnNames));
	    return table;
	
	}
	
private JTable showAccounts(){
		
	
		
	JTable table = new JTable(buildTableModel());
	
		
		return table;
		
	}

private JTable searchAccounts(String id, String mesaj){
	JTable table = searchAccount(id, mesaj);
	if (table == null)
		return null;
	table.setPreferredScrollableViewportSize(table.getPreferredSize()); 
	return table;
}
	


}
