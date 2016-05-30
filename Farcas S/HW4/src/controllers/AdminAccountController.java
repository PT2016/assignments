package controllers;

import views.*;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import models.*;

public class AdminAccountController extends AbstractController {

	private AdminAccountView adminAccountView;
	private Person p;

	public AdminAccountController(AdminAccountView frame, boolean hasBackButton) {
		super(frame, hasBackButton);
		adminAccountView = frame;
		adminAccountView.setRemoveButtonActionListener(new RemoveButtonActionListener());
		adminAccountView.setAddButtonActionListener(new AddButtonActionListener());
		p = adminAccountView.getP();
	}

	public class RemoveButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = adminAccountView.getTable().getSelectedRow();
			if (index != -1) {
				String id = (String) adminAccountView.getTable().getModel().getValueAt(index, 0);
				for (Account a : Bank.getInstance().getInfo().get(p)) {
					if (a.getId().equals(id)) {
						try {
							Bank.getInstance().removeAccount(p, a);
						} catch (IllegalOperationException e1) {
							e1.printStackTrace();
						}
						break;
					}
				}
				((DefaultTableModel) adminAccountView.getTable().getModel()).removeRow(index);
			}
		}
	}

	public class AddButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] options = {"Savings Account", "Spendings Account"};
			int choice = JOptionPane.showOptionDialog(null, "What type of account do you want?", "Choose an option", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]); 
			int money = Integer.parseInt(JOptionPane.showInputDialog("What will be your inital balance?"));
			Account a = null;
			if(choice == 0){
				try {
					a = new SavingsAccount(money);
				} catch (NotEnoughFundsException e1) {
					JOptionPane.showMessageDialog(null, "Not enough funds");
					return;
				}
			}else{
				a = new SpendingsAccount(money);
			}
			a.addObserver(Bank.getInstance());
			try {
				Bank.getInstance().addAccount(p, a);
			} catch (IllegalOperationException e1) {
				e1.printStackTrace();
				return;
			}
			((DefaultTableModel) adminAccountView.getTable().getModel()).addRow(new Object[] { a.getId(), a.getType(), a.getMoney(), a.getExpireDate()});
		}
	}

}
