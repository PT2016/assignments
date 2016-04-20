package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.DisplayControl;
import Control.TaskScheduler;
import Helper.TimeConverter;

public class CommandPanel extends JPanel {

	private static final long serialVersionUID = 8057384542280274589L;

	private JTextField nrQueuesField = new JTextField(5);
	private JTextField minServiceField = new JTextField(5);
	private JTextField maxServiceField = new JTextField(5);
	private JTextField minArrivalField = new JTextField(5);
	private JTextField maxArrivalField = new JTextField(5);
	private JTextField startTimeField = new JTextField(5);
	private JTextField finishTimeField = new JTextField(5);
	private JTextField sleepingTime = new JTextField(5);

	private JLabel nrQueuesLabel = new JLabel("Number of queues   ");
	private JLabel minServiceLabel = new JLabel("Minimum service time (sec)   ");
	private JLabel maxServiceLabel = new JLabel("Maximum service time (sec)   ");
	private JLabel minArrivalLabel = new JLabel("Minimum arrival time (sec)   ");
	private JLabel maxArrivalLabel = new JLabel("Maximum arrival time (sec)   ");
	private JLabel startTimeLabel = new JLabel("Start at   ");
	private JLabel finishTimeLabel = new JLabel("Finish at   ");
	private JLabel currentTimeLabel = new JLabel("Current time");
	private JLabel changingTimeLabel = new JLabel("--:--:--");
	private JLabel sleepingTimeLabel = new JLabel("Sleeping Time");

	private JButton startButton = new JButton("START");

	private JLabel[] taskLabels;
	private JLabel[] serverLabels;

	public CommandPanel(JLabel[] tasks, JLabel[] servers) {

		taskLabels = tasks;
		serverLabels = servers;

		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createRaisedBevelBorder());
		setBackground(Color.decode("0xc1ffc1"));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(nrQueuesLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(nrQueuesField, gbc);

		gbc.gridx = 7;
		gbc.gridy = 0;
		add(currentTimeLabel, gbc);

		gbc.gridx = 7;
		gbc.gridy = 1;
		changingTimeLabel.setFont(new Font(null, Font.BOLD, 16));
		changingTimeLabel.setForeground(Color.red);
		add(changingTimeLabel, gbc);

		gbc.gridx = 8;
		gbc.gridy = 0;
		add(sleepingTimeLabel, gbc);
		
		gbc.gridx = 8;
		gbc.gridy = 1;
		add(sleepingTime, gbc);
		
		gbc.gridx = 9;
		gbc.gridy = 0;
		add(startButton, gbc);

		gbc.anchor = GridBagConstraints.LINE_END;

		gbc.gridx = 1;
		gbc.gridy = 0;
		add(minServiceLabel, gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		add(minArrivalLabel, gbc);

		gbc.gridx = 5;
		gbc.gridy = 0;
		add(startTimeLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		add(maxServiceLabel, gbc);

		gbc.gridx = 5;
		gbc.gridy = 1;
		add(finishTimeLabel, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		add(maxArrivalLabel, gbc);

		gbc.anchor = GridBagConstraints.LINE_START;

		gbc.gridx = 2;
		gbc.gridy = 0;
		add(minServiceField, gbc);

		gbc.gridx = 4;
		gbc.gridy = 0;
		add(minArrivalField, gbc);

		gbc.gridx = 6;
		gbc.gridy = 0;
		add(startTimeField, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		add(maxServiceField, gbc);

		gbc.gridx = 4;
		gbc.gridy = 1;
		add(maxArrivalField, gbc);

		gbc.gridx = 6;
		gbc.gridy = 1;
		add(finishTimeField, gbc);

		startButton.addActionListener(new StartAction());

	}

	public class StartAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			DisplayControl displayControl = new DisplayControl(taskLabels, serverLabels);
			
			Thread schedulerThread = new Thread(new TaskScheduler(startTimeField.getText(), finishTimeField.getText(),
					maxArrivalField.getText(), minArrivalField.getText(), maxServiceField.getText(),
					minServiceField.getText(), nrQueuesField.getText(), displayControl, sleepingTime.getText()));
			schedulerThread.start();

			Thread timeThread = new Thread(
					new TimeConverter(startTimeField.getText(), finishTimeField.getText(), changingTimeLabel, sleepingTime.getText()));
			timeThread.start();

			JButton but = (JButton) event.getSource();
			but.setEnabled(false);

		}

	}

}
