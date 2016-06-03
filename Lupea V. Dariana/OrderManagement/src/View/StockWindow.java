package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Comparators.ProductComparator;
import Model.Product;
import Model.Warehouse;
import Serializing.ReadFileS;
import Serializing.WriteFileS;

public class StockWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnAdd, btnDelete, btnUpdate;
	private JButton refreshStock, searchProduct, seeStock;
	private JTextField textId, textName, textQuantity, textPrice;
	private JTable table;
	private DefaultTableModel model;
	Warehouse w;
	TreeSet<Product> products;

	public StockWindow() {

		super("Warehouse");

		w = new Warehouse();
		products = new TreeSet<Product>(new ProductComparator());

		table = new JTable();

		Object[] columns = { "Id", "Name", "Quantity", "Price" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setBackground(Color.lightGray);
		table.setForeground(Color.GRAY);
		table.setRowHeight(30);

		btnAdd = new JButton("Add");
		btnDelete = new JButton("Delete");
		btnUpdate = new JButton("Update");

		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		// btnUpdate.addActionListener(this);

		textId = new JTextField();
		textName = new JTextField();
		textQuantity = new JTextField();
		textPrice = new JTextField();

		btnAdd.setBounds(150, 220, 100, 25);
		btnUpdate.setBounds(150, 260, 100, 25);
		btnDelete.setBounds(150, 300, 100, 25);

		textId.setBounds(20, 220, 100, 25);
		textName.setBounds(20, 250, 100, 25);
		textQuantity.setBounds(20, 280, 100, 25);
		textPrice.setBounds(20, 310, 100, 25);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 200);

		refreshStock = new JButton("Refresh stock");
		refreshStock.addActionListener(this);
		refreshStock.setBounds(400, 300, 200, 25);

		searchProduct = new JButton("Search product");
		searchProduct.addActionListener(this);
		searchProduct.setBounds(400, 250, 200, 25);

		seeStock = new JButton("See stock");
		seeStock.addActionListener(this);
		seeStock.setBounds(400, 200, 200, 25);

		setLayout(null);
		add(pane);

		add(textId);
		add(textName);
		add(textQuantity);
		add(textPrice);

		add(btnAdd);
		add(btnUpdate);
		add(btnDelete);
		add(refreshStock);
		add(searchProduct);
		add(seeStock);

		setVisible(true);
		setSize(900, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent event) {
				int i = table.getSelectedRow();
				textId.setText(model.getValueAt(i, 0).toString());
				textName.setText(model.getValueAt(i, 1).toString());
				textQuantity.setText(model.getValueAt(i, 2).toString());
				textPrice.setText(model.getValueAt(i, 3).toString());
			}
		});

		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int j = table.getSelectedRow();

				if (j >= 0) {
					model.setValueAt(textId.getText(), j, 0);
					model.setValueAt(textName.getText(), j, 1);
					model.setValueAt(textQuantity.getText(), j, 2);
					model.setValueAt(textPrice.getText(), j, 3);
					Product newProduct = getInput();
					w.addProduct(newProduct);
					refresh();
				} else {
					System.out.println("Update error!");
				}
			}

		});

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == btnAdd) {
			changeRows();
			Product newProduct = getInput();
			w.addProduct(newProduct);
			refresh();
		}
		if (event.getSource() == btnDelete) {
			int i = table.getSelectedRow();
			if (i >= 0) {
				Product newProduct = getInput();
				w.removeProduct(newProduct);
				model.removeRow(i);
				refresh();
			} else {
				System.out.println("Delete error");
			}

		}
		if (event.getSource() == refreshStock) {
			WriteFileS s = new WriteFileS(w);
			JOptionPane.showMessageDialog(this, "The changes on the stock were saved...");

		}

		if (event.getSource() == seeStock) {
			ReadFileS read = new ReadFileS();
			Warehouse stock = new Warehouse();
			stock = read.ReadFile();
			Iterator<Product> iterator = stock.checkProducts();
			Object[] row = new Object[4];

			while (iterator.hasNext()) {
				Product p = iterator.next();
				String id = p.getId().toString();
				String quantity = p.getQuantity().toString();
				String price = p.getPrice().toString();

				textId.setText(id);
				textName.setText(p.getName());
				textQuantity.setText(quantity);
				textPrice.setText(price);
				changeRows();
				refresh();

			}
		}
		if (event.getSource() == searchProduct) {
			ReadFileS read = new ReadFileS();
			Warehouse stock = new Warehouse();
			stock = read.ReadFile();
			String product = JOptionPane.showInputDialog(this, "Please enter the name of the product:");
			Iterator<Product> iterator = stock.checkProducts();
			boolean ok = false;
			int prod = 0;
			while (iterator.hasNext()) {
				Product p = iterator.next();
				if (p.getName().equals(product)) {
					ok = true;
					prod = p.getQuantity();

				}
			}
			if (ok == true) {
				JOptionPane.showMessageDialog(null, prod + " " + product + " in stock");
				System.out.println(prod + " " + product + " in stock");
			} else {
				System.out.println("Sorry! This product is not available!");
				JOptionPane.showMessageDialog(null, "Sorry! This product is not available!");

			}
		}
	}

	public void changeRows() {

		Object[] row = new Object[4];
		row[0] = textId.getText();
		row[1] = textName.getText();
		row[2] = textQuantity.getText();
		row[3] = textPrice.getText();
		model.addRow(row);
	}

	public void refresh() {
		textId.setText(" ");
		textName.setText(" ");
		textQuantity.setText(" ");
		textPrice.setText(" ");

	}

	public Product getInput() {
		String name = (textName.getText()).trim();
		String id = (textId.getText()).trim();
		int newId = Integer.parseInt(id);
		String quantity = (textQuantity.getText()).trim();
		int newQuantity = Integer.parseInt(quantity);
		String price = (textPrice.getText()).trim();
		int newPrice = Integer.parseInt(price);
		Product p = new Product(name, newId, newQuantity, newPrice);
		return p;
	}

}
