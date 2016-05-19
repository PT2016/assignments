package pt.ObserverBank.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import pt.ObserverBank.basicObjects.AccountType;
import pt.ObserverBank.basicObjects.Bank;
import pt.ObserverBank.basicObjects.Person;

public class StartWindow extends JFrame{
	Bank bank=new Bank();
	JButton admin,client;
	public StartWindow(){
		this.setTitle("Programming techniques-Assignment4-Drimbarean Maria");
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new FlowLayout());
		
		admin = new JButton("Administrator!");
		admin.setBackground(Color.PINK);
		this.add(admin);
		client = new JButton("Client!");
		client.setBackground(Color.PINK);
		this.add(client);
		
		Handler handler=new Handler();
		SerializeBank s=new SerializeBank();
		s.readBank();
		bank=s.getBank();
		
		/*
		  Person p1=new Person(10, "Maria");
		  Person p2=new Person(12,"Igor");
		  bank.addPerson(10, "Maria");
		  bank.addPerson(12, "Igor");
		  bank.addAccount(p1, 100.24, AccountType.SAVINGS);
		  bank.addAccount(p1, 11.9, AccountType.SPENDINGS);
		  bank.addAccount(p2, 233.0, AccountType.SPENDINGS);
		  bank.addAccount(p2, 20.0, AccountType.SAVINGS);
		  bank.withdraw(p1, 0, 9);*/
		
		admin.addActionListener(handler);
		client.addActionListener(handler);
		
		this.setVisible(true);
	}
	public static void main(String args[]){
		new StartWindow();
	}
  private class Handler implements ActionListener{
	  @Override
	  public void actionPerformed(ActionEvent event){
		  if (event.getSource()==admin)
		  {
			  new AdminView(bank);
		  }else if (event.getSource()==client)
		  {
			  new ClientView(bank);
		  }
	  }
  }
}
