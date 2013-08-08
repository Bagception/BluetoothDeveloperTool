package de.philipphock.bluetooth.controller;

import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;

import de.philipphock.bluetooth.core.BTServer;
import de.philipphock.bluetooth.core.mvc.listener.BTServerStateListener;
import de.philipphock.bluetooth.core.mvc.listener.BTServerStateListenerImpl;
import de.philipphock.bluetooth.coreImpl.BTObservableHandler;
import de.philipphock.bluetooth.coreImpl.BTObservableHandlerFactory;
import de.philipphock.bluetooth.exceptions.ServerAlreadyStartedException;
import de.philipphock.bluetooth.service.BluetoothService;
import de.philipphock.bluetooth.ui.BTServerUI;
public class BTServerController {
	private BTServer bt_server;
	private final BTServerUI serverUI;
	private BTServerStateListener serverStateListener;
	private final BTObservableHandlerFactory handlerFactory;
	public BTServerController() {
		handlerFactory = new BTObservableHandlerFactory();
		Vector<BluetoothService> bts = (new BluetoothService_TextLoader()).loadServices();
		if (bts.size()==0){
			JOptionPane.showMessageDialog(null, "No Bluetooth services found\nPlease create a bluetooth service first.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		serverUI = new BTServerUI(new BTServerUIButtonController(this),bts);
		
		
	}
	
	
	private void newServer(){
		
		bt_server = new BTServer(handlerFactory, serverUI.getBluetoothService());
		
		
		//status
		serverStateListener = new BTServerStateListenerImpl(serverUI);
		bt_server.getBTServerState().addListener(serverStateListener);
		try {
			bt_server.init();
		} catch (IOException | ServerAlreadyStartedException e) {
			e.printStackTrace();
		}
		//recv
		//TODO
		
	}
	
	private void deleteServer(){
		if (bt_server != null){
			bt_server.getBTServerState().removeAllListeners();
		}
	}
	
	

	
	public void startServer(){
		
		deleteServer();
		newServer();		
		System.out.println("start new server");
	}
	
	private void addHandlerServerListeners(BTObservableHandler handler){
		
	}
}
