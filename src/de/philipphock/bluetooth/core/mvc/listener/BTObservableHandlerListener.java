package de.philipphock.bluetooth.core.mvc.listener;

public interface BTObservableHandlerListener {

	public void recv(String s);
	public void connEstablished();
	public void connLost();
}
