package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CustomerWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel1, panel2;
	private JLabel email, password;
	private JTextField text1;
	private JPasswordField text2;
	private JButton ok;
	private JLabel welcomeLabel;

	public CustomerWindow() {
		super("Customer Login");

		getContentPane().setLayout(new BorderLayout());
		panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		email = new JLabel("Email: ");
		password = new JLabel("Password: ");
		text1 = new JTextField();
		text1.setPreferredSize(new Dimension(200, 24));
		text2 = new JPasswordField();
		text2.setPreferredSize(new Dimension(200, 24));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(email, c);
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(text1, c);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(password, c);
		c.gridx = 1;
		c.gridy = 1;
		panel1.add(text2, c);
		c.gridx = 1;
		c.gridy = 2;

		ok = new JButton("OK");
		ok.setBounds(200, 30, 200, 30);
		ok.addActionListener(this);

		panel2 = new JPanel();
		welcomeLabel = new JLabel("Welcome to Dari's Shop! ");
		welcomeLabel.setBounds(250, 51, 50, 20);
		panel2.add(welcomeLabel);
		panel1.add(ok, c);
		add(panel1, BorderLayout.CENTER);

		add(panel2, BorderLayout.NORTH);
		setVisible(true);
		setSize(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String myPass = String.valueOf(text2.getPassword());
		if (e.getSource() == ok) {
			if ((text1.getText().equals("dari_lu44@yahoo.com")) && (myPass.equals("dariana"))) {
				System.out.println("goooood");
				dispose();
				new ShopWindow();
			} else {

				JOptionPane.showMessageDialog(this, "Please enter a valid user!", "Error", JOptionPane.ERROR_MESSAGE);
				text1.setText("");
				text2.setText("");

			}

		}
	}

}
