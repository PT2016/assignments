package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import utilities.Constants;

public class LoggerPanel extends JPanel{
	
	private JTextArea taLogger;
	private JTextArea taStatistics;
	private JScrollPane loggerScroll;
	private JScrollPane statisticsScroll;
	
	public LoggerPanel(){
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.setBorder(new LineBorder(Color.BLUE,1));
		
		taLogger = new JTextArea();
		loggerScroll = new JScrollPane(taLogger);
		this.add(loggerScroll, BorderLayout.WEST);
		
		taStatistics = new JTextArea();
		statisticsScroll = new JScrollPane(taStatistics);
		this.add(statisticsScroll, BorderLayout.EAST);
		
		loggerScroll.setPreferredSize(new Dimension(300, 320));
		loggerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		taLogger.setWrapStyleWord(true);
		taLogger.setLineWrap(true);
		taLogger.setEditable(false);
		taLogger.setBackground(Color.BLACK);
		taLogger.setForeground(Color.WHITE);
		taLogger.setBorder(new LineBorder(Color.GRAY, 10));
		
		statisticsScroll.setPreferredSize(new Dimension(100, 320));
		statisticsScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		taStatistics.setWrapStyleWord(true);
		taStatistics.setLineWrap(true);
		taStatistics.setEditable(false);
		taStatistics.setBackground(Color.BLACK);
		taStatistics.setForeground(Color.WHITE);
		taStatistics.setBorder(new LineBorder(Color.GRAY, 10));
	}
	
	public JTextArea getTaLogger(){
		return this.taLogger;
	}
	
	public JTextArea getTaStatistics(){
		return this.taStatistics;
	}
}
