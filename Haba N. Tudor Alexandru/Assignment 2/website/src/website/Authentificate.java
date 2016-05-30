package website;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Authentificate extends JFrame {
	JFrame frame = new JFrame("Authentificate");
	JTextField userName = new JTextField(20);
	JPasswordField password = new JPasswordField();
	JPanel info = new JPanel(new GridLayout(2, 1, 5, 5));
	JPanel lnPanel = new JPanel(new GridLayout(1, 1, 5, 5));
	JButton lnButton = new JButton("Log in");
	DefaultListModel<Product> listModel = new DefaultListModel<Product>();
	JList<Product> itemList = new JList<Product>(listModel);

	char[] adminPass = { '1', '2', '3', '4', '5' };
	char[] customerPass = { '6', '2', '3' };

	public Authentificate() {
		info.add(userName);
		info.add(password);
		lnPanel.add(lnButton);
		frame.setLayout(new FlowLayout());
		frame.setResizable(false);
		frame.add(info);
		frame.add(lnPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		// frame.setVisible(true);
		Warehouse warehouse = new Warehouse();
		for (Product product : warehouse.getTree()) {
			listModel.addElement(product);
		}
		String user = getInput();

		if (user.equals("admin")) {
			new AdminOp(warehouse, this);
		} else if (user.equals("customer")) {
			new CustomerOp(warehouse, this);
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect user or pass");
		}
	}

	private String getInput() {
		JTextField user = new JTextField();
		JPasswordField pass = new JPasswordField();
		Object[] ob = { user, pass };
		JOptionPane.showConfirmDialog(null, ob, "Authenticate", JOptionPane.OK_CANCEL_OPTION);
		if (Arrays.equals(adminPass, pass.getPassword())) {
			return user.getText();
		} else if (Arrays.equals(customerPass, pass.getPassword())) {
			return user.getText();
		}
		return "mistake";
	}

	public DefaultListModel<Product> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<Product> listModel) {
		this.listModel = listModel;
	}

	public JList<Product> getItemList() {
		return itemList;
	}

	public void setItemList(JList<Product> itemList) {
		this.itemList = itemList;
	}

	public void modifyStock(Product product, int stock) {
		product.setStock(stock);
	}

}
