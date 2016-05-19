package tables;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InfoTable extends JPanel {

	public InfoTable(InfoTableModel infoTableModel) {
		JTable infoTable = new JTable(infoTableModel);
		JScrollPane scrollPane = new JScrollPane(infoTable);
		add(scrollPane);
		setVisible(true);
	}
}
