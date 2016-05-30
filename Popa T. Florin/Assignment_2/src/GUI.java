import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import panels.Panel1;
import panels.Panel2;
import panels.Panel3;
import panels.Panel4;
import panels.Panel5;

public class GUI {

	Warehouse warehouse = new Warehouse();
	OPDept orderProcessing = new OPDept();

	JFrame frame;
	JPanel panel;
	CardLayout card;

	JTable table;
	JTable table1;

	private int admin;
	private int orderIndex;

	public GUI() {

		initialize();

	}

	public void initialize() {

		frame = new JFrame();
		frame.setSize(640, 480);

		card = new CardLayout();
		panel = new JPanel();
		panel.setLayout(card);

		Panel1 p1 = new Panel1();
		Panel2 p2 = new Panel2();
		Panel3 p3 = new Panel3();
		Panel4 p4 = new Panel4();
		Panel5 p5 = new Panel5();

		panel.add(p1, "Login");
		panel.add(p2, "User");
		panel.add(p3, "Admin");
		panel.add(p4, "Order");
		panel.add(p5, "History");

		// THE ORDER TABLE
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.setBounds(165, 87, 100, 100);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(40, 55, 558, 241);
		model.addColumn("Name");
		model.addColumn("Price");
		model.addColumn("Quantity");

		// THE HISTORY TABLE
		DefaultTableModel model1 = new DefaultTableModel();
		table1 = new JTable(model1);
		table1.setBounds(165, 87, 100, 100);
		JScrollPane scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(40, 55, 558, 241);
		model1.addColumn("Product");
		model1.addColumn("Customer");
		model1.addColumn("Quantity");

		p5.add(scrollPane1);
		frame.getContentPane().add(panel);
		
		//BUTTON EVENTS
		p1.login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				String usr = p1.userField.getText();
				String pass = new String(p1.passField.getPassword());
				Properties config = new Properties();
				InputStream file;

				try {
					file = new FileInputStream("res/data.properties");
					config.load(file);

					if (usr.toString().equals("admin")) {

						String adminPassword = config.getProperty("username." + usr.toString());
						if (adminPassword.equals(pass.toString())) {
							p3.add(scrollPane);
							admin = 1;
							card.show(panel, "Admin");
							p1.error.setVisible(false);
							p1.passField.setText(null);
							p1.userField.setText(null);
						} else {
							p1.error.setVisible(true);
							p1.passField.setText(null);
							p1.userField.setText(null);
						}
					} else {
						String password = config.getProperty("username." + usr.toString());
						if (password != null && password.equals(pass.toString())) {
							p2.add(scrollPane);
							admin = 0;
							card.show(panel, "User");
							p1.error.setVisible(false);
							p1.passField.setText(null);
							p1.userField.setText(null);
						} else {
							p1.error.setVisible(true);
							p1.passField.setText(null);
							p1.userField.setText(null);
						}
					}

					file.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		p3.adminLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				card.show(panel, "Login");
				p3.remove(scrollPane);
			}
		});

		p3.btnAddProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				String productName = p3.textField.getText();
				String productPrice = p3.productPriceField.getText();
				String quantity = p3.quantField.getText();

				Product p = new Product(productName, Double.parseDouble(productPrice), Integer.parseInt(quantity));
				warehouse.addProduct(p);
				model.addRow(new Object[] { p.getName(), p.getPrice(), p.getQuantity() });
			}
		});

		p3.btnRemoveProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				int index = table.getSelectedRow();
				String name = table.getValueAt(index, 0).toString();
				model.removeRow(index);
				Iterator<Product> itProduct = warehouse.iterate();

				while (itProduct.hasNext()) {
					Product p = itProduct.next();
					if (p.getName().equals(name)) {
						warehouse.removeProduct(p);
						break;
					}
				}
			}
		});

		p3.btnModifyProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				int index = table.getSelectedRow();

				String name = table.getValueAt(index, 0).toString();

				String quantity = p3.quantModifyField.getText();

				Iterator<Product> itProduct = warehouse.iterate();

				while (itProduct.hasNext()) {
					Product p = itProduct.next();
					if (p.getName().equals(name)) {

						p.setQuantity(Integer.parseInt(quantity));
						model.setValueAt(p.getQuantity(), index, 2);
						break;
					}
				}
			}
		});

		p2.userLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				card.show(panel, "Login");
				p2.remove(scrollPane);
			}
		});

		p2.btnOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				int index = table.getSelectedRow();
				if (index != -1) {
					String name = table.getValueAt(index, 0).toString();
					p4.productField.setText(name);
					orderIndex = index;
					card.show(panel, "Order");
				}

			}
		});

		p4.btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				card.show(panel, "User");
				p4.lblOrderSuccessfullyPlaced.setVisible(false);
			}
		});

		p4.btnPlaceOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (orderIndex != -1) {

					String prodName = table.getValueAt(orderIndex, 0).toString();
					String name = p4.nameField.getText();
					String quantity = p4.quantityField.getText();
					Customer c = new Customer(name);

					Iterator<Product> itProduct = warehouse.iterate();

					while (itProduct.hasNext()) {
						Product p = itProduct.next();
						if (p.getName().equals(prodName)) {

							Order o = new Order(p, c, Integer.parseInt(quantity));
							orderProcessing.addOrder(o);
							model1.addRow(new Object[] { o.getProduct().getName(), o.getCustomer().getName(),
									o.getQuantity() });
							p4.lblOrderSuccessfullyPlaced.setVisible(true);
							break;
						}
					}
				}
			}
		});

		p3.adminOrderHistory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				card.show(panel, "History");

			}
		});

		p2.userOrderHistory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				card.show(panel, "History");

			}
		});

		p5.back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (admin == 0) {
					card.show(panel, "User");
				} else if (admin == 1) {
					card.show(panel, "Admin");
				}
			}
		});
		
		p2.btnFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				double left = Double.parseDouble(p2.priceLeft.getText());
				double right = Double.parseDouble(p2.priceRight.getText());
				
				for(int i=0;i<warehouse.getSize();i++){
					System.out.println("SIZE: " + warehouse.getSize()+"\n");
					double value = Double.parseDouble(table.getValueAt(i, 1).toString());
					System.out.println(value+"\n");
					if(value < left || value>right){
						model.removeRow(i);
						i-=1;
					}
				}
			}
		});
		
		p2.btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				int rowCount = model.getRowCount();
				for(int i=rowCount-1;i>=0;i--){
					model.removeRow(i);
				}
				
				Iterator<Product> itProduct = warehouse.iterate();
				
				while(itProduct.hasNext()){
					Product p = itProduct.next();
					model.addRow(new Object[] { p.getName(), p.getPrice(), p.getQuantity() });
				}
				
			}
		});
	}
}
