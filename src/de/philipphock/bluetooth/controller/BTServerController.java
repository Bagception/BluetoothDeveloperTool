package de.philipphock.bluetooth.controller;

import java.util.Vector;
import javax.swing.JOptionPane;
import de.philipphock.bluetooth.service.BluetoothService;
import de.philipphock.bluetooth.ui.BTServerUI;
public class BTServerController {

	
	public BTServerController() {
		
	}
	
	public void initGui(){
		Vector<BluetoothService> bts = (new BluetoothService_TextLoader()).loadServices();
		if (bts.size()==0){
			JOptionPane.showMessageDialog(null, "No Bluetooth services found\nPlease create a bluetooth service first.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		new BTServerUI(new BTServerUIButtonController(this),bts);
	}
	
	
	public void startServer(){
		
	}
}
