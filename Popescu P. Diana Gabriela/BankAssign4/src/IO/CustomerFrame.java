package IO;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.Account;
import models.Person;

public class CustomerFrame extends GenericFrame {

	public static int NR_ELEM_IN_TABLE;

	private JLabel holderInfoLabel; 
	
	private JScrollPane jPanelContent;
	public JTable tableCustomer;
	private DefaultTableModel tableModel;

	public CustomerFrame(Person person, Set<Account> accounts) {
		super();
		logInOutButton = new JButton("LOGOUT");

		holderInfoLabel = new JLabel(person.toString());
		
		tableModel = new DefaultTableModel();
		
		tableModel.addColumn("AccountID");
		tableModel.addColumn("Account type");
		tableModel.addColumn("Available MONEY");
		tableModel.addColumn("OP1"); // withdraw
		tableModel.addColumn("OP2"); // deposit

		tableCustomer = new JTable(tableModel);

		tableCustomer.setFillsViewportHeight(true);
		jPanelContent = new JScrollPane(tableCustomer);

		this.updateTable(accounts);

		frame.setLayout(new BorderLayout());
		frame.add(holderInfoLabel, BorderLayout.NORTH);
		frame.add(jPanelContent, BorderLayout.CENTER);
		frame.add(logInOutButton, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void updateTable(Set<Account> accounts) {
		this.removeAllRows(); // preserve the order in table
		try {
			NR_ELEM_IN_TABLE = accounts.size();
		} catch (NullPointerException ex) {
			NR_ELEM_IN_TABLE = 0;
		}
		// if (NR_ELEM_IN_TABLE == 0) {
		// JLabel noElemLabel = new JLabel("no product to be displayed");
		// jPanelContent.add(noElemLabel);
		// }
		if (NR_ELEM_IN_TABLE > 0) {
			Iterator<Account> iterator = accounts.iterator();
			while (iterator.hasNext()) {
				Account auxA = iterator.next();
				tableModel.addRow(new Object[] { auxA.ID, auxA.type, auxA.money, "WITHDRAW", "DEPOSIT" });
			}
		}
	}

	// *** 1 ***/
	public double queryForMoney() {
		String input = JOptionPane.showInputDialog(String.format("Money:"));

		Double nr;
		try {
			nr = Double.parseDouble(input);
			if(nr < 0)
			{
				nr = -nr;
			}
		} catch (NumberFormatException e) {
			nr = (double) -1;// error
		}
		return nr;
	}

	public void removeAllRows() {
		int n = tableModel.getRowCount();
		for (int i = n - 1; i >= 0; i--) {
			tableModel.removeRow(i);// ???
		}
	}
}