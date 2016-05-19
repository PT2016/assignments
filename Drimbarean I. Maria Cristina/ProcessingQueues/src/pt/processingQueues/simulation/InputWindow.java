package pt.processingQueues.simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pt.processingQueues.principal.SupermarketCheckout;


@SuppressWarnings("serial")
public class InputWindow extends JFrame {
	private JTextField minServiceField;
	private JTextField maxServiceField;
	private JTextField minArrivalField;
	private JTextField maxArrivalField;
	private JTextField queuesField;
	private JTextField simulationTimeField;
	private JTextArea output;
	private Simulation simulation;
	int number;
	
	public InputWindow() {
		this.setTitle("Programming techniques-Assignment3-Drimbarean Maria");
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());
        
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(6,2));
		
		JButton button1=new JButton("Give minimum service time:");
		button1.setEnabled(false);
		button1.setBackground(Color.PINK);
		panel.add(button1);
		
		minServiceField=new JTextField();
		panel.add(minServiceField);
		
		JButton button2=new JButton("Give maximum service time:");
		button2.setEnabled(false);
		button2.setBackground(Color.PINK);
		panel.add(button2);
		this.setVisible(true);
		
		maxServiceField=new JTextField();
		panel.add(maxServiceField);
		
		JButton button3=new JButton("Give minimum arrival time:");
		button3.setEnabled(false);
		button3.setBackground(Color.PINK);
		panel.add(button3);
		this.setVisible(true);
		
		minArrivalField=new JTextField();
		panel.add(minArrivalField);
		
		JButton button4=new JButton("Give maximum arrival time:");
		button4.setEnabled(false);
		button4.setBackground(Color.PINK);
		panel.add(button4);
		this.setVisible(true);
		
		maxArrivalField=new JTextField();
		panel.add(maxArrivalField);
		
		JButton button5=new JButton("Give numer of checkouts:");
		button5.setEnabled(false);
		button5.setBackground(Color.PINK);
		panel.add(button5);
		this.setVisible(true);
		
		queuesField=new JTextField();
		panel.add(queuesField);
		
		JButton button6=new JButton("Give simulation time:");
		button6.setEnabled(false);
		button6.setBackground(Color.PINK);
		panel.add(button6);
		this.setVisible(true);
		
		simulationTimeField=new JTextField();
		panel.add(simulationTimeField);
		
		this.add(panel,BorderLayout.CENTER);
		panel.setVisible(true);
		
		JPanel panel2=new JPanel();
		panel2.setLayout(new FlowLayout());
		
		JButton start=new JButton("Start");
		start.setBackground(Color.CYAN);
		JButton pause=new JButton("Pause");
		pause.setBackground(Color.YELLOW);
		JButton restart=new JButton("Restart");
		restart.setBackground(Color.PINK);
		JButton stop=new JButton("Stop");
		stop.setBackground(Color.CYAN);
		
		panel2.add(start);
		panel2.add(pause);
		panel2.add(restart);
		panel2.add(stop);
		
		this.add(panel2,BorderLayout.NORTH);
		panel2.setVisible(true);
		
		output=new JTextArea();
		output.setBackground(Color.yellow);
		this.add(output,BorderLayout.SOUTH);
		
		this.setVisible(true);
		start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int minS=Integer.parseInt(minServiceField.getText());
              int maxS=Integer.parseInt(maxServiceField.getText());
              int minA=Integer.parseInt(minArrivalField.getText());
              int maxA=Integer.parseInt(maxArrivalField.getText());
              number=Integer.parseInt(queuesField.getText());
              int sim=Integer.parseInt(simulationTimeField.getText());
              simulation=new Simulation(minS,maxS,minA,maxA,number,sim);
              simulation.initSimulation();
              output.append("Simulation has started!\n");
            }
        });
		pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              simulation.pause();
              output.append("Simulation is paused!\n");
              output.append(simulation.getPeakHour()+" average service:"+simulation.getAvgServiceTime()+" average waiting:"+simulation.getAvgWaitingTime()+"\n");
            }
        });
		restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              simulation.start();
              output.append("Simulation is reastarted!\n");
            }
        });
		stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              simulation.stop();
              output.append("Simulation has stopped!\n");
              output.append(simulation.getPeakHour()+" average service:"+simulation.getAvgServiceTime()+" average waiting:"+simulation.getAvgWaitingTime()+"\n");
              int i=0;
              for (SupermarketCheckout s:simulation.queues)
              { 
            	  output.append(s.toString()+" average service time "+s.avgServiceTime()+" average waiting time "+s.avgWaitingTime()+" has served "+s.getClientsServed()+" clients!\n");
            	  i++;
            	  if (i==number)
            		  break;
              }
            }
        });
	}
}
