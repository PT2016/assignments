package order.management.IO;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LogInFrame extends GenericFrame implements ActionListener{

	public static final String GUEST="Guest";
	public static final String ADMIN="Admin";
	
	private JComboBox<String> jComboBox;
	private JLabel jLabelWelcome;
	public String selectedUser = ADMIN;
	
	public LogInFrame() {
		super();
		logInOutButton = new JButton("LOGIN");
		logInOutButton.setSize(new Dimension(30, 10));
		jLabelWelcome = new JLabel(" Order MANAGEMENT App ", SwingConstants.CENTER);

		String users[] = { ADMIN, GUEST };
		jComboBox = new JComboBox<String>(users);
		jComboBox.setBounds(50, 50, 90, 20);
		jComboBox.addActionListener(this);
		
		frame.add(jLabelWelcome);
		frame.add(jComboBox);
		frame.add(logInOutButton);
		frame.setLayout(new GridLayout(3, 1));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		@SuppressWarnings("rawtypes")
		JComboBox cb = (JComboBox)event.getSource();
		selectedUser = (String)cb.getSelectedItem();
		System.out.println(selectedUser);
	}
}