package de.philipphock.bluetooth.coreImpl;

import de.philipphock.bluetooth.core.BTHandler;
import de.philipphock.bluetooth.core.BTHandlerFactory;

public class BTObservableHandlerFactory implements BTHandlerFactory{

	@Override
	public BTHandler createHandler() {
		return new BTObservableHandler();
	}

}
