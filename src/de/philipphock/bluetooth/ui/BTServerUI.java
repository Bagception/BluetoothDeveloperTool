package de.philipphock.bluetooth.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;



public class BTServerUI extends JFrame {

	private static final long serialVersionUID = 2495452297340253126L;

	private final JTextArea statusText;
	private final JTextArea recvText;
	private final JTextArea sendText;	
	
	public BTServerUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		// initialization of class variables
		
		//======= status text =======
		statusText = new JTextArea();
		statusText.setEditable(false);
		
		//======= recv text =======
		recvText = new JTextArea();
		
		//send text
		sendText = new JTextArea();
		
		
		//======= left panel ===========
		{	
			JPanel left = new JPanel();
			left.setLayout(new GridLayout(3, 1));
			left.setPreferredSize(new Dimension((int)(this.getWidth()*0.3), 0));
			
			
			//======= status panel ===========
			{
				JPanel statusPanel = new JPanel(new BorderLayout());
				TitledBorder statusBorder = BorderFactory.createTitledBorder("Status");
				statusPanel.setBorder(statusBorder);
				JScrollPane statusTextScrollPane = new JScrollPane(statusText,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				statusPanel.add(statusTextScrollPane,BorderLayout.CENTER);
				statusPanel.setBackground(Color.RED);
				
				
				left.add(statusPanel);
			}
			//======= bt service panel ===========		
			{
				JPanel btservicePanel = new JPanel();
				TitledBorder btserviceBorder = BorderFactory.createTitledBorder("BT Service");
				btservicePanel.setBorder(btserviceBorder);
				btservicePanel.setBackground(Color.BLUE);
				
				left.add(btservicePanel);
			}
			//======= control panel ===========
			{
				JPanel controlPanel = new JPanel();
				TitledBorder controlBorder = BorderFactory.createTitledBorder("Server Control");
				controlPanel.setBorder(controlBorder);
				controlPanel.setBackground(Color.GREEN);
				
				left.add(controlPanel);
			}
			
			getContentPane().add(left,BorderLayout.WEST);
		}
		
		
		
		
		//======= message panel ==========	
		{
			JPanel messagePanel = new JPanel(new GridLayout(2, 1)); 
			messagePanel.setBackground(Color.YELLOW);
			
			
			//======= recv panel ==========
			{
				JPanel recvPanel = new JPanel(new BorderLayout());
				//======= recv head panel ==========
				{
					
				}
				recvPanel.add(recvText,BorderLayout.CENTER);
			}
			
			getContentPane().add(messagePanel,BorderLayout.CENTER);
		}
		
		setVisible(true);
	}

}
