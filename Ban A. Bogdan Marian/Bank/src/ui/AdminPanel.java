package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import model.PersonsTableModel;

/**
 * Class that is used for creating the panel visible by the admin user.
 * 
 * @author Bogdan
 * 
 */
@SuppressWarnings("serial")
public class AdminPanel extends JPanel {
	private JButton addPerson = new JButton("Add Person");
	private JButton deletePerson = new JButton("Delete Person");
	private JButton addAccount = new JButton("Add Account");
	private JButton deleteAccount = new JButton("Delete Account");
	private JButton read = new JButton("Read data");
	private JButton write = new JButton("Write data");
	private JButton report = new JButton("Report");
	private JLabel greetLabel = new JLabel("Welcome admin");
	private Bank bank;

	public AdminPanel(LoginController loginController, JFrame frame) {
		this.bank = loginController.getBank();

		this.setLayout(new BorderLayout());

		add(this.greetLabel, BorderLayout.PAGE_START);
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(7, 1));
		bPanel.setBackground(Color.GRAY);

		bPanel.add(this.addPerson);
		bPanel.add(this.deletePerson);
		bPanel.add(this.addAccount);
		bPanel.add(this.deleteAccount);
		bPanel.add(this.read);
		bPanel.add(this.write);
		bPanel.add(this.report);

		add(bPanel, BorderLayout.EAST);
		PersonsTableModel personsTableModel = new PersonsTableModel(bank);
		JTable table = new JTable(personsTableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		addPerson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel nameLabel = new JLabel("Name of the person: ");
				JTextField name = new JTextField();
				JLabel typeLabel = new JLabel("Type of the account: ");
				JTextField type = new JTextField();
				Object[] ob = { nameLabel, name, typeLabel, type };
				int result = JOptionPane.showConfirmDialog(null, ob, "Adding person", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if (type.getText().equals("Spending")) {
						bank.addPerson(name.getText(), AccountType.SPENDING);
					} else if (type.getText().equals("Saving")) {
						bank.addPerson(name.getText(), AccountType.SAVING);
					} else {
						JOptionPane.showMessageDialog(frame, "Unknow account type");
					}
				}
				((PersonsTableModel) table.getModel()).updateModel();
				table.updateUI();
			}
		});

		deletePerson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bank.removePerson((long) table.getValueAt(table.getSelectedRow(), 1));
				// ((PersonsTableModel)
				// table.getModel()).removeRow(table.getSelectedRow());
				((PersonsTableModel) table.getModel()).updateModel();
				table.updateUI();
			}
		});

		deleteAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bank.removeHolderAccount((long) table.getValueAt(table.getSelectedRow(), 1),
						(long) table.getValueAt(table.getSelectedRow(), 2));
				((PersonsTableModel) table.getModel()).removeRow(table.getSelectedRow());
				((PersonsTableModel) table.getModel()).updateModel();
				table.updateUI();
				//System.out.println(bank.getHashtable());
			}
		});

		addAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel typeLabel = new JLabel("Type of the account: ");
				JTextField type = new JTextField();
				Object[] ob = { typeLabel, type };
				int result = JOptionPane.showConfirmDialog(null, ob, "Adding account", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if (type.getText().equals("Spending")) {
						bank.addHolderAccount((long) table.getValueAt(table.getSelectedRow(), 1), AccountType.SPENDING);
					} else if (type.getText().equals("Saving")) {
						bank.addHolderAccount((long) table.getValueAt(table.getSelectedRow(), 1), AccountType.SAVING);
					} else {
						JOptionPane.showMessageDialog(frame, "Unknow account type");
					}
				}
				((PersonsTableModel) table.getModel()).updateModel();
				table.updateUI();
			}
		});

		write.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel amountLabel = new JLabel("Amount of money: ");
				JTextField amount = new JTextField();
				Object[] ob = { amountLabel, amount };
				int result = JOptionPane.showConfirmDialog(null, ob, "Writing", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					bank.writeAccountData((long) table.getValueAt(table.getSelectedRow(), 1),
							(long) table.getValueAt(table.getSelectedRow(), 2), Long.parseLong(amount.getText()));
				}
				((PersonsTableModel) table.getModel()).updateModel();
				table.updateUI();
			}
		});

		read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long sum = bank.readAccountData((long) table.getValueAt(table.getSelectedRow(), 1),
						(long) table.getValueAt(table.getSelectedRow(), 2));
				JOptionPane.showMessageDialog(frame,
						"Account " + table.getValueAt(table.getSelectedRow(), 2) + " has amount of " + sum + " lei");
			}
		});

		report.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Long> list = bank.reportGenerator();
				JOptionPane.showMessageDialog(frame,
						"Ids of the persons who have at least one account with 0 lei:" + list);
			}
		});
	}

	public void setBound(Component comp, Rectangle bounds) {
		comp.setBounds(bounds);
	}
}
