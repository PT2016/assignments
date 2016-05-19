package pt.ObserverBank.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import pt.ObserverBank.basicObjects.Bank;
import pt.ObserverBank.basicObjects.Person;

public class ClientView extends JFrame{
  private Bank bank;
  JButton withdraw,deposit,listAccounts,exit;
  JTextField name,personId,accountId,sum;
  public ClientView(Bank bank){
	  this.bank=bank;
	  this.setTitle("Programming techniques-Assignment4-Drimbarean Maria");
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);

		this.setLayout(new GridLayout(3, 3));
		
		withdraw = new JButton("Withdraw!");
		withdraw.setBackground(Color.PINK);
		this.add(withdraw);

		personId = new JTextField("Enter id!");
		personId.setBackground(Color.YELLOW);
		this.add(personId);

		name = new JTextField("Enter name!");
		name.setBackground(Color.YELLOW);
		this.add(name);
		
		deposit = new JButton("Deposit!");
		deposit.setBackground(Color.PINK);
		this.add(deposit);

		accountId = new JTextField("Enter account id!");
		accountId.setBackground(Color.YELLOW);
		this.add(accountId);

		sum = new JTextField("Enter sum!");
		sum.setBackground(Color.YELLOW);
		this.add(sum);
		
		listAccounts = new JButton("List Accounts!");
		listAccounts.setBackground(Color.PINK);
		this.add(listAccounts);
		
		JButton b1 = new JButton();
		b1.setBackground(Color.pink);
		b1.setEnabled(false);
		this.add(b1);
		
		exit = new JButton("Save and exit!");
		exit.setBackground(Color.PINK);
		this.add(exit);
		
		Handler handler = new Handler();
		 listAccounts.addActionListener(handler);
		 withdraw.addActionListener(handler);
		 deposit.addActionListener(handler);
		 exit.addActionListener(handler);
		 
		this.setVisible(true);
  }
  private class Handler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==listAccounts){
			String n=name.getText();
			int id=Integer.parseInt(personId.getText());
			System.out.println(bank.listClient(new Person(id,n)));
		}else if (event.getSource()==withdraw){
			String n=name.getText();
			int id=Integer.parseInt(personId.getText());
			int aId=Integer.parseInt(accountId.getText());
			double s=Double.parseDouble(sum.getText());
			bank.withdraw(new Person(id,n), aId, s);
		}else if (event.getSource()==deposit){
			String n=name.getText();
			int id=Integer.parseInt(personId.getText());
			int aId=Integer.parseInt(accountId.getText());
			double s=Double.parseDouble(sum.getText());
			bank.deposit(new Person(id,n), aId, s);
		}else if(event.getSource()==exit){
			new SerializeBank().writeBank(bank);
			System.exit(0);
		}
		
	}
	  
  }
}
