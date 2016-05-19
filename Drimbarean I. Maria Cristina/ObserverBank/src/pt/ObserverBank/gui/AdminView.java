package pt.ObserverBank.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import pt.ObserverBank.basicObjects.AccountType;
import pt.ObserverBank.basicObjects.Bank;
import pt.ObserverBank.basicObjects.Person;


@SuppressWarnings("serial")
public class AdminView extends JFrame {
	JButton addPerson, removePerson, addAccount, removeAccount, applyInterest, listClient, listAll,exit;
	JTextField personId, name, accountId, sum, interest;
	@SuppressWarnings("rawtypes")
	JComboBox type;
	Bank bank;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AdminView(Bank bank) {
		this.bank=bank;
		this.setTitle("Programming techniques-Assignment4-Drimbarean Maria");
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);

		this.setLayout(new GridLayout(10, 3));

		addPerson = new JButton("Add Client!");
		addPerson.setBackground(Color.PINK);
		this.add(addPerson);

		personId = new JTextField("Enter id!");
		personId.setBackground(Color.YELLOW);
		this.add(personId);

		name = new JTextField("Enter name!");
		name.setBackground(Color.YELLOW);
		this.add(name);

		removePerson = new JButton("Remove Client!");
		removePerson.setBackground(Color.PINK);
		this.add(removePerson);

		String types[] = { "Savings Acccount", "Spendings Account" };
		type = new JComboBox(types);
		type.setBackground(Color.YELLOW);
		this.add(type);

		JButton b1 = new JButton();
		b1.setBackground(Color.pink);
		b1.setEnabled(false);
		this.add(b1);

		addAccount = new JButton("Add Account!");
		addAccount.setBackground(Color.PINK);
		this.add(addAccount);

		sum = new JTextField("Enter sum!");
		sum.setBackground(Color.YELLOW);
		this.add(sum);

		accountId = new JTextField("Enter account id!");
		accountId.setBackground(Color.YELLOW);
		this.add(accountId);

		removeAccount = new JButton("Remove Account!");
		removeAccount.setBackground(Color.PINK);
		this.add(removeAccount);

		JButton b2 = new JButton();
		b2.setBackground(Color.pink);
		b2.setEnabled(false);
		this.add(b2);

		JButton b3 = new JButton();
		b3.setBackground(Color.pink);
		b3.setEnabled(false);
		this.add(b3);

		applyInterest = new JButton("Apply interest!");
		applyInterest.setBackground(Color.PINK);
		this.add(applyInterest);

		interest = new JTextField("Enter interest procentage!");
		interest.setBackground(Color.YELLOW);
		this.add(interest);

		JButton b4 = new JButton();
		b4.setBackground(Color.pink);
		b4.setEnabled(false);
		this.add(b4);

		listClient = new JButton("List Client!");
		listClient.setBackground(Color.PINK);
		this.add(listClient);

		JButton b5 = new JButton();
		b5.setBackground(Color.pink);
		b5.setEnabled(false);
		this.add(b5);

		JButton b6 = new JButton();
		b6.setBackground(Color.pink);
		b6.setEnabled(false);
		this.add(b6);

		listAll = new JButton("List All!");
		listAll.setBackground(Color.PINK);
		this.add(listAll);

		JButton b7 = new JButton();
		b7.setBackground(Color.pink);
		b7.setEnabled(false);
		this.add(b7);

		exit = new JButton("Save and exit!");
		exit.setBackground(Color.pink);
		this.add(exit);

		Handler handler = new Handler();
		
		listAll.addActionListener(handler);
		listClient.addActionListener(handler);
		addPerson.addActionListener(handler);
		removePerson.addActionListener(handler);
		addAccount.addActionListener(handler);
		removeAccount.addActionListener(handler);
		applyInterest.addActionListener(handler);
		exit.addActionListener(handler);
		
		this.setVisible(true);
	}

	private class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
				if(event.getSource()==listAll)
				  System.out.println(bank.toString());
				else if (event.getSource()==listClient)
				{
					String n=name.getText();
					int id=Integer.parseInt(personId.getText());
					System.out.println(bank.listClient(new Person(id,n)));
				}
				else if (event.getSource()==addPerson){
					String n=name.getText();
					int id=Integer.parseInt(personId.getText());
					bank.addPerson(id, n);
				}else if (event.getSource()==removePerson){
					String n=name.getText();
					int id=Integer.parseInt(personId.getText());
					bank.removePerson(id, n);
				}else if (event.getSource()==addAccount){
					String n=name.getText();
					int id=Integer.parseInt(personId.getText());
					double s=Double.parseDouble(sum.getText());
			        String t = (String)type.getSelectedItem();
					AccountType p=AccountType.SAVINGS;
					if (t.equals("Savings Account"))
						p=AccountType.SAVINGS;
					else if (t.equals("Spendings Account"))
						p=AccountType.SPENDINGS;
					bank.addAccount(new Person(id,n),s,p);
				}else if (event.getSource()==removeAccount){
					String n=name.getText();
					int id=Integer.parseInt(personId.getText());
					int aId=Integer.parseInt(accountId.getText());
			        String t = (String)type.getSelectedItem();
					AccountType p=AccountType.SAVINGS;
					if (t.equals("Savings Account"))
						p=AccountType.SAVINGS;
					else if (t.equals("Spendings Account"))
						p=AccountType.SPENDINGS;
					bank.removeAccount(new Person(id,n),aId, p);
				}else if (event.getSource()==applyInterest){
					String n=name.getText();
					int id=Integer.parseInt(personId.getText());
					int aId=Integer.parseInt(accountId.getText());
					double i=Double.parseDouble(interest.getText());
					bank.applyInterest(new Person(id,n), aId, i);
				}else if (event.getSource()==exit){
					new SerializeBank().writeBank(bank);
					System.exit(0);
				}
		}
	}

}
