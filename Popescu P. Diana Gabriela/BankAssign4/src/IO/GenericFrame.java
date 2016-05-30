package IO;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class GenericFrame {
	
	public JFrame frame;
	public JButton logInOutButton;
	
	public GenericFrame(){
		frame = new JFrame("Bank Management");
		//frame.setLayout(new GridLayout(5, 1));

	}
}
