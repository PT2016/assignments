package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;
import java.util.ArrayList;
import java.util.Map.Entry;

import controller.Serializer;
import models.Bank;
import models.Person;
import models.Account;

public class AdminViewClients {

	private Serializer io;
	private JTable table;
	private DefaultTableModel model;
	private Bank bank;

	/**
	 * constructor
	 */
	public AdminViewClients() {

		JFrame frame = new JFrame();
		io = new Serializer();
		table = new JTable();
		bank = io.deserializeBank();

		// create a table model and set a Column Identifiers to this model
		Object[] columns = { "Name", "Occupation", "Age", "Number of accounts" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		

		Font font = new Font("", 1, 22);
		table.setModel(model);
		table.setBackground(new Color(255, 255, 230));
		table.setForeground(new Color(81, 52, 21));
		table.setGridColor(new Color(81, 52, 21));
	
		table.setFont(font);
		table.setRowHeight(30);

		for (Person person : bank.getBankData().keySet()) {
			Object[] row = { person.getName(), person.getOccupation(), person.getAge(), person.getNumberOfAccounts() };
			model.addRow(row);
		}

		JTextField textName = new JTextField();
		JTextField textOccupation = new JTextField();
		JTextField textAge = new JTextField();
		JTextField textPIN = new JTextField();
		

		// create JButtons

		JButton btnAdd = new JButton("Add Person");
		btnAdd.setBackground(new Color(255, 255, 230));
		JButton btnRemove = new JButton("Remove Person");
		btnRemove.setBackground(new Color(255, 255, 230));
		JButton btnGenerateBankReport = new JButton("Generate report");
		btnGenerateBankReport.setBackground(new Color(255, 255, 230));
		JButton btnViewAccounts = new JButton("All accounts");
		btnViewAccounts.setBackground(new Color(255, 255, 230));
		
		// textId.setBounds(20, 220, 100, 25);
		textName.setBounds(20, 210, 100, 25);
		textName.setBackground(new Color(255, 255, 230));
		textOccupation.setBounds(20, 240, 100, 25);
		textOccupation.setBackground(new Color(255, 255, 230));
		textAge.setBounds(20, 270, 100, 25);
		textAge.setBackground(new Color(255, 255, 230));
		textPIN.setBounds(20, 300, 100, 25);
		textPIN.setBackground(new Color(255, 255, 230));
		
		btnAdd.setBounds(150, 210, 130, 25);
		btnGenerateBankReport.setBounds(150, 240, 130, 25);
		btnRemove.setBounds(150, 270, 130, 25);
		btnViewAccounts.setBounds(150, 300, 130, 25);
		
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
		frame.add(textOccupation);
		frame.add(textAge);
		frame.add(textPIN);
	
		// add JButtons to the jframe

		frame.add(btnAdd);
		frame.add(btnRemove);
		frame.add(btnViewAccounts);
        frame.add(btnGenerateBankReport);
        // create an array of objects to set the row data

		Object[] row = new Object[4];

		// button add row

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row[0] = textName.getText();
				row[1] = textOccupation.getText();
				row[2] = textAge.getText();
				row[3] = 0;
			
				model.addRow(row);
				Person person=new Person(row[0].toString(),row[1].toString(),Integer.parseInt(row[2].toString()),Integer.parseInt(textPIN.getText().toString()));
		        bank.addPerson(person);
		        System.out.println(bank.getReport());
				io.SerializeBank(bank);
		
			}
		});

		btnViewAccounts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminViewAccounts();
				frame.dispose();
			}

		});

		// button remove row
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//row[0] = textName.getText();
				int i = table.getSelectedRow();
				if (i >= 0) {
					java.util.Iterator<Entry<Person, ArrayList<Account>>> it= bank.getBankData().entrySet().iterator();
					Entry<Person, ArrayList<Account>> entry ;
                     while (it.hasNext()){
                    	 entry = it.next();
                         if (entry.getKey().getName().compareTo(textName.getText().toString())==0)
                        	 if (entry.getKey().getOccupation().compareTo(textOccupation.getText().toString())==0)
                        		 if (entry.getKey().getAge()==(Integer.parseInt(textAge.getText().toString())))
                        			 it.remove();
                         		     io.SerializeBank(bank);
                         		     System.out.println(bank.getReport());
                         		
                     }

                     model.removeRow(i);
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
				textOccupation.setText(model.getValueAt(i, 1).toString());
				textAge.setText(model.getValueAt(i, 2).toString());
				textPIN.setText("0");
			//	textPIN.setText(model.getValueAt(i, 3).toString());
				
			}
		});
		// button update row
		btnGenerateBankReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BankReportView(bank.getReport());
	
				
			}
		});
		frame.setSize(900, 410);
		frame.getContentPane().setBackground(new Color(162, 104, 42));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
