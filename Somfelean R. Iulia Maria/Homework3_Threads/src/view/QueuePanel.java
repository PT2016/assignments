package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.CashRegister;
import model.Client;
import utilities.Constants;

public class QueuePanel extends JPanel {

	private CashRegister cashRegister;
	private int id;
	private LinkedList<Color> color = new LinkedList<Color>();
	static int guiNrOfClients;
	ArrayList<JLabel> lblClients;

	public QueuePanel(int id) {
		this.id = id;
		this.guiNrOfClients = 0;
		this.lblClients = new ArrayList<JLabel>();
		this.setPreferredSize(new Dimension(Constants.X_FRAME, 280));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Cash Register"+id);
		this.setBorder(title);

		

	}

	public void drawSalesman() {

		JLabel salesman = new JLabel("CashRegister" + id);
		salesman.setBackground(Color.BLACK);

		this.add(salesman);

	}

	public void drawElements(CashRegister cashRegister) {

		int nrClients = cashRegister.getClientsQueue().size();
		Client[] clients = (Client[]) cashRegister.getClientsQueue().toArray(new Client[0]);
		int i;
		JLabel label;
		this.removeAll();
		try {

			for (i = 0; i < nrClients; i++) {
				label = new JLabel(clients[i].getId() + "->");
				this.add(label);
				revalidate();
				repaint();
			}

			this.guiNrOfClients = nrClients;
			this.getTopLevelAncestor().setVisible(true);
		} catch (Exception e) {
		}
		
	}

}
