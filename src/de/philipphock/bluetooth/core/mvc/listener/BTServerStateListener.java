package de.philipphock.bluetooth.core.mvc.listener;

public interface BTServerStateListener {

	public void serverStarted();
	public void serverListening();
	public void serverAccept();
	public void serverStopped();
	public void serverWaiting();
	public void serverException(Exception e);
}
