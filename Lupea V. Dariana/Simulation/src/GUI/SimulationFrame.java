package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.TaskGenerator;
import Model.TaskScheduler;

/**
 * Creates the GUI for the simulation
 * 
 * @author Dariana Lupea
 *
 */
public class SimulationFrame extends JFrame implements ActionListener {

	JFrame f = new JFrame();

	private JPanel panel, content, servers, tasks;
	private JButton startButton;
	private JTextField field1, field2, field3, field4, field5, field6;
	private JButton b1, b2, b3, b4, b5, b6;

	public SimulationFrame() {
		super("Simulation");

		f.setLayout(new BorderLayout());
		panel = new JPanel();
		content = new JPanel();
		servers = new JPanel();
		tasks = new JPanel();
		//content.setBounds(200, 100,250, 500);
		b1 = new JButton("Min Arrival Time");
		b2 = new JButton("Max Arrival Time");
		b3 = new JButton("Min Service Time");
		b4 = new JButton("Max Service Time");
		b5 = new JButton("Simulation Time");
		b6 = new JButton("No of queues");
		startButton = new JButton("Start Simulation!");
		startButton.addActionListener(this);

		field1 = new JTextField("       ");
		field2 = new JTextField("       ");
		field3 = new JTextField("       ");
		field4 = new JTextField("       ");
		field5 = new JTextField("       ");
		field6 = new JTextField("       ");

		b1.setBounds(20, 20, 130, 30);
		b2.setBounds(170, 20, 130, 30);
		b3.setBounds(320, 20, 140, 30);
		b4.setBounds(470, 20, 140, 30);
		b5.setBounds(620, 20, 130, 30);
		b6.setBounds(770, 20, 130, 30);

		startButton.setBounds(920, 30, 130, 30);
		field1.setBounds(50, 50, 80, 30);
		field2.setBounds(200, 50, 80, 30);
		field3.setBounds(350, 50, 80, 30);
		field4.setBounds(500, 50, 80, 30);
		field5.setBounds(650, 50, 80, 30);
		field6.setBounds(800, 50, 80, 30);

		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.add(startButton);
		f.add(field1);
		f.add(field2);
		f.add(field3);
		f.add(field4);
		f.add(field5);
		f.add(field6);

		f.add(panel);
		
		f.setVisible(true);
		f.setSize(1100, 700);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		int minArrival = getInput(field1);
		int maxArrival = getInput(field2);
		int minService = getInput(field3);
		int maxService = getInput(field4);
		int simulationTime = getInput(field5);
		int noOfQueues = getInput(field6);

		if (event.getSource() == startButton) {
			
			TaskGenerator taskGen = new TaskGenerator(minArrival, maxArrival, minService, maxService);
			TaskScheduler scheduler = new TaskScheduler(noOfQueues, taskGen, simulationTime);
			Thread th = new Thread(scheduler);
			th.start();
			
           // servers.setLayout(new GridLayout(noOfQueues, 1));
         //   tasks.setLayout(new GridLayout(noOfQueues, 1));
         //   content.setLayout(new GridLayout(1, 2));
            
        //    for (int i = 0; i < noOfQueues; i++) {
      //      	JLabel label = new JLabel(" Server " + i);
       //     	servers.add(label);
       //     }
      //      
       //     content.add(tasks);
       //     content.add(servers);
        //    f.add(content);
            
           
			//// displayData(scheduler.getAvailableServer().getTasks());
		//	Thread th = new Thread(scheduler);
		//	th.start();
		}
	}

	public int getInput(JTextField field) {
		String input = (field.getText()).trim();
		int number = Integer.parseInt(input);
		return number;

	}
}
