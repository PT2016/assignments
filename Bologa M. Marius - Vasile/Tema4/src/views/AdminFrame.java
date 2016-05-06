package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import models.Person;

public class AdminFrame extends JFrame {
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;
	private JPanel topPanel, topPanel1;
	private JPanel btnPanel, btnPanel2;
	private JTabbedPane jtp = new JTabbedPane();
	private static JTable table;
	private static JTable reflectionTable;
	private JButton addacc, delacc, addHolder, deleteHolder, generateReports;
	String header[] = { "ID", "Name", "AccountID", "Sum", "Type", "Open Date", "Close Date" };
	private static final long serialVersionUID = 1L;

	public AdminFrame() {
		setTitle("User Management Tool");

		getContentPane().add(jtp);

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();

		jtp.addTab("Accounts", jp1);
		jtp.addTab("Persons", jp2);
		setSize(550, 550);
		setBackground(Color.gray);
		topPanel = new JPanel();
		topPanel1 = new JPanel();
		btnPanel = new JPanel();
		btnPanel2 = new JPanel();
		topPanel.setLayout(new BorderLayout());
		jp1.add(topPanel, BorderLayout.CENTER);
		jp1.add(btnPanel, BorderLayout.SOUTH);
		jp2.add(topPanel1, BorderLayout.CENTER);
		jp2.add(btnPanel2, BorderLayout.SOUTH);

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
		addacc = new JButton("Add Account");
		delacc = new JButton("Delete Account");
		deleteHolder = new JButton("Delete Person");
		addHolder = new JButton("Add Person");
		generateReports = new JButton("Reports");
		btnPanel.add(addacc);
		btnPanel.add(delacc);
		btnPanel.add(generateReports);
		btnPanel2.add(addHolder);
		btnPanel2.add(deleteHolder);
		setVisible(true);

	}

	private void initializeTable(DefaultTableModel tableModel) {
		Class<?> c = Person.class;
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			if (f.getName().equals("serialVersionUID")) {
				continue;
			} else {
				tableModel.addColumn(f.getName());
			}
		}
		reflectionTable = new JTable(tableModel);
	}

	public static JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		AdminFrame.table = table;
	}

	public final void setAddActionListener(final ActionListener a) {
		addacc.addActionListener(a);

	}

	public final void setDelButtonActionListener(final ActionListener a) {
		delacc.addActionListener(a);

	}

	public final void setAddHolderButtonActionListener(final ActionListener a) {
		addHolder.addActionListener(a);

	}

	public final void setDeleteHolderButtonActionListener(final ActionListener a) {
		deleteHolder.addActionListener(a);

	}

	public final void setReportsActionListener(final ActionListener a) {
		generateReports.addActionListener(a);

	}

	public static JTable getRefTable() {
		return reflectionTable;
	}

	public void setRefTable(JTable reflectionTable) {
		AdminFrame.reflectionTable = reflectionTable;
	}

}
