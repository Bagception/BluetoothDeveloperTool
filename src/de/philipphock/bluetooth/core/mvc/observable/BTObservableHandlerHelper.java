package de.philipphock.bluetooth.core.mvc.observable;

import de.philipphock.bluetooth.core.mvc.listener.BTObservableHandlerListener;
import de.philipphock.lib.GenericObservable;


public class BTObservableHandlerHelper extends GenericObservable<BTObservableHandlerListener> {
	public static final int CONNECTION_ESTABLISEHD = 1;
	public static final int CONNECTION_LOST = 2;
	public static final int RECV = 4;

	@Override
	public void onNotify(BTObservableHandlerListener listener, int event, Object o) {
		if ((CONNECTION_ESTABLISEHD & event)>0){
			listener.connEstablished();
		}

		if ((CONNECTION_LOST & event)>0){
			listener.connLost();
		}		
		
		if ((RECV & event)>0){
			listener.recv((String) o);
		}	
		

	}



}
