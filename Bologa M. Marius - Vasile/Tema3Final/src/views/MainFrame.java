package views;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextArea logText = new JTextArea();
	private JTextField currentCycleField = new JTextField();
	private JTextField minArrivalTimeField;
	private JTextField maxArrivalTimeField;
	private JTextField minServingTimeField;
	private JTextField maxServingTimeField;
	private JTextField queueCountField;
	private JTextField simulationTimeField;
	private JScrollPane scrollPane = new JScrollPane();
	private JButton startButton = new JButton("Start Simulation");
	private JButton btnClearLog = new JButton("Clear Log");
	private JPanel graphPanel = new JPanel();

	public MainFrame() {
		frame = new JFrame();
		frame.setSize(927, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Queue Simulation");
		initializeFieldsAndLabels();
		initializeLog();
		frame.setVisible(true);
	}

	public void setLog(String text) {
		logText.setText(text);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}

	public void setCycleCountField(String text) {
		currentCycleField.setText(text);
	}

	public void initializeFieldsAndLabels() {
		JLabel minArraivalLabel = new JLabel("Min Arrival Time");
		minArraivalLabel.setBounds(173, 12, 115, 20);
		frame.getContentPane().add(minArraivalLabel);

		JLabel maxArrivalLabel = new JLabel("Max Arrival Time");
		maxArrivalLabel.setBounds(173, 64, 115, 20);
		frame.getContentPane().add(maxArrivalLabel);

		JLabel minServingLabel = new JLabel("Min Serving Time");
		minServingLabel.setBounds(12, 12, 115, 20);
		frame.getContentPane().add(minServingLabel);

		JLabel maxServingLabel = new JLabel("Max Serving Time");
		maxServingLabel.setBounds(12, 64, 127, 20);
		frame.getContentPane().add(maxServingLabel);

		JLabel queueCountLabel = new JLabel("Queue Count");
		queueCountLabel.setBounds(320, 12, 127, 20);
		frame.getContentPane().add(queueCountLabel);

		JLabel simulationTimeLabel = new JLabel("Simulation Time");
		simulationTimeLabel.setBounds(320, 64, 127, 20);
		frame.getContentPane().add(simulationTimeLabel);
		minArrivalTimeField = new JTextField();
		minArrivalTimeField.setText("1");
		minArrivalTimeField.setBounds(173, 32, 135, 20);
		frame.getContentPane().add(minArrivalTimeField);
		minArrivalTimeField.setColumns(10);

		maxArrivalTimeField = new JTextField();
		maxArrivalTimeField.setText("24");
		maxArrivalTimeField.setColumns(10);
		maxArrivalTimeField.setBounds(174, 84, 135, 20);
		frame.getContentPane().add(maxArrivalTimeField);

		minServingTimeField = new JTextField();
		minServingTimeField.setText("1");
		minServingTimeField.setColumns(10);
		minServingTimeField.setBounds(12, 32, 135, 20);
		frame.getContentPane().add(minServingTimeField);

		maxServingTimeField = new JTextField();
		maxServingTimeField.setText("25");
		maxServingTimeField.setColumns(10);
		maxServingTimeField.setBounds(12, 84, 135, 20);
		frame.getContentPane().add(maxServingTimeField);

		queueCountField = new JTextField();
		queueCountField.setText("5");
		queueCountField.setColumns(10);
		queueCountField.setBounds(320, 32, 135, 20);
		frame.getContentPane().add(queueCountField);

		simulationTimeField = new JTextField();
		simulationTimeField.setText("30");
		simulationTimeField.setColumns(10);
		simulationTimeField.setBounds(321, 84, 135, 20);
		frame.getContentPane().add(simulationTimeField);
		startButton.setBounds(465, 27, 135, 29);
		frame.getContentPane().add(startButton);
	}

	public void setGraphPanel(SimulationPanel gPanel) {
		frame.getContentPane().remove(graphPanel);
		graphPanel = gPanel;
		graphPanel.setBounds(12, 116, 587, 443);
		frame.getContentPane().add(graphPanel);
	}

	public void initializeLog() {
		JLabel currentCycleLabel = new JLabel("CurrentCycle");
		currentCycleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		currentCycleLabel.setBounds(475, 64, 95, 20);
		frame.getContentPane().add(currentCycleLabel);

		currentCycleField.setEditable(false);
		currentCycleField.setBounds(468, 84, 95, 20);
		currentCycleField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		currentCycleField.setHorizontalAlignment(SwingConstants.CENTER);
		currentCycleField.setColumns(10);
		frame.getContentPane().add(currentCycleField);

		scrollPane.setBounds(612, 12, 297, 512);
		logText.setEditable(false);
		scrollPane.setViewportView(logText);
		frame.getContentPane().add(scrollPane);
		btnClearLog.setBounds(820, 536, 89, 23);
		frame.getContentPane().add(btnClearLog);
	}

	public final void setStartButtonActionListener(final ActionListener a) {
		startButton.addActionListener(a);

	}

	public final void setClearButtonActionListener(final ActionListener a) {
		btnClearLog.addActionListener(a);

	}

	public String getCurrentCycleField() {
		return currentCycleField.getText();
	}

	public String getMinArrivalTimeField() {
		return minArrivalTimeField.getText();
	}

	public String getMaxArrivalTimeField() {
		return maxArrivalTimeField.getText();
	}

	public String getMinServingTimeField() {
		return minServingTimeField.getText();
	}

	public String getMaxServingTimeField() {
		return maxServingTimeField.getText();
	}

	public String getQueueCountField() {
		return queueCountField.getText();
	}

	public String getSimulationTimeField() {
		return simulationTimeField.getText();
	}
}
