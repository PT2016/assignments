package website;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CustomerOp {
	JFrame frame = new JFrame("Customer");
	JPanel panel = new JPanel(new GridLayout(5, 2));
	JButton logOut = new JButton("Log out");
	JButton search = new JButton("Search");
	JButton order = new JButton("Order");
	JButton history = new JButton("History");
	DefaultListModel<Product> listModelOrder = new DefaultListModel<Product>();
	private JTextArea text = new JTextArea();
	Customer customer = new Customer(2345234, "Tudor");
	List<Order> orders = new ArrayList<>();

	public CustomerOp(Warehouse warehouse, Authentificate authentificate) {
		frame.setLayout(new BorderLayout());

		frame.add(panel, BorderLayout.EAST);

		panel.add(logOut);
		panel.add(search);
		panel.add(order);
		panel.add(history);
		panel.add(text);
		text.setEditable(false);

		JPanel p = new JPanel();
		p.setBounds(new Rectangle(20, 50, 150, 400));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		JScrollPane sp = new JScrollPane(authentificate.getItemList());
		p.add(sp);
		frame.add(p, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		logOut();
		order(warehouse, authentificate);
		history(warehouse, authentificate);
		search(warehouse, authentificate);

	}

	private void logOut() {
		logOut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	private void search(Warehouse warehouse, Authentificate authentificate) {
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField product = new JTextField();
				Object[] ob = { product };
				JOptionPane.showConfirmDialog(null, ob, "Search", JOptionPane.OK_CANCEL_OPTION);
				getProductSelected(product.getText(), warehouse, authentificate);
			}
		});
	}

	private void order(Warehouse warehouse, Authentificate authentificate) {
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField number = new JTextField();
				Product product = authentificate.getItemList().getSelectedValue();
				Object[] obiect = { number };
				int result = JOptionPane.showConfirmDialog(null, obiect, "Order", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int numb = Integer.parseInt(number.getText());
					Order order = new Order(customer, product, numb);
					orders.add(order);

				}
			}

		});
	}

	private void history(Warehouse warehouse, Authentificate authentificate) {
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Order order : orders) {
					JOptionPane.showMessageDialog(frame,
							"Product " + order.getProduct() + " with amount " + order.getNumberToOrder() + "\n");
				}

			}

		});
	}

	public void getProductSelected(String name, Warehouse warehouse, Authentificate authentificate) {
		for (Product product : warehouse.getTree()) {
			if (product.getName().contains(name)) {
				int index = authentificate.getListModel().indexOf(product);
				authentificate.getItemList().setSelectedIndex(index);
			}
		}
	}

}
