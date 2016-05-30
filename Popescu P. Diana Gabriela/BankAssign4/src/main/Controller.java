package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Iterator;
import java.util.Set;

import IO.*;
import models.Account;
import models.Bank;
import models.Person;
import models.SavingAccount;
import test.Test;

public class Controller implements ActionListener {

	private static final int LOG_IN = 0;
	private static final int ADMIN = 1;
	private static final int GUEST = 2;
	private int CURRENT_FRAME = LOG_IN;

	private Bank bank;
	private LogInFrame logInFrame;
	private AdminFrame adminFrame;
	private CustomerFrame customerFrame;

	public Controller(LogInFrame logInFrame) throws Exception {
		new Test();
		this.logInFrame = logInFrame;
		this.logInFrame.logInOutButton.addActionListener(this);

		adminFrame = null;
		customerFrame = null;

		//bank = new Bank(); //if not-deserialization

		deserialization(); // bank
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == logInFrame.logInOutButton) {
			if (logInFrame.selectedUser.equals(LogInFrame.ADMIN)) { // instantiate
																	// adminFrame
				CURRENT_FRAME = ADMIN;
				logInFrame.frame.setVisible(false);
				adminFrame = new AdminFrame(bank);

				adminFrame.logInOutButton.addActionListener(this);
				adminFrame.searchFilterButton.addActionListener(this);
				adminFrame.addAccountButton.addActionListener(this);
				adminFrame.addHolderButton.addActionListener(this);
				adminFrame.removeHolderButton.addActionListener(this);

				adminFrame.tableAdmin.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						int auxRow = adminFrame.tableAdmin.rowAtPoint(evt.getPoint());
						int auxCol = adminFrame.tableAdmin.columnAtPoint(evt.getPoint());
						if (auxRow >= 0 && auxCol >= 0) {
							if (auxCol == 5) { // REMOVE ACCOUNT
								Set<Person> holders = bank.getHolders();
								Iterator<Person> iterator = holders.iterator();
								while (iterator.hasNext()) { // iterate holders
									Person auxP = iterator.next();
									if (auxP.ID.equals(adminFrame.tableAdmin.getValueAt(auxRow, 0))) {
										Set<Account> accounts = bank.getAccounts(auxP);

										Iterator<Account> iterator2 = accounts.iterator();
										while (iterator2.hasNext()) {
											Account auxA = iterator2.next();
											if (auxA.ID.equals(adminFrame.tableAdmin.getValueAt(auxRow, 2))) {
												bank.removeAccount(auxP, auxA);
												adminFrame.updateTable(bank);
												serialization();
												break;
											}
										}
										break;
									}
								}
							}
						}
					}
				});
			} else if (logInFrame.selectedUser.equals(LogInFrame.GUEST)) { // instantiate
																			// customerFrame
				CURRENT_FRAME = GUEST;
				Person loggedPerson = logInFrame.queryLogInCustomer(bank);
				if (loggedPerson == null) {
					logInFrame.errorMessageLogIn();
				} else {
					logInFrame.frame.setVisible(false);
					Set<Account> accountsOfLoggedPerson = bank.getAccounts(loggedPerson);

					customerFrame = new CustomerFrame(loggedPerson, accountsOfLoggedPerson);
					customerFrame.logInOutButton.addActionListener(this);

					customerFrame.tableCustomer.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							int auxRow = customerFrame.tableCustomer.rowAtPoint(evt.getPoint());
							int auxCol = customerFrame.tableCustomer.columnAtPoint(evt.getPoint());
							if (auxRow >= 0 && auxCol >= 0) {
								if (auxCol == 3) { // WITHDRAW
									Account auxA = new SavingAccount("", 0);
									Iterator<Account> iterator2 = accountsOfLoggedPerson.iterator();
									while (iterator2.hasNext()) {
										auxA = iterator2.next();
										if (auxA.ID.equals(customerFrame.tableCustomer.getValueAt(auxRow, 0))) {
											break;
										}
									}
									double sum = customerFrame.queryForMoney();
									bank.withdraw(sum, auxA.ID, loggedPerson);
									serialization();
									customerFrame.updateTable(accountsOfLoggedPerson);
								} else if (auxCol == 4) { // DEPOSIT
									Account auxA = new SavingAccount("", 0);
									Iterator<Account> iterator2 = accountsOfLoggedPerson.iterator();
									while (iterator2.hasNext()) {
										auxA = iterator2.next();
										if (auxA.ID.equals(customerFrame.tableCustomer.getValueAt(auxRow, 0))) {
											break;
										}
									}
									double sum = customerFrame.queryForMoney();
									bank.deposit(sum, auxA.ID, loggedPerson);
									serialization();
									customerFrame.updateTable(accountsOfLoggedPerson);
								}
							}
						}
					});
				}
			}
		} else if (CURRENT_FRAME == ADMIN && adminFrame != null) {
			if (source == adminFrame.logInOutButton) {
				CURRENT_FRAME = LOG_IN;
				adminFrame.frame.setVisible(false);
				logInFrame.frame.setVisible(true);
			} else if (source == adminFrame.addAccountButton) {
				adminFrame.queryAddAccount(bank);
				adminFrame.updateTable(bank);
				serialization();
			} else if (source == adminFrame.addHolderButton) {
				Person auxP = adminFrame.queryAddCustomer(bank);
				if(auxP != null){
					bank.addHolder(auxP);
					System.out.println("holder added: " + auxP.toString());
					adminFrame.updateTable(bank);
					serialization();
				}
				else{
					System.out.println("person is null");
				}
			} else if (source == adminFrame.removeHolderButton) {
				boolean success = adminFrame.queryRemoveCustomer(bank);
				if(success == true){ //it was removed
					adminFrame.updateTable(bank);
					serialization();
				}
			} else if (source == adminFrame.searchFilterButton) {
				adminFrame.updateTable(bank, adminFrame.jTextField.getText());
			}
		} else if (CURRENT_FRAME == GUEST && customerFrame != null) {
			if (source == customerFrame.logInOutButton) {
				CURRENT_FRAME = LOG_IN;
				customerFrame.frame.setVisible(false);
				logInFrame.frame.setVisible(true);
			}
		} // event source = customer frame
	}

	private void deserialization() {
		try {
			FileInputStream fileIn1 = new FileInputStream("bank.ser");
			ObjectInputStream in1 = new ObjectInputStream(fileIn1);
			bank = (Bank) in1.readObject();
			in1.close();
			fileIn1.close();
		} catch (IOException i) { // FileNotFoundException
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("class not found");
			c.printStackTrace();
			return;
		}
	}

	private void serialization() {
		try {
			FileOutputStream fileOut1 = new FileOutputStream("bank.ser");
			ObjectOutputStream out1 = new ObjectOutputStream(fileOut1);
			out1.writeObject(bank);
			out1.flush();
			out1.close();
			fileOut1.close();
			System.out.printf("Serialized data is saved in /bank.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
