package de.philipphock.bluetooth.core.mvc;

public interface BTServerStateListener {

	public void serverStarted();
	public void serverListening();
	public void serverStopped();
	
	public void serverException(Exception e);
}
