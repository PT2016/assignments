/**
 * 
 */
package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.Serializer;
import models.Bank;

/**
 * @author marat
 *
 */
public class BankReportView {
	JTextArea display;
	JFrame frame ;
	Serializer io;
	JScrollPane pane ;
public BankReportView(String report){
	frame = new JFrame();
	io=new Serializer();
	Font ft=new Font("Arial",Font.BOLD,14);
	Bank bank=io.deserializeBank();
	display=new JTextArea(bank.getReport());
	display.setFont(ft);
	display.setBackground(new Color(232, 189, 128));
	pane = new JScrollPane(display);
	pane.setBounds(0, 0, 880, 380);
	pane.setForeground(new Color(232, 189, 128));
	pane.setOpaque(false);
	pane.getViewport().setBackground(new Color(232, 189, 128));
	pane.setBackground(new Color(232, 189, 128));
	frame.setLayout(null);
	frame.add(pane);
	frame.setVisible(true);
	frame.setSize(900, 410);
	frame.getContentPane().setBackground(new Color(162, 104, 42));
	frame.setLocationRelativeTo(null);
}
}
