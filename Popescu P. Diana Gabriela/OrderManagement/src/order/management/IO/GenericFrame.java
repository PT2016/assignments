package order.management.IO;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class GenericFrame {
	
	public JFrame frame;
	public JButton logInOutButton;
	
	public GenericFrame(){
		frame = new JFrame("Order Management");
		//frame.setLayout(new GridLayout(5, 1));

	}
}
