package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class MessageDialogs {
	private static String type = null;

	public static int getRowByValue(TableModel model, Object value) {
		int row = 0;
		for (int i = model.getRowCount() - 1; i >= 0; --i) {
			for (int j = model.getColumnCount() - 1; j >= 0; --j) {
				if (model.getValueAt(i, j).equals(value)) {
					row = i;
				}
			}
		}
		return row;
	}

	public static String[] addAccountWindow() {
		String[] result1 = new String[3];
		JTextField name = new JTextField(7);
		JTextField id = new JTextField(4);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("ID:"));
		myPanel.add(id);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("Person"));
		myPanel.add(name);
		name.setText(LoginFrame.getUserName());
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("Type:"));
		JPanel p = new JPanel();
		JComboBox<String> typeC = new JComboBox<String>();
		typeC.addItem("Saving Account");
		typeC.addItem("Spending Account");
		p.add(typeC);
		myPanel.add(p);
		typeC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedType = (String) combo.getSelectedItem();

				if (selectedType.equals("Saving Account")) {
					type = "Saving Account";
				} else if (selectedType.equals("Spending Account")) {
					type = "Spending Account";
				}
			}
		});

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter proper values in all the fields",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			result1[0] = name.getText();
			result1[1] = type;
			result1[2] = id.getText();
		}
		return result1;
	}

	public static String[] addHolderWindow() {
		String[] result1 = new String[2];
		JTextField name = new JTextField(7);
		JTextField id = new JTextField(4);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("ID:"));
		myPanel.add(id);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("Person"));
		myPanel.add(name);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter proper values in all the fields",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			result1[0] = name.getText();
			result1[1] = id.getText();
		}
		return result1;
	}

	public static String[] addMoneyWindow() {
		String[] result1 = new String[4];
		JTextField name = new JTextField(7);
		JTextField id = new JTextField(4);
		JTextField idAC = new JTextField(4);
		JTextField sum = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("ID:"));
		myPanel.add(id);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("Person"));
		myPanel.add(name);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("AccountID"));
		myPanel.add(idAC);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("Sum"));
		myPanel.add(sum);
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter proper values in all the fields",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			result1[0] = name.getText();
			result1[1] = id.getText();
			result1[2] = idAC.getText();
			result1[3] = sum.getText();
		}
		return result1;
	}

	public static String[] withDrawMoneyWindow() {
		String[] result1 = new String[4];
		JTextField name = new JTextField(7);
		JTextField id = new JTextField(4);
		JTextField idAC = new JTextField(4);
		JTextField sum = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("ID:"));
		myPanel.add(id);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("Person"));
		myPanel.add(name);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("AccountID"));
		myPanel.add(idAC);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("Sum"));
		myPanel.add(sum);
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter proper values in all the fields",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			result1[0] = name.getText();
			result1[1] = id.getText();
			result1[2] = idAC.getText();
			result1[3] = sum.getText();
		}
		return result1;
	}
}
