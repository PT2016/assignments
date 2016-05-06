package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import models.Account;
import models.Bank;
import models.Person;
import models.utilities.UserReport;
import views.LoginFrame;
import views.MessageDialogs;
import views.UserFrame;

public class UserFrameController {
	private static UserFrame frame = new UserFrame();
	private Bank bank = new Bank();
	private SerializableManager manager = new SerializableManager();
	private Date date;

	public UserFrameController() throws ParseException {
		frame.setAddMoneyButtonActionListener(new AddMoneyButtonActionListener());
		frame.setWithDrawButtonActionListener(new WithDrawMoneyButtonActionListener());
		frame.setReportButtonActionListener(new ReportButtonActionListener());
		bank.setContent(manager.deserializeBank().getContent());
		Iterator<Entry<Person, ArrayList<Account>>> iterator = bank.getContent().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, ArrayList<Account>> entry = iterator.next();
			System.out.println(entry.getKey().getName());
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (entry.getValue().get(i).getP().getName().equals(LoginFrame.getUserName())) {
					Object[] row1 = { entry.getKey().getId(), entry.getKey().getName(), entry.getValue().get(i).getId(),
							entry.getValue().get(i).getSum(), entry.getValue().get(i).getType(),
							entry.getValue().get(i).getDate(), entry.getValue().get(i).getCloseDate() };
					Object[] row = { entry.getValue().get(i).getId(), entry.getValue().get(i).getSum(),
							entry.getKey().getName(), entry.getValue().get(i).getDate(),
							entry.getValue().get(i).getCloseDate(), entry.getValue().get(i).getType() };
					((DefaultTableModel) UserFrame.getTable().getModel()).addRow(row1);
					((DefaultTableModel) UserFrame.getReflectionTable().getModel()).addRow(row);
				}
			}
		}
	}

	public static void printTheAccount(Account a) {
		Object[] row = { a.getP().getId(), a.getP().getName(), a.getSum(), a.getType(), a.getDate(), a.getCloseDate() };
		((DefaultTableModel) UserFrame.getTable().getModel()).addRow(row);
	}

	public class AddMoneyButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			String data[] = MessageDialogs.addMoneyWindow();
			Iterator<Entry<Person, ArrayList<Account>>> iterator = bank.getContent().entrySet().iterator();
			int row = MessageDialogs.getRowByValue(UserFrame.getTable().getModel(), Integer.parseInt(data[2]));
			while (iterator.hasNext()) {
				Entry<Person, ArrayList<Account>> entry = iterator.next();
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).getId() == Integer.parseInt(data[2])) {
						entry.getValue().get(i).depositMoney(Double.parseDouble(data[3]));
						UserFrame.getTable().getModel().setValueAt(new Double(entry.getValue().get(i).getSum()), row,
								3);
						date = new Date();
						try {
							frame.details(entry.getValue().get(i), date);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}
			}
			manager.serializeBank(bank);

		}
	}

	public class WithDrawMoneyButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			String data[] = MessageDialogs.withDrawMoneyWindow();
			Iterator<Entry<Person, ArrayList<Account>>> iterator = bank.getContent().entrySet().iterator();
			int row = MessageDialogs.getRowByValue(UserFrame.getTable().getModel(), Integer.parseInt(data[2]));
			while (iterator.hasNext()) {
				Entry<Person, ArrayList<Account>> entry = iterator.next();
				for (int i = 0; i < entry.getValue().size(); i++) {
					if (entry.getValue().get(i).getId() == Integer.parseInt(data[2])) {
						entry.getValue().get(i).withdrawMoney(Double.parseDouble(data[3]));
						UserFrame.getTable().getModel().setValueAt(new Double(entry.getValue().get(i).getSum()), row,
								3);
						date = new Date();
						try {
							frame.details(entry.getValue().get(i), date);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			}
			manager.serializeBank(bank);

		}

	}

	public class ReportButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			new UserReport();
		}

	}

}
