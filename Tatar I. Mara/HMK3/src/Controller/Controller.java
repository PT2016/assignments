package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Simulator;
import parameters.Parameters;

public class Controller implements ActionListener{
	private Simulator sim;
	private Thread th;


	public Controller() {
		 sim = new Simulator();
		 th = new Thread(sim);
		sim.getSimulatorFrame().getStartButton().addActionListener(this);
		
	}

	

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(sim.getSimulatorFrame().getStartButton())) {
				Parameters.maxNoServers = sim.getSimulatorFrame().getParameters()[5];
				Parameters.minArrivalInterval=sim.getSimulatorFrame().getParameters()[0];
				Parameters.maxArrivalInterval=sim.getSimulatorFrame().getParameters()[1];
				Parameters.minServiceTime=sim.getSimulatorFrame().getParameters()[2];
				Parameters.maxServiceTime=sim.getSimulatorFrame().getParameters()[3];
				Parameters.finishTime=sim.getSimulatorFrame().getParameters()[4];
			
               

				th.start();
				
			
			}
			
		
		

	}
}