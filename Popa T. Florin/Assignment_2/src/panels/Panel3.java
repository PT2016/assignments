package panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel3 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton adminLogout;
	public JButton btnAddProduct;
	public JButton btnRemoveProduct;
	public JButton btnModifyProduct;
	public JButton adminOrderHistory;
	
	public JTextField textField;
	public JTextField productPriceField;
	public JTextField quantField;
	public JTextField quantModifyField;
	
	public JLabel lblProductName;
	public JLabel lblProductPrice;
	public JLabel lblQuantity;

	public Panel3(){
		
		setLayout(null);
		
		adminLogout = new JButton("Logout");
		adminLogout.setBounds(481, 14, 117, 29);
		
	    textField = new JTextField();
		textField.setBounds(100, 308, 130, 26);
		
		productPriceField = new JTextField();
		productPriceField.setBounds(100, 338, 130, 26);
		
		quantField = new JTextField();
		quantField.setBounds(100, 367, 130, 26);
		
		btnAddProduct = new JButton("ADD PRODUCT");
		btnAddProduct.setBounds(100, 407, 130, 29);

		
		lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(6, 313, 98, 16);
		
		lblProductPrice = new JLabel("Product Price:");
		lblProductPrice.setBounds(6, 343, 98, 16);
		
		lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(6, 372, 61, 16);

		
		btnRemoveProduct = new JButton("REMOVE PRODUCT");
		btnRemoveProduct.setBounds(427, 407, 153, 29);
		
		btnModifyProduct = new JButton("MODIFY PRODUCT");
		btnModifyProduct.setBounds(250, 407, 153, 29);
		
		quantModifyField = new JTextField();
		quantModifyField.setBounds(260, 367, 130, 26);
		
		adminOrderHistory = new JButton("Order History");
		adminOrderHistory.setBounds(352, 14, 117, 29);
		
		add(adminLogout);
		add(textField);
		add(productPriceField);
		add(quantField);
		add(btnAddProduct);
		add(lblProductName);
		add(lblProductPrice);
		add(lblQuantity);
		add(btnRemoveProduct);
		add(btnModifyProduct);
		add(quantModifyField);
		add(adminOrderHistory);
	}
	
}
