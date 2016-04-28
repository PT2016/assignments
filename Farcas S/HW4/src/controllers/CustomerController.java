package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import models.*;
import views.*;

public class CustomerController extends AbstractController {

	private CustomerView customerView;
	private Person p;

	public CustomerController(CustomerView frame, boolean hasBackButton) {
		super(frame, hasBackButton);
		customerView = frame;
		customerView.setWithdrawButtonActionListener(new WithdrawButtonActionListener());
		customerView.setDepositButtonActionListener(new DepositButtonActionListener());
		customerView.setGenerateReportButtonActionListener(new GenerateReportButtonActionListener());
		p = customerView.getP();
	}

	public class WithdrawButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = customerView.getTable().getSelectedRow();
			Account found = null;
			if (index != -1) {
				String id = (String) customerView.getTable().getModel().getValueAt(index, 0);
				for (Account a : Bank.getInstance().getInfo().get(p)) {
					if (a.getId().equals(id)) {
						found = a;
						try {
							a.withdraw(Double.parseDouble(customerView.getAmount().getText()));
						} catch (NotEnoughFundsException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Not enough funds");
							return;
						}
						break;
					}
				}
				((DefaultTableModel) customerView.getTable().getModel()).setValueAt(new Double(found.getMoney()), index,
						2);
			}
		}
	}

	public class DepositButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = customerView.getTable().getSelectedRow();
			Account found = null;
			if (index != -1) {
				String id = (String) customerView.getTable().getModel().getValueAt(index, 0);
				for (Account a : Bank.getInstance().getInfo().get(p)) {
					if (a.getId().equals(id)) {
						found = a;
						a.deposit(Double.parseDouble(customerView.getAmount().getText()));
						break;
					}
				}
				((DefaultTableModel) customerView.getTable().getModel()).setValueAt(new Double(found.getMoney()), index,
						2);
			}
		}
	}

	public class GenerateReportButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = customerView.getTable().getSelectedRow();
			if (index != -1) {
				String id = (String) customerView.getTable().getModel().getValueAt(index, 0);
				for (Account a : Bank.getInstance().getInfo().get(p)) {
					if (a.getId().equals(id)) {
						a.generateReportHolder();
						break;
					}
				}
			}
		}
	}
}
