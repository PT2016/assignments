package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import models.Account;
import models.Bank;
import models.Person;
import models.SavingAccount;
import models.SpendingAccount;
import views.AdminFrame;
import views.MessageDialogs;

public class AdminFrameController {
	private static AdminFrame frame = new AdminFrame();
	private Bank bank = new Bank();
	private SerializableManager manager = new SerializableManager();

	public AdminFrameController() {
		frame.setAddActionListener(new AddButtonActionListener());
		frame.setDelButtonActionListener(new DeleteButtonActionListener());
		frame.setAddHolderButtonActionListener(new AddHolderButtonActionListener());
		frame.setDeleteHolderButtonActionListener(new DeleteHolderButtonActionListener());
		frame.setReportsActionListener(new ReportsButtonActionListener());
		this.bank.setContent(manager.deserializeBank().getContent());
		Iterator<Entry<Person, ArrayList<Account>>> iterator = bank.getContent().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, ArrayList<Account>> entry = iterator.next();
			for (int i = 0; i < entry.getValue().size(); i++) {
				Object[] row = { entry.getKey().getId(), entry.getKey().getName(), entry.getValue().get(i).getId(),
						entry.getValue().get(i).getSum(), entry.getValue().get(i).getType(),
						entry.getValue().get(i).getDate(), entry.getValue().get(i).getCloseDate() };
				((DefaultTableModel) AdminFrame.getTable().getModel()).addRow(row);
			}
			Object[] row1 = { entry.getKey().getId(), entry.getKey().getName() };
			((DefaultTableModel) AdminFrame.getRefTable().getModel()).addRow(row1);
		}
	}

	public static void printTheAccount(Account a) {
		Object[] row = { a.getP().getId(), a.getP().getName(), a.getId(), a.getSum(), a.getType(), a.getDate(),
				a.getCloseDate() };
		((DefaultTableModel) AdminFrame.getTable().getModel()).addRow(row);
	}

	public class AddButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			String data[] = MessageDialogs.addAccountWindow();
			Person p = new Person(Integer.parseInt(data[2]), data[0]);
			DateFormat df = new SimpleDateFormat("dd/MM/yy");
			Date dateobj = new Date();
			String date = df.format(dateobj);
			Account a = null;
			if (data[1].equals("Spending Account")) {
				a = new SpendingAccount(0.0, p, date, "Spending Account");
			} else if (data[1].equals("Saving Account")) {
				a = new SavingAccount(0.0, p, date, "Saving Account");
			}
			bank.addAccountToHolder(a, p);
			printTheAccount(a);
			manager.serializeBank(bank);
		}
	}

	public class DeleteButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			int viewIndex = AdminFrame.getTable().getSelectedRow();
			if (viewIndex != -1) {
				int id = Integer.parseInt(AdminFrame.getTable().getValueAt(viewIndex, 0).toString());
				int accId = Integer.parseInt(AdminFrame.getTable().getValueAt(viewIndex, 2).toString());
				Iterator<Entry<Person, ArrayList<Account>>> iterator = bank.getContent().entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<Person, ArrayList<Account>> entry = iterator.next();
					if (entry.getKey().getId() == id) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							if (entry.getValue().get(i).getId() == accId)
								bank.deleteAccountToHolder(entry.getValue().get(i), entry.getKey());
						}
					}
				}
				int modelIndex = AdminFrame.getTable().convertRowIndexToModel(viewIndex);
				DefaultTableModel model = (DefaultTableModel) AdminFrame.getTable().getModel();
				model.removeRow(modelIndex);
			}
			manager.serializeBank(bank);
		}

	}

	public class AddHolderButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			String data[] = MessageDialogs.addHolderWindow();
			Person p = new Person(Integer.parseInt(data[1]), data[0]);
			bank.addPerson(p);
			Object[] row1 = { p.getId(), p.getName() };
			((DefaultTableModel) AdminFrame.getRefTable().getModel()).addRow(row1);
			manager.serializeBank(bank);
		}
	}

	public class DeleteHolderButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			int viewIndex = AdminFrame.getRefTable().getSelectedRow();
			if (viewIndex != -1) {
				int id = Integer.parseInt(AdminFrame.getRefTable().getValueAt(viewIndex, 0).toString());
				String name = AdminFrame.getRefTable().getValueAt(viewIndex, 1).toString();
				bank.deletePerson(new Person(id, name));
				int modelIndex = AdminFrame.getRefTable().convertRowIndexToModel(viewIndex);
				DefaultTableModel model = (DefaultTableModel) AdminFrame.getRefTable().getModel();
				model.removeRow(modelIndex);
				Iterator<Entry<Person, ArrayList<Account>>> iterator = bank.getContent().entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<Person, ArrayList<Account>> entry = iterator.next();
					System.out.println(entry.getKey().getName());
				}
				manager.serializeBank(bank);
			}
		}
	}

	public class ReportsButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			bank.generateReports();
		}
	}

	public static AdminFrame getFrame() {
		return frame;
	}

	public static void setFrame(AdminFrame frame) {
		AdminFrameController.frame = frame;
	}
}
