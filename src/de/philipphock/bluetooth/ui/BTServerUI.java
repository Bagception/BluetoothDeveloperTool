package de.philipphock.bluetooth.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		recvText = new JTextArea("r");
		
		//send text
		sendText = new JTextArea("s");
		
		
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
				
				
				left.add(statusPanel);
			}
			//======= bt service panel ===========		
			{
				JPanel btservicePanel = new JPanel();
				TitledBorder btserviceBorder = BorderFactory.createTitledBorder("BT Service");
				btservicePanel.setBorder(btserviceBorder);
				
				left.add(btservicePanel);
			}
			//======= control panel ===========
			{
				JPanel controlPanel = new JPanel();
				TitledBorder controlBorder = BorderFactory.createTitledBorder("Server Control");
				controlPanel.setBorder(controlBorder);
				
				left.add(controlPanel);
			}
			
			getContentPane().add(left,BorderLayout.WEST);
		}
		
		
		
		
		//======= message panel ==========	
		{
			JPanel messagePanel = new JPanel(new GridLayout(2, 1)); 
			//======= recv panel ==========
			{
				JPanel recvPanel = new JPanel(new BorderLayout());
				//======= recv head panel ==========
				{
					JPanel recvHeadPanel = new JPanel(new GridLayout(1,2));
					recvHeadPanel.add(new JLabel("recv:"));
					recvHeadPanel.add(new JButton("clear"));
					
					recvPanel.add(recvHeadPanel,BorderLayout.NORTH);
					JScrollPane recvTextScrollPane = new JScrollPane(recvText,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					recvPanel.add(recvTextScrollPane,BorderLayout.CENTER);
				}
				
				messagePanel.add(recvPanel);
			}
			
			//======= send panel ==========
			{
				JPanel sendPanel = new JPanel(new BorderLayout());
				//======= recv head panel ==========
				{
					JPanel sendHeadPanel = new JPanel(new GridLayout(1,1));
					sendHeadPanel.add(new JButton("clear"));
					
					JScrollPane sendTextScrollPane = new JScrollPane(sendText,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					sendPanel.add(sendTextScrollPane,BorderLayout.CENTER);
					sendPanel.add(sendHeadPanel,BorderLayout.SOUTH);
				}
				
				messagePanel.add(sendPanel);
			}
			
			
			
			
			getContentPane().add(messagePanel,BorderLayout.CENTER);
		}
		
		setVisible(true);
	}

}
