package GraphicUserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.Actions;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1955031667697464926L;
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 300;
	private static final int X_POSITION = 100;
	private static final int Y_POSITION = 100;

	private JButton addClientButton;
	private JButton removeClientButton;
	private JButton addAccountButton;
	private JButton removeAccountButton;
	private JButton viewAllDataButton;
	private JButton viewClientDataButton;
	private JPanel buttonsPanel;
	
	
	private Actions actions;

	public AdminFrame() {

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(AdminFrame.EXIT_ON_CLOSE);
		setLocation(X_POSITION, Y_POSITION);
		setTitle("Welcome Admin !");
		
		setLayout(new BorderLayout());
		buttonsPanel = new JPanel();
		
		addClientButton = new JButton("Add Client");
		removeClientButton = new JButton("Remove Client");
		addAccountButton = new JButton("Add Account");
		removeAccountButton = new JButton("Remove Account");
		viewAllDataButton = new JButton("View All Data");
		viewClientDataButton = new JButton("Generate Report");

		actions = new Actions();
	
		addButtons();
		addFunctionsToButtons();

		add(buttonsPanel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Welcome Administrator");
		Font font = new Font(null, Font.BOLD + Font.ITALIC, 18);
		label.setFont(font);
		label.setForeground(Color.blue);
		
		add(label, BorderLayout.NORTH);
		
		setVisible(true);
	}
	
	private void addButtons() {
		
		buttonsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonsPanel.add(addClientButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		buttonsPanel.add(removeClientButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		buttonsPanel.add(addAccountButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		buttonsPanel.add(removeAccountButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		buttonsPanel.add(viewAllDataButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		buttonsPanel.add(viewClientDataButton, gbc);
	}
	
	private void addFunctionsToButtons() {
		addClientButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				actions.addAClient();
				
			}
			
		});
		
		removeClientButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				actions.removeAClient();
				
			}
			
		});
		
		viewAllDataButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				actions.viewAllClients();
				
			}
			
		});
		
		addAccountButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				actions.addAnAccount();
				
			}
			
		});
		
		removeAccountButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				actions.removeAnAccount();
				
			}
			
		});
		
		viewClientDataButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				actions.viewOneClient();
				
			}
			
		});
	}

}
