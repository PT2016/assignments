package order.management.IO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import order.management.models.OPDept;
import order.management.models.Order;
import order.management.models.Product;
import order.management.models.Warehouse;

public class AdminFrame extends GenericFrame {

	public static int NR_ELEM_IN_TABLE;

	public JButton addProductButton;
	public JButton seePendingOrders;
	private JPanel logOutSeeOrdersPanel;

	public JTextField jTextField;
	public JButton searchFilterButton;
	private JPanel searchPanelButton;

	private JScrollPane jPanelContent;
	public JTable tableProducts;
	private DefaultTableModel tableModel;


	// initialize warehouse:
	public AdminFrame() {
		super();
		logInOutButton = new JButton("LOGOUT");
		seePendingOrders = new JButton("SEE PENDING ORDERS");
		addProductButton = new JButton("ADD new PRODUCT");
		logOutSeeOrdersPanel = new JPanel(new BorderLayout());
		logOutSeeOrdersPanel.add(addProductButton, BorderLayout.WEST);
		logOutSeeOrdersPanel.add(seePendingOrders, BorderLayout.CENTER);
		logOutSeeOrdersPanel.add(logInOutButton, BorderLayout.EAST);

		jTextField = new JTextField(30);
		jTextField.setBounds(10, 30, 300, 300);
		jTextField.setBackground(Color.white);
		jTextField.setForeground(Color.black);
		searchFilterButton = new JButton("Search");
		searchPanelButton = new JPanel(new BorderLayout());
		JLabel labelType = new JLabel("Filter:");

		searchPanelButton.add(labelType, BorderLayout.WEST);
		searchPanelButton.add(jTextField, BorderLayout.CENTER);
		searchPanelButton.add(searchFilterButton, BorderLayout.EAST);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Product");
		tableModel.addColumn("Price");
		tableModel.addColumn("Stock");
		tableModel.addColumn("Description");
		tableModel.addColumn("OP1"); // modify
		tableModel.addColumn("OP2"); // remove

		tableProducts = new JTable(tableModel);

		tableProducts.setFillsViewportHeight(true);
		jPanelContent = new JScrollPane(tableProducts);

		this.updateTable();

		frame.setLayout(new BorderLayout());
		frame.add(searchPanelButton, BorderLayout.NORTH);
		frame.add(jPanelContent, BorderLayout.CENTER);
		frame.add(logOutSeeOrdersPanel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public AdminFrame(Warehouse wh) {
		super();
		logInOutButton = new JButton("LOGOUT");
		seePendingOrders = new JButton("SEE PENDING ORDERS");
		addProductButton = new JButton("ADD new PRODUCT");
		logOutSeeOrdersPanel = new JPanel(new BorderLayout());
		logOutSeeOrdersPanel.add(addProductButton, BorderLayout.WEST);
		logOutSeeOrdersPanel.add(seePendingOrders, BorderLayout.CENTER);
		logOutSeeOrdersPanel.add(logInOutButton, BorderLayout.EAST);

		jTextField = new JTextField(30);
		jTextField.setBounds(10, 30, 300, 300);
		jTextField.setBackground(Color.white);
		jTextField.setForeground(Color.black);
		searchFilterButton = new JButton("Search");
		searchPanelButton = new JPanel(new BorderLayout());
		JLabel labelType = new JLabel("Filter:");

		searchPanelButton.add(labelType, BorderLayout.WEST);
		searchPanelButton.add(jTextField, BorderLayout.CENTER);
		searchPanelButton.add(searchFilterButton, BorderLayout.EAST);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Product");
		tableModel.addColumn("Price");
		tableModel.addColumn("Stock");
		tableModel.addColumn("Description");
		tableModel.addColumn("OP1");
		tableModel.addColumn("OP2");
		tableProducts = new JTable(tableModel);

		this.updateTable(wh);
		tableProducts.setFillsViewportHeight(true);
		jPanelContent = new JScrollPane(tableProducts);

		frame.setLayout(new BorderLayout());
		frame.add(searchPanelButton, BorderLayout.NORTH);
		frame.add(jPanelContent, BorderLayout.CENTER);
		frame.add(logOutSeeOrdersPanel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// initialize warehouse:
	public void updateTable() {
		Warehouse wh = new Warehouse();
		wh.addExistentProduct(new Product("milk", 50, 3, "white"));
		wh.addExistentProduct(new Product("rose", 20, 4, "red; it smells like spring"));

		Iterator<Product> iterator = wh.products.iterator();
		while (iterator.hasNext()) {
			Product auxP = iterator.next();
			tableModel.addRow(new Object[] { auxP.name, auxP.price, auxP.stock, auxP.description, "MODIFY", "REMOVE" });
		}
	}

	public void updateTable(Warehouse wh) {
		this.removeAllRows(); // preserve the order as in warehouse
		try {
			NR_ELEM_IN_TABLE = wh.products.size();
		} catch (NullPointerException ex) {
			NR_ELEM_IN_TABLE = 0;
		}
		// if (NR_ELEM_IN_TABLE == 0) {
		// JLabel noElemLabel = new JLabel("no product to be displayed");
		// jPanelContent.add(noElemLabel);
		// }
		if (NR_ELEM_IN_TABLE > 0) {
			Iterator<Product> iterator = wh.products.iterator();
			while (iterator.hasNext()) {
				Product auxP = iterator.next();
				tableModel.addRow(
						new Object[] { auxP.name, auxP.price, auxP.stock, auxP.description, "MODIFY", "REMOVE" });
			}
		}
	}

	public void updateTable(Warehouse wh, String searchFor) {
		CharSequence charSequence = searchFor;
		if (searchFor.length() == 0) { // empty string
			this.updateTable(wh); // the same order as in warehouse
		} else if(NR_ELEM_IN_TABLE == wh.products.size()){
			Iterator<Product> iterator = wh.products.iterator();
			int indexOfProduct = -1;
			while (iterator.hasNext()) {
				Product auxP = iterator.next();
				indexOfProduct++; // indexOfProduct in warehouse/table

				if (!auxP.name.contains(charSequence)) {
					tableModel.removeRow(indexOfProduct);
					indexOfProduct--;
					NR_ELEM_IN_TABLE--;
				}
			}
		} // there is a string
	}

	public void queryModifyProduct(Product p) {
		JTextField xField = new JTextField(15); // name
		xField.setText(p.name);
		JTextField yField = new JTextField(15); // price
		yField.setText("" + p.price);
		JTextField zField = new JTextField(15);// stock
		zField.setText("" + p.stock);
		JTextField wField = new JTextField(30);// description
		wField.setText("" + p.description);

		JPanel myPanel = new JPanel(new GridLayout(4, 1));
		myPanel.add(new JLabel("NAME:"));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("PRICE:"));
		myPanel.add(yField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("STOCK:"));
		myPanel.add(zField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("DESCRIPTION:"));
		myPanel.add(wField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Modify product:", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			p.name = xField.getText();
			p.price = Float.parseFloat(yField.getText());
			p.stock = Integer.parseInt(zField.getText());
			p.description = wField.getText();
		}
	}

	public void seePendingOrders(OPDept orderDept) {
		JPanel myPanel;
		try {
			JLabel[] labelsForOrders = new JLabel[orderDept.orders.size()];
			myPanel = new JPanel(new GridLayout(orderDept.orders.size(), 1));
			Iterator<Order> iterator = orderDept.orders.iterator();
			int indexOfProduct = -1;
			while (iterator.hasNext()) {
				Order auxOO = iterator.next();
				indexOfProduct++; // indexOfProduct in warehouse/table

				labelsForOrders[indexOfProduct] = new JLabel(auxOO.toString());
				myPanel.add(labelsForOrders[indexOfProduct]);
			}
		} catch (NullPointerException n) {
			JLabel labelForOrders = new JLabel("no pending order");
			myPanel = new JPanel();
			myPanel.add(labelForOrders);
		}

		JOptionPane.showConfirmDialog(null, myPanel, "Pending Orders:", JOptionPane.OK_CANCEL_OPTION);
	}

	public Product getNewProduct() {
		Product p = new Product(" ");
		JTextField xField = new JTextField(15); // name
		JTextField yField = new JTextField(15); // price
		JTextField zField = new JTextField(15);// stock
		JTextField wField = new JTextField(30);// description

		JPanel myPanel = new JPanel(new GridLayout(4, 1));
		myPanel.add(new JLabel("NAME:"));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("PRICE:"));
		myPanel.add(yField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("STOCK:"));
		myPanel.add(zField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("DESCRIPTION:"));
		myPanel.add(wField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "ADD product:", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if (!xField.getText().isEmpty()) {
				p.name = xField.getText();

				if (!yField.getText().isEmpty()) {
					try {
						p.price = Float.parseFloat(yField.getText());
					} catch (NumberFormatException ex) {
						return null; // invalid price
					}

					try {
						p.stock = Math.abs(Integer.parseInt(zField.getText()));
					} catch (NumberFormatException ex) {
						p.stock = 0;
					}

					p.description = wField.getText();
					System.out.println(p.name + "_p: " + p.price + "_s: " + p.stock + p.description);
					return p;
				}
			}
			// return null; //invalid name/price
		}
		return null; // CANCEL_OP
	}

	public void addNewProductToTable(Product p) {
		tableModel.addRow(
				new Object[] { p.name, p.price, p.stock, p.description, new JButton("MODIFY"), new JButton("REMOVE") });
	}

	public void removeAllRows() {
		int n = tableModel.getRowCount();
		for (int i = n - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}

	public void removeRowFromTable(int i) {
		tableModel.removeRow(i);
	}
}
