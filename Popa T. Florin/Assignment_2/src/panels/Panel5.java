package panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel5 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton back;
	public JLabel lblOrderHistory;

	public Panel5(){
		
		setLayout(null);
		
		 back = new JButton("BACK");
		 back.setBounds(275, 385, 117, 29);
		 
		 lblOrderHistory = new JLabel("ORDER HISTORY");
		 lblOrderHistory.setBounds(273, 25, 102, 16);
		 
		 add(back);
		 add(lblOrderHistory);
	}
	
}
