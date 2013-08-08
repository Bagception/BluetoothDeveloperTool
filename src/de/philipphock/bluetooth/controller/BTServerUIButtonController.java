package de.philipphock.bluetooth.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BTServerUIButtonController implements ActionListener{

	private final BTServerController mainController;
	public BTServerUIButtonController(BTServerController mainController) {
		this.mainController = mainController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()){
			case BTServerUIActionCommands.SERVER_START:
				mainController.startServer();
				break;
				
			case BTServerUIActionCommands.SERVER_STOP:
				mainController.startServer();
				break;
				
			case BTServerUIActionCommands.SEND:
				mainController.startServer();
				break;
		}
		
	}

}
