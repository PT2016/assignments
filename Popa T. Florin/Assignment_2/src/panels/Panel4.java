package panels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel4 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel lblNewLabel;
	public JLabel lblName;
	public JLabel lblProduct;
	public JLabel lblOrderSuccessfullyPlaced;
	public JLabel lblQuant;
	
	public JTextField nameField;
	public JTextField productField;
	public JTextField quantityField;
	
	public JButton btnBack;
	public JButton btnPlaceOrder;
	

	public Panel4(){
		
		setLayout(null);
		
		lblNewLabel = new JLabel("Provide the information in the fields bellow:");
		lblNewLabel.setBounds(25, 29, 327, 28);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(25, 80, 61, 16);
		
		nameField = new JTextField();
		nameField.setBounds(72, 75, 160, 26);
		
		lblQuant = new JLabel("Quantity:");
		lblQuant.setBounds(6, 118, 61, 16);
		
		quantityField = new JTextField();
		quantityField.setBounds(72, 113, 160, 26);
		
		lblProduct = new JLabel("Product:");
		lblProduct.setBounds(294, 80, 61, 16);
		
		productField = new JTextField();
		productField.setBounds(353, 75, 160, 26);
		productField.setEditable(false);
		
		btnBack = new JButton("BACK");
		btnBack.setBounds(517, 16, 117, 29);
		
		btnPlaceOrder = new JButton("PLACE ORDER");
		btnPlaceOrder.setBounds(268, 196, 117, 29);

		
		lblOrderSuccessfullyPlaced = new JLabel("Order successfully placed!");
		lblOrderSuccessfullyPlaced.setForeground(Color.BLUE);
		lblOrderSuccessfullyPlaced.setBounds(251, 237, 166, 16);
		lblOrderSuccessfullyPlaced.setVisible(false);
		
		add(lblNewLabel);
		add(lblName);
		add(nameField);
		add(lblQuant);
		add(quantityField);
		add(lblProduct);
		add(productField);
		add(btnPlaceOrder);
		add(btnBack);
		add(lblOrderSuccessfullyPlaced);
		
	}
	
}
