package panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel2 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton userLogout;
	public JButton btnOrder;
	public JButton userOrderHistory;
	public JButton btnFilter;
	public JButton btnRefresh;
	
	public JTextField priceLeft;
	public JTextField priceRight;
	
	public JLabel dash;
	public JLabel lblPrice; 

	public Panel2(){
		
		setLayout(null);
		
		userLogout = new JButton("Logout");
		userLogout.setBounds(481, 14, 117, 29);
		
		btnOrder = new JButton("ORDER");
		btnOrder.setBounds(271, 382, 117, 29);
		
		userOrderHistory = new JButton("Order History");
		userOrderHistory.setBounds(352, 14, 117, 29);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(33, 340, 61, 16);
		
		priceLeft = new JTextField();
		priceLeft.setBounds(71, 335, 53, 26);
		
		dash = new JLabel("-");
		dash.setBounds(123, 340, 61, 16);
		
		priceRight = new JTextField();
		priceRight.setBounds(133, 335, 53, 26);
		
		btnFilter = new JButton("FILTER");
		btnFilter.setBounds(71, 382, 117, 29);
		
		btnRefresh = new JButton("REFRESH");
		btnRefresh.setBounds(471, 382, 117, 29);
		
		add(userLogout);
		add(btnOrder);
		add(userOrderHistory);
		add(lblPrice);
		add(priceLeft);
		add(dash);
		add(priceRight);
		add(btnFilter);
		add(btnRefresh);
	}
	
}
