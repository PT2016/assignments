package tables;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClientsTable extends JPanel {

	public ClientsTable(ClientsTableModel clientsModel) {
		JTable clientsTable = new JTable(clientsModel);
		JScrollPane scr = new JScrollPane(clientsTable);
		add(scr);
		setVisible(true);
	}

}
