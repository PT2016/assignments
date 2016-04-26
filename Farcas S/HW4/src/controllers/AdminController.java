package controllers;

import java.awt.event.*;

import models.*;
import views.*;

import javax.swing.JOptionPane;
import javax.swing.table.*;

import controllers.CustomerController.GenerateReportButtonActionListener;

public class AdminController extends AbstractController {

	private AdminView adminView;

	public AdminController(AdminView frame, boolean hasBackButton) {
		super(frame, hasBackButton);
		adminView = frame;
		adminView.setRemoveButtonActionListener(new RemoveButtonActionListener());
		adminView.setAddButtonActionListener(new AddButtonActionListener());
		adminView.setAccountsButtonActionListener(new AccountsButtonActionListener());
		adminView.setGenerateReportButtonActionListener(new GenerateReportButtonActionListener());
	}

	public class RemoveButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = adminView.getTable().getSelectedRow();
			if (index != -1) {
				String name = (String) adminView.getTable().getModel().getValueAt(index, 0);
				for (Person p : Bank.getInstance().getInfo().keySet()) {
					if (p.getName().equals(name)) {
						try {
							Bank.getInstance().removePerson(p);
						} catch (IllegalOperationException e1) {
							e1.printStackTrace();
						}
						break;
					}
				}
				((DefaultTableModel) adminView.getTable().getModel()).removeRow(index);
			}
		}
	}

	public class AddButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = adminView.getUserName().getText();
			int age = Integer.parseInt(adminView.getAge().getText());
			Person p = new Person(name, age);
			try {
				Bank.getInstance().addPerson(p);
			} catch (IllegalOperationException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Illegal Operation Exception");
				return;
			}
			((DefaultTableModel) adminView.getTable().getModel()).addRow(new Object[] { name, age });
			AdminAccountView adminAccountView = new AdminAccountView("Accounts", p);
			new AdminAccountController(adminAccountView, true);
			adminAccountView.getAdd().doClick();
		}
	}

	public class AccountsButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = adminView.getTable().getSelectedRow();
			if (index != -1) {
				String name = (String) adminView.getTable().getModel().getValueAt(index, 0);
				for (Person p : Bank.getInstance().getInfo().keySet()) {
					if (p.getName().equals(name)) {
						new AdminAccountController(new AdminAccountView("Accounts", p), true);
						break;
					}
				}
			}
		}
	}
	
	public class GenerateReportButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Bank.getInstance().generateReportAdmin();		
		}
	}

}
