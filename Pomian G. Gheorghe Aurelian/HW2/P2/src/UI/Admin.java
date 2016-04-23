package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import Helpers.StateManager;
import Helpers.StateManager.State;
import Item.Product;
import data.Accounts;
import data.OPDept;
import data.Warehouse;

public class Admin {
	public JFrame frame;
	private JTextField addItem;
	private JTextField addAmount;
	private JTextField amount;
	private JLabel Name = new JLabel("name");
	private JList<String> stock = new JList<String>();
	private JList<String> orders = new JList<String>();
	private Product product = new Product(0);
	private OPDept orderss = new OPDept();
	private Warehouse warehouse = new Warehouse();
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private DefaultListModel<String> listModel2 = new DefaultListModel<String>();
	private int index;
	private JTextField price;
	private JTextField addPrice;
	private String text = "";
	private JTextField search;

	public void setName(String name) {
		Name.setText(name);
	}

	public void initializeStock(String text) {
		warehouse.getProducts();
		String[] S = new String[50];
		S = warehouse.getDisplayProducts();
		int i = 0;

		listModel.clear();

		if (Objects.equals(text, ""))
			while (S[i] != null) {
				listModel.addElement(S[i]);
				i++;
			}
		else
			while (S[i] != null) {
				if (S[i].contains(text))
					listModel.addElement(S[i]);
				i++;
			}
	}

	public void initializeOrders() {
		orderss.getOrders();
		String[] S = new String[50];
		S = orderss.getStringOrders();
		int i = 0;
		listModel2.clear();

		while (S[i] != null) {
			listModel2.addElement(S[i]);
			i++;
		}
	}

	/**
	 * Create the application.
	 */
	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 650, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLoggedInAs = new JLabel("Logged in as:");
		lblLoggedInAs.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoggedInAs.setForeground(new Color(0, 191, 255));
		lblLoggedInAs.setBounds(10, 15, 93, 14);
		frame.getContentPane().add(lblLoggedInAs);
		Name.setFont(new Font("Tahoma", Font.BOLD, 11));
		Name.setForeground(new Color(0, 250, 154));

		Name.setBounds(113, 15, 64, 14);
		frame.getContentPane().add(Name);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdmin.setForeground(new Color(0, 191, 255));
		lblAdmin.setBounds(479, 15, 46, 14);
		frame.getContentPane().add(lblAdmin);

		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setForeground(new Color(25, 25, 112));
		btnLogOut.setBackground(new Color(95, 158, 160));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StateManager.setState(State.MAINMENU);
				StateManager.update();
			}
		});
		btnLogOut.setBounds(535, 8, 77, 23);
		frame.getContentPane().add(btnLogOut);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(new Color(139, 69, 19));
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setBounds(259, 11, 173, 14);
		frame.getContentPane().add(lblStock);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setForeground(new Color(240, 230, 140));
		btnAdd.setBackground(new Color(139, 69, 19));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				product = new Product(Float.parseFloat(addPrice.getText()));
				product.setName(addItem.getText());
				product.setAmount(Integer.parseInt(addAmount.getText()));
				warehouse.updateProduct(product);
				warehouse.updateProducts();
				initializeStock("");
				try {
				    Thread.sleep(100);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
		});
		btnAdd.setBounds(445, 145, 64, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemove.setForeground(new Color(240, 230, 140));
		btnRemove.setBackground(new Color(139, 69, 19));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = stock.getSelectedIndex();
				warehouse.removeProduct(index);
				warehouse.updateProducts();
				initializeStock("");
				try {
				    Thread.sleep(300);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}

			}
		});
		btnRemove.setBounds(519, 145, 93, 23);
		frame.getContentPane().add(btnRemove);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfirm.setForeground(new Color(127, 255, 212));
		btnConfirm.setBackground(new Color(0, 139, 139));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
 				try {
				    Thread.sleep(300);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				orderss.confirmOrder(orders.getSelectedIndex());
				orderss.updateOrders();
				if(orderss.lenght() == 0)
					orderss = new OPDept();
 				initializeOrders();
 				try {
				    Thread.sleep(300);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
		});
		btnConfirm.setBounds(10, 214, 81, 23);
		frame.getContentPane().add(btnConfirm);

		JButton btnReject = new JButton("Reject");
		btnReject.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReject.setForeground(new Color(127, 255, 212));
		btnReject.setBackground(new Color(0, 139, 139));
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				orderss.remove(orders.getSelectedIndex());
				orderss.updateOrders();
				initializeOrders();
			}
		});
		btnReject.setBounds(10, 245, 81, 23);
		frame.getContentPane().add(btnReject);

		addItem = new JTextField();
		addItem.setForeground(new Color(240, 230, 140));
		addItem.setBackground(new Color(139, 69, 19));
		addItem.setBounds(445, 114, 46, 20);
		frame.getContentPane().add(addItem);
		addItem.setColumns(10);

		addAmount = new JTextField();
		addAmount.setForeground(new Color(240, 230, 140));
		addAmount.setBackground(new Color(139, 69, 19));
		addAmount.setColumns(10);
		addAmount.setBounds(505, 114, 46, 20);
		frame.getContentPane().add(addAmount);

		JLabel lblName = new JLabel("Item");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setForeground(new Color(139, 69, 19));
		lblName.setBounds(445, 95, 46, 14);
		frame.getContentPane().add(lblName);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAmount.setForeground(new Color(139, 69, 19));
		lblAmount.setBounds(505, 95, 46, 14);
		frame.getContentPane().add(lblAmount);

		stock.setModel(listModel);
		stock.setBounds(0, 0, 16, 105);
		frame.getContentPane().add(stock);

		orders.setModel(listModel2);
		orders.setBounds(0, 14, 169, 74);
		frame.getContentPane().add(orders);

		JLabel lblOrders = new JLabel("Orders");
		lblOrders.setForeground(new Color(0, 128, 128));
		lblOrders.setFont(new Font("Dialog", Font.BOLD, 12));
		lblOrders.setBounds(160, 40, 46, 20);
		frame.getContentPane().add(lblOrders);

		JTextPane txtpnItem = new JTextPane();
		txtpnItem.setForeground(new Color(240, 230, 140));
		txtpnItem.setBackground(new Color(139, 69, 19));
		txtpnItem.setEditable(false);
		txtpnItem.setText("Name / Price / Amount");
		txtpnItem.setBounds(280, 36, 152, 19);
		frame.getContentPane().add(txtpnItem);

		JTextPane txtpnInStock = new JTextPane();
		txtpnInStock.setForeground(new Color(127, 255, 212));
		txtpnInStock.setBackground(new Color(0, 139, 139));
		txtpnInStock.setEditable(false);
		txtpnInStock.setText("Date - User");
		txtpnInStock.setBounds(101, 70, 165, 20);
		frame.getContentPane().add(txtpnInStock);

		amount = new JTextField();
		amount.setForeground(new Color(240, 230, 140));
		amount.setBackground(new Color(139, 69, 19));
		amount.setText("1");
		amount.setHorizontalAlignment(SwingConstants.CENTER);
		amount.setBounds(511, 240, 50, 20);
		frame.getContentPane().add(amount);
		amount.setColumns(10);

		JButton btnNewButton = new JButton("+");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setForeground(new Color(240, 230, 140));
		btnNewButton.setBackground(new Color(139, 69, 19));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				product.setAmount(Integer.parseInt(amount.getText()));
				index = stock.getSelectedIndex();
				warehouse.changeAmount(index, product.getAmount());
				warehouse.updateProducts();
				initializeStock("");
				stock.setSelectedIndex(index);
			}
		});
		btnNewButton.setBounds(460, 239, 46, 23);
		frame.getContentPane().add(btnNewButton);

		JButton button = new JButton("-");
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setForeground(new Color(240, 230, 140));
		button.setBackground(new Color(139, 69, 19));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				product.setAmount(Integer.parseInt(amount.getText()) * (-1));
				index = stock.getSelectedIndex();
				warehouse.changeAmount(index, product.getAmount());
				warehouse.updateProducts();
				initializeStock("");
				stock.setSelectedIndex(index);
			}
		});
		button.setBounds(566, 239, 46, 23);
		frame.getContentPane().add(button);
		orders.setForeground(new Color(127, 255, 212));
		orders.setBackground(new Color(0, 139, 139));

		JScrollPane orderPane = new JScrollPane(orders);
		orderPane.setBounds(101, 101, 169, 166);
		frame.getContentPane().add(orderPane);
		stock.setForeground(new Color(240, 230, 140));
		stock.setBackground(new Color(139, 69, 19));

		JScrollPane stockPane = new JScrollPane(stock);
		stockPane.setBounds(280, 59, 152, 210);
		frame.getContentPane().add(stockPane);

		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accounts.infoBox(orderss.getOrder(orders.getSelectedIndex()).info(), orderss.getOrder(orders.getSelectedIndex()).display());
			}
		});
		btnView.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnView.setForeground(new Color(127, 255, 212));
		btnView.setBackground(new Color(0, 139, 139));
		btnView.setBounds(10, 105, 81, 23);
		frame.getContentPane().add(btnView);

		JButton btnChangePrice = new JButton("Change price");
		btnChangePrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChangePrice.setForeground(new Color(240, 230, 140));
		btnChangePrice.setBackground(new Color(139, 69, 19));
		btnChangePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float newPrice = (Float.parseFloat(price.getText()));
				index = stock.getSelectedIndex();
				product = warehouse.getProduct(index);
				product.setPrice(newPrice);
				warehouse.updateProduct(product);
				warehouse.updateProducts();
				initializeStock("");
				stock.setSelectedIndex(index);
			}
		});
		btnChangePrice.setBounds(460, 179, 152, 23);
		frame.getContentPane().add(btnChangePrice);

		price = new JTextField();
		price.setForeground(new Color(240, 230, 140));
		price.setBackground(new Color(139, 69, 19));
		price.setBounds(460, 212, 86, 20);
		frame.getContentPane().add(price);
		price.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setForeground(new Color(139, 69, 19));
		lblPrice.setBounds(566, 95, 46, 14);
		frame.getContentPane().add(lblPrice);

		addPrice = new JTextField();
		addPrice.setForeground(new Color(240, 230, 140));
		addPrice.setBackground(new Color(139, 69, 19));
		addPrice.setColumns(10);
		addPrice.setBounds(566, 114, 46, 20);
		frame.getContentPane().add(addPrice);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setForeground(new Color(240, 230, 140));
		btnSearch.setBackground(new Color(139, 69, 19));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = search.getText();
				initializeStock(text);
			}
		});
		btnSearch.setBounds(487, 68, 89, 23);
		frame.getContentPane().add(btnSearch);

		search = new JTextField();
		search.setForeground(new Color(240, 230, 140));
		search.setBackground(new Color(139, 69, 19));
		search.setBounds(445, 40, 167, 20);
		frame.getContentPane().add(search);
		search.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 33, 273, 259);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(273, 33, 376, 259);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 140, 0));
		panel_2.setBounds(273, 0, 162, 84);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(47, 79, 79));
		panel_3.setBounds(0, 0, 680, 36);
		frame.getContentPane().add(panel_3);
	}
}
