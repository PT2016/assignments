package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import queues.Simulation;
import queues.SimulationInput;

@SuppressWarnings("serial")
public class Window extends JPanel {
	private JButton start = new JButton("Start");
	private JLabel welcome = new JLabel("Welcome !");
	private JLabel nrClients = new JLabel("Number of clients:");
	private JLabel nrQueues = new JLabel("Number of queues:");
	private JLabel arrivalMin = new JLabel("Minimum arriving time:");
	private JLabel arrivalMax = new JLabel("Maximum arriving time:");
	private JLabel serviceMin = new JLabel("Minimum service:");
	private JLabel serviceMax = new JLabel("Maximum service:");
	private JLabel hours = new JLabel("Hours:");
	private JTextField nrClientsField = new JTextField(10);
	private JTextField nrQueuesField = new JTextField(10);
	private JTextField arrivalMinField = new JTextField(10);
	private JTextField arrivalMaxField = new JTextField(10);
	private JTextField serviceMinField = new JTextField(10);
	private JTextField serviceMaxField = new JTextField(10);
	private JTextField hoursFieldMin = new JTextField(10);
	private JTextField hoursFieldMax = new JTextField(10);
	private JSeparator bar = new JSeparator(JSeparator.HORIZONTAL);
	private JTextArea textArea = new JTextArea();
	private JScrollPane scroll = new JScrollPane(textArea);
	private DefaultCaret caret = (DefaultCaret) textArea.getCaret();

	public Window() {
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLayout(null);

		this.setLayout(null);

		setBound(this.start, new Rectangle(600, 45, 70, 30));
		add(start);

		setBound(this.welcome, new Rectangle(300, 0, 100, 50));
		add(welcome);

		setBound(this.nrClients, new Rectangle(10, 30, 150, 50));
		add(nrClients);

		setBound(this.nrClientsField, new Rectangle(120, 48, 30, 17));
		nrClientsField.setText("15");
		add(nrClientsField);

		setBound(this.nrQueues, new Rectangle(10, 50, 150, 50));
		add(nrQueues);

		setBound(this.nrQueuesField, new Rectangle(120, 68, 30, 17));
		nrQueuesField.setText("3");
		add(nrQueuesField);

		setBound(this.arrivalMin, new Rectangle(170, 30, 150, 50));
		add(arrivalMin);

		setBound(this.arrivalMinField, new Rectangle(310, 48, 30, 17));
		arrivalMinField.setText("1");
		add(arrivalMinField);
		arrivalMinField.setToolTipText("Enter value in minutes");

		setBound(this.arrivalMax, new Rectangle(170, 50, 150, 50));
		add(arrivalMax);

		setBound(this.arrivalMaxField, new Rectangle(310, 68, 30, 17));
		arrivalMaxField.setToolTipText("Enter value in minutes");
		arrivalMaxField.setText("5");
		add(arrivalMaxField);
		arrivalMaxField.setToolTipText("Enter value in minutes");

		setBound(this.serviceMin, new Rectangle(360, 30, 150, 50));
		add(serviceMin);

		setBound(this.serviceMinField, new Rectangle(470, 48, 30, 17));
		serviceMinField.setText("1");
		add(serviceMinField);
		serviceMinField.setToolTipText("Enter value in minutes");

		setBound(this.serviceMax, new Rectangle(360, 50, 150, 50));
		add(serviceMax);

		setBound(this.serviceMaxField, new Rectangle(470, 68, 30, 17));
		serviceMaxField.setText("5");
		add(serviceMaxField);
		serviceMaxField.setToolTipText("Enter value in minutes");

		setBound(this.hours, new Rectangle(510, 58, 100, 17));
		add(hours);

		setBound(this.hoursFieldMin, new Rectangle(550, 48, 30, 17));
		hoursFieldMin.setText("8");
		add(hoursFieldMin);

		setBound(this.hoursFieldMax, new Rectangle(550, 68, 30, 17));
		hoursFieldMax.setText("14");
		add(hoursFieldMax);

		setBound(this.bar, new Rectangle(0, 100, 700, 10));
		add(bar);

		JPanel area = new JPanel();
		area.setBackground(Color.GRAY);
		frame.add(area);

		area.setBounds(new Rectangle(20, 110, 650, 350));
		add(area);

		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textArea.setBounds(new Rectangle(175, 20, 300, 300));
		area.add(textArea, BorderLayout.CENTER);

		scroll.setBounds(175, 20, 300, 300);
		scroll.setViewportView(textArea);
		area.add(scroll, BorderLayout.PAGE_END);

		addValidate();
	}

	public void setBound(Component comp, Rectangle bounds) {
		comp.setBounds(bounds);
	}

	private void addValidate() {
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimulationInput input = new SimulationInput(getIntFromField(nrClientsField),
						getIntFromField(nrQueuesField), getIntFromField(arrivalMinField) * 1000,
						getIntFromField(arrivalMaxField) * 1000, getIntFromField(serviceMinField) * 1000,
						getIntFromField(serviceMaxField) * 1000, getIntFromField(hoursFieldMin),
						getIntFromField(hoursFieldMax));
				new Simulation(input, textArea);

			}
		});
	}

	private int getIntFromField(JTextField field) {
		try {
			return Integer.parseInt(field.getText());
		} catch (NumberFormatException ex) {
			System.out.println("Error ! Parsing input , using default value");
			return 1;
		}
	}
}
