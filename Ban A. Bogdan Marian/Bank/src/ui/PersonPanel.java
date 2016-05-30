package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bank.Bank;
import model.AccountType;
import model.Person;
import model.PersonsTableModel;

/**
 * Class that is used for creating the panel visible by the person.
 * 
 * @author Bogdan
 * 
 */
@SuppressWarnings("serial")
public class PersonPanel extends JPanel {
	private JButton extract = new JButton("Extract money");
	private JButton write = new JButton("Add money");
	private JButton exit = new JButton("Log out");
	private JLabel greetLabel = new JLabel("Welcome !");
	private Bank bank;

	public PersonPanel(LoginController loginController, JFrame frame,Person person) {
		this.bank = loginController.getBank();

		this.setLayout(new BorderLayout());

		add(this.greetLabel, BorderLayout.PAGE_START);
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(3, 1));
		bPanel.setBackground(Color.GRAY);

		bPanel.add(this.extract);
		bPanel.add(this.write);
		bPanel.add(this.exit);
		

		add(bPanel, BorderLayout.EAST);
		PersonsTableModel personsTableModel = new PersonsTableModel(bank,person);
		JTable table = new JTable(personsTableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		extract.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel amountLabel = new JLabel("Amount of money: ");
				JTextField amount = new JTextField();
				Object[] ob = { amountLabel, amount };
				int result = JOptionPane.showConfirmDialog(null, ob, "Extracting", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if((AccountType)table.getValueAt(table.getSelectedRow(), 3)==AccountType.SPENDING){
					long num = (Long)(table.getValueAt(table.getSelectedRow(), 4)) - Long.parseLong(amount.getText());
					if(num < 0){
						JOptionPane.showMessageDialog(frame, "Not enough money");
					} else{
					personsTableModel.setValueAt(num, table.getSelectedRow(), 4);
					JOptionPane.showMessageDialog(frame, "You extracted " +  Long.parseLong(amount.getText()) + " lei.");
					}
				} else{
					JOptionPane.showMessageDialog(frame, "Cannot extract money from saving account.");
				}
				}
				((PersonsTableModel) table.getModel()).updateModel(person);
				table.updateUI();
			}
		});
		
		write.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel amountLabel = new JLabel("Amount of money: ");
				JTextField amount = new JTextField();
				Object[] ob = { amountLabel, amount };
				int result = JOptionPane.showConfirmDialog(null, ob, "Adding", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					long num = (Long)(table.getValueAt(table.getSelectedRow(), 4)) + Long.parseLong(amount.getText());
					personsTableModel.setValueAt(num, table.getSelectedRow(), 4);
					JOptionPane.showMessageDialog(frame, "Added " + Long.parseLong(amount.getText()) + " lei to the account " + table.getValueAt(table.getSelectedRow(), 2));
				}
				((PersonsTableModel) table.getModel()).updateModel(person);
				table.updateUI();
			}
		});
	}

	public void setBound(Component comp, Rectangle bounds) {
		comp.setBounds(bounds);
	}

}
