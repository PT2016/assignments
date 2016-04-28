package UI;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import data.Library;

public class History {

	public JFrame frame;
	private JList<String> orders = new JList<String>();
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private Library library = new Library();
	private String name;
	


	private void initializeHistory() {
		library.getHistory();
		String[] S = new String[50];
		library.setName(name);
		S = library.getDisplayHistory();
		int i = 0;
		listModel.clear();

		while (S[i] != null) {
			listModel.addElement(S[i]);
			i++;
		}
	}
	/**
	 * Create the application.
	 */
	public History() {
		initialize();
	}
	
	public void show() {
		initializeHistory();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 600, 303);
		frame.getContentPane().setLayout(null);
		orders.setForeground(SystemColor.windowText);
		orders.setBackground(SystemColor.inactiveCaptionBorder);
		
		orders.setModel(listModel);
		orders.setBounds(10, 36, 552, 181);
		frame.getContentPane().add(orders);
		
		JButton btnDone = new JButton("Done");
		btnDone.setForeground(SystemColor.control);
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDone.setBackground(SystemColor.inactiveCaptionText);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnDone.setBounds(10, 228, 89, 23);
		frame.getContentPane().add(btnDone);
		
		JLabel lblOrderHistory = new JLabel("Order History");
		lblOrderHistory.setForeground(SystemColor.inactiveCaptionBorder);
		lblOrderHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrderHistory.setBounds(10, 11, 147, 14);
		frame.getContentPane().add(lblOrderHistory);
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
