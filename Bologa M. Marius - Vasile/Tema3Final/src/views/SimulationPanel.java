package views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import models.Server;
import models.TaskScheduler;

public class SimulationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int HORIZONTAL_SPACING = 10;
	private int actualServerRectWidth, actualTaskRectWidth;
	private int maxServerRectWidth = 100, maxTaskRectWidth = 90;
	TaskScheduler scheduler;

	public SimulationPanel(TaskScheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int panelWidth = getWidth();
		int columnCount = Math.max(scheduler.getServers().length, 1);
		int queueRectWidth = (panelWidth - (columnCount - 1) * HORIZONTAL_SPACING) / columnCount;
		actualServerRectWidth = Math.min(queueRectWidth, maxServerRectWidth);
		int clientRectWidth = (int) (queueRectWidth * 0.9);
		actualTaskRectWidth = Math.min(clientRectWidth, maxTaskRectWidth);

		Color color;
		int time, x = 0;
		for (int i = 0; i < scheduler.getServers().length; i++) {
			int y = 0;
			drawServerHeader(g, x, y);

			for (int j = 0; j < scheduler.getServers()[i].queueSize(); j++) {
				y += 25;
				Server currQ = scheduler.getServers()[i];
				if (currQ.isAlive() && currQ != null && currQ.getTasks()[j] != null) {
					time = currQ.getTasks()[j].getServingTime();
					color = (time >= 12) ? Color.BLACK
							: (time >= 9) ? Color.BLUE
									: (((time >= 6) ? Color.RED : ((time >= 4) ? Color.YELLOW : Color.GREEN)));
					drawTask(g, x, y, color);
				}
			}
			x += actualServerRectWidth + HORIZONTAL_SPACING;
		}
	}

	public void drawServerHeader(Graphics g, int x, int y) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, actualServerRectWidth, 20);
		this.repaint();
	}

	public void drawTask(Graphics g, int x, int y, Color c) {
		g.setColor(c);
		g.fillRect(x + (actualServerRectWidth - actualTaskRectWidth) / 2, y, actualTaskRectWidth, 15);
		this.repaint();
	}

}
