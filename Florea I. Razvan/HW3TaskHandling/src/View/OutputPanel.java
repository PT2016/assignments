package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OutputPanel extends JPanel {

	private static final long serialVersionUID = -5149616029307556323L;

	private JLabel[] taskLabels;
	private JLabel[] serverLabels;

	public OutputPanel() {
		setLayout(new GridLayout(6, 16));
		setBorder(BorderFactory.createRaisedBevelBorder());
		setBackground(Color.decode("0xfffacd"));

		taskLabels = new JLabel[90];
		serverLabels = new JLabel[6];

		int taskNumber = -1;
		for (int i = 0; i < 6; i++) {

			serverLabels[i] = new JLabel("<html>Server<br>" + i + "</html>");

			serverLabels[i].setFont(new Font(null, Font.BOLD, 16));
			serverLabels[i].setForeground(Color.blue);
			serverLabels[i].setVisible(false);
			add(serverLabels[i]);
			
			for (int j = 1; j <= 15; j++) {
				taskNumber++;
				taskLabels[taskNumber] = new JLabel("<html>Task<br>" + taskNumber + "</html>");
				taskLabels[taskNumber].setVisible(false);
				add(taskLabels[taskNumber]);
			}
		}

	}

	public JLabel[] getTaskLabels() {
		return taskLabels;
	}

	public JLabel[] getServerLabels() {
		return serverLabels;
	}
}
