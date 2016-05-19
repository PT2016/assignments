package order.management.IO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Random;

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

import order.management.models.Customer;
import order.management.models.Order;
import order.management.models.Product;
import order.management.models.Warehouse;

public class CustomerFrame extends GenericFrame {

	public static int NR_ELEM_IN_TABLE;

	public JTextField jTextField;
	public JButton searchFilterButton;
	private JPanel searchPanelButton;

	private JScrollPane jPanelContent;
	public JTable tableProducts;
	private DefaultTableModel tableModel;

	public CustomerFrame(Warehouse wh) {
		super();
		logInOutButton = new JButton("LOGOUT");

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
		tableModel.addColumn("OP"); // order

		tableProducts = new JTable(tableModel);

		tableProducts.setFillsViewportHeight(true);
		jPanelContent = new JScrollPane(tableProducts);

		this.updateTable(wh);

		frame.setLayout(new BorderLayout());
		frame.add(searchPanelButton, BorderLayout.NORTH);
		frame.add(jPanelContent, BorderLayout.CENTER);
		frame.add(logInOutButton, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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
				tableModel.addRow(new Object[] { auxP.name, auxP.price, auxP.stock, auxP.description, "ORDER" });
			}
		}
	}

	public void updateTable(Warehouse wh, String searchFor) {
		CharSequence charSequence = searchFor;
		if (searchFor.length() == 0) { // empty string
			this.updateTable(wh); // the same order as in warehouse
		} else {
			Iterator<Product> iterator = wh.products.iterator();
			int indexOfProduct = -1;
			while (iterator.hasNext()) {
				Product auxP = iterator.next();
				indexOfProduct++; // indexOfProduct in warehouse/table

				if (!auxP.name.contains(charSequence)) {
					tableModel.removeRow(indexOfProduct);
					NR_ELEM_IN_TABLE--;
				}
			}
		} // there is a string
	}

	// *** 1 ***/
	public int queryPiecesForOrder() {
		String input = JOptionPane.showInputDialog(String.format("Number of pieces:"));

		Integer nrPieces;
		try {
			nrPieces = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			nrPieces = -1;// error
		}
		return nrPieces;
	}

	// *** 2 ***/
	public void errorMessageOrder() {
		JPanel myPanel = new JPanel();
		JLabel labelForOrders = new JLabel("ERROR: invalid number of pieces");
		myPanel.add(labelForOrders);

		JOptionPane.showConfirmDialog(null, myPanel, "Pending Orders:", JOptionPane.OK_CANCEL_OPTION);
	}

	// *** 3 ***/
	public Order queryPlaceOrderAsCustomer(Product p, int nrPieces) {
		JTextField xField = new JTextField(10); // id
		JTextField yField = new JTextField(15); // name

		JPanel myPanel = new JPanel(new GridLayout(2, 1));
		myPanel.add(new JLabel("ID:"));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("NAME:"));
		myPanel.add(yField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter your log-in details",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Integer id;
			try {
				id = Integer.parseInt(xField.getText());
			} catch (NumberFormatException ex) {
				id = new Random().nextInt(300); // new customer
			}
			return new Order(new Product(p.name, p.price, p.description), nrPieces, new Customer(id, yField.getText()));
		}
		return null;
	}

	public void removeAllRows() {
		int n = tableModel.getRowCount();
		for (int i = n - 1; i >= 0; i--) {
			tableModel.removeRow(i);// ???
		}
	}

	public void removeRowFromTable(int i) {
		tableModel.removeRow(i);
	}
}