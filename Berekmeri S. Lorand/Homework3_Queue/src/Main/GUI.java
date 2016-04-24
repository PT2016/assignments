package Main;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

/**
 * 
 * @author Lorand
 *
 */
public class GUI extends JFrame {
	private JFrame Frm;
	private JMenuBar meniu;
	private JLabel timpSosireMin, timpSosireMax, timpServireMin, timpServireMax, timp, timpMediu, nrClienti, nrCase;
	private JTextField tSosireMin, tSosireMax, tServireMin, tServireMax, addTimp, addNrClienti, addNrCase;
	public static JTextField[] queues;
	private JButton start, enter;

	private JLabel addTimpSosireMin, addTimpSosireMax, addTimpServireMin, addTimpServireMax;
	public static int nrC, nrCl, minSosire, maxSosire, minServire, maxServire;
	protected Algorithm algorithm;
	static JTextField incrementTime, afTimpMediu;
	
	/**
	 * Constructor
	 */
	public GUI() {
		Frm = new JFrame("Queues - Homework 3");
		Frm.setBounds(200, 30, 900, 700);
		Frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frm.getContentPane().setLayout(null);

		meniu = new JMenuBar();
		Frm.setJMenuBar(meniu);
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		meniu.add(file);

		JMenuItem eMenuItem = new JMenuItem("Exit Program");
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		}); // exit

		timpSosireMin = new JLabel();
		timpSosireMin.setText("Arrival Time:");
		timpSosireMin.setBounds(40, 20, 100, 20);
		Frm.getContentPane().add(timpSosireMin);

		addTimpSosireMin = new JLabel();
		addTimpSosireMin.setText("Minimum:");
		addTimpSosireMin.setBounds(150, 40, 100, 20);
		Frm.getContentPane().add(addTimpSosireMin);

		tSosireMin = new JTextField();
		tSosireMin.setBounds(250, 40, 40, 30);
		Frm.getContentPane().add(tSosireMin);

		timpSosireMax = new JLabel();
		timpSosireMax.setText("Arrival time:");
		timpSosireMax.setBounds(40, 20, 100, 20);
		Frm.getContentPane().add(timpSosireMax);

		addTimpSosireMax = new JLabel();
		addTimpSosireMax.setText("Maximum:");
		addTimpSosireMax.setBounds(150, 70, 100, 20);
		Frm.getContentPane().add(addTimpSosireMax);

		tSosireMax = new JTextField();
		tSosireMax.setBounds(250, 70, 40, 30);
		Frm.getContentPane().add(tSosireMax);

		timpServireMin = new JLabel();
		timpServireMin.setText("Service Time:");
		timpServireMin.setBounds(350, 20, 100, 20);
		Frm.getContentPane().add(timpServireMin);

		addTimpServireMin = new JLabel();
		addTimpServireMin.setText("Minimum:");
		addTimpServireMin.setBounds(460, 40, 100, 20);
		Frm.getContentPane().add(addTimpServireMin);

		tServireMin = new JTextField();
		tServireMin.setBounds(540, 40, 40, 30);
		Frm.getContentPane().add(tServireMin);

		timpServireMax = new JLabel();
		timpServireMax.setText("Service Time:");
		timpServireMax.setBounds(350, 20, 100, 20);
		Frm.getContentPane().add(timpServireMax);

		addTimpServireMax = new JLabel();
		addTimpServireMax.setText("Maximum:");
		addTimpServireMax.setBounds(460, 70, 100, 20);
		Frm.getContentPane().add(addTimpServireMax);

		tServireMax = new JTextField();
		tServireMax.setBounds(540, 70, 40, 30);
		Frm.getContentPane().add(tServireMax);

		timp = new JLabel();
		timp.setText("Time:");
		timp.setBounds(40, 150, 100, 20);
		Frm.getContentPane().add(timp);

		incrementTime = new JTextField();
		incrementTime.setBounds(110, 150, 70, 30);
		incrementTime.setEditable(false);
		Frm.getContentPane().add(incrementTime);

		timpMediu = new JLabel();
		timpMediu.setText("Average waiting time:");
		timpMediu.setBounds(200, 150, 150, 20);
		Frm.getContentPane().add(timpMediu);

		afTimpMediu = new JTextField();
		afTimpMediu.setBounds(370, 150, 70, 30);
		afTimpMediu.setEditable(false);
		Frm.getContentPane().add(afTimpMediu);

		nrClienti = new JLabel();
		nrClienti.setText("Customers:");
		nrClienti.setBounds(40, 190, 100, 20);
		Frm.getContentPane().add(nrClienti);

		addNrClienti = new JTextField();
		addNrClienti.setBounds(110, 190, 70, 30);
		Frm.getContentPane().add(addNrClienti);

		nrCase = new JLabel();
		nrCase.setText("NrCase:");
		nrCase.setBounds(295, 190, 100, 20);
		Frm.getContentPane().add(nrCase);

		addNrCase = new JTextField();
		addNrCase.setBounds(370, 190, 70, 30);
		Frm.getContentPane().add(addNrCase);

		start = new JButton("Start");
		start.setBounds(710, 200, 70, 30);
		Frm.getContentPane().add(start);

		enter = new JButton("Save data");
		enter.setBounds(550, 200, 150, 30);
		Frm.getContentPane().add(enter);

		file.add(eMenuItem);

		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == enter) {

					nrC = Integer.parseInt(addNrCase.getText());

					if (nrC <= 6) {
						queues = new JTextField[nrC];

						for (int i = 0; i <= nrC - 1; i++) {
							queues[i] = new JTextField();
							queues[i].setText(" ");
							queues[i].setBounds(110, 260 + 60 * i, 500, 50);
							queues[i].setVisible(true);
							Frm.getContentPane().add(queues[i]);
						}
					} else
						JOptionPane.showMessageDialog(null, "Numarul maxim de case de care dispune magazinul este 6");

				}
			}
		});

		start.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == start) {
				minSosire = Integer.parseInt(tSosireMin.getText());
				maxSosire = Integer.parseInt(tSosireMax.getText());
				minServire = Integer.parseInt(tServireMin.getText());
				maxServire = Integer.parseInt(tServireMax.getText());
				nrC = Integer.parseInt(addNrCase.getText());
				nrCl = Integer.parseInt(addNrClienti.getText());
				ArrayList<Client> clnt = new ArrayList<Client>();
				int timpSosire, timpServire;
				System.out.printf(
						"Offices: %d \t Customers: %d \nMin arrival: %d \t Max arrival:  %d \nMin service: %d \t Max service: %d \n",
						nrC, nrCl, minSosire, maxSosire, minServire, maxServire);
				for (int i = 0; i <= nrCl - 1; i++) {
					timpSosire = randomSpace(minSosire, maxSosire);
					timpServire = randomSpace(minServire, maxServire);
					Client cl = new Client(timpSosire, timpServire);
					clnt.add(cl);
				}
				algorithm = new Algorithm(nrCl, nrC);
				Thread th = new Thread(algorithm);
				th.start();

			}

		}
	});

		Frm.setVisible(true);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private int randomSpace(int x, int y) {
		Random randomm = new Random();
		int a;
		/*
		 * while(a<x || a>y) a=randomm.nextInt(100000);
		 */
		do {
			a = randomm.nextInt(1000000);
		} while ((a < x) || (a > y));

		return a;
	}
}
