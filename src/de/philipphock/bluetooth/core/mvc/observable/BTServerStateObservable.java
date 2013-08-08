package de.philipphock.bluetooth.core.mvc.observable;

import de.philipphock.bluetooth.core.mvc.listener.BTServerStateListener;
import de.philipphock.lib.GenericObservable;


public class BTServerStateObservable extends GenericObservable<BTServerStateListener> {
	public final static int SERVER_STARTED=1;
	public final static int SERVER_ACCEPT=2;
	public final static int SERVER_STOPPED=4;
	public final static int SERVER_ERROR=8;
	public final static int SERVER_LISTENING=16;
	public final static int SERVER_WAITING=32;	

	@Override
	protected void onNotify(BTServerStateListener listener, int event, Object o) {
		if ((SERVER_STARTED & event)>0){
			listener.serverStarted();
		}

		if ((SERVER_ACCEPT & event)>0){
			listener.serverAccept();
		}		
		
		if ((SERVER_STOPPED & event)>0){
			listener.serverStopped();
		}	
		
		if ((SERVER_LISTENING & event)>0){
			listener.serverListening();
		}	
		
		if ((SERVER_ERROR & event)>0){
			listener.serverException((Exception) o);
		}	
		if ((SERVER_WAITING & event)>0){
			listener.serverWaiting();
		}	
		
	}

}
