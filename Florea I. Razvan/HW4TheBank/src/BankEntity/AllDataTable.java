package BankEntity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import AccountEntities.Account;
import UserEntities.Person;

public class AllDataTable {
	
	private Bank bank;
	private String[] header = { "Client Name", "#Spending Accounts", "#Saving Accounts" };
	private Object[][] data;
	private JTable table;

	public AllDataTable() {
		
		bank = new Bank();
		
		bank.updateBankData();
		
		data = new Object[bank.getBankData().keySet().size()][3];
		int i = 0;
		for (Map.Entry<Person, List<Account>> entry : bank.getBankData().entrySet()) {
			
			data[i][0] = entry.getKey().getName();
			int spendingNr = 0;
			int savingNr = 0;
			for(Account account : entry.getValue()){
				if(account.getType().equals("saving"))
					savingNr++;
				if(account.getType().equals("spending"))
					spendingNr++;
			}
			data[i][1] = spendingNr;
			data[i][2] = savingNr;
			
			i++;
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
	
	public JTable getTable(){
		return table;
	}

}
