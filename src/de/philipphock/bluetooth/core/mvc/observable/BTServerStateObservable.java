package de.philipphock.bluetooth.core.mvc.observable;

import de.philipphock.bluetooth.core.mvc.listener.BTServerStateListener;
import de.philipphock.lib.GenericObservable;


public class BTServerStateObservable extends GenericObservable<BTServerStateListener> {
	public final static int SERVER_STARTED=1;
	public final static int SERVER_LISTENING=2;
	public final static int SERVER_STOPPED=4;
	
	public final static int SERVER_ERROR=4;
	
		

	@Override
	protected void onNotify(BTServerStateListener listener, int event, Object o) {
		System.out.println("notify Serverstate");
		if ((SERVER_STARTED & event)>0){
			listener.serverStarted();
		}

		if ((SERVER_LISTENING & event)>0){
			listener.serverStarted();
		}		
		
		if ((SERVER_STOPPED & event)>0){
			listener.serverStarted();
		}	
		
		if ((SERVER_ERROR & event)>0){
			listener.serverException((Exception) o);
		}	
		
		
	}

}
