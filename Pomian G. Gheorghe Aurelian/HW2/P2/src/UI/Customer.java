package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import Item.Order;
import Item.Product;
import data.Accounts;
import data.OPDept;
import data.Warehouse;

public class Customer {

	public JFrame frame;
	private JTextField amount;
	private JLabel Name = new JLabel("name");
	private JTextField search;
	private Warehouse warehouse = new Warehouse();
	private OPDept orders = new OPDept();
	private Order order = new Order();
	private Product product = new Product(0);
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private DefaultListModel<String> listModel2 = new DefaultListModel<String>();
	private JList<String> stockList = new JList<String>();
	private JList<String> orderList = new JList<String>();
	private String text;
	private JTextField total;
	private int index = 0;
	private boolean isFinal = true;
	private History history = new History();

	public void setName(String name) {
		Name.setText(name);
	}

	public void initializeStock(String text) {
		if (isFinal)
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

	public void initializeOrder() {
		String[] S = new String[50];
		S = order.getDisplayProducts();
		int i = 0;

		listModel2.clear();

		while (S[i] != null) {
			listModel2.addElement(S[i]);
			i++;
		}
	}
	
	private void setTotal() {
		total.setText("" + order.getTotal());
	}

	/**
	 * Create the application.
	 */
	public Customer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 310);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("Logged in as");
		label.setForeground(new Color(0, 100, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 11, 93, 14);
		frame.getContentPane().add(label);
		Name.setFont(new Font("Tahoma", Font.BOLD, 11));
		Name.setForeground(new Color(0, 255, 0));

		Name.setBounds(105, 11, 57, 14);
		frame.getContentPane().add(Name);

		JButton button = new JButton("Log out");
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setForeground(new Color(0, 255, 0));
		button.setBackground(new Color(0, 100, 0));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StateManager.setState(State.MAINMENU);
				StateManager.update();
			}
		});
		button.setBounds(172, 7, 77, 23);
		frame.getContentPane().add(button);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setForeground(new Color(240, 230, 140));
		btnAdd.setBackground(new Color(139, 69, 19));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = stockList.getSelectedIndex();
				product = warehouse.getProduct(index);
				text = amount.getText();
				if (Integer.parseInt(text) > product.getAmount())
					Accounts.infoBox("Not enought in stock!", "Error!");
				else {
					isFinal = false;

					product.setAmount(product.getAmount() - Integer.parseInt(text));
					initializeStock("");

					order.addProduct(product, Integer.parseInt(text));
					initializeOrder();
					setTotal();
				}
			}
		});
		btnAdd.setBounds(172, 162, 75, 23);
		frame.getContentPane().add(btnAdd);

		amount = new JTextField();
		amount.setForeground(new Color(240, 230, 140));
		amount.setBackground(new Color(139, 69, 19));
		amount.setToolTipText("Amount");
		amount.setBounds(257, 163, 35, 23);
		frame.getContentPane().add(amount);
		amount.setColumns(10);
		amount.setText("1");

		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isFinal = true;
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				
				order.setDate(dateFormat.format(date));
				order.setName(Name.getText());
				
				orders.add(order);
				orders.updateOrders();
				warehouse.updateProducts();
				
				order = new Order();
				
				initializeStock("");
				initializeOrder();
				try {
				    Thread.sleep(300);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
		});
		btnPlaceOrder.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPlaceOrder.setForeground(new Color(127, 255, 212));
		btnPlaceOrder.setBackground(new Color(0, 139, 139));
		btnPlaceOrder.setBounds(172, 239, 110, 23);
		frame.getContentPane().add(btnPlaceOrder);

		JTextPane txtpnNamePrice_1 = new JTextPane();
		txtpnNamePrice_1.setForeground(new Color(127, 255, 212));
		txtpnNamePrice_1.setBackground(new Color(0, 139, 139));
		txtpnNamePrice_1.setText("Name / Price / Amount");
		txtpnNamePrice_1.setEditable(false);
		txtpnNamePrice_1.setBounds(10, 92, 153, 19);
		frame.getContentPane().add(txtpnNamePrice_1);

		stockList.setModel(listModel);
		stockList.setBounds(302, 55, 152, 207);
		frame.getContentPane().add(stockList);

		orderList.setModel(listModel2);
		orderList.setBounds(10, 114, 152, 148);
		frame.getContentPane().add(orderList);

		JLabel lblOrder = new JLabel("Order");
		lblOrder.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrder.setForeground(new Color(0, 128, 128));
		lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrder.setBounds(10, 70, 143, 14);
		frame.getContentPane().add(lblOrder);

		JTextPane txtpnNamePrice = new JTextPane();
		txtpnNamePrice.setForeground(new Color(240, 230, 140));
		txtpnNamePrice.setBackground(new Color(139, 69, 19));
		txtpnNamePrice.setText("Name / Price / Amount");
		txtpnNamePrice.setEditable(false);
		txtpnNamePrice.setBounds(302, 33, 152, 19);
		frame.getContentPane().add(txtpnNamePrice);

		JLabel label_3 = new JLabel("Stock");
		label_3.setForeground(new Color(139, 69, 19));
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(302, 11, 152, 14);
		frame.getContentPane().add(label_3);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = orderList.getSelectedIndex();
				Product[] products = order.getProducts();

				int auxindex = warehouse.getProductIndex(products[index]);
				warehouse.changeAmount(auxindex, products[index].getAmount());

				order.removeProduct(index);

				initializeStock("");
				initializeOrder();
				setTotal();
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemove.setForeground(new Color(127, 255, 212));
		btnRemove.setBackground(new Color(0, 139, 139));
		btnRemove.setBounds(172, 196, 110, 23);
		frame.getContentPane().add(btnRemove);

		JButton btnOrderHistory = new JButton("Order History");
		btnOrderHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				history.setName(Name.getText());
				history.show();
			}
		});
		btnOrderHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOrderHistory.setForeground(new Color(127, 255, 212));
		btnOrderHistory.setBackground(new Color(0, 139, 139));
		btnOrderHistory.setBounds(10, 36, 125, 23);
		frame.getContentPane().add(btnOrderHistory);

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
		btnSearch.setBounds(215, 95, 77, 23);
		frame.getContentPane().add(btnSearch);

		search = new JTextField();
		search.setForeground(new Color(240, 230, 140));
		search.setBackground(new Color(139, 69, 19));
		search.setBounds(172, 67, 120, 20);
		frame.getContentPane().add(search);
		search.setColumns(10);
		orderList.setForeground(new Color(127, 255, 212));
		orderList.setBackground(new Color(0, 139, 139));

		JScrollPane orderPane = new JScrollPane(orderList);
		orderPane.setBounds(10, 114, 153, 110);
		frame.getContentPane().add(orderPane);
		stockList.setForeground(new Color(240, 230, 140));
		stockList.setBackground(new Color(139, 69, 19));

		JScrollPane stockPane = new JScrollPane(stockList);
		stockPane.setBounds(302, 55, 152, 210);
		frame.getContentPane().add(stockPane);

		total = new JTextField();
		total.setForeground(new Color(127, 255, 212));
		total.setBackground(new Color(0, 139, 139));
		total.setEditable(false);
		total.setBounds(55, 239, 86, 22);
		frame.getContentPane().add(total);
		total.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setForeground(new Color(0, 128, 128));
		lblTotal.setBounds(20, 239, 46, 22);
		frame.getContentPane().add(lblTotal);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(167, 60, 297, 131);
		frame.getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(292, 0, 172, 272);
		frame.getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(135, 206, 250));
		panel_2.setBounds(0, 34, 167, 238);
		frame.getContentPane().add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(135, 206, 250));
		panel_3.setBounds(83, 187, 209, 85);
		frame.getContentPane().add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(60, 179, 113));
		panel_4.setBounds(0, 0, 304, 69);
		frame.getContentPane().add(panel_4);
	}
}
