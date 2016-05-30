package website;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminOp {
	JFrame frame = new JFrame("Admin");
	JPanel panel = new JPanel(new GridLayout(5, 2));
	JButton logOut = new JButton("Log out");
	JButton add = new JButton("Add");
	JButton delete = new JButton("Delete");
	JButton changeStock = new JButton("Change stock");
	private JTextArea text = new JTextArea();

	public AdminOp(Warehouse warehouse, Authentificate authentificate) {
		frame.setLayout(new BorderLayout());

		frame.add(panel, BorderLayout.EAST);

		panel.add(logOut);
		panel.add(add);
		panel.add(delete);
		panel.add(changeStock);
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
		add(warehouse, authentificate);
		delete(authentificate);
		modifyStock(authentificate);

	}

	private void logOut() {
		logOut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	private void add(Warehouse warehouse, Authentificate authentificate) {
		add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JTextField nume = new JTextField();
				JTextField pret = new JTextField();
				JTextField colour = new JTextField();
				JTextField stock = new JTextField();
				Object[] ob = { nume, pret, colour, stock };
				JOptionPane.showConfirmDialog(null, ob, "Add", JOptionPane.OK_CANCEL_OPTION);
				Product product = new Product(nume.getText(), Double.parseDouble(pret.getText()), colour.getText(),
						Integer.parseInt(stock.getText()));
				authentificate.getListModel().addElement(product);
				warehouse.getProductList().add(product.getName());
			}
		});
	}

	private void delete(Authentificate authentificate) {
		delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int selectedIndex = authentificate.getItemList().getSelectedIndex();
				if (selectedIndex != -1) {
					authentificate.getListModel().remove(selectedIndex);
				}
			}
		});
	}

	private void modifyStock(Authentificate authentificate) {
		changeStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField stock = new JTextField();
				Object[] ob = { stock };
				JOptionPane.showConfirmDialog(null, ob, "ModifyStock", JOptionPane.OK_CANCEL_OPTION);
				authentificate.modifyStock((Product) authentificate.getItemList().getSelectedValue(),
						Integer.parseInt(stock.getText()));
				frame.repaint();
			}
		});
	}
}
