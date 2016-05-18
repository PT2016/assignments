package BankEntity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import AccountEntities.Account;
import UserEntities.Person;

public class SpecificClientTable {

	private Bank bank;
	private String[] header = { "Client Name", "Account Type", "Account ID", "Money" };
	private Object[][] data;
	private JTable table;

	public SpecificClientTable(String clientName) {

		bank = new Bank();

		bank.updateBankData();

		int nrAccounts = 0;

		for (Map.Entry<Person, List<Account>> entry : bank.getBankData().entrySet()) {
			if (entry.getKey().getName().equals(clientName)) {
				nrAccounts = entry.getValue().size();
				break;
			}
		}

		data = new Object[nrAccounts][4];
		int i = 0;
		for (Map.Entry<Person, List<Account>> entry : bank.getBankData().entrySet()) {
			if (entry.getKey().getName().equals(clientName)) {
				for (Account account : entry.getValue()) {
					data[i][0] = entry.getKey().getName();
					data[i][1] = account.getType();
					data[i][2] = account.getID();
					data[i][3] = account.getMoney();

					i++;
				}
				
				break;
			}
		}

		table = new JTable(data, header);

		table.setPreferredScrollableViewportSize(new Dimension(500, 200));
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		table.setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setForeground(Color.decode("0x121212"));
		table.setBackground(Color.decode("0xc9c9c9"));
		table.setGridColor(Color.WHITE);
		table.setRowHeight(25);
	}

	public JTable getTable() {
		return table;
	}
}
