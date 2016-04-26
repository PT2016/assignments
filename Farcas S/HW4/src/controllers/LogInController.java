package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Bank;
import views.*;
import models.*;

public class LogInController extends AbstractController {

	private LogIn logIn;

	public LogInController(LogIn frame, boolean hasBackButton) {
		super(frame, hasBackButton);
		frame.setSubmitButtonActionListener(new SubmitButtonActionListener());
		logIn = frame;
	}

	private class SubmitButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Person found = null;
			if (logIn.getUserName().getText().equals(Bank.admin)) {
				new AdminController(new AdminView("Admin"), true);
				return;
			}
			for (Person p : Bank.getInstance().getInfo().keySet())
				if (p.getName().equals(logIn.getUserName().getText()))
					found = p;
			if (found != null) {
				new CustomerController(new CustomerView("Customer", found), true);
				return;
			}
			logIn.getUserName().setText("Invalid log");
		}
	}

}
