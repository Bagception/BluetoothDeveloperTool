package de.philipphock.bluetooth.coreImpl;

import de.philipphock.bluetooth.core.BTHandler;
import de.philipphock.bluetooth.core.BTHandlerFactory;
import de.philipphock.bluetooth.core.BTServer;
import de.philipphock.bluetooth.core.mvc.listener.BTObservableHandlerListenerImpl;

public class BTObservableHandlerFactory implements BTHandlerFactory{

	private final BTObservableHandlerListenerImpl handlerListener;
	public BTObservableHandlerFactory(BTObservableHandlerListenerImpl handlerListener) {
		this.handlerListener=handlerListener;
	}

	@Override
	public BTHandler createHandler(BTServer server) {
		BTObservableHandler handler = new BTObservableHandler(server);
		handler.getObservable().addListener(handlerListener);
		return handler;
	}

}
