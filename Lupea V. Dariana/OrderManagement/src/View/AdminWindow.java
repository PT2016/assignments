package View;

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

public class AdminWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel1;
	private JLabel email, password;
	private JTextField text1;
	private JPasswordField text2;
	private JButton ok;

	public AdminWindow() {
		super("Admin Login");
		panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		email = new JLabel("AdminName: ");
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
		panel1.add(ok, c);

		add(panel1);
		setVisible(true);
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String myPass = String.valueOf(text2.getPassword());
		if (event.getSource() == ok) {
			if ((text1.getText().equals("Dari")) && (myPass.equals("dariana"))) {
				System.out.println("goooood");
				dispose();
				new StockWindow();
			} else {

				JOptionPane.showMessageDialog(this, "Please enter a valid admin!", "Error", JOptionPane.ERROR_MESSAGE);
				text1.setText("");
				text2.setText("");

			}

		}
	}
}