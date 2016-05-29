package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Comparators.OrderComparator;
import Model.OPD;
import Model.Order;
import Model.Product;
import Model.Warehouse;
import Serializing.ReadFileS;
import Serializing.WriteFileS;

/**
 * 
 * @author Dariana Lupea
 * This class represents the GUI for the "shop".
 *
 */
public class ShopWindow extends JFrame implements ActionListener {

	private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
	private JButton but1, but2, but3, but4, but5, but6, but7, but8;
	private JTextField field1, field2, field3, field4, field5, field6, field7, field8;
	private JButton shoppingBag, placeOrder, newOrder, seeOrderHistory;
	private int rasp, banana, orange, apple, kiwi, strawberry, peach, pome;
	Warehouse wh;
	OPD opd;
	ReadFileS readWarehouse;
	Warehouse warehouse;
	TreeSet<Order> orders;
	private static final long serialVersionUID = 1L;
	int orderId = 0;

	public ShopWindow() {
		super("*******Dari's SHOP*******");
		getContentPane().setLayout(null);

		///// Initialize and set labels////
		label1 = new JLabel();
		label1.setText("Raspberry");
		label1.setBounds(30, 20, 60, 20);

		label2 = new JLabel();
		label2.setText("Apple");
		label2.setBounds(180, 20, 60, 20);

		label3 = new JLabel();
		label3.setText("Orange");
		label3.setBounds(330, 20, 60, 20);

		label4 = new JLabel();
		label4.setText("Banana");
		label4.setBounds(480, 20, 60, 20);

		label5 = new JLabel();
		label5.setText("Kiwi");
		label5.setBounds(40, 200, 60, 20);

		label6 = new JLabel();
		label6.setText("Strawberry");
		label6.setBounds(175, 200, 70, 20);

		label7 = new JLabel();
		label7.setText("Peach");
		label7.setBounds(325, 200, 70, 20);

		label8 = new JLabel();
		label8.setText("Pomegranate");
		label8.setBounds(460, 200, 80, 20);

		add(label1);
		add(label2);
		add(label3);
		add(label4);
		add(label5);
		add(label6);
		add(label7);
		add(label8);

		///// Put images on buttons and set them/////
		ImageIcon img1 = new ImageIcon("Images/berry.jpg");
		ImageIcon newImg1 = resizeImageForButton(img1);
		but1 = new JButton(newImg1);

		ImageIcon img2 = new ImageIcon("Images/apple.jpg");
		ImageIcon newImg2 = resizeImageForButton(img2);
		but2 = new JButton(newImg2);

		ImageIcon img3 = new ImageIcon("Images/orange.jpg");
		ImageIcon newImg3 = resizeImageForButton(img3);
		but3 = new JButton(newImg3);

		ImageIcon img4 = new ImageIcon("Images/banana.jpg");
		ImageIcon newImg4 = resizeImageForButton(img4);
		but4 = new JButton(newImg4);

		ImageIcon img5 = new ImageIcon("Images/kiwi.jpg");
		ImageIcon newImg5 = resizeImageForButton(img5);
		but5 = new JButton(newImg5);

		ImageIcon img6 = new ImageIcon("Images/strawberry.jpg");
		ImageIcon newImg6 = resizeImageForButton(img6);
		but6 = new JButton(newImg6);

		ImageIcon img7 = new ImageIcon("Images/peach.jpg");
		ImageIcon newImg7 = resizeImageForButton(img7);
		but7 = new JButton(newImg7);

		ImageIcon img8 = new ImageIcon("Images/pome.jpg");
		ImageIcon newImg8 = resizeImageForButton(img8);
		but8 = new JButton(newImg8);

		but1.setBounds(15, 50, 100, 100);
		but2.setBounds(160, 50, 100, 100);
		but3.setBounds(305, 50, 100, 100);
		but4.setBounds(450, 50, 100, 100);
		but5.setBounds(15, 230, 100, 100);
		but6.setBounds(160, 230, 100, 100);
		but7.setBounds(305, 230, 100, 100);
		but8.setBounds(450, 230, 100, 100);

		add(but1);
		add(but2);
		add(but3);
		add(but4);
		add(but5);
		add(but6);
		add(but7);
		add(but8);

		//// Create JTextFields and set them////
		field1 = new JTextField("0");
		field1.setBounds(55, 150, 20, 20);
		field2 = new JTextField("0");
		field2.setBounds(200, 150, 20, 20);
		field3 = new JTextField("0");
		field3.setBounds(345, 150, 20, 20);
		field4 = new JTextField("0");
		field4.setBounds(495, 150, 20, 20);
		field5 = new JTextField("0");
		field5.setBounds(55, 330, 20, 20);
		field6 = new JTextField("0");
		field6.setBounds(200, 330, 20, 20);
		field7 = new JTextField("0");
		field7.setBounds(345, 330, 20, 20);
		field8 = new JTextField("0");
		field8.setBounds(495, 330, 20, 20);

		add(field1);
		add(field2);
		add(field3);
		add(field4);
		add(field5);
		add(field6);
		add(field7);
		add(field8);

		//// Create other useful JButtons and set them////
		shoppingBag = new JButton("Shopping bag");
		shoppingBag.setBounds(600, 50, 120, 30);
		shoppingBag.addActionListener(this);
		add(shoppingBag);

		placeOrder = new JButton("Place order");
		placeOrder.setBounds(600, 100, 120, 30);
		placeOrder.addActionListener(this);
		add(placeOrder);

		seeOrderHistory = new JButton("See order history");
		seeOrderHistory.addActionListener(this);
		seeOrderHistory.setBounds(600, 150, 135, 30);
		add(seeOrderHistory);

		newOrder = new JButton("New Order");
		newOrder.addActionListener(this);
		newOrder.setBounds(600, 200, 135, 30);
		add(newOrder);

		////////////////////////////////
		readWarehouse = new ReadFileS();
		warehouse = readWarehouse.ReadFile();// read the products from stock
		wh = new Warehouse();// used to change the stock's state
		opd = new OPD();// used to save all the orders
		orders = new TreeSet<Order>(new OrderComparator());
		opd.setOrder(orders);

		/////// Frame details///////
		setVisible(true);
		setSize(900, 430);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	/*
	 * Method used to resize the image to be put on the button
	 */
	public ImageIcon resizeImageForButton(ImageIcon image) {
		Image takeImage = image.getImage();
		Image resizedImg = takeImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(resizedImg);
		return newImage;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		//////// quantities introduced by user for each product///////
		rasp = Integer.parseInt(field1.getText().trim());
		apple = Integer.parseInt(field2.getText().trim());
		orange = Integer.parseInt(field3.getText().trim());
		banana = Integer.parseInt(field4.getText().trim());
		kiwi = Integer.parseInt(field5.getText().trim());
		strawberry = Integer.parseInt(field6.getText().trim());
		peach = Integer.parseInt(field7.getText().trim());
		pome = Integer.parseInt(field8.getText().trim());

		///////// see what is in the order-list///////////
		if (event.getSource() == shoppingBag) {

			Integer[] quantity = new Integer[8];
			quantity[0] = rasp;
			quantity[1] = apple;
			quantity[2] = orange;
			quantity[3] = banana;
			quantity[4] = kiwi;
			quantity[5] = strawberry;
			quantity[6] = peach;
			quantity[7] = pome;

			ArrayList<String> fruits = new ArrayList<String>();
			fruits.add("Raspberry:  ");
			fruits.add("Apple:          ");
			fruits.add("Orange:        ");
			fruits.add("Banana:        ");
			fruits.add("Kiwi:             ");
			fruits.add("Strawberry:  ");
			fruits.add("Peach:          ");
			fruits.add("Pome:           ");

			String output = "";
			for (int i = 0; i < fruits.size(); i++) {
				String everything = fruits.get(i).toString();
				String everything1 = quantity[i].toString();
				output += everything + " " + everything1 + "\n";
			}
			JOptionPane.showMessageDialog(this, output, "Your order is", JOptionPane.PLAIN_MESSAGE);
		}

		//// Place an order

		if (event.getSource() == placeOrder) {
			orderId++;
			Set<Product> orderedProducts = placeAnOrder();

			for (Product p : orderedProducts) {
				System.out.println("ordered products:");
				System.out.println(p.getName());
				System.out.println(p.getQuantity());
				System.out.println("\n");
				/////////////////////////////
				Order newOrder = new Order(orderId, orderedProducts.size(), getCurrentTime(), "Pending...", null);
				newOrder.setOrderedProducts(orderedProducts);
				opd.addOrder(newOrder);
				WriteFileS writeOPD = new WriteFileS(opd);
				System.out.println(newOrder.getOrderDate());
				System.out.println(newOrder.getNoOfProductsContained());
				System.out.println(newOrder.getOrderId());
			}

		}
		if (event.getSource() == newOrder) {
			field1.setText("0");
			field2.setText("0");
			field3.setText("0");
			field4.setText("0");
			field5.setText("0");
			field6.setText("0");
			field7.setText("0");
			field8.setText("0");
		}

		if (event.getSource() == seeOrderHistory) {
			new seeOrderH();

		}
		

	}

	public Set<Product> placeAnOrder() {

		boolean b1 = true;
		boolean b2 = true;
		boolean b3 = true;
		boolean b4 = true;
		boolean b5 = true;
		boolean b6 = true;
		boolean b7 = true;
		boolean b8 = true;

		ArrayList<String> availableProducts = new ArrayList<String>();

		Set<Product> orderedProducts = new HashSet<Product>();
		Iterator<Product> iterator = warehouse.checkProducts();

		while (iterator.hasNext()) {
			Product p = iterator.next();

			///////////////////////////
			if (p.getName().equals("banana")) {
				if (banana != 0) {
					if (p.getQuantity() >= banana) {
						Product p2 = new Product(p.getName(), p.getId(), banana, p.getPrice());
						p.setQuantity(p.getQuantity() - banana);
						orderedProducts.add(p2);
						System.out.println("Bananas added");
						availableProducts.add(p.getName());
						System.out.println(p.getQuantity());
					} else if (p.getQuantity() >= 1) {
						JOptionPane.showMessageDialog(this, "Sorry! Only " + p.getQuantity() + " bananas in stock");
						b1 = false;
					}
				} else {
					// b1 = false;
					// System.out.println("Bananas are not on stock");
				}
			}

			//////////////////////////
			else if (p.getName().equals("raspberry")) {
				if (rasp != 0) {
					if (p.getQuantity() >= rasp) {
						Product p2 = new Product(p.getName(), p.getId(), rasp, p.getPrice());
						p.setQuantity(p.getQuantity() - rasp);
						orderedProducts.add(p2);
						System.out.println("Raspberry added");
						availableProducts.add(p.getName());
						System.out.println(p.getQuantity());
					} else if (p.getQuantity() >= 1) {
						JOptionPane.showMessageDialog(this, "Sorry! Only " + p.getQuantity() + " raspberries in stock");
						b2 = false;
					}
				} else {
					// b2 = false;
				}
			}
			///////////////////////////////
			else if (p.getName().equals("apple")) {
				if (apple != 0) {
					if (p.getQuantity() >= apple) {
						Product p2 = new Product(p.getName(), p.getId(), apple, p.getPrice());
						p.setQuantity(p.getQuantity() - apple);
						orderedProducts.add(p2);
						System.out.println("Apple added");
						availableProducts.add(p.getName());
						System.out.println(p.getQuantity());
					} else if (p.getQuantity() >= 1) {
						JOptionPane.showMessageDialog(this, "Sorry! Only " + p.getQuantity() + " apples in stock");
						b3 = false;
					}
				} else {
					// b3 = false;

				}
			}
			////////////////////////////////////////////////
			else if (p.getName().equals("orange")) {
				if (orange != 0) {
					if (p.getQuantity() >= orange) {
						Product p2 = new Product(p.getName(), p.getId(), orange, p.getPrice());
						p.setQuantity(p.getQuantity() - orange);
						orderedProducts.add(p2);
						System.out.println("Oranges added");
						availableProducts.add(p.getName());
						System.out.println(p.getQuantity());
					} else if (p.getQuantity() >= 1) {
						JOptionPane.showMessageDialog(this, "Sorry! Only " + p.getQuantity() + " oranges in stock");
						b4 = false;
					}
				} else {
					// b4 = false;
				}
			}
			///////////////////////////////////////
			else if (p.getName().equals("kiwi")) {
				if (kiwi != 0) {
					if (p.getQuantity() >= kiwi) {
						Product p2 = new Product(p.getName(), p.getId(), kiwi, p.getPrice());
						p.setQuantity(p.getQuantity() - kiwi);
						orderedProducts.add(p2);
						System.out.println("Kiwies added");
						availableProducts.add(p.getName());
						System.out.println(p.getQuantity());
					} else if (p.getQuantity() >= 1) {
						JOptionPane.showMessageDialog(this, "Sorry! Only " + p.getQuantity() + " kiwis in stock");
						b5 = false;
					}
				} else {
					// b5 = false;
				}
			}
			////////////////////////////////////////////////
			else if (p.getName().equals("strawberry")) {
				if (strawberry != 0) {
					if (p.getQuantity() >= strawberry) {
						Product p2 = new Product(p.getName(), p.getId(), strawberry, p.getPrice());
						p.setQuantity(p.getQuantity() - strawberry);
						orderedProducts.add(p2);
						System.out.println("Strawberry added");
						availableProducts.add(p.getName());
						System.out.println(p.getQuantity());
					} else if (p.getQuantity() >= 1) {
						JOptionPane.showMessageDialog(this,
								"Sorry! Only " + p.getQuantity() + " strawberries in stock");
						b6 = false;
					}
				} else {
					// b6 = false;
				}
			}
			///////////////////////////////////
			else if (p.getName().equals("peach")) {
				if (peach != 0) {
					if (p.getQuantity() >= peach) {
						Product p2 = new Product(p.getName(), p.getId(), peach, p.getPrice());
						p.setQuantity(p.getQuantity() - peach);
						orderedProducts.add(p2);
						System.out.println("Peach added");
						availableProducts.add(p.getName());
						System.out.println(p.getQuantity());
					} else if (p.getQuantity() >= 1) {
						JOptionPane.showMessageDialog(this, "Sorry! Only " + p.getQuantity() + " peaches in stock");
						b7 = false;
					}
				} else {
					// b7 = false;
				}
			}
			///////////////////////////////////
			else if (p.getName().equals("pome")) {
				if (pome != 0) {
					if (p.getQuantity() >= pome) {
						Product p2 = new Product(p.getName(), p.getId(), pome, p.getPrice());
						p.setQuantity(p.getQuantity() - peach);
						orderedProducts.add(p2);
						System.out.println("Pomegrenate added");
						availableProducts.add(p.getName());
						System.out.println(p.getQuantity());
					} else if (p.getQuantity() >= 1) {
						JOptionPane.showMessageDialog(this,
								"Sorry! Only " + p.getQuantity() + " pomegrenates in stock");
						b8 = false;
					}
				} else {
					// b8 = false;
				}
			}

		}

		String out = "";

		for (int i = 0; i < availableProducts.size(); i++) {
			out += "Available: " + availableProducts.get(i) + "\n";
		}

		JOptionPane.showMessageDialog(this, out, "Your order:", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, "Your order was placed. Thank you!");

		// what remains on the stock
		while (iterator.hasNext()) {
			Product p = iterator.next();
			warehouse.addProduct(p);
		}
		WriteFileS write = new WriteFileS(warehouse);
		System.out.println("changed stock");// change the warehouse state

		/////////////////////////////////////
		return orderedProducts;

	}

	public String getCurrentTime() {

		Calendar cal = new GregorianCalendar();
		int second = cal.get(Calendar.SECOND);
		int minute = cal.get(Calendar.MINUTE);
		int hour = cal.get(Calendar.HOUR);
		String time = hour + ":" + (minute) + ":" + second;
		return time;

	}

	public class seeOrderH extends JFrame {

		private static final long serialVersionUID = 1L;
		private JPanel topPanel;
		private JTable table;
		private JScrollPane scrollPane;
		private DefaultTableModel model;

		public seeOrderH() {
			super("Orders");

			setTitle("Latest orders");
			setSize(300, 200);
			setBackground(Color.GRAY);

			// Create a panel to hold all other components
			topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			getContentPane().add(topPanel);

			// Create columns names
			String columnNames[] = { "Order ID", "Order Date", "Order Status" };
			
			ReadFileS read = new ReadFileS(); 
			OPD newOPD = read.ReadOPD(); 
		
			table = new JTable();

			Object[] columns = {  "Order ID", "Order Date", "Order Status" };
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columns);
			table.setModel(model);
			Iterator<Order> iterator = newOPD.checkOrders();
			while (iterator.hasNext()) {
				Order o = iterator.next();
				String id = o.getOrderId().toString();
				String date = o.getOrderDate();
				String status = o.getOrderStatus();
				Object[] row = new Object[3];
				row[0] = id;
				row[1] = date;
				row[2] = status;
				model.addRow(row);
			}
			
			// Add the table to a scrolling pane
			scrollPane = new JScrollPane(table);
			topPanel.add(scrollPane, BorderLayout.CENTER);

			// getContentPane().setLayout(null);
			setVisible(true);
			setSize(900, 430);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
		}

	}
}
