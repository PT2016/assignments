package GraphicUserInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Control.Actions;

public class ClientFrame extends JFrame{

	private static final long serialVersionUID = 2091391419107064362L;

	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 300;
	private static final int X_POSITION = 100;
	private static final int Y_POSITION = 100;

	private JButton addMoneyButton;
	private JButton withdrawMoneyButton;
	private JButton viewDataButton;
	private String clientName;
	
	private Actions actions;

	public ClientFrame(String clientName) {

		this.clientName = clientName;
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(AdminFrame.EXIT_ON_CLOSE);
		setLocation(X_POSITION, Y_POSITION);
		setTitle("Welcome Client " + this.clientName);
		setLayout(new GridLayout(3, 1));

		addMoneyButton = new JButton("Add Money");
		withdrawMoneyButton = new JButton("Withdraw Money");
		viewDataButton = new JButton("View Client Details");

		actions = new Actions();
		
		addFunctionsToButtons();
		
		add(addMoneyButton);
		add(withdrawMoneyButton);
		add(viewDataButton);
		
		setVisible(true);
	}
	
	private void addFunctionsToButtons() {
		addMoneyButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				actions.addDatMoney(clientName);
				
			}
			
		});
		
		withdrawMoneyButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				actions.takeDatMoney(clientName);
				
			}
			
		});
		
		viewDataButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				actions.viewClientDetails(clientName);
				
			}
			
		});
	}
	
}
