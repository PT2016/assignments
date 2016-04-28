package View;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Queue.Scheduler;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel arrivingtime;
	private JLabel servicetime;
	private JLabel simulationtime;
	private JLabel q1, q2, q3;

	private static JTextField minarrtime;
	private static JTextField maxarrtime;
	private static JTextField minservice;
	private static JTextField maxservice;
	private static JTextField simtime;

	private static JTextArea textarea = new JTextArea();
	private JScrollPane scrollbar = new JScrollPane(textarea);
	
	private JButton start;

	ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
	private Runnable simulator= new Runnable(){
		public void run(){
			new Scheduler();
		}
	};

	public GUI() {
		super("Queue Simulator");
		setLayout(null);
		arrivingtime = new JLabel("Min and Max interval of arriving time: ");
		servicetime = new JLabel("Min and Max service time: ");
		q1 = new JLabel("Queue 1");
		q2 = new JLabel("Queue 2");
		q3 = new JLabel("Queue 3");
		simulationtime = new JLabel("Simulation Time: ");

		minarrtime = new JTextField();
		maxarrtime = new JTextField();
		minservice = new JTextField();
		maxservice = new JTextField();
		simtime = new JTextField();

		start = new JButton("Start");

		arrivingtime.setBounds(10, 5, 300, 30);
		add(arrivingtime);
		minarrtime.setBounds(10, 35, 70, 20);
		add(minarrtime);
		maxarrtime.setBounds(105, 35, 70, 20);
		add(maxarrtime);

		servicetime.setBounds(10, 60, 300, 30);
		add(servicetime);
		minservice.setBounds(10, 90, 70, 20);
		add(minservice);
		maxservice.setBounds(105, 90, 70, 20);
		add(maxservice);
		simulationtime.setBounds(10, 120, 100, 30);
		add(simulationtime);
		simtime.setBounds(10, 150, 70, 20);
		add(simtime);

		start.setBounds(55, 200, 70, 30);
		add(start);

		scrollbar.setBounds(10, 250, 350, 200);
		add(scrollbar);

		q1.setBounds(450, 5, 80, 30);
		add(q1);
		q2.setBounds(560, 5, 80, 30);
		add(q2);
		q3.setBounds(670, 5, 80, 30);
		add(q3);
		

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exec.execute(simulator);
				//new Scheduler();
			}
		});
		setSize(800, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static int getMinArrivalTime() {
		int minarr = Integer.parseInt(minarrtime.getText());
		return minarr;
	}

	public static int getMaxArrivalTime() {
		int maxarr = Integer.parseInt(maxarrtime.getText());
		return maxarr;
	}

	public static int getMinServiceTime() {
		int minserv = Integer.parseInt(minservice.getText());
		return minserv;
	}

	public static int getMaxServiceTime() {
		int maxserv = Integer.parseInt(maxservice.getText());
		return maxserv;
	}

	public static long getSimulationTime() {
		long sim = Integer.parseInt(simtime.getText());
		return sim;
	}

	public static void displayText(String text) {
		System.out.println(text + "\n");
		textarea.append(text + "\n");
	}

}
