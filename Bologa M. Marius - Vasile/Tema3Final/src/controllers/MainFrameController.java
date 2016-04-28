package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.TaskGenerator;
import models.TaskScheduler;
import models.Utilities;
import views.MainFrame;

public class MainFrameController {
	private MainFrame frame;
	private TaskScheduler scheduler;

	public MainFrameController(final MainFrame frame) {
		this.frame = frame;
		frame.setStartButtonActionListener(new StartButtonActionListener());
		frame.setClearButtonActionListener(new ClearButtonActionListener());
	}

	public class StartButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			instantiateServerManager();
		}
	}

	public class ClearButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			Utilities.setLogText(scheduler, "");

		}
	}

	public void instantiateServerManager() {
		try {
			if (frame.getMinArrivalTimeField().isEmpty() || frame.getMaxArrivalTimeField().isEmpty()
					|| frame.getMinServingTimeField().isEmpty() || frame.getMaxServingTimeField().isEmpty()
					|| frame.getQueueCountField().isEmpty() || frame.getSimulationTimeField().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (Integer.parseInt(frame.getMinArrivalTimeField()) > Integer.parseInt(frame.getSimulationTimeField())
						|| Integer.parseInt(frame.getMaxArrivalTimeField()) > Integer
								.parseInt(frame.getSimulationTimeField())) {
					JOptionPane.showMessageDialog(null, "Arrival time greater than simulation time");
				} else {
					if (Integer.parseInt(frame.getMinServingTimeField()) > Integer
							.parseInt(frame.getSimulationTimeField())
							|| Integer.parseInt(frame.getMaxServingTimeField()) > Integer
									.parseInt(frame.getSimulationTimeField())) {
						JOptionPane.showMessageDialog(null, "Serving time greater than simulation time");
					} else {
						int minSrT = (int) Integer.parseInt(frame.getMinServingTimeField());
						int maxSrT = (int) Integer.parseInt(frame.getMaxServingTimeField());
						int minArT = (int) Integer.parseInt(frame.getMinArrivalTimeField());
						int maxArT = (int) Integer.parseInt(frame.getMaxArrivalTimeField());
						int queueCount = (int) Integer.parseInt(frame.getQueueCountField());
						int serviceTime = (int) Integer.parseInt(frame.getSimulationTimeField());
						TaskGenerator t = new TaskGenerator(minSrT, maxSrT, minArT, maxArT, serviceTime);
						scheduler = new TaskScheduler(t, queueCount, serviceTime, frame);
						Thread th = new Thread(scheduler);
						th.start();
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please insert only numbers!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
