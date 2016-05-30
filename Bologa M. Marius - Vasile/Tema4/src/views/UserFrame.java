package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import models.Account;

public class UserFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel topPanel, topPanel1;
	private JTabbedPane jtp = new JTabbedPane();
	private JPanel btnPanel;
	private JPanel det;
	private JButton addMoney, withdrawMoney, generateReports;
	private static JTable table;
	private static JTable reflectionTable;
	private JScrollPane scrollPane, scrollPane1;
	String header[] = { "ID", "Name", "AccId", "Sum", "Type", "Open Date", "Close Date" };

	public UserFrame() {
		setTitle("User Management Tool");

		getContentPane().add(jtp);

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		det = new JPanel();

		jtp.addTab("Accounts&Details", jp1);
		jtp.addTab("Accounts", jp2);
		jtp.addTab("See Details", det);
		setSize(550, 550);
		setBackground(Color.gray);
		topPanel = new JPanel();
		topPanel1 = new JPanel();
		btnPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		jp1.add(topPanel, BorderLayout.CENTER);
		jp1.add(btnPanel, BorderLayout.SOUTH);
		jp2.add(topPanel1, BorderLayout.CENTER);

		table = new JTable(0, 7);
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column1 = table.getTableHeader().getColumnModel().getColumn(i);

			column1.setHeaderValue(header[i]);
		}
		reflectionTable = new JTable();

		initializeTable((DefaultTableModel) reflectionTable.getModel());
		scrollPane = new JScrollPane(table);
		scrollPane1 = new JScrollPane(reflectionTable);
		topPanel.add(scrollPane, BorderLayout.CENTER);
		topPanel1.add(scrollPane1, BorderLayout.CENTER);
		addMoney = new JButton("Add Money");
		withdrawMoney = new JButton("Withdraw Money");
		generateReports = new JButton("Reports");
		btnPanel.add(addMoney);
		btnPanel.add(withdrawMoney);
		btnPanel.add(generateReports);
		setVisible(true);

	}

	private void initializeTable(DefaultTableModel tableModel) {
		Class<?> c = Account.class;
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			if (f.getName().equals("serialVersionUID") || (f.getName().equals("rand"))) {
				continue;
			} else {
				tableModel.addColumn(f.getName());
			}
		}
		reflectionTable = new JTable(tableModel);
	}

	public void details(Account a, Date date) throws ParseException {
		det.setLayout(new GridLayout(16, 0));
		Random rand = new Random();
		JLabel l1 = new JLabel("The holder id: " + String.valueOf(a.getP().getId()));
		JLabel l2 = new JLabel("The holder name: " + String.valueOf(a.getP().getName()));
		JLabel l3 = new JLabel("Account id: " + String.valueOf(a.getId()));
		JLabel l4 = new JLabel("Money in account: " + String.valueOf(a.getSum()));
		JLabel l5 = new JLabel("Type of the account: " + String.valueOf(a.getType()));
		JLabel l6 = new JLabel("Open day fo the account: " + String.valueOf(a.getDate()));
		JLabel l7 = new JLabel("Closing day for the account: " + String.valueOf(a.getCloseDate()));
		JLabel l8 = new JLabel("Gain rate for deposit: " + "0.1");
		JLabel l9 = new JLabel("Interest rate for withdraw" + "0.1");
		JLabel l10 = new JLabel(
				"The increade of the gaining rate is proposed in " + (rand.nextInt(10) + 1) + " months");
		JLabel l11 = new JLabel("Last transaction: " + date);
		det.add(l1);
		det.add(l2);
		det.add(l3);
		det.add(l4);
		det.add(l5);
		det.add(l6);
		det.add(l7);
		det.add(l8);
		det.add(l9);
		det.add(l10);
		det.add(l11);

	}

	public static JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		UserFrame.table = table;
	}

	public final void setAddMoneyButtonActionListener(final ActionListener a) {
		addMoney.addActionListener(a);
	}

	public final void setWithDrawButtonActionListener(final ActionListener a) {
		withdrawMoney.addActionListener(a);

	}

	public final void setReportButtonActionListener(final ActionListener a) {
		generateReports.addActionListener(a);

	}

	public static JTable getReflectionTable() {
		return reflectionTable;
	}

	public static void setReflectionTable(JTable reflectionTable) {
		UserFrame.reflectionTable = reflectionTable;
	}
}
