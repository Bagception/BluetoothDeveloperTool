package de.philipphock.bluetooth.core.mvc.listener;

import de.philipphock.bluetooth.ui.BTServerUI;

public class BTObservableHandlerListenerImpl implements BTObservableHandlerListener{

	private final BTServerUI ui;
	
	public BTObservableHandlerListenerImpl(BTServerUI ui) {
		this.ui = ui;
	}
	
	@Override
	public void recv(String s) {
		this.ui.addRecv(s);
		
	}

	@Override
	public void connEstablished() {
		this.ui.addStatus("connection established");
		
	}

	@Override
	public void connLost() {
		this.ui.addStatus("connection lost");
		
	}

}
