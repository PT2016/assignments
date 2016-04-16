package Helper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimeConverter implements Runnable {

	private int sleepingTime;

	private CheckerClass checker;
	private boolean valid;

	private int hour;
	private int minute;
	private int second;

	private long start;
	private long stop;

	private JLabel timeLabel;

	public TimeConverter(String startingTime, String finishTime, JLabel timeLabel, String sleep) {
		checker = new CheckerClass();
		this.timeLabel = timeLabel;
		valid = true;
		
		sleepingTime = Integer.parseInt(sleep);

		if (!(checker.checkTimeFormat(startingTime))) {
			valid = false;
			JOptionPane.showMessageDialog(null, "Invalid time format for starting time !", "ERROR time format",
					JOptionPane.ERROR_MESSAGE);
		}
		if (!(checker.checkTimeFormat(finishTime))) {
			valid = false;
			JOptionPane.showMessageDialog(null, "Invalid time format for finish time !", "ERROR time format",
					JOptionPane.ERROR_MESSAGE);
		}

		if (valid) {

			hour = Integer.parseInt(finishTime.substring(0, 2));
			minute = Integer.parseInt(finishTime.substring(3, 5));
			second = Integer.parseInt(finishTime.substring(6));
			stop = second + minute * 60 + hour * 3600;

			hour = Integer.parseInt(startingTime.substring(0, 2));
			minute = Integer.parseInt(startingTime.substring(3, 5));
			second = Integer.parseInt(startingTime.substring(6));
			start = second + minute * 60 + hour * 3600;
		}
	}

	@Override
	public void run() {

		if (valid) {
			while (start <= stop) {
				timeLabel.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second));
				try {
					Thread.sleep(sleepingTime);
				} catch (InterruptedException e) {
					System.out.println("Sleeping error in TimeConverter");
				}

				second++;
				if (second == 60) {
					minute++;
					second = 0;
				}
				if (minute == 60) {
					hour++;
					minute = 0;
				}

				if (hour == 24) {
					hour = 0;
				}

				start = second + minute * 60 + hour * 3600;
			}
		}
	}

}
