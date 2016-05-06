package order.management.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import order.management.IO.AdminFrame;
import order.management.IO.CustomerFrame;
import order.management.IO.LogInFrame;
import order.management.models.OPDept;
import order.management.models.Order;
import order.management.models.Product;
import order.management.models.Warehouse;

public class Controller implements ActionListener {

	private static final int LOG_IN = 0;
	private static final int ADMIN = 1;
	private static final int GUEST = 2;
	private int CURRENT_FRAME = LOG_IN;

	private OPDept orderDept;
	private Warehouse warehouse;
	private LogInFrame logInFrame;
	private AdminFrame adminFrame;
	private CustomerFrame customerFrame;

	public Controller(LogInFrame logInFrame) {
		this.logInFrame = logInFrame;
		this.logInFrame.logInOutButton.addActionListener(this);

		adminFrame = null;
		customerFrame = null;
		
		warehouse = null;
		orderDept = null;
		
		deserialization(); // warehouse & orderDept
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == logInFrame.logInOutButton) {
			if (logInFrame.selectedUser.equals(LogInFrame.ADMIN)) { // instantiate
																	// adminFrame
				CURRENT_FRAME = ADMIN;
				logInFrame.frame.setVisible(false);
				adminFrame = new AdminFrame(warehouse);
				
				adminFrame.logInOutButton.addActionListener(this);
				adminFrame.searchFilterButton.addActionListener(this);
				adminFrame.seePendingOrders.addActionListener(this);
				adminFrame.addProductButton.addActionListener(this);

				adminFrame.tableProducts.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						int auxRow = adminFrame.tableProducts.rowAtPoint(evt.getPoint());
						int auxCol = adminFrame.tableProducts.columnAtPoint(evt.getPoint());
						if (auxRow >= 0 && auxCol >= 0) {
							if (auxCol == 4) { // MODIFY
								Iterator<Product> iterator = warehouse.products.iterator();
								while (iterator.hasNext()) {
									Product auxP = iterator.next();
									if (auxP.name.equals(adminFrame.tableProducts.getValueAt(auxRow, 0))) {
										adminFrame.queryModifyProduct(auxP);

										adminFrame.updateTable(warehouse);

										serializationWarehouse();
										break; // found product
									}
								}
							} else if (auxCol == 5) { // REMOVE
								Iterator<Product> iterator = warehouse.products.iterator();
								while (iterator.hasNext()) {
									Product auxP = iterator.next();
									if (auxP.name.equals(adminFrame.tableProducts.getValueAt(auxRow, 0))) {
										adminFrame.removeRowFromTable(auxRow);
										warehouse.products.remove(auxP);
										adminFrame.updateTable(warehouse);

										serializationWarehouse();
										break; // found product
									}
								}
							}
						}
					}
				});
			} else if (logInFrame.selectedUser.equals(LogInFrame.GUEST)) { // instantiate
																			// customerFrame
				CURRENT_FRAME = GUEST;
				logInFrame.frame.setVisible(false);
				customerFrame = new CustomerFrame(warehouse);
				customerFrame.logInOutButton.addActionListener(this);
				customerFrame.searchFilterButton.addActionListener(this);

				customerFrame.tableProducts.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						int auxRow = customerFrame.tableProducts.rowAtPoint(evt.getPoint());
						int auxCol = customerFrame.tableProducts.columnAtPoint(evt.getPoint());
						if (auxRow >= 0 && auxCol >= 0) {
							if (auxCol == 4) { // ORDER
								Iterator<Product> iterator = warehouse.products.iterator();
								while (iterator.hasNext()) {
									Product auxP = iterator.next();
									if (auxP.name.equals(customerFrame.tableProducts.getValueAt(auxRow, 0))) {
										// *** 1 ***//
										int nrPieces = customerFrame.queryPiecesForOrder();
										if (nrPieces <= 0
												|| nrPieces > (int) customerFrame.tableProducts.getValueAt(auxRow, 2)) {
											// >stock
											// *** 2 ***//
											customerFrame.errorMessageOrder();
										} else {
											// if the required number of pieces
											// is
											// available
											// *** 3 ***//
											Order auxOO = customerFrame.queryPlaceOrderAsCustomer(auxP, nrPieces);
											orderDept.orders.add(auxOO);
											
											serializationOrderDept();//-----------------------------
											// save the last order
											auxP.stock -= nrPieces;
											customerFrame.updateTable(warehouse);
											serializationWarehouse();//--------------------------????
											// save the new stock
										}
										break; // found product
									}
								}
							}
						}
					}
				});
			}
		} else if (CURRENT_FRAME == ADMIN && adminFrame != null) {
			if (source == adminFrame.logInOutButton) {
				CURRENT_FRAME = LOG_IN;
				adminFrame.frame.setVisible(false);
				logInFrame.frame.setVisible(true);
			} else if (source == adminFrame.addProductButton) {
				Product auxPP = adminFrame.getNewProduct();
				if (auxPP != null) { // catch nullPointerEx
					warehouse.addExistentProduct(auxPP);
					serializationWarehouse();
				}

				adminFrame.updateTable(warehouse);
			} else if (source == adminFrame.seePendingOrders) {
				adminFrame.seePendingOrders(orderDept);
			} else if (source == adminFrame.searchFilterButton) {
				adminFrame.updateTable(warehouse, adminFrame.jTextField.getText());
			}
		} else if (CURRENT_FRAME == GUEST && customerFrame != null) {
			if (source == customerFrame.logInOutButton) {
				CURRENT_FRAME = LOG_IN;
				customerFrame.frame.setVisible(false);
				logInFrame.frame.setVisible(true);
			} else if (source == customerFrame.searchFilterButton) {
				customerFrame.updateTable(warehouse, customerFrame.jTextField.getText());
			}
		} // event source = customer frame

	}

	//@SuppressWarnings("unused")
	private void deserialization() {
		try {
			FileInputStream fileIn1 = new FileInputStream("wh.ser");
			FileInputStream fileIn2 = new FileInputStream("od.ser");
			ObjectInputStream in1 = new ObjectInputStream(fileIn1);
			ObjectInputStream in2 = new ObjectInputStream(fileIn2);
			warehouse = (Warehouse) in1.readObject();
			orderDept = (OPDept) in2.readObject();
			in1.close();
			fileIn1.close();
			in2.close();
			fileIn2.close();
		} catch (IOException i) { // FileNotFoundException
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("class not found");
			c.printStackTrace();
			return;
		}
	}

	private void serializationWarehouse() {
		try {
			FileOutputStream fileOut1 = new FileOutputStream("wh.ser");// new
																		// File("wh.ser"));
			ObjectOutputStream out1 = new ObjectOutputStream(fileOut1);
			out1.writeObject(warehouse);
			out1.flush();
			out1.close();
			fileOut1.close();
			System.out.printf("Serialized data is saved in /wh.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private void serializationOrderDept() {
		try {
			FileOutputStream fileOut1 = new FileOutputStream("od.ser");// new
																		// File("od.ser"));
			ObjectOutputStream out1 = new ObjectOutputStream(fileOut1);
			out1.writeObject(orderDept);
			out1.flush();
			out1.close();
			fileOut1.close();
			System.out.printf("Serialized data is saved in /od.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
