package de.philipphock.bluetooth.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import de.philipphock.bluetooth.controller.BTServerUIActionCommands;
import de.philipphock.bluetooth.controller.BTServerUIButtonController;
import de.philipphock.bluetooth.service.BluetoothService;



public class BTServerUI extends JFrame implements BTServerUIActionCommands{

	private static final long serialVersionUID = 2495452297340253126L;

	private final JTextArea statusText;
	private final JTextArea recvText;
	private final JTextArea sendText;	
	private final JList<BluetoothService> btserviceList;
	private final JButton startStopServerButton;

	
	public BTServerUI(BTServerUIButtonController buttoncontroller,Vector<BluetoothService> btservices) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		// initialization of class variables
		
		//======= status text =======
		statusText = new JTextArea();
		statusText.setEditable(false);
		
		//======= recv text =========
		recvText = new JTextArea("");
		recvText.setEditable(false);
		
		//======= send text =========
		sendText = new JTextArea("");
		
		//====== BTServicelist ======
		
		btserviceList = new JList<BluetoothService>(btservices);
		btserviceList.setSelectedIndex(0);
		
		
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
				JPanel btservicePanel = new JPanel(new BorderLayout());
				TitledBorder btserviceBorder = BorderFactory.createTitledBorder("BT Service");
				btservicePanel.setBorder(btserviceBorder);
				btservicePanel.add(btserviceList,BorderLayout.CENTER);
				
				left.add(btservicePanel);
			}
			//======= control panel ===========
			{
				JPanel controlPanel = new JPanel();
				TitledBorder controlBorder = BorderFactory.createTitledBorder("Server Control");
				controlPanel.setBorder(controlBorder);
				startStopServerButton = new JButton("start server");
				startStopServerButton.addActionListener(buttoncontroller);
				startStopServerButton.setActionCommand(BTServerUIActionCommands.SERVER_START);
				controlPanel.add(startStopServerButton);
				
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
					JButton clearButton = new JButton("clear");
					recvHeadPanel.add(clearButton);
					clearButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							recvText.setText("");
						}
					});
					
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
					JButton sendButton = new JButton("send");
					sendButton.addActionListener(buttoncontroller);
					sendButton.setActionCommand(BTServerUIActionCommands.SEND);
					
					sendHeadPanel.add(sendButton);
					
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

	
	public BluetoothService getBluetoothService(){
		return this.btserviceList.getSelectedValue();
	}
	
	public void addStatus(String txt){
		this.statusText.append(txt);
	}
	
	public void addRecv(String s){
		this.recvText.append(s);
	}

	public void serverStopped(){
		startStopServerButton.setText("Start server");
		startStopServerButton.setActionCommand(BTServerUIActionCommands.SERVER_START);
	}
	
	public void serverStarted(){
		startStopServerButton.setText("Start stop");
		startStopServerButton.setActionCommand(BTServerUIActionCommands.SERVER_STOP);
	}

}
