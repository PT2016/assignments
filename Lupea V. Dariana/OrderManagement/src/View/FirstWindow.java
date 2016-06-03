package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JRadioButton;

public class FirstWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JRadioButton b1, b2;
	private ButtonGroup group;
	private JLabel text;
	private JButton ok;
	boolean rad1, rad2;

	public FirstWindow() {
		super("Welcome!");
		getContentPane().setLayout(null);

		text = new JLabel();
		text.setText("Please choose how you want to login: ");
		text.setBounds(120, 60, 300, 14);
		getContentPane().add(text);

		b1 = new JRadioButton("Admin");
		b1.addActionListener(this);
		b1.setBounds(156, 79, 109, 23);
		getContentPane().add(b1);

		b2 = new JRadioButton("Customer");
		b2.addActionListener(this);
		b2.setBounds(156, 107, 109, 23);
		getContentPane().add(b2);

		group = new ButtonGroup();
		rad1 = false;
		rad2 = false;
		group.add(b1);
		group.add(b2);

		ok = new JButton("OK");
		ok.addActionListener(this);
		ok.setBounds(166, 135, 89, 23);
		getContentPane().add(ok);

		setVisible(true);
		setSize(450, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object c = (Object) event.getSource();

		if (c == b1) {
			rad1 = true;
			rad2 = false;
		}

		if (c == b2) {
			rad1 = false;
			rad2 = true;
		}
		if ((rad1 == true) && (event.getSource() == ok)) {
			dispose();
			new AdminWindow();
		} else if ((rad2 == true) && (event.getSource() == ok)) {
			dispose();
			new CustomerWindow();
		}

	}

}
