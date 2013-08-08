package de.philipphock.bluetooth.core.mvc.listener;

import de.philipphock.bluetooth.ui.BTServerUI;

public class BTServerStateListenerImpl implements BTServerStateListener{

	private final BTServerUI ui;
	
	public BTServerStateListenerImpl(BTServerUI ui) {
		this.ui = ui;
	}
	@Override
	public void serverStarted() {
		ui.addStatus("server started");
		
	}

	@Override
	public void serverListening() {
		ui.addStatus("server listening");
		
	}

	@Override
	public void serverStopped() {
		ui.addStatus("server stopped");
		
	}

	@Override
	public void serverException(Exception e) {
		ui.addStatus(e.getMessage());
		
	}

}
