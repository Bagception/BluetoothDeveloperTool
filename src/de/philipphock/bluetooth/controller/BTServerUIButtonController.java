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
		System.out.println(e.getActionCommand());
		switch(e.getActionCommand()){
		}
		
	}

}
