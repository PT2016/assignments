package threadsAssign3;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class SimulatorFrame extends JFrame{
	private static final long serialVersionUID = -3737453827491103543L;

    private JPanel frame = new JPanel(new GridLayout(2, 1, 10, 10));

    private JPanel controls = new JPanel(new GridLayout(4, 4));
    private Button startButton = new Button("Start simulation");

    //minimum arrival interval between clients
    private JLabel minimumArrivalIntervalLabel = new JLabel("Minimum arrival interval:");
    private JTextField minimumArrivalInterval = new JTextField();

    //maximum arrival interval between clients
    private JLabel maximumArrivalIntervalLabel = new JLabel("Maximum arrival interval:");
    private JTextField maximumArrivalInterval = new JTextField();

    private JLabel minimumServiceTimeLabel = new JLabel("Minimum service time:");
    private JTextField minimumServiceTime = new JTextField();

    private JLabel maximumServiceTimeLabel = new JLabel("Maximum service time:");
    private JTextField maximumServiceTime = new JTextField();

    private JLabel nrOfServersLabel = new JLabel("Nr of servers:");
    private JTextField nrOfServers = new JTextField();

    private JLabel thresholdLabel = new JLabel("Threshold:");
    private JTextField threshold = new JTextField();

    private JLabel simulationTimeLabel = new JLabel("Simulation time:");
    private JTextField simulationTime = new JTextField();

    private JTextField communication = new JTextField("");

    private JPanel simulatorFrame = new JPanel(new GridLayout(2, 5, 10, 10));

    private Thread thread;

    private static final int WIDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;

    public SimulatorFrame() {
        setSize(WIDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setControls();
        frame.add(controls);
        frame.add(simulatorFrame);
        add(frame);
        setVisible(true);
    }

    private void setControls() {
        controls.setMaximumSize(new Dimension(600, 100));

        addFields();
        startButtonActions();
        controls.add(startButton);
        controls.add(communication);
    }

    private void addFields() {
        controls.add(minimumArrivalIntervalLabel);
        controls.add(minimumArrivalInterval);
        controls.add(maximumArrivalIntervalLabel);
        controls.add(maximumArrivalInterval);
        controls.add(minimumServiceTimeLabel);
        controls.add(minimumServiceTime);
        controls.add(maximumServiceTimeLabel);
        controls.add(maximumServiceTime);
        controls.add(nrOfServersLabel);
        controls.add(nrOfServers);
        controls.add(thresholdLabel);
        controls.add(threshold);
        controls.add(simulationTimeLabel);
        controls.add(simulationTime);
    }

    @SuppressWarnings("deprecation")
	private void startButtonActions() {
        startButton.addActionListener(e -> {
            if (thread != null) {
                thread.stop();
                EventsLog.log("Simulation halted");
                EventsLog.close();
                Simulator.resetParameters();
            }
            try {
                setSimulationParameters();
            } catch (Exception exception) {
                communication.setText("The fields should have integer values");
                return;
            }
            EventsLog.initialize();
            Simulator.setScheduler(new Scheduler());
            Simulator simulator = new Simulator();
            thread = new Thread(simulator);
            thread.start();
        });
    }

    private void setSimulationParameters() throws Exception {
        if (!minimumArrivalInterval.getText().isEmpty())
            Simulator.setMinArrivalInterval(toInt(minimumArrivalInterval.getText()));
        if (!maximumArrivalInterval.getText().isEmpty())
            Simulator.setMaxArrivalInterval(toInt(maximumArrivalInterval.getText()));
        if (!minimumServiceTime.getText().isEmpty())
            Simulator.setMinProcessTime(toInt(minimumServiceTime.getText()));
        if (!maximumServiceTime.getText().isEmpty())
            Simulator.setMaxProcessTime(toInt(maximumServiceTime.getText()));
        if (!nrOfServers.getText().isEmpty())
            Simulator.setMaxNrOfServers(toInt(nrOfServers.getText()));
        if (!threshold.getText().isEmpty())
            Simulator.setThreshold(toInt(threshold.getText()));
        if (!simulationTime.getText().isEmpty())
            Simulator.setFinishTime(toInt(simulationTime.getText()));
    }

    private static int toInt(String string) throws Exception {
        return Integer.parseInt(string);
    }

    public void showReports(float averageWaitingTime, float serviceTime, int peakHour) {
        communication.setText(String.format("Average waiting time: %f; Service time: %f; Peak hour: %d",
                averageWaitingTime, serviceTime, peakHour));
    }

    public void showCurrentTime() {
        communication.setText(String.format("%d", Simulator.getCurrentTime()));
    }

    public void displayData(ArrayList<String[]> tasksOnServers) {
        simulatorFrame.removeAll();
        simulatorFrame.revalidate();
        for (String[] tasks : tasksOnServers) {
            simulatorFrame.add(new JList<>(tasks));
        }
        simulatorFrame.repaint();
        simulatorFrame.revalidate();
    }
}
