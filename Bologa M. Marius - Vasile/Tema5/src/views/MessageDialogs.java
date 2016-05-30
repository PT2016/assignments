package views;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MessageDialogs {
	public static String[] addWordWindow() {
		String[] result1 = new String[2];
		JTextField word = new JTextField(7);
		JTextField synonim = new JTextField(20);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Word"));
		myPanel.add(word);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("Synonim"));
		myPanel.add(synonim);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter proper values in all the fields",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			result1[0] = word.getText();
			result1[1] = synonim.getText();
		}
		return result1;
	}

	public static String delWordWindow() {
		JTextField word = new JTextField(7);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Word"));
		myPanel.add(word);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter proper values in all the fields",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return word.getText();
		}
		return null;
	}

}
